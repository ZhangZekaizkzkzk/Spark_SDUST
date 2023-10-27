package _02trasnform

import org.apache.spark.{SparkConf, SparkContext}

object _12sortBy {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val sc = new SparkContext(sparkConf)
    // 默认升序
//    val  rdd = sc.makeRDD(List(1, 4, 2, 3))
//    val rdd1 = rdd.sortBy()
    val dataRDD = sc.makeRDD(List(
      1, 2, 3, 4, 1, 2
    ), 2)
    val dataRDD1 = dataRDD.sortBy(num => num, false, 4)
    dataRDD1.collect().foreach(println)
  }
}
