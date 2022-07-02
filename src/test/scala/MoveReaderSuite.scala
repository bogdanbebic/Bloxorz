import org.scalatest.Assertions._
import org.scalatest.flatspec.AnyFlatSpec

import game._

class MoveReaderSuite extends AnyFlatSpec {
  "parseEtfFormat" should "return Failure when input contains non-ETF format chars" in {
    val input = Vector("x")

    assert(MoveReader.parseEtfFormat(input).isFailure)
  }

  it should "return Success and empty Vector on empty input" in {
    val map = MoveReader.parseEtfFormat(Vector())
    assert(map.isSuccess)
    assert(Vector() == map.get)
  }

  it should "return Success and successfully parsed ETF format" in {
    val input = Vector("u", "d", "l", "r")
    val expected = Vector(
      UpMove,
      DownMove,
      LeftMove,
      RightMove
    )

    val map = MoveReader.parseEtfFormat(input)
    assert(map.isSuccess)
    assert(expected == map.get)
  }
}
