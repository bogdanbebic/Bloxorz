package map

import scala.annotation.tailrec
import scala.util.{Try, Success, Failure}

import map.transformations.SingleTileMapTransformation

final class ConsoleMapEditor(private var _map: Vector[Vector[MapTile]])
    extends MapEditor {
  def map = _map

  private val usage: String = """Choose map editing operation:
    - <row> <col>: Remove tile from given position
    o <row> <col>: Add tile to given position
    . <row> <col>: Replace tile on given position with special tile
    S <row> <col>: Move start tile to given position
    T <row> <col>: Move target tile to given position
    create <name>: Create complex operation with given name
    inversion: Swap start and target tiles
    switch: Switch all special tiles to regular tiles
    filter <row> <col> <N>: Convert given tile to regular tile if a special tile is within N vertical or horizontal tiles
    complex <name>: Run complex operation with given name
    show: Print current map on output
    exit: Exit map editing"""

  def edit(): Unit = {
    // continue editing until user exits
    while (true) {
      println(usage)
      val input = io.StdIn.readLine()
      input match {
        case s"- $i $j" =>
          _map = parseAndRunSingleTileTransformation(i, j, _map, NoTile)
        case s"o $i $j" =>
          _map = parseAndRunSingleTileTransformation(i, j, _map, RegularTile)
        case s". $i $j" =>
          _map =
            parseAndRunSingleTileTransformation(i, j, _map, FallthroughTile)
        case s"S $i $j"         => println(s"S '$i' '$j'")
        case s"T $i $j"         => println(s"T '$i' '$j'")
        case s"create $name"    => println(s"create '$name'")
        case "inversion"        => println("inversion")
        case "switch"           => println("switch")
        case s"filter $i $j $n" => println(s"filter '$i' '$j' '$n'")
        case s"complex $name"   => println(s"complex '$name'")
        case "show"             => println(map)
        case "exit"             => return
        case option             => println(s"Unrecognized option: '$option'")
      }
    }
  }

  private def parseAndRunSingleTileTransformation(
      i: String,
      j: String,
      map: Vector[Vector[MapTile]],
      tileType: MapTile
  ): Vector[Vector[MapTile]] = {
    val position = parseMapPosition(i, j)
    position match {
      case Success((row: Int, col: Int)) => {
        val transformation = SingleTileMapTransformation(row, col, tileType)
        transformation.transform(map)
      }
      case Failure(e) => println(e)
      case Success(_) => println("Program bug, please report")
    }

    println("Map not changed")
    map
  }

  private def parseMapPosition(row: String, col: String): Try[Tuple] = Try {
    (row.toInt, col.toInt)
  }
}
