package game

import map.MapTile
import map.FallthroughTile
import map.NoTile
import map.TargetTile

case class Position(row: Int, col: Int)

case class BlockPosition(upperLeft: Position, additional: Option[Position])

def moveBlock(block: BlockPosition, move: Move): BlockPosition = {
  BlockPosition(Position(0, 0), None)
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
