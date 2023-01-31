package game

import map.MapTile
import map.FallthroughTile
import map.NoTile
import map.TargetTile

case class Position(row: Int, col: Int)

case class BlockPosition(upperLeft: Position, additional: Option[Position])

def moveBlock(block: BlockPosition, move: Move): BlockPosition = {
  val col = block.upperLeft.col
  val row = block.upperLeft.row

  block.additional match {
    case None => {
      move match
        case UpMove => {
          return BlockPosition(
            Position(row - 2, col),
            Some(Position(row - 1, col))
          )
        }
        case DownMove => {
          return BlockPosition(
            Position(row + 1, col),
            Some(Position(row + 2, col))
          )
        }
        case LeftMove => {
          return BlockPosition(
            Position(row, col - 2),
            Some(Position(row, col - 1))
          )
        }
        case RightMove => {
          return BlockPosition(
            Position(row, col + 1),
            Some(Position(row, col + 2))
          )
        }
    }

    case Some(additional) => {
      if (row == additional.row) {
        // block is left to right
        move match
          case UpMove => {
            return BlockPosition(
              Position(row - 1, col),
              Some(Position(additional.row - 1, additional.col))
            )
          }
          case DownMove => {
            return BlockPosition(
              Position(row + 1, col),
              Some(Position(additional.row + 1, additional.col))
            )
          }
          case LeftMove => {
            return BlockPosition(
              Position(row, col - 1),
              None
            )
          }
          case RightMove => {
            return BlockPosition(
              Position(row, col + 2),
              None
            )
          }
      } else {
        // block is up to down
        move match
          case UpMove => {
            return BlockPosition(
              Position(row - 1, col),
              None
            )
          }
          case DownMove => {
            return BlockPosition(
              Position(row + 2, col),
              None
            )
          }
          case LeftMove => {
            return BlockPosition(
              Position(row, col - 1),
              Some(Position(additional.row, additional.col - 1))
            )
          }
          case RightMove => {
            return BlockPosition(
              Position(row, col + 1),
              Some(Position(additional.row, additional.col + 1))
            )
          }
      }
    }
  }
}

def getOutcome(
    block: BlockPosition,
    map: Vector[Vector[MapTile]]
): MoveOutcome = {
  block.additional match {
    case None => {
      map(block.upperLeft.row)(block.upperLeft.col) match {
        case FallthroughTile => return FellThrough
        case NoTile          => return OutOfBounds
        case TargetTile      => return GameWon
        case _               => return ValidMove
      }
    }

    case Some(position) => {
      if (
        map(block.upperLeft.row)(block.upperLeft.col) == NoTile ||
        map(position.row)(position.col) == NoTile
      ) {
        return OutOfBounds
      }
    }
  }

  ValidMove
}
