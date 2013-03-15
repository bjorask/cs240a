package similarity.sort;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import dos.WeightArrayWritable;
import dos.WeightWritable;

public class MapSort extends Mapper<Text, FloatWritable, FloatWritable, Text> {

	public void map(Text value, FloatWritable key, Context context) throws IOException, InterruptedException {
		context.write(key, value);
	}
}
