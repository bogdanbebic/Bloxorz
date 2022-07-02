import org.scalatest.Assertions._
import org.scalatest.flatspec.AnyFlatSpec

import map._

class MapValidatorSuite extends AnyFlatSpec {
  "validate" should "return true when map is valid" in {
    val input = Vector(Vector(StartTile, TargetTile))
    assert(MapValidator.validate(input))
  }

  it should "return false when map does not have a StartTile" in {
    val input = Vector(Vector(TargetTile))
    assert(!MapValidator.validate(input))
  }

  it should "return false when map does not have a TargetTile" in {
    val input = Vector(Vector(StartTile))
    assert(!MapValidator.validate(input))
  }
}
