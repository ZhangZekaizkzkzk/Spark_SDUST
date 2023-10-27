package _02trasnform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object _16reduceByKey {

    def main(args: Array[String]): Unit = {
        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
        val sc = new SparkContext(sparkConf)

        // TODO 算子 - (Key - Value类型)
        val rdd = sc.makeRDD(List(
            ("a", 1), ("a", 2), ("a", 5), ("b", 4)
        ))

        // reduceByKey: 相同的key的数据进行value数据的聚合操作
        // scala语言中一般的聚合操作都是两两聚合，spark基于scala开发的，所以它的聚合也是两两聚合
        // 【1，2，3】
        // 【3，3】
        // 【6】
        // reduceByKey中如果key的数据只有一个，是不会参与运算的。
        val rdd1: RDD[(String, Int)] = rdd.reduceByKey( (x:Int, y:Int) => {
            println("x="+x+",y="+y)
            x + y
        } )

        rdd1.collect().foreach(println)
        sc.stop()
    }

    def main2(args: Array[String]): Unit = {
        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
        val sc = new SparkContext(sparkConf)

        // TODO 算子 - (Key - Value类型)
        val rdd = sc.makeRDD(List(
            ("a", 1), ("a", 2), ("a", 3), ("a", 4), ("a", 5), ("a", 6)
        ),3)

        val rdd1: RDD[(String, Int)] = rdd.reduceByKey((x: Int, y: Int) => {
            println("x=" + x + ",y=" + y)
            x + y
        })

        rdd1.collect().foreach(println)
        sc.stop()
    }

}
