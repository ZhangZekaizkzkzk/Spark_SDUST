package _02trasnform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object _03mapPartitionsWithIndex {

    def main1(args: Array[String]): Unit = {
        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
        val sc = new SparkContext(sparkConf)

        // TODO 算子 - mapPartitions
        // TODO 只用第2个分区的数据
        val rdd:RDD[Int] = sc.makeRDD(List(1,2,3,4), 2)
        //    取第2个分区的数据
        val mpiRDD = rdd.mapPartitionsWithIndex(
            (index, iter) => {
                if ( index == 1 ) {
                    iter
                } else {
                    Nil.iterator
                }
            }
        )
        mpiRDD.collect().foreach(println)
        sc.stop()
    }


    def main(args: Array[String]): Unit = {
        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
        val sc = new SparkContext(sparkConf)

        // TODO 算子 - mapPartitions
        // TODO 显示每条数据所在的分区
        val rdd:RDD[Int] = sc.makeRDD(List(1, 2, 3, 4))

        val mpiRDD = rdd.mapPartitionsWithIndex(
            (index, iter) => {
                iter.map(
                    num => {
                        (index, num)
                    }
                )
            }
        )
        mpiRDD.collect().foreach(println)
        sc.stop()
    }
}
