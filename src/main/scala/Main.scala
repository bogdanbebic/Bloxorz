import scala.annotation.tailrec

@main def bloxorz: Unit =
  gameLoop

val usage: String = """Choose menu option:
  1 <filepath>: Load map from file
  2: Start game from loaded map
  3: Create new map from loaded map
  0: Exit game"""

@tailrec def gameLoop: Unit =
  println(usage)
  val input = io.StdIn.readLine()
  input match {
    case s"1 $filepath" => println(s"Load map from '$filepath'")
    case "2"            => println("Start game")
    case "3"            => println("Create new map")
    case "0"            => return
    case option         => println(s"Unrecognized menu option: '$option'")
  }

  gameLoop
