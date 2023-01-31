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

  private var block: BlockPosition = this.positionFromMap(this.map)

  def game(): Unit = {
    // continue prompting the user for actions until exit
    while (true) {
      println(MapWriter.toEtfFormatString(this.map, block))
      println(usage)

      val input = io.StdIn.readLine()
      input match {
        case s"play $filepath" => {
          MoveReader.readFromFile(filepath) match {
            case Failure(ex) => println(ex)
            case Success(moves) =>
              moves.foreach(move => {
                this.block = moveBlock(this.block, move)
                val outcome = getOutcome(this.block, this.map)
                outcome match {
                  case ValidMove => println("Block moved.")
                  case FellThrough => {
                    println("The block fell through the tiles.")
                    return
                  }
                  case OutOfBounds => {
                    println("The block went out of bounds.")
                    return
                  }
                  case GameWon => {
                    println("You won the game!")
                    return
                  }
                }
              })
          }
        }

        case "solve" => println("Print solution")

        case s"save $filepath" => println(s"Save solution to $filepath")

        case "exit" => return

        case option =>
          fromEtfFormat(option) match {
            case None => println(s"Unrecognized menu option: '$option'")
            case Some(move) => {
              this.block = moveBlock(this.block, move)
              val outcome = getOutcome(this.block, this.map)
              outcome match {
                case ValidMove => println("Block moved.")
                case FellThrough => {
                  println("The block fell through the tiles.")
                  return
                }
                case OutOfBounds => {
                  println("The block went out of bounds.")
                  return
                }
                case GameWon => {
                  println("You won the game!")
                  return
                }
              }
            }
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
}
