package _02trasnform

import org.apache.spark.{SparkConf, SparkContext}

object _04flatMap {
  def main1(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val sc = new SparkContext(sparkConf)

    val rdd1 = sc.makeRDD(List(List(1,2), List(3,4)))
    val rdd2 = rdd1.flatMap(
      list =>{
        println(list)
        list
      }
    )
    rdd2.collect().foreach(println)
    sc.stop()
  }

  def main2(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val sc = new SparkContext(sparkConf)

    val rdd1 = sc.makeRDD(List(List(1, 2), 3, List(4, 5)))
    val rdd2 = rdd1.flatMap(
      data =>{
        data match {
          case x: List[Any] => x
          case x => List(x)
        }
      }
    )
    rdd2.collect().foreach(println)
    sc.stop()
  }

  def main(args: Array[String]): Unit = {
    // 一个GPT给的例子
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val sc = new SparkContext(sparkConf)

    // 创建一个包含多行文本的RDD
    val data = Seq("Hello world", "Spark is amazing", "RDD flatMap example")
    val rdd = sc.parallelize(data)

    // 使用flatMap将每行文本分解成单词
    val wordsRdd = rdd.flatMap(line => line.split(" "))

    // 收集结果
    val result = wordsRdd.collect().foreach(println)

    sc.stop()
  }

}
