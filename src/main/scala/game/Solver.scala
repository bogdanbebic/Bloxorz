package game

import map.MapTile

trait Solver {
  def solve(map: Vector[Vector[MapTile]], block: BlockPosition): Vector[Move]
}
