package map.transformations

import map.{MapTile, StartTile, TargetTile}

final class InversionMapTransformation() extends MapTransformation {
  def transform(map: Vector[Vector[MapTile]]): Vector[Vector[MapTile]] =
    Vector.tabulate(map.length, map(0).length)((i, j) =>
      map(i)(j) match {
        case StartTile  => TargetTile
        case TargetTile => StartTile
        case tile       => tile
      }
    )
}
