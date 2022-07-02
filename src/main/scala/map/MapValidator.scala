package map

object MapValidator {
  def validate(map: Vector[Vector[MapTile]]): Boolean = {
    val flattened: Vector[MapTile] = map.flatten
    flattened.count(tile => tile == StartTile) == 1 &&
      flattened.count(tile => tile == TargetTile) == 1
  }
}
