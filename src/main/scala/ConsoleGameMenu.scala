package game

import map.MapTile
import map.{MapEditor, ConsoleMapEditor}

object ConsoleGameMenu {
  private var map: Vector[Vector[MapTile]] = Vector(Vector())

  private val usage: String = """Choose menu option:
    load <filepath>: Load map from file
    start: Start game from loaded map
    create: Create new map from loaded map
    exit: Exit game"""

  def gameLoop(): Unit = {
    // continue prompting the user for actions until exit
    while (true) {
      println(usage)

      val input = io.StdIn.readLine()
      input match {
        case s"load $filepath" => println(s"Load map from '$filepath'")
        case "start"           => println("Start game")
        case "create" => {
          val editor = ConsoleMapEditor(map)
          editor.edit()
          map = editor.map
        }

        case "exit" => return
        case option => println(s"Unrecognized menu option: '$option'")
      }
    }
  }
}
