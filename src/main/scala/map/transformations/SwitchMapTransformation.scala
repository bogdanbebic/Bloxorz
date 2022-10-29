package map.transformations

import map.{MapTile, FallthroughTile, RegularTile}

final class SwitchMapTransformation() extends MapTransformation {
  def transform(map: Vector[Vector[MapTile]]): Vector[Vector[MapTile]] =
    Vector.tabulate(map.length, map(0).length)((i, j) =>
      map(i)(j) match {
        case FallthroughTile => RegularTile
        case tile            => tile
      }
    )
}
