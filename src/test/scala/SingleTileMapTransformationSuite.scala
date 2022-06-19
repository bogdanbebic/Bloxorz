import org.scalatest.Assertions._
import org.scalatest.flatspec.AnyFlatSpec

import map.MapTile
import map.{RegularTile, NoTile}
import map.transformations.SingleTileMapTransformation

class SingleTileMapTransformationSuite extends AnyFlatSpec {
  "transform" should "do nothing on empty map" in {
    val map: Vector[Vector[MapTile]] = Vector(Vector())
    val expected: Vector[Vector[MapTile]] = Vector(Vector())
    val transformation = SingleTileMapTransformation(0, 0, RegularTile)

    assert(transformation.transform(map) == expected)
  }

  it should "transform map with given element" in {
    val map: Vector[Vector[MapTile]] = Vector(Vector(NoTile))
    val expected: Vector[Vector[MapTile]] = Vector(Vector(RegularTile))
    val transformation = SingleTileMapTransformation(0, 0, RegularTile)

    assert(transformation.transform(map) == expected)
  }

  it should "transform only the given element of the map" in {
    val map: Vector[Vector[MapTile]] =
      Vector(Vector(NoTile, NoTile), Vector(NoTile, NoTile))

    val expected: Vector[Vector[MapTile]] =
      Vector(Vector(RegularTile, NoTile), Vector(NoTile, NoTile))

    val transformation = SingleTileMapTransformation(0, 0, RegularTile)

    assert(transformation.transform(map) == expected)
  }
}
