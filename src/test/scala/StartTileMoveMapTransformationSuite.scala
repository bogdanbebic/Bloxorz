import org.scalatest.Assertions._
import org.scalatest.flatspec.AnyFlatSpec

import map.MapTile
import map.{RegularTile, NoTile, StartTile}
import map.transformations.StartTileMoveMapTransformation

class StartTileMoveMapTransformationSuite extends AnyFlatSpec {
  "transform" should "do nothing on empty map" in {
    val map: Vector[Vector[MapTile]] = Vector(Vector())
    val transformation = StartTileMoveMapTransformation(0, 0)

    assert(transformation.transform(map) == map)
  }

  it should "transform map with given element" in {
    val map: Vector[Vector[MapTile]] =
      Vector(Vector(StartTile, RegularTile))

    val expected: Vector[Vector[MapTile]] =
      Vector(Vector(RegularTile, StartTile))

    val transformation = StartTileMoveMapTransformation(0, 1)

    assert(transformation.transform(map) == expected)
  }

  it should "transform only the given elements of the map" in {
    val map: Vector[Vector[MapTile]] =
      Vector(Vector(StartTile, RegularTile), Vector(NoTile, NoTile))

    val expected: Vector[Vector[MapTile]] =
      Vector(Vector(RegularTile, StartTile), Vector(NoTile, NoTile))

    val transformation = StartTileMoveMapTransformation(0, 1)

    assert(transformation.transform(map) == expected)
  }
}
