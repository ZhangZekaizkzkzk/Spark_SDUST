package _02trasnform

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.rdd.RDD

object _21job {
  def main(args: Array[String]): Unit = {
    // 创建Spark配置和Spark上下文
    val SparkConf = new SparkConf().setMaster("local[*]").setAppName("Operator")
    val sc = new SparkContext(SparkConf)

    val logFile = "data/agent.log"
    val lines: RDD[String] = sc.textFile(logFile)

    // 读取并分割
    val rdd_split: RDD[(String, String)] = lines.map(line => {
      val words = line.split(" ")
      (words(1), words(4))
    })

    // 创建((省份, 广告) ,1)的元组，统计出现次数
    val counts: RDD[((String, String), Int)] = rdd_split.map((_, 1)).reduceByKey(_ + _)

    // 将结果按省份分组
    val groupedByProvince: RDD[(String, Iterable[((String, String), Int)])] = counts.groupBy(_._1._1)

    // 对每个省份的数据按点击数量降序排序，并获取前三名
    val topN = 3
    val topAdCountsByProvince: RDD[(String, List[((String, String), Int)])] = groupedByProvince.mapValues(iterable => {
      iterable.toList.sortBy(-_._2).take(topN)
    })

    // 打印结果
    topAdCountsByProvince.collect().foreach {
      case (province, topAds) =>
        println("省份: "+province)
        topAds.foreach {
          case ((_, ad), count) =>
            println("  广告编号: " + ad +  "点击数: " + count)
        }
    }
    topAdCountsByProvince.coalesce(1).saveAsTextFile("output")
    sc.stop()
  }
}
