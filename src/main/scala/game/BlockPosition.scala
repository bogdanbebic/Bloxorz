package game

import map.MapTile

case class Position(row: Int, col: Int)

case class BlockPosition(upperLeft: Position, additional: Option[Position])

def moveBlock(block: BlockPosition, move: Move): BlockPosition = {
  BlockPosition(Position(0, 0), None)
}

def getOutcome(
    block: BlockPosition,
    map: Vector[Vector[MapTile]]
): MoveOutcome = {
  ValidMove
}
