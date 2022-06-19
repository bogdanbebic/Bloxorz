package map.transformations

import map.MapTile

final class SingleTileMapTransformation(
    val row: Int,
    val col: Int,
    val tileType: MapTile
) extends MapTransformation {
  def transform(map: Vector[Vector[MapTile]]): Vector[Vector[MapTile]] =
    Vector.tabulate(map.length, map(0).length)((i, j) =>
      if (i == row && j == col)
        tileType
      else map(i)(j)
    )
}
