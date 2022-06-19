package map

trait MapEditor {
  def map: Vector[Vector[MapTile]]
  def edit(): Unit
}
