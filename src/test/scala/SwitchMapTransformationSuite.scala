import org.scalatest.Assertions._
import org.scalatest.flatspec.AnyFlatSpec

import map.MapTile
import map.{RegularTile, NoTile, StartTile, TargetTile, FallthroughTile}
import map.transformations.SwitchMapTransformation

class SwitchMapTransformationSuite extends AnyFlatSpec {
  "transform" should "do nothing on empty map" in {
    val map: Vector[Vector[MapTile]] = Vector(Vector())
    val transformation = SwitchMapTransformation()

    assert(transformation.transform(map) == map)
  }

  it should "transform switch fallthrough tiles to regular tiles" in {
    val map: Vector[Vector[MapTile]] =
      Vector(Vector(FallthroughTile))

    val expected: Vector[Vector[MapTile]] =
      Vector(Vector(RegularTile))

    val transformation = SwitchMapTransformation()

    assert(transformation.transform(map) == expected)
  }

  it should " do nothing on non-fallthrough tiles tiles" in {
    val map: Vector[Vector[MapTile]] =
      Vector(Vector(RegularTile, NoTile, StartTile, TargetTile))

    val expected: Vector[Vector[MapTile]] =
      Vector(Vector(RegularTile, NoTile, StartTile, TargetTile))

    val transformation = SwitchMapTransformation()

    assert(transformation.transform(map) == expected)
  }
}
