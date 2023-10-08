package _02trasnform

import org.apache.spark.{SparkConf, SparkContext}

object _02mapPartitions {
  def main1(args: Array[String]): Unit = {
    val SparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val sc = new SparkContext(SparkConf)
    val rdd = sc.makeRDD(List(1,2,3,4))

    val mapRDD = rdd.mapPartitions(
      data =>{
        println(">>>>>>>>>")
        data.map(_*2)
      }
    )
    mapRDD.collect().foreach(println)
    sc.stop()
  }
}
