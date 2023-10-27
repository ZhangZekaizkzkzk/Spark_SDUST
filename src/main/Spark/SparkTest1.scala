import org.apache.spark.{SparkConf, SparkContext}


object SparkTest1 {
  def main(args: Array[String]): Unit = {

        val sparkConf = new SparkConf().setAppName("WordCount").setMaster("local[*]")
        val sc = new SparkContext(sparkConf)

        val textFile = sc.textFile("data/1.txt")

        val words = textFile.flatMap(line => line.split(" ")) // 切分单词
        val wordCounts = words.map(word => (word, 1)).reduceByKey(_ + _) // 映射为 (单词, 1) 键值对并进行统计

        wordCounts.foreach(println) // 打印统计结果

        sc.stop()

  }
}
