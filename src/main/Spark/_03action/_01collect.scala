package _03action

import org.apache.spark.{SparkConf, SparkContext}

object _01collect {

    def main(args: Array[String]): Unit = {
        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
        val sc = new SparkContext(sparkConf)

        val rdd = sc.makeRDD(List(1,2,3,4))

        // TODO - 行动算子
        // 所谓的行动算子，其实就是触发作业(Job)执行的方法
        rdd.collect()
        sc.stop()
    }
}
