package _02trasnform

import org.apache.spark.{SparkConf, SparkContext}

object _01map {
  def main(args: Array[String]): Unit = {
    // RDD算子
    // 1.转换算子
    val SparkConf = new SparkConf().setMaster("local[*]").setAppName("RDD")
    val sc = new SparkContext(SparkConf)
    //    val rdd = sc.makeRDD(List(1,2,3,4))
    //    val rdd1 = rdd.map((num:Int) => num*2)
    //    rdd1.collect().foreach(println)
    val rdd_log = sc.textFile("src/main/Spark/apache.log", 3)
    val rdd = rdd_log.map(
      (log: String) => {
        val y = log.split(" ")
        y(6)
      }
    )
//    rdd.collect().foreach(println)
    rdd.saveAsTextFile("output")
    sc.stop()
  }
}
