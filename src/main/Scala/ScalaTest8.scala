object ScalaTest8 {
  def main(args: Array[String]): Unit = {
    val map = Map( "a"->1, "b"->2, "c"->3 )
//    println(map.get("a").get)
    println(map.getOrElse("a", 0))
    println(map.getOrElse("d", 0))
  }
}
