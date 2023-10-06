import scala.collection.mutable.ArrayBuffer

object ScalaTest6 {
  def main(args: Array[String]): Unit = {
    val arr01 = new Array[Int](4)
//    println(arr01.mkString(","))
    arr01(1) = 1
    arr01(3) = 3
//    println(arr01.mkString(","))
    arr01.update(0,5)
    arr01.foreach(print)
////    println(arr01.mkString(","))
////    for(i <- arr01){
////      println(i)
////    }
//    val ints: Array[Int] = arr01 :+ 5
//    println(ints.mkString(","))
//    val arr02 = ArrayBuffer[Any]()
//    arr02.append(1.3)
//    arr02.append(1)
//    arr02.append("zhangzekai")
//    println(arr02.mkString(","))
//    arr02.update(0,7)
//    println(arr02.mkString(","))
////    arr02.append(1)
//    arr02.+=(1)
//    println(arr02.mkString(","))
//    arr02.insert(0, 8, 9)
//    println(arr02.mkString(","))
//      val arr03 = Array.ofDim[Int](3,4)
//      arr03(1)(2) = 5
//      val arr04 = arr03.toBuffer
//    arr04(5)(6) = 7
// 使用ArrayBuffer创建可变的二维数组
//val dynamicBuffer2D = ArrayBuffer.fill(3, 3)(0)
//    // 在运行时添加一行
//    dynamicBuffer2D += ArrayBuffer(4, 5, 6)
//
//    // 在运行时添加一列
//    dynamicBuffer2D.foreach(row => row += 7)
//
//    // 删除一行
//    dynamicBuffer2D.remove(1)
//
//    // 删除一列
//    dynamicBuffer2D.foreach(row => row.remove(0))
  }
}
