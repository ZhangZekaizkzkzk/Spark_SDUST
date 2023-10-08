package _02trasnform

import org.apache.spark.{SparkConf, SparkContext}

import java.text.SimpleDateFormat

object _06groupBy {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val sc = new SparkContext(sparkConf)
    val rdd = sc.makeRDD(List("Hive","Sello","Spark","Hbase","Hadoop"),2)
    val groupRDD  = rdd.groupBy(_.charAt(0))
    rdd.saveAsTextFile("output0")
    groupRDD.saveAsTextFile("output1")
  }

  def main2(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val sc = new SparkContext(sparkConf)

    val rdd = sc.textFile("data/apache.log")
    val time_rdd = rdd.map(
      line => {
        val datalist = line.split(" ")
        val time = datalist(3)
        val sdf = new SimpleDateFormat("dd/MM/yyyy:HH:mm:ss")
        val date = sdf.parse(time)
        val sdf1 = new SimpleDateFormat("HH")
        val hour: String = sdf1.format(date)
        (hour, 1)
      }
    ).groupBy(_._1).sortBy(_._1)

    time_rdd.map {
      case (hour, iter) => {
        (hour, iter.size)
      }
    }.collect().foreach(println)
    sc.stop()
  }

}
