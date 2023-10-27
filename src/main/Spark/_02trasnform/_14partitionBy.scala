package _02trasnform

import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

object _14partitionBy {

    def main(args: Array[String]): Unit = {
        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
        val sc = new SparkContext(sparkConf)

        // TODO 算子 - (Key - Value类型)
        val rdd = sc.makeRDD(List(1,2,3,4),2)
        var rdd1: RDD[(Int, Int)] = rdd.map((_, 1))
        val rdd2 = sc.makeRDD(List((1,1),(2,1),(3,1),(4,1)),2)

        // partitionBy根据指定的分区规则对数据进行重分区
        // rdd.partitionBy(new HashPartitioner(2))，报错，因为partitionBy算子只适用于（k，v）
        var rdd3: RDD[(Int, Int)] = rdd2.partitionBy(new HashPartitioner(2))
        rdd3.partitionBy(new HashPartitioner(2))
        rdd3.saveAsTextFile("output")
        sc.stop()
    }

}
