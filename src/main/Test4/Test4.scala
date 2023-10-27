import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
object Test4 {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setMaster("local").setAppName("Test4")
    val spark = SparkSession.builder().config(sparkConf).getOrCreate()

    val df = spark.read
      .format("csv")
      .option("header", "true")
      .option("treatEmptyValuesAsNulls", "true")
      .option("inferSchema", "true")
      .load("实验4数据/*.csv")
    val filteredDF = df.filter(col("Rk") =!= "Rk")
    filteredDF.createOrReplaceTempView("data")
//    filteredDF.show()

//    val result1_AgeCount = spark.sql("SELECT age, count(Age) AS AgeCount  FROM data GROUP BY Age ORDER BY Age")
//    result1_AgeCount.show()
//    result1_AgeCount.coalesce(1).write.json("result/AgeCount")

//    val result2_AllCurryData = spark.sql("SELECT * FROM data WHERE Player == 'Stephen Curry' ")
//    result2_AllCurryData.show()
//    result2_AllCurryData.coalesce(1).write.json("result/AllCurryData")


    val result3_CurryRanking = spark.sql(
      "SELECT Player, ThreePointScore , Ranking " +
        "FROM ( " +
        "  SELECT Player, SUM(G * 3P * 3) AS ThreePointScore, " +
        "  DENSE_RANK() OVER (ORDER BY SUM(G * 3P * 3) DESC) AS Ranking " +
        "  FROM data GROUP BY Player " +
        ") " +
        "WHERE Player = 'Stephen Curry'"
    )
    result3_CurryRanking.show()

  }
}
