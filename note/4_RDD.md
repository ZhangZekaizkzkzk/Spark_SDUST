# RDD
## RDD的定义与特点 
**弹性**、**分布式**数据集（Resilient Distributed Dataset，RDD）:是Spark中最基本的数据处理模型，

特点：
1. 弹性
```
    存储弹性：内存与自盘自动切换；
    容错弹性：数据丢失自动回复；
    计算弹性：出错重试机制；
    分片弹性：可以根据需要重新分片。
```
2.  分布式
```
    储存在集群的不同的节点上.
```
3. 数据集
```
    封装了计算逻辑，并不保存数据；
    是一个抽象类，需要具体子类实现；
    不可变；
    可分区，可并行计算。
```

## 实现原理
1. 启动Yarn集群
2. Spark申请资源创建调度节点（Driver）与计算节点(Executor)
3. 调度节点根据需求将计算逻辑根据分区划分成不同任务
```
    RDD位于调度节点内，在划分时任务时对计算逻辑进行封装，并生成 Task储存在任务池内（Task Pool） 
```
4. 调度节点根据计算节点状态将Task发送到对应计算节点进行计算 

## RDD的创建与操作
### 基础设置
```scala
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val sc = new SparkContext(sparkConf)
```
### 创建RDD
1. 从内存中创建
```scala
 val rdd = sc.makeRDD(List(1,2,3,4))
 val rdd = sc.parallelize(List(1,2,3,4))
```
2. 从外部存储中创建
```scala
 val rdd = sc.textFile("data/apache.log")
```
从文件中创建时同样支持HDFS、HBase等数据源

### RDD转换算子
1. map:对数据进行逐条映射
```scala
    val rdd = sc.makeRDD(List(1,2,3,4))
    val rdd1 = rdd.map((num:Int) => num*2)
    rdd1.collect().foreach(println) //(2,4,6,8)
```
2. mapPartitions:将数据以分区为单位发送到计算节点进行处理
```scala
    val dataRDD1: RDD[Int] = dataRDD.mapPartitions(
     datas => {
     datas.filter(_==2)
     }
    )
```
3. mapPartitionsWithIndex:将数据以分区为单位发送到计算节点进行处理,并获取当前分区的索引
```scala
        val rdd:RDD[Int] = sc.makeRDD(List(1, 2, 3, 4))
        val mpiRDD = rdd.mapPartitionsWithIndex(
            (index, iter) => {
                iter.map(
                    num => {
                        (index, num)
                    }
                )
            }
        )
        mpiRDD.collect().foreach(println)
```
4. flatMap:对数据进行扁平化后再进行映射处理

扁平化：指将一个嵌套或多层次的数据结构转换为一个单层的结构，以便更容易处理或分析数据。

可以简单理解为**将多层嵌套结构转换为单层结构**。
```scala
    val rdd1 = sc.makeRDD(List(List(1, 2), 3, List(4, 5)))
    val rdd2 = rdd1.flatMap(
      data =>{
        data match {
          case x: List[Any] => x
          case x => List(x)
        } 
      }
    )
    rdd2.collect().foreach(println)// (1,2,3,4,5)
```
5. glom:将同一个分区的数据直接转换为相同类型的内存数组进行处理，分区不变

**没听懂，所以问下GPT**
```
glom函数用于将RDD的每个分区中的元素合并成一个数组。让我详细解释一下glom函数的作用以及每个步骤对数据的影响：

假设你有一个包含10个元素的RDD，它被分为3个分区。这个RDD看起来可能是这样的：

RDD: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

分区1: [1, 2, 3]
分区2: [4, 5, 6]
分区3: [7, 8, 9, 10]

调用glom()函数：

rdd.glom()

glom函数将每个分区中的元素合并为数组，生成一个新的RDD，每个分区变成了一个数组的元素：

New RDD: [[1, 2, 3], [4, 5, 6], [7, 8, 9, 10]]

在这个新的RDD中，每个元素都是一个数组，对应着原来RDD中的一个分区。原来的分区结构保留，但现在每个分区都是一个数组，这意味着你可以将分区中的数据作为一个整体进行处理。

如果你想查看或处理每个分区中的数组，你可以调用collect()函数将这个新的RDD的所有数据收集回驱动程序节点。这时，你就可以在驱动程序中访问这些数据了。

Collected Data:
Partition 0: [1, 2, 3]
Partition 1: [4, 5, 6]
Partition 2: [7, 8, 9, 10]

总结一下，glom函数的主要作用是将RDD中的分区转换为数组，从而使你能够以分区为单位对数据进行批处理操作。在上述示例中，原来的RDD包含10个元素，分为3个分区，经过glom操作后，数据仍然被分成3个分区，但每个分区现在是一个包含多个元素的数组。这可以用于某些需要以分区为单位进行处理的场景，例如批量操作或分区级别的数据保存。
```
翻译一下：将几个小数据结构绑定成一个大的数据结构，方便之后可以以小数据结构为单位对数据进行操作。
6. groupBy:分组 
```scala
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val sc = new SparkContext(sparkConf)
    val rdd = sc.makeRDD(List("Hive","Sello","Spark","Hbase","Hadoop"),2)
    val groupRDD  = rdd.groupBy(_.charAt(0))
```
7. filter:筛选
```scala
  //  筛选2015年5月17日的数据
    val rdd = sc.textFile("data/apache.log")
    val rdd_filter = rdd.map {
      line => {
        val date = line.split(" ")
        val time = date(3)
        time.startsWith("17/05/2015")
      }
    }.collect().foreach(println)
```
8. sample:随机抽取数据

sample有两种模式，放回与不放回

rdd.sample(false,0.5,1) //（不放回， 每个元素被抽中的概率为0.5，    随机数种子为1）
rdd.sample(true,2,1)    //（放回，  每个元素期望被期望抽取到的次数， 随机数种子为1）
```scala
  // 不放回
  val dataRDD1 = dataRDD.sample(false, 0.5, 1) //3,4,8,9
  // 放回
  val dataRDD2 = dataRDD.sample(true, 1, 1)  // 1,1,3,3,5,5,6,7,9
```
9. distinct:去重
```scala
  val rdd = sc.makeRDD(List(1,2,3,4,1,2,3,4))
  val rdd1: RDD[Int] = rdd.distinct()
  rdd1.collect().foreach(println) //(1,2,3,4)
```
10. coalesce:减少分区数量
```scala
    val rdd = sc.makeRDD(List(1,2,3,4,5,6),6)
    // 6个划分成6个，默认也没开启shuffle，其实就是没变 
    val rdd2 = rdd.coalesce(6)
    rdd2.saveAsTextFile("output2")
    // 6个变成三个，分别是（1, 2）、（3, 4）、（5, 6）
    val rdd3 = rdd.coalesce(3)
    rdd3.saveAsTextFile("output3")
    // 经过shuffle之后实际上变成了两个，（ ）、（1, 3, 6）、（2, 4, 5）
    val rdd4 = rdd.coalesce(3,shuffle = true)
    rdd4.saveAsTextFile("output4")
```
**这里问了一下GPT关于开不开启shuffle的区别，GPT的回答是**
```
在Apache Spark中，coalesce方法用于减少RDD的分区数量。这个方法可以选择是否开启shuffle，而开启与不开启shuffle的区别主要体现在性能和计算成本方面。

不开启shuffle：

当你调用coalesce方法并传递一个小于当前分区数量的参数时，Spark会尝试在不进行数据重新分布（shuffle）的情况下减少分区数量。这种情况下，Spark会简单地将一些分区合并到一个分区中，而不涉及数据的移动。这通常比开启shuffle的方式要快，因为不涉及数据的网络传输和重新排序。

优点：

性能较好，因为不需要数据的重新分发和排序。
计算成本相对较低。
缺点：

不能改变分区的数据分布，因为数据仍然保留在原来的分区中。
开启shuffle：

当你调用coalesce方法并传递一个大于当前分区数量的参数时，Spark会进行数据的重新分布（shuffle）。这种情况下，Spark会重新组织数据，将数据均匀地分散到新的分区中。这通常需要涉及数据的网络传输和重新排序，因此性能开销较大。

优点：

可以改变数据的分布，使数据更加均匀，有助于提高后续操作的性能。
可以增加或减少分区的数量。

缺点：
性能开销较大，因为需要数据的重新分发和排序。
计算成本相对较高。

选择是否开启shuffle取决于你的需求和性能要求。如果只是简单地减少分区数量而不需要改变数据分布，可以选择不开启shuffle。但如果需要改变数据分布或者增加分区数量以提高并行性，那么可能需要开启shuffle，尽管它会带来更大的性能开销。
```
总结 ：开启shuffle可以使数据集更均匀合理地分布，进而提升后续的计算效率，
但是由于shuffle本身需要一定的个计算资源，因此会产生更大的计算成本。