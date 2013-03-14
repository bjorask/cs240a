package similarity;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;

public class Similarity {

	public static void printUsage() {
		System.out.println("Usage:bin/hadoop jar similarity.jar <inputDir> <outputDir>");
		System.exit(0);
	}

	public static void main(String[] args) throws Exception {
		if (args.length != 2)
			printUsage();
		Configuration conf = new Configuration();
		Job job = new Job(conf, "Similarity");
		job.setJarByClass(Similarity.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(FloatWritable.class);

		job.setMapperClass(MapSimilarity.class);
		job.setReducerClass(ReduceSimilarity.class);

		job.setInputFormatClass(SequenceFileInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		job.waitForCompletion(true);
	}
}
