import org.scalatest.Assertions._
import org.scalatest.flatspec.AnyFlatSpec

import game.{Position, BlockPosition}
import game.{getOutcome, moveBlock}

import game.{ValidMove, OutOfBounds, GameWon, FellThrough}

import map.{RegularTile, StartTile, TargetTile, NoTile, FallthroughTile}

class BlockPositionSuite extends AnyFlatSpec {
  "getOutcome" should "return ValidMove when block vertical on regular tile" in {
    val block = BlockPosition(Position(0, 0), None)
    val map = Vector(Vector(RegularTile))

    assert(getOutcome(block, map) == ValidMove)
  }

  it should "return OutOfBounds when block vertical on no tile" in {
    val block = BlockPosition(Position(0, 0), None)
    val map = Vector(Vector(NoTile))

    assert(getOutcome(block, map) == OutOfBounds)
  }

  it should "return ValidMove when block vertical on start tile" in {
    val block = BlockPosition(Position(0, 0), None)
    val map = Vector(Vector(StartTile))

    assert(getOutcome(block, map) == ValidMove)
  }

  it should "return GameWon when block vertical on target tile" in {
    val block = BlockPosition(Position(0, 0), None)
    val map = Vector(Vector(TargetTile))

    assert(getOutcome(block, map) == GameWon)
  }

  it should "return FellThrough when block vertical on fallthrough tile" in {
    val block = BlockPosition(Position(0, 0), None)
    val map = Vector(Vector(FallthroughTile))

    assert(getOutcome(block, map) == FellThrough)
  }

  it should "return OutOfBounds when either part of the block on no tile" in {
    val block = BlockPosition(Position(0, 0), Some(Position(0, 1)))

    assert(getOutcome(block, Vector(Vector(NoTile, RegularTile))) == OutOfBounds)
    assert(getOutcome(block, Vector(Vector(RegularTile, NoTile))) == OutOfBounds)
    assert(getOutcome(block, Vector(Vector(NoTile, NoTile))) == OutOfBounds)
  }

  it should "return ValidMove when block horizontal on tiles" in {
    val block = BlockPosition(Position(0, 0), Some(Position(0, 1)))

    assert(getOutcome(block, Vector(Vector(RegularTile, RegularTile))) == ValidMove)
    assert(getOutcome(block, Vector(Vector(FallthroughTile, FallthroughTile))) == ValidMove)
    assert(getOutcome(block, Vector(Vector(TargetTile, TargetTile))) == ValidMove)

    assert(getOutcome(block, Vector(Vector(RegularTile, StartTile))) == ValidMove)
    assert(getOutcome(block, Vector(Vector(RegularTile, TargetTile))) == ValidMove)
    assert(getOutcome(block, Vector(Vector(RegularTile, FallthroughTile))) == ValidMove)

    assert(getOutcome(block, Vector(Vector(StartTile, RegularTile))) == ValidMove)
    assert(getOutcome(block, Vector(Vector(StartTile, TargetTile))) == ValidMove)
    assert(getOutcome(block, Vector(Vector(StartTile, FallthroughTile))) == ValidMove)

    assert(getOutcome(block, Vector(Vector(TargetTile, RegularTile))) == ValidMove)
    assert(getOutcome(block, Vector(Vector(TargetTile, StartTile))) == ValidMove)
    assert(getOutcome(block, Vector(Vector(TargetTile, FallthroughTile))) == ValidMove)

    assert(getOutcome(block, Vector(Vector(FallthroughTile, RegularTile))) == ValidMove)
    assert(getOutcome(block, Vector(Vector(FallthroughTile, StartTile))) == ValidMove)
    assert(getOutcome(block, Vector(Vector(FallthroughTile, TargetTile))) == ValidMove)
  }
}
