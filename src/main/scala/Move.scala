package moves

sealed trait Move

case object UpMove extends Move
case object DownMove extends Move
case object LeftMove extends Move
case object RightMove extends Move

def fromEtfFormat(etfFormat: String): Option[Move] = {
  etfFormat match {
    case "u" => Some(UpMove)
    case "d" => Some(DownMove)
    case "l" => Some(LeftMove)
    case "r" => Some(RightMove)
    case _   => None
  }
}

def toEtfFormat(move: Move): String = {
  move match {
    case UpMove    => "u"
    case DownMove  => "d"
    case LeftMove  => "l"
    case RightMove => "r"
  }
}
