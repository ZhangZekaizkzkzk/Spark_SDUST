package _02trasnform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object _15groupByKey {

    def main(args: Array[String]): Unit = {
        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
        val sc = new SparkContext(sparkConf)

        // TODO 算子 - (Key - Value类型)
        val rdd = sc.makeRDD(List(
            ("a", 1), ("a", 2), ("a", 3), ("b", 4)
        ))

        // groupByKey : 将数据源中的数据，相同key的数据分在一个组中，形成一个对偶元组
        //              元组中的第一个元素就是key，
        //              元组中的第二个元素就是相同key的value的集合
        val rdd1: RDD[(String, Iterable[Int])] = rdd.groupByKey()
        rdd1.collect().foreach(println)

//        // groupBy，注意区分groupByKey
//        val rdd2: RDD[(String, Iterable[(String, Int)])] = rdd.groupBy(_._1)
//        rdd2.collect().foreach(println)

        sc.stop()
    }
}
