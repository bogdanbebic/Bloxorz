import org.scalatest.Assertions._
import org.scalatest.flatspec.AnyFlatSpec

import map._

class MapTileSuite extends AnyFlatSpec {

  "fromEtfFormat" should "return None when invoked with non [o-ST.]" in {
    assert(fromEtfFormat("x").isEmpty)
  }

  it should "return RegularTile when invoked with 'u'" in {
    assert(fromEtfFormat("o") == Some(RegularTile))
  }

  it should "return NoTile when invoked with 'd'" in {
    assert(fromEtfFormat("-") == Some(NoTile))
  }

  it should "return StartTile when invoked with 'l'" in {
    assert(fromEtfFormat("S") == Some(StartTile))
  }

  it should "return TargetTile when invoked with 'r'" in {
    assert(fromEtfFormat("T") == Some(TargetTile))
  }

  it should "return FallthroughTile when invoked with 'r'" in {
    assert(fromEtfFormat(".") == Some(FallthroughTile))
  }

  "toEtfFormat" should "return 'o' when invoked with RegularTile" in {
    assert(toEtfFormat(RegularTile) == "o")
  }

  it should "return '-' when invoked with NoTile" in {
    assert(toEtfFormat(NoTile) == "-")
  }

  it should "return 'S' when invoked with StartTile" in {
    assert(toEtfFormat(StartTile) == "S")
  }

  it should "return 'T' when invoked with TargetTile" in {
    assert(toEtfFormat(TargetTile) == "T")
  }

  it should "return '.' when invoked with FallthroughTile" in {
    assert(toEtfFormat(FallthroughTile) == ".")
  }
}
