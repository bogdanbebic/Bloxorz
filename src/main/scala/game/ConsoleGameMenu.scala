package game

import map.{MapTile, StartTile}
import scala.util.{Success, Failure}
import map.MapWriter

class ConsoleGameMenu(private val map: Vector[Vector[MapTile]]) {
  private val usage: String = """Choose game option:
    <move>: Play move
    play <filepath>: Play moves from file
    solve: Print solution
    save <filepath>: Save solution to file
    exit: Exit game"""

  def game(): Unit = {
    val block = this.positionFromMap(this.map)

    // continue prompting the user for actions until exit
    while (true) {
      println(MapWriter.toEtfFormatString(this.map, block))
      println(usage)

      val input = io.StdIn.readLine()
      input match {
        case s"play $filepath" => {
          MoveReader.readFromFile(filepath) match {
            case Success(moves) => moves foreach this.playMove
            case Failure(ex)    => println(ex)
          }
        }

        case "solve" => println("Print solution")

        case s"save $filepath" => println(s"Save solution to $filepath")

        case "exit" => return

        case option =>
          fromEtfFormat(option) match {
            case Some(move) => this.playMove(move)
            case None       => println(s"Unrecognized menu option: '$option'")
          }
      }
    }
  }

  private def positionFromMap(map: Vector[Vector[MapTile]]): BlockPosition = {
    val startTileIndices = Iterator.range(0, map.length).flatMap { i =>
      Iterator.range(0, map(i).length).map { j =>
        (i, j)
      }
    } find { (i, j) => map(i)(j) == StartTile }

    val (row: Int, col: Int) = startTileIndices.get
    BlockPosition(Position(row, col), None)
  }

  private def playMove(move: Move): Unit = {
    println(s"Play move $move")
  }
}
