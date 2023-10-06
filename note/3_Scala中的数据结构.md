# Scala中的数据结构
## Scala中数据结构的类型
Scala中的数据结构共有三大类：
1. 序列（Seq）
2. 集（Set）
3. 映射（Map）

三类数据结构均有可变与不可变两个版本
## 数组
### 不可变数组
#### 定义数组
1. 指定长度与数据类型，不赋初始值。
```scala
// 定义一个长度为10、数据类型为整形的数组
val arr1 = new Array[Int](10)
```
2. 定义时直接赋值
```scala
// 创建一个数组（1，3）
val arr2 = new Array(1,3)
```
#### 不可变数组的操作
1. 数组赋值
```scala
// 将第四个数字修改为1（这里的下标同样从0开始）
arr01(3) = 1
arr01.update(0,1)
// 打印（数组 . 以字符串的形式 . 用“,”分割）
println(arr01.mkString(","))
```
2. 数组遍历
```scala
for(i <- arr01){
  println(i)
}
```
3. 增加元素
对于*不可变数组*，增加元素的实质就是*创建新数组*
```scala
val ints: Array[Int] = arr01 :+ 5
println(ints.mkString(","))
```
### 可变数组
#### 定义数组
```scala
// 创建一个可变数组（ArrayBuffer），能够存储任意类型的数据(Any)，然后初始化进去三个值
val arr02 = ArrayBuffer[Any](1.3, 2, "zhangzekai")
// 对应的，当然也可以创建只能放Int/Double/String的数组
val temp_arr01 = ArrayBuffer[Int](1,2,3)
val temp_arr02 = ArrayBuffer[String]("a","b","zzk")
// 肯定也可以不赋初始值
val temp_arr03 = ArrayBuffer[Any]()
```
#### 可变数组的新增
```scala
// 向后追加
arr02.append(1)
arr02.+= (4) // 两个效果一样
// 指定位置插入
// insert(插入位置，插入内容)
arr02.insert(0,7,8) // 在下标为0的位置插入7，8 
// （1，2，3，4） → （7，8，1，2，3，4）
```
#### 遍历与修改
和前面一样，就不多说了
1. 遍历
```scala
for(i <- arr02){
  println(i)
}
```
2. 修改
```scala
arr02.update(0,2)
arr02(0) = 2
```
### 可变数组与不可变数组的转换
**toBuffer**与**toArray**

```scala
// 不可变 → 可变
arr = arr01.toBuffer
// 可变 → 不可变
arr = arr02.toArray
```
### 多维数组
#### 定义多维数组
```scala
// 创建一个3X4的数组
val arr03 = Array.ofDim[Int](3,4)
```

#### 操作
1. 赋值
```scala
// 这里不能用update了
arr03(1)(2) = 3 
```

2. 遍历
```scala
for (i <- arr) { //i 就是一维数组
  for (j <- i) {
    print(j + " ")
 }
}
```
这里我问了一下GPT，多维数组同样使用有可变与不可变的区分,可变数组对应的变更操作如下
```scala
// 使用ArrayBuffer创建可变的二维数组
val dynamicBuffer2D = ArrayBuffer.fill(3, 3)(0)
// 在运行时添加一行
dynamicBuffer2D += ArrayBuffer(4, 5, 6)
// 在运行时添加一列
dynamicBuffer2D.foreach(row => row += 7)
// 删除一行
dynamicBuffer2D.remove(1)
// 删除一列
dynamicBuffer2D.foreach(row => row.remove(0))
```
## 列表
***有顺序***、***可重复***
### 不可变列表：List
#### 定义列表
```scala
val list:List[Int] = List(1,2,3,4,5)
// 其实下面这样就可以，同样适用于至简原则。
val list = List(5,4,3,2,1) 
```
#### 列表增加数据
```scala
// 使用“::”合并数据
// 可以往里加数字
List1 = 5:list // （1，2，3，4，5） → （5，1，2，3，4，5）
// 也可以合并集合
list2 = list1::list // （5，1，2）+（3，4，5） → （5，1，2，3，4，5）
```
### 可变列表
基本情况和上面相同
定义时改成“ListBuffer”
```scala
val buffer = ListBuffer(1,2,3,4)
```
多了新增和修改数据的几个操作,这边和数组一样
```scala
// 增加数据
buffer += (5) // (1,2,3,4,5)
buffer.append(6) // (1,2,3,4,5,6)
// 插入数据
buffer.insert(1,2) //(1,2,2,3,4,5,6)
// 修改数据
buffer(1) = 6 // (1,6,2,3,4,5,6)
buffer(1,7) //(1,7,2,3,4,5,6)
// 删除数据
buffer.-(5) // (1,7,2,3,4,5,6)
buffer.remove(5) // (1,7,2,3,4,6)
```
## Set集合
***无序***、***不可重复***<br>
不可变用 *Set*<br>
可变要用 *mutable.Set*<br>
没其他可说的了，内容都一样

## Map集合（K-V）
不可变用 *Map*<br>
可变要用 *mutable.Map*<br>

1. 创建Map
```scala
  val map = Map( "a"->1, "b"->2, "c"->3 )
```
2. 获取Map的数据
```scala
  print(map.get("a"))
```
但是这里get返回的是一个特殊类型选项，有值返回Some,无值返回None,所以如果需要获取a的对应值需要
```scala
print(map.get("a").get)
```
如果对应的值不存在但是还是.get了，比如
```scala
print(map.get("d")) // 前面没用d这个键
```
那么会报错
```
Exception in thread "main" java.util.NoSuchElementException: None.get
```
可是我不想专门看一下d到底用没用，所以应该
```scala
println(map.getOrElse("d", 0))
```
如果找到了，打印d对应的值；如果没找到，打印0

3. 可变集合
操作差不多
```scala
 // 向集合增加数据
 map.+=("d"->4) // (a1,b2,c3,d4)
 // 将数值 4 添加到集合，并把集合中原值 1 返回
 val maybeInt: Option[Int] = map.put("a", 4) // (a4,b2,c3,d4)
 //（4）删除数据
 map.-=("b", "c") // (a4,d4)
 //（5）修改数据
 map.update("d",5) //(a4,d5)
 map("d") = 5 // 和上一行一样的功能
```
ps:这几个全有一个**update**方法，我全用这个不就好了吗...

### 元组
***最大只能有22个元素***、***不可变***
```scala
// 定义
val tuple: (Int, String, Boolean) = (40,"bobo",true)
// 获取数据
println(tuple._1)
println(tuple._2)
println(tuple._3)
// 或者
println(tuple.productElement(0))
```

## 关于遍历中的foreach方法
PDF中好像没讲，所以问GPT
```
foreach方法是用于对集合（例如数组、列表、集等）中的每个元素执行指定的操作的方法。它是一种迭代方法，通常用于遍历集合中的元素并对每个元素执行某个操作，例如打印元素、累积计算等。
foreach方法的基本语法如下：
    collection.foreach(function)
其中：
    collection是你要遍历的集合，可以是数组、列表、集等Scala集合类型。
    function是一个接受集合中元素作为参数的函数，用于定义对每个元素执行的操作。
```
然后让它举了几个例子

1. 遍历数组并求和
```scala
// 创建一个整数数组
val numbers = Array(1, 2, 3, 4, 5)

// 使用foreach方法计算数组元素的总和
var sum = 0
numbers.foreach(element => sum += element)

println(s"数组元素的总和是：$sum")
```
2. 遍历列表筛选元素
```scala
// 创建一个字符串列表
val fruits = List("apple", "banana", "cherry", "date", "elderberry")

// 使用foreach方法筛选出包含字母'a'的水果并打印
fruits.foreach {
  case fruit if fruit.contains("a") => println(fruit)
  case _ => // 忽略不包含字母'a'的元素
}
```
3. 遍历自定义对象打印信息
```scala
// 定义一个自定义的Person类
case class Person(name: String, age: Int)

// 创建一个包含Person对象的列表
val people = List(Person("Alice", 30), Person("Bob", 25), Person("Charlie", 35))

// 使用foreach方法打印每个人的信息
people.foreach(person => println(s"Name: ${person.name}, Age: ${person.age}"))
```
***谢谢GPT***