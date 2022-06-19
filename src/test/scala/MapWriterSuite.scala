import org.scalatest.Assertions._
import org.scalatest.flatspec.AnyFlatSpec

import map._

class MapWriterSuite extends AnyFlatSpec {
  "toEtfFormatString" should "return empty string on empty map" in {
    val input: Vector[Vector[MapTile]] = Vector(Vector())

    assert(MapWriter.toEtfFormatString(input) == "")
  }

  it should "return successfully converted ETF format string" in {
    val input = Vector(
      Vector(NoTile, RegularTile),
      Vector(StartTile, FallthroughTile),
      Vector(NoTile, TargetTile)
    )

    assert(MapWriter.toEtfFormatString(input) == "-o\nS.\n-T")
  }
}
