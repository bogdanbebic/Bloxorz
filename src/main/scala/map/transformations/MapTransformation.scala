package map.transformations

import map.MapTile

trait MapTransformation {
  def transform(map: Vector[Vector[MapTile]]): Vector[Vector[MapTile]]
}
