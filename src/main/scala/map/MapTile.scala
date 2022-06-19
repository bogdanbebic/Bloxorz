package map

sealed trait MapTile

case object RegularTile extends MapTile
case object NoTile extends MapTile
case object StartTile extends MapTile
case object TargetTile extends MapTile
case object FallthroughTile extends MapTile

def fromEtfFormat(etfFormat: Char): Option[MapTile] = {
  etfFormat match {
    case 'o' => Some(RegularTile)
    case '-' => Some(NoTile)
    case 'S' => Some(StartTile)
    case 'T' => Some(TargetTile)
    case '.' => Some(FallthroughTile)
    case _   => None
  }
}

def toEtfFormat(mapTile: MapTile): Char = {
  mapTile match {
    case RegularTile     => 'o'
    case NoTile          => '-'
    case StartTile       => 'S'
    case TargetTile      => 'T'
    case FallthroughTile => '.'
  }
}
