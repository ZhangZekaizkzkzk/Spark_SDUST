

object ScalaTest2 {
  def main(array: Array[String]): Unit = {
//    println("age:")
//    var age = StdIn.readInt()
//    if (age<18){
//      println("小于18")
//    }else if (age >=18 && age <=60){
//      println("18-60")
//    }else{
//      println("》60")
//    }
//    println("------To------")
//    for(i <- 1 to 3){
//      println(i)
//    }
//    println("-----Until----")
//    for(j <- 1 until 3){
//      println(j)
//    }
//    for(i <- 1 to 3; j <- 1  to 3 ){
//      println("i:" + i + ", j:" + j)
//    }
//    for {
//      i <- 1 to 3
//      j = 3 - i
//    } {
//      println("i=" + i + " j=" + j)
//    }
//    for(i <- 1 to 10 by 2) {
//      print(i +"\t")
//    }
//    for (i <- 1 to 5 if i != 2) {
//      print(i + "\t")
//    }
//    for (i <- 1 to 10 reverse)
//      print(i + "\t")

//    breakable {
//      for (elem <- 1 to 10) {
//        printf(elem+ "\t")
//        if (elem == 5) break
//      }
//    }
//    println("正常结束循环")
//    for (elem <- 1 to 10) {
//      if (elem % 2 == 1) {
//        println(elem)
//      } else {
//        println("continue")
//      }
//    }
    try{
      for (i <- 1 to 10) {
        printf(i + "\t")
        if (i == 5)
          throw new RuntimeException
      }
    }catch {
      case e:RuntimeException =>
    }
  println("结束循环")
  }
}
