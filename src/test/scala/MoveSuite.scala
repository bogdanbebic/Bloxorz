import org.scalatest.Assertions._
import org.scalatest.flatspec.AnyFlatSpec

import moves._

class MoveSuite extends AnyFlatSpec {

  "fromEtfFormat" should "return None when invoked with non [udlr]" in {
    assert(fromEtfFormat("x").isEmpty)
  }

  it should "return UpMove when invoked with 'u'" in {
    assert(fromEtfFormat("u") == Some(UpMove))
  }

  it should "return DownMove when invoked with 'd'" in {
    assert(fromEtfFormat("d") == Some(DownMove))
  }

  it should "return LeftMove when invoked with 'l'" in {
    assert(fromEtfFormat("l") == Some(LeftMove))
  }

  it should "return RightMove when invoked with 'r'" in {
    assert(fromEtfFormat("r") == Some(RightMove))
  }

  "toEtfFormat" should "return 'u' when invoked with UpMove" in {
    assert(toEtfFormat(UpMove) == "u")
  }

  it should "return 'd' when invoked with DownMove" in {
    assert(toEtfFormat(DownMove) == "d")
  }

  it should "return 'l' when invoked with LeftMove" in {
    assert(toEtfFormat(LeftMove) == "l")
  }

  it should "return 'r' when invoked with RightMove" in {
    assert(toEtfFormat(RightMove) == "r")
  }
}
