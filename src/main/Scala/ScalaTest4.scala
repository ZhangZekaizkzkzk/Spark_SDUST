object ScalaTest4 {
  def main(args: Array[String]): Unit = {
    val x = 1.1

    //    def myfun1(): String = {
    //      "这是一个无参函数"
    //    }
    //    val temp1 = myfun1()
    //    print(temp1)
    def myfun2(x: Double): Unit =
      print("没有返回值的函数，当然就不会返回参数了")
    myfun2(x)

    def myfun3() = "这是一个无参函数，但是会返回函数体内的东西"
    var temp3 = myfun3()
    print(temp3)
    def myfun4(x:Int,y:Int){
      print(x*y)
    }
    myfun4(2,5)
  }
}
