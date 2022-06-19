package map

import scala.util.{Try, Success, Failure}

object MapWriter {
  def writeToFile(filepath: String, map: Vector[Vector[MapTile]]): Unit = {
    val contents: String = toEtfFormatString(map)
    // TODO: print to file
  }

  def toEtfFormatString(map: Vector[Vector[MapTile]]): String =
    val etfFormat = Vector.tabulate(map.length, map(0).length)((i, j) =>
      toEtfFormat(map(i)(j))
    )

    etfFormat.map(row => row.mkString).mkString("\n")
}
