package _02trasnform

import org.apache.spark.{SparkConf, SparkContext}

object _07filter {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val sc = new SparkContext(sparkConf)

    val rdd = sc.textFile("data/apache.log")
    val rdd_filter = rdd.map {
      line => {
        val date = line.split(" ")
        val time = date(3)
        time.startsWith("17/05/2015")
      }
    }.collect().foreach(println)
    sc.stop()
  }
}
