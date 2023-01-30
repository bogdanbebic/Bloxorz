package map.transformations

import map.MapTile
import map.TargetTile
import map.RegularTile

final class TargetTileMoveMapTransformation(
    val row: Int,
    val col: Int
) extends MapTransformation {
  def transform(map: Vector[Vector[MapTile]]): Vector[Vector[MapTile]] =
    Vector.tabulate(map.length, map(0).length)((i, j) =>
      if (i == row && j == col)
        TargetTile
      else if (map(i)(j) == TargetTile)
        RegularTile
      else
        map(i)(j)
    )
}
