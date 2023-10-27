package _02trasnform

import org.apache.spark.rdd.RDD
import org.apache.spark.{SparkConf, SparkContext}

object _17aggregateByKey {

    def main(args: Array[String]): Unit = {
        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
        val sc = new SparkContext(sparkConf)

        // TODO 算子 - (Key - Value类型)
        val rdd = sc.makeRDD(List(
            ("a", 1), ("a", 2), ("a", 3), ("a", 4)
        ),2)
        // (a,【1,2】), (a, 【3，4】)
        // (a, 2), (a, 4)
        // (a, 6)

        // aggregateByKey存在函数柯里化，有两个参数列表
        // 第一个参数列表,需要传递一个参数，表示为初始值
        //       主要用于当碰见第一个key的时候，和value进行分区内计算
        // 第二个参数列表需要传递2个参数
        //      第一个参数表示分区内计算规则
        //      第二个参数表示分区间计算规则

        // math.min(x, y)
        // math.max(x, y)
        rdd.aggregateByKey(0)(
            (x, y) => math.max(x, y),
            (x, y) => x + y
        ).collect.foreach(println)
        sc.stop()
    }


    def main2(args: Array[String]): Unit = {
        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
        val sc = new SparkContext(sparkConf)

        // TODO 算子 - (Key - Value类型)
        val rdd = sc.makeRDD(List(
            ("a", 1), ("a", 2), ("b", 3),
            ("b", 4), ("b", 5), ("a", 6)
        ), 2)

        rdd.aggregateByKey(0)(
            (x, y) => math.max(x, y),
            (x, y) => x + y
        ).collect.foreach(println)

        rdd.aggregateByKey(5)(
            (x, y) => math.max(x, y),
            (x, y) => x + y
        ).collect.foreach(println)

//        rdd.aggregateByKey(0)(
//            (x, y) => x + y,
//            (x, y) => x + y
//        ).collect.foreach(println)

//        rdd.aggregateByKey(0)(_ + _, _ + _).collect.foreach(println)

        sc.stop()
    }


    def main3(args: Array[String]): Unit = {
        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
        val sc = new SparkContext(sparkConf)

        // TODO 算子 - (Key - Value类型)
        val rdd = sc.makeRDD(List(
            ("a", 1), ("a", 2), ("b", 3),
            ("b", 4), ("b", 5), ("a", 6)
        ), 2)

        //rdd.aggregateByKey(0)(_+_, _+_).collect.foreach(println)
        // 如果聚合计算时，分区内和分区间计算规则相同，spark提供了简化的方法
        rdd.foldByKey(0)(_ + _).collect.foreach(println)
        sc.stop()
    }


    def main4(args: Array[String]): Unit = {
        val sparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
        val sc = new SparkContext(sparkConf)

        // TODO 算子 - (Key - Value类型)
        val rdd = sc.makeRDD(List(
            ("a", 1), ("a", 2), ("b", 3),
            ("b", 4), ("b", 5), ("a", 6)
        ), 2)

        // aggregateByKey最终的返回数据结果应该和初始值的类型保持一致
        //val aggRDD: RDD[(String, String)] = rdd.aggregateByKey("")(_ + _, _ + _)
        //aggRDD.collect.foreach(println)

        // 获取相同key的数据的平均值 => (a, 3),(b, 4)
        // (t, v)，t表示数量综合，v表示出现的总次数
        val newRDD: RDD[(String, (Int, Int))] = rdd.aggregateByKey((0, 0))(
            (t, v) => {
                (t._1 + v, t._2 + 1)
            },
            (t1, t2) => {
                (t1._1 + t2._1, t1._2 + t2._2)
            }
        )

        val resultRDD: RDD[(String, Int)] = newRDD.mapValues {
            case (num, cnt) => {
                num / cnt
            }
        }
        resultRDD.collect().foreach(println)
        sc.stop()
    }
}
