package fr.epita.titanic.exercise;

import org.apache.spark.ml.classification.DecisionTreeClassificationModel;
import org.apache.spark.ml.classification.DecisionTreeClassifier;
import org.apache.spark.ml.feature.StringIndexer;
import org.apache.spark.ml.feature.VectorAssembler;
import org.apache.spark.mllib.evaluation.MulticlassMetrics;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;
import org.apache.spark.sql.types.DataType;
import org.apache.spark.sql.types.DataTypes;

public class Launcher {

	private static final String SURVIVED = "Survived";

	public static void main(String[] args) {
		SparkSession session = SparkSession.builder().config("spark.master", "local").config("spark.ui.enabled", false)
				.appName("titanic").getOrCreate();

		Dataset<Row> df = session.read().format("csv").option("header", "true").option("inferSchema", "true")
				.csv("data/input.csv");

		session.sparkContext().setLogLevel("ERROR");

		df.show();

		df.describe().show();

		df = df.na().drop();

		df.describe().show();

		Dataset<Row> counts = df.sort(functions.asc(SURVIVED)).groupBy(SURVIVED).agg(functions.count("*"));

		counts.show();

		System.out.println("before Sex indexing");

		StringIndexer sexIndexer = new StringIndexer();
		df = sexIndexer.setInputCol("Sex").setOutputCol("indexedSex").fit(df).transform(df);

		System.out.println("result of sex indexing : ");
		df.show();

		Dataset<Row>[] trainTestSplit = df.randomSplit(new double[] { 0.8, 0.2 });

		Dataset<Row> trainDf = trainTestSplit[0];
		Dataset<Row> testDf = trainTestSplit[1];

		trainDf.show();

		String featuresColumn = "features";

		VectorAssembler vectorAssembler = new VectorAssembler().setInputCols(new String[] { "Pclass", "Age",
				"Siblings/Spouses Aboard", "Parents/Children Aboard", "Fare", "indexedSex" })
				.setOutputCol(featuresColumn);
		trainDf = vectorAssembler.transform(trainDf);
		testDf = vectorAssembler.transform(testDf);

		trainDf.show();

		DecisionTreeClassifier decisionTree = new DecisionTreeClassifier().setLabelCol(SURVIVED)
				.setPredictionCol("prediction").setFeaturesCol(featuresColumn);

		DecisionTreeClassificationModel model = decisionTree.fit(trainDf);
		Dataset<Row> predicted = model.transform(testDf);

		System.out.println("testDf count = " + testDf.count());

		Dataset<Row> truthVsPrediction = predicted.select("Survived", "prediction");

		System.out.println("truth vs predictions :");
		truthVsPrediction.show();
		truthVsPrediction = truthVsPrediction
				.withColumn("Survived_temp", truthVsPrediction.col(SURVIVED).cast(DataTypes.DoubleType)).drop(SURVIVED)
				.withColumnRenamed("Survived_temp", SURVIVED);

		MulticlassMetrics metrics = new MulticlassMetrics(truthVsPrediction);

		System.out.println("accuracy : " + metrics.accuracy());

		System.out.println(metrics.confusionMatrix().toString());

		double[] doubleArray = model.featureImportances().toArray();
		for (double d : doubleArray) {
			System.out.println(d);
		}

	}

}
