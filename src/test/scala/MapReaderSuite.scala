import org.scalatest.Assertions._
import org.scalatest.flatspec.AnyFlatSpec

import map._

class MapReaderSuite extends AnyFlatSpec {
  "parseEtfFormat" should "return Failure when lines are not of same length" in {
    val input = Vector("-", "--")

    assert(MapReader.parseEtfFormat(input).isFailure)
  }

  "parseEtfFormat" should "return Failure when input contains non-ETF format chars" in {
    val input = Vector("x")

    assert(MapReader.parseEtfFormat(input).isFailure)
  }

  "parseEtfFormat" should "return Success and successfully parsed ETF format" in {
    val input = Vector("-o", "S.", "-T")
    val expected = Vector(
      Vector(NoTile, RegularTile),
      Vector(StartTile, FallthroughTile),
      Vector(NoTile, TargetTile)
    )

    val map = MapReader.parseEtfFormat(input)
    assert(map.isSuccess)
    assert(expected == map.get)
  }
}
