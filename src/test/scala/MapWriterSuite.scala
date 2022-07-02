import org.scalatest.Assertions._
import org.scalatest.flatspec.AnyFlatSpec

import map._
import game.BlockPosition
import game.Position

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

  it should "return block string when block on position" in {
    val map = Vector(
      Vector(NoTile, RegularTile),
      Vector(StartTile, FallthroughTile),
      Vector(NoTile, TargetTile)
    )

    val block = BlockPosition(Position(1, 0), None)

    assert(MapWriter.toEtfFormatString(map, block) == "-o\n$.\n-T")
  }

  it should "return block string when block on multiple positions" in {
    val map = Vector(
      Vector(NoTile, RegularTile),
      Vector(StartTile, FallthroughTile),
      Vector(NoTile, TargetTile)
    )

    val block = BlockPosition(Position(1, 0), Some(Position(1, 1)))

    assert(MapWriter.toEtfFormatString(map, block) == "-o\n$$\n-T")
  }
}
