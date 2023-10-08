package _02trasnform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object _05glom {

    def main(args: Array[String]): Unit = {
        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
        val sc = new SparkContext(sparkConf)

        // TODO 算子 - glom
        val rdd : RDD[Int] = sc.makeRDD(List(1,2,3,4), 2)

        // List => Int
        // Int => Array
        val glomRDD: RDD[Array[Int]] = rdd.glom()

        glomRDD.collect().foreach(arr=> println(arr.mkString(",")))
        sc.stop()
    }


    def main2(args: Array[String]): Unit = {
        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
        val sc = new SparkContext(sparkConf)

        // TODO 算子 - glom
        val rdd: RDD[Int] = sc.makeRDD(List(1, 2, 3, 4), 2)

        // 【1，2】，【3，4】
        // 【2】，【4】
        // 【6】
        val glomRDD: RDD[Array[Int]] = rdd.glom()

        val maxRDD: RDD[Int] = glomRDD.map(
            array => {
                array.max
            }
        )
//        maxRDD.foreach(println)
        println(maxRDD.collect().sum)
        sc.stop()
    }

}
