import org.scalatest.Assertions._
import org.scalatest.flatspec.AnyFlatSpec

import map.MapTile
import map.{RegularTile, NoTile, StartTile, TargetTile, FallthroughTile}
import map.transformations.InversionMapTransformation

class InversionMapTransformationSuite extends AnyFlatSpec {
  "transform" should "do nothing on empty map" in {
    val map: Vector[Vector[MapTile]] = Vector(Vector())
    val transformation = InversionMapTransformation()

    assert(transformation.transform(map) == map)
  }

  it should "transform invert start and target tiles" in {
    val map: Vector[Vector[MapTile]] =
      Vector(Vector(StartTile, TargetTile))

    val expected: Vector[Vector[MapTile]] =
      Vector(Vector(TargetTile, StartTile))

    val transformation = InversionMapTransformation()

    assert(transformation.transform(map) == expected)
  }

  it should " do nothing on non start or target tiles" in {
    val map: Vector[Vector[MapTile]] =
      Vector(Vector(RegularTile, NoTile, FallthroughTile))

    val expected: Vector[Vector[MapTile]] =
      Vector(Vector(RegularTile, NoTile, FallthroughTile))

    val transformation = InversionMapTransformation()

    assert(transformation.transform(map) == expected)
  }
}
