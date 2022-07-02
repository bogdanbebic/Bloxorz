package game

import map.MapTile
import map.ConsoleMapEditor
import map.MapReader
import map.MapValidator
import map.MapWriter
import scala.util.{Success, Failure}

object ConsoleMainMenu {
  private var map: Vector[Vector[MapTile]] = Vector(Vector())

  private val usage: String = """Choose menu option:
    load <filepath>: Load map from file
    start: Start game from loaded map
    create: Create new map from loaded map
    save <filepath>: Save map to file
    exit: Exit game"""

  def gameLoop(): Unit = {
    // continue prompting the user for actions until exit
    while (true) {
      println(usage)

      val input = io.StdIn.readLine()
      input match {
        case s"load $filepath" => {
          MapReader.readFromFile(filepath) match {
            case Success(parsedMap) => map = parsedMap
            case Failure(ex)        => println(ex)
          }
        }

        case "start" => {
          MapValidator.validate(map) match {
            case true  => ConsoleGameMenu(map).game()
            case false => println("Map not valid")
          }
        }

        case "create" => {
          val editor = ConsoleMapEditor(map)
          editor.edit()
          map = editor.map
        }

        case s"save $filepath" => {
          MapWriter.writeToFile(filepath, map) match {
            case Success(_)  => println(s"Saved map to $filepath")
            case Failure(ex) => println(ex)
          }
        }

        case "exit" => return

        case option => println(s"Unrecognized menu option: '$option'")
      }
    }
  }
}
