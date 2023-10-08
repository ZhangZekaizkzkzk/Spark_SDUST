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

  def main(args: Array[String]): Unit = {
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
}
