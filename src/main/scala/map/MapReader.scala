package map

import scala.util.{Try, Success, Failure}

object MapReader {
  def readFromFile(filepath: String): Try[Vector[Vector[MapTile]]] = Try {
    val source = io.Source.fromFile(filepath)
    val lines =
      try source.getLines().toVector
      finally source.close()

    return parseEtfFormat(lines)
  }

  def parseEtfFormat(etfFormat: Vector[String]): Try[Vector[Vector[MapTile]]] =
    Try {
      val rows = etfFormat.length
      val cols = etfFormat(0).length

      // checks whether all lines are of the same length
      if (etfFormat.exists(_.length != cols)) {
        return Failure(java.lang.Exception())
      }

      return Success(
        Vector.tabulate(rows, cols)((i, j) =>
          fromEtfFormat(etfFormat(i)(j)).get
        )
      )
    }
}
