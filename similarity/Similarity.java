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
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;

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

		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(FloatWritable.class);
		
		job.setOutputKeyClass(FloatWritable.class);
		job.setOutputValueClass(Text.class);

		job.setMapperClass(MapSimilarity.class);
		job.setReducerClass(ReduceSimilarity.class);

		job.setInputFormatClass(SequenceFileInputFormat.class);
	//	job.setOutputFormatClass(TextOutputFormat.class);
		job.setOutputFormatClass(SequenceFileOutputFormat.class);
		
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		job.waitForCompletion(true);
	}
}
