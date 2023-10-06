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