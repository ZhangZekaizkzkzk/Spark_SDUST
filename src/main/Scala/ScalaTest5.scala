trait Ball {
  def describe(): String = {
    "ball"
  }
}
// Color继承了Ball,将describe重写为"blue-ball"
trait Color extends Ball {
  override def describe(): String = {
    "blue-" + super.describe()
  }
}
//Category同样继承了ball,将describe重写为了“foot-ball”
trait Category extends Ball {
  override def describe(): String = {
    "foot-" + super.describe()
  }
}
// 这时创建了一个MyBall类,同时继承了Category与Color两个特征
class MyBall extends Category with Color {
  override def describe(): String = {
    "my ball is a " + super.describe()
  }
}
//创建MyBall对象,并调用方法。
object TestTrait {
  def main(args: Array[String]): Unit = {
    println(new MyBall().describe())
    //此时的输出为“my ball is a blue-foot-ball”，同时满足了以上两个特征的重写内容。
  }
}