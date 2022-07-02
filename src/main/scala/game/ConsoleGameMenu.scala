package game

import map.MapTile
import scala.util.{Success, Failure}

class ConsoleGameMenu(private val map: Vector[Vector[MapTile]]) {
  private val usage: String = """Choose game option:
    <move>: Play move
    play <filepath>: Play moves from file
    solve: Print solution
    save <filepath>: Save solution to file
    exit: Exit game"""

  def game(): Unit = {
    // continue prompting the user for actions until exit
    while (true) {
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

  private def playMove(move: Move): Unit = {
    println(s"Play move $move")
  }
}
