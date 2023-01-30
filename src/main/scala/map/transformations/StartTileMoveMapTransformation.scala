package map.transformations

import map.MapTile
import map.StartTile
import map.RegularTile

final class StartTileMoveMapTransformation(
    val row: Int,
    val col: Int
) extends MapTransformation {
  def transform(map: Vector[Vector[MapTile]]): Vector[Vector[MapTile]] =
    Vector.tabulate(map.length, map(0).length)((i, j) =>
      if (i == row && j == col)
        StartTile
      else if (map(i)(j) == StartTile)
        RegularTile
      else
        map(i)(j)
    )
}
