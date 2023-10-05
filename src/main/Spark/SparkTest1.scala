import org.apache.spark.{SparkConf, SparkContext}

object SparkTest1 {
  def main(args: Array[String]): Unit = {

    val cfg = new SparkConf().setMaster("local").setAppName("wc")
    val sc = new SparkContext(cfg)
    sc.stop()
    print("关闭spark");
  }
}
