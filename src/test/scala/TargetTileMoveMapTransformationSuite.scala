import org.scalatest.Assertions._
import org.scalatest.flatspec.AnyFlatSpec

import map.MapTile
import map.{RegularTile, NoTile, TargetTile}
import map.transformations.TargetTileMoveMapTransformation

class TargetTileMoveMapTransformationSuite extends AnyFlatSpec {
  "transform" should "do nothing on empty map" in {
    val map: Vector[Vector[MapTile]] = Vector(Vector())
    val transformation = TargetTileMoveMapTransformation(0, 0)

    assert(transformation.transform(map) == map)
  }

  it should "transform map with given element" in {
    val map: Vector[Vector[MapTile]] =
      Vector(Vector(TargetTile, RegularTile))

    val expected: Vector[Vector[MapTile]] =
      Vector(Vector(RegularTile, TargetTile))

    val transformation = TargetTileMoveMapTransformation(0, 1)

    assert(transformation.transform(map) == expected)
  }

  it should "transform only the given elements of the map" in {
    val map: Vector[Vector[MapTile]] =
      Vector(Vector(TargetTile, RegularTile), Vector(NoTile, NoTile))

    val expected: Vector[Vector[MapTile]] =
      Vector(Vector(RegularTile, TargetTile), Vector(NoTile, NoTile))

    val transformation = TargetTileMoveMapTransformation(0, 1)

    assert(transformation.transform(map) == expected)
  }
}
