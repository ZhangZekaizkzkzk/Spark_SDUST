package _01bulider

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object _01memory {
  def main1(args: Array[String]): Unit = {
    val SparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val sc = new SparkContext(SparkConf)
    // 创建RDD
//     val rdd1 = sc.parallelize(
//      List(1,2,3,4),
//      4
//    )
//    rdd1.collect().foreach(println)
//    val rdd2 = sc.parallelize(
//      List(1, 2, 3, 4),
//      4
//    )
//    rdd2.collect().foreach(println)
    // 从文件中读取RDD
    val rdd:RDD[String] = sc.textFile("testdata.txt",3)
    // 保存RDD中的数据
    rdd.saveAsTextFile("output")
    rdd.collect().foreach(println)
    sc.stop()
  }
}
