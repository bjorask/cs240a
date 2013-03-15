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

public class MapSort extends Mapper<FloatWritable, Text, FloatWritable, Text> {

	public void map(FloatWritable key, Text value, Context context) throws IOException, InterruptedException {
		key.set(-1*key.get());
		context.write(key, value);
	}
}
