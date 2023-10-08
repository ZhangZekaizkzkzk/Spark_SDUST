package _02trasnform

import org.apache.spark.{SparkConf, SparkContext}

object _10coalesce {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val sc = new SparkContext(sparkConf)

    val rdd = sc.makeRDD(List(1,2,3,4,5,6),6)

    val rdd2 = rdd.coalesce(6)
    rdd2.saveAsTextFile("output2")

    val rdd3 = rdd.coalesce(3)
    rdd3.saveAsTextFile("output3")

    val rdd4 = rdd.coalesce(3,shuffle = true)
    rdd4.saveAsTextFile("output4")

  }
}
