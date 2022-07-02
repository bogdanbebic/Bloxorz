package game

import scala.util.{Try, Success, Failure}

object MoveReader {
  def readFromFile(filepath: String): Try[Vector[Move]] = Try {
    val source = io.Source.fromFile(filepath)
    val lines =
      try source.getLines().toVector
      finally source.close()

    return parseEtfFormat(lines)
  }

  def parseEtfFormat(etfFormat: Vector[String]): Try[Vector[Move]] = Try {
    return Success(
      etfFormat.map(moveString => fromEtfFormat(moveString).get)
    )
  }
}
