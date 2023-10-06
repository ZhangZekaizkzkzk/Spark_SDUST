import scala.collection.mutable.ListBuffer

object ScalaTest7 {
  def main(args: Array[String]): Unit = {
//    val list:List[Int] = List(1,2,3,4,5)
//    list.foreach(print)
//    println("")
////    val list5 = 1::2::3::4::Nil
////    list5.foreach(println)
//    val list1 = 5::list
//    list1.foreach(print)
    // 增加数据
    val buffer = ListBuffer(1,2,3,4)
    buffer += (5) // (1,2,3,4,5)
    buffer.foreach(print)
    println("")
    buffer.append(6) // (1,2,3,4,5,6)
    buffer.foreach(print)
    println("")
    // 插入数据
    buffer.insert(1, 2) //(1,2,2,3,4,5,6)
    buffer.foreach(print)
    println("")
    // 修改数据
    buffer(1) = 6 // (1,6,2,3,4,5,6)
    buffer.foreach(print)
    println("")
    buffer.update(1,7) //(1,7,2,3,4,5,6)
    buffer.foreach(print)
    println("")
    // 删除数据
    buffer.-(5) // (1,2,3,4,5,6)
    buffer.foreach(print)
    println("")
    buffer.remove(5) // (1,7,2,3,4,6)
    buffer.foreach(print)
    println("")
  }
}
