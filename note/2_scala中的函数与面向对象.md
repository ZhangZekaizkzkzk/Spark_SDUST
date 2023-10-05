# Scala中的函数面向对象变编程

## Scala中的函数

### 定义函数

例：实现sum函数，返回两数之和

```Scala
def sum(x:Int, y:Int): Int = {
    x+y
}
//def : 函数声明关键字
//sum : 函数名
//x,y : 参数:数据类型（数据类型必须加）
//最后一个Int : 返回值类型 
```

无参函数

```Scala
def myfun1():String = {
  "这是一个无参函数，但是会返回函数体内的东西"
}
// “声明”（def）一个名为“myfun1”的函数，没有参数值“（）”，返回值为String
```

无返回值函数

```Scala
def myfun2(x:Double): Unit = {
  print("没有返回值的函数，当然就不会返回参数了")
}
```

*对于无返回值的函数，使用“temp = myfun2()”的写法会报错*

### 函数至简原则

***至简原则细节***

1. return 可以省略，Scala 会使用函数体的最后一行代码作为返回值
2. 如果函数体只有一行代码，可以省略花括号

```scala
def myfun3(): String = 
  "由于这个函数只有一行，所以可以没有花括号"
```

3. 返回值类型如果能够推断出来，那么可以省略（:和返回值类型一起省略）

```scala
def myfun4() = "双引号肯定是String，所以不写返回值了"
```

4. 如果有 return，则不能省略返回值类型，必须指定
5. 如果函数明确声明 unit，那么即使函数体中使用 return 关键字也不起作用
6. Scala 如果期望是无返回值类型，可以省略等号

```scala
def myfun4(x:Int,y:Int){
  print(x*y)
}
```

7. 如果函数无参，但是声明了参数列表，那么调用时，小括号，可加可不加
8. 如果函数没有参数列表，那么小括号可以省略，调用时小括号必须省略
9. 如果不关心名称，只关心逻辑处理，那么函数名（def）可以省略

### 关于函数的其他用法

***函数的其他应用***

1. 作为值进行传递

```scala
def foo():Int = {
  println("foo...")
  1
}
val f = foo
println(f)
```

2. 作为参数进行传递

```scala
def add(a: Int, b: Int): Int = a + b
println(f1(add))
println(f1(add _))
```

3. 作为函数返回值返回

```scala
def f1() = {
 def f2() = {
}
f2 _
}
```

***匿名函数***

*就是没有名字的函数*

```scala
(x: Int)=> x+1
//等于
def plus1(x:Int):Int = {
  x + 1
}
```

***函数柯里化&闭包***

pass

***值调用与名调用***

pass

***惰性加载***

pass

## Scala中的面向对象
***由于语法和Java基本一样，就只说不一样的地方了***
### 伴生对象
```
“好吧，没看懂，留一下这部分”
```
### 特征（trait）
***就是Java中的接口***
1. 在使用时，同样使用extend关键字，如果需要多个特征/父类+特征，用with连接
```
没有父类：
    class 类名 extends 特质 1 with 特质 2 with 特质 3 ...
有父类：
    class 类名 extends 父类 with 特质 1 with 特质 2 with 特质 3 ...
```
注意：如果同时继承父类和特征，要 extend 父类 with 特征 关键词不能错
2. 不同特质之间可以叠加
```scala
// 第一个特质：Ball，方法describe为“ball”
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
      //先是 extend Category : my ball is a foot-ball
      //然后 with Color : my ball is a blue-foot-ball
      //因此此时的输出为“my ball is a blue-foot-ball”，
    }
  }

```
