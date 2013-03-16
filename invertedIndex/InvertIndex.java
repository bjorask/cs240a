package invertedIndex;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;

import dos.*;


public class InvertIndex {

	public static void printUsage() {
		System.out.println("Usage:bin/hadoop jar similarity.jar <inputDir> <outputDir>");
		System.exit(0);
	}

	public static void main(String[] args) throws Exception {
		if (args.length != 2)
			printUsage();
		Configuration conf = new Configuration();
		Job job = new Job(conf, "InvertIndex");
		job.setJarByClass(InvertIndex.class);

		job.setMapOutputKeyClass(IntWritable.class);
		job.setMapOutputValueClass(WeightWritable.class);
		
		job.setOutputKeyClass(IntWritable.class);
		job.setOutputValueClass(WeightArrayWritable.class);

		job.setMapperClass(MapInvert.class);
		job.setReducerClass(ReduceInvert.class);
		
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(SequenceFileOutputFormat.class);

		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		job.waitForCompletion(true);
	}
}
