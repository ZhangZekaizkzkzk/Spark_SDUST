package _05SparkSQL

import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.{SparkConf, SparkContext}

class _01RDD_DataFrame_DataSet {
  def main(args: Array[String]): Unit = {
    val SparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val spark = SparkSession.builder.config(SparkConf).getOrCreate()
    val rdd1 = spark.sparkContext.makeRDD(List((1, "zhangsan", 30),(2, "lisi", 40)))
//    val df1:DataFrame = rdd1.toDF()
    println()
  }
}
