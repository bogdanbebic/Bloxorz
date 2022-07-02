package game

sealed trait MoveOutcome

case object FellThrough extends MoveOutcome
case object OutOfBounds extends MoveOutcome
case object ValidMove extends MoveOutcome
case object GameWon extends MoveOutcome
