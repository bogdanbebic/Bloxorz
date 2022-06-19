import org.scalatest.Assertions._
import org.scalatest.flatspec.AnyFlatSpec

import map.MapTile
import map.{RegularTile, NoTile, FallthroughTile}
import map.transformations.{ComplexMapTransformation, SingleTileMapTransformation}

class ComplexMapTransformationSuite extends AnyFlatSpec {
  "transform" should "do nothing on empty map" in {
    val map: Vector[Vector[MapTile]] = Vector(Vector())
    val transformation = ComplexMapTransformation()

    assert(transformation.transform(map) == map)
  }

  it should "do nothing when it contains no transformations" in {
    val map: Vector[Vector[MapTile]] = Vector(Vector(NoTile))
    val transformation = ComplexMapTransformation()

    assert(transformation.transform(map) == map)
  }

  it should "perform the added transformations" in {
    val map: Vector[Vector[MapTile]] =
      Vector(Vector(NoTile, NoTile), Vector(NoTile, NoTile))

    val expected: Vector[Vector[MapTile]] =
      Vector(Vector(RegularTile, NoTile), Vector(NoTile, FallthroughTile))

    val transformation1 = SingleTileMapTransformation(0, 0, RegularTile)
    val transformation2 = SingleTileMapTransformation(1, 1, FallthroughTile)

    val transformations = ComplexMapTransformation()
    transformations += transformation1
    transformations += transformation2

    assert(transformations.transform(map) == expected)
  }

  it should "perform the added transformations in order" in {
    val map: Vector[Vector[MapTile]] =
      Vector(Vector(NoTile, NoTile), Vector(NoTile, NoTile))

    val expected: Vector[Vector[MapTile]] =
      Vector(Vector(RegularTile, NoTile), Vector(NoTile, NoTile))

    val transformation1 = SingleTileMapTransformation(0, 0, FallthroughTile)
    val transformation2 = SingleTileMapTransformation(0, 0, RegularTile)

    val transformations = ComplexMapTransformation()
    transformations += transformation1
    transformations += transformation2

    assert(transformations.transform(map) == expected)
  }
}
