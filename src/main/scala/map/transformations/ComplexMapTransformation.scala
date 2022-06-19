package map.transformations

import map.MapTile

/** Applies a chain of transformations to a given map
  *
  * Similar to composite. Has an intermediate result passed between children.
  */
final class ComplexMapTransformation extends MapTransformation {
  private var children: List[MapTransformation] = List()

  def transform(map: Vector[Vector[MapTile]]): Vector[Vector[MapTile]] = {
    var intermediateResult = map
    children foreach { child =>
      intermediateResult = child.transform(intermediateResult)
    }

    intermediateResult
  }

  def +=(child: MapTransformation) = children = children :+ child
}
