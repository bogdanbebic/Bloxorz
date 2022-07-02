package game

case class Position(row: Int, col: Int)

case class BlockPosition(upperLeft: Position, additional: Option[Position])
