package map

import scala.util.{Try, Success, Failure}
import game.{BlockPosition, Position}

object MapWriter {
  def writeToFile(
      filepath: String,
      map: Vector[Vector[MapTile]]
  ): Try[Nothing] = {
    val contents: String = toEtfFormatString(map)
    Failure(java.lang.UnsupportedOperationException())
  }

  def toEtfFormatString(map: Vector[Vector[MapTile]]): String = {
    val etfFormat = Vector.tabulate(map.length, map(0).length)((i, j) =>
      toEtfFormat(map(i)(j))
    )

    etfFormat.map(row => row.mkString).mkString("\n")
  }

  def toEtfFormatString(
      map: Vector[Vector[MapTile]],
      block: BlockPosition
  ): String = {
    val etfFormat = Vector.tabulate(map.length, map(0).length)((i, j) =>
      val position = Position(i, j)
      if (
        position == block.upperLeft ||
        (block.additional.isDefined && position == block.additional.get)
      ) {
        "$"
      } else {
        toEtfFormat(map(i)(j))
      }
    )

    etfFormat.map(row => row.mkString).mkString("\n")
  }
}
