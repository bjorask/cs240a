package quality;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import dos.*;

public class ReduceQuality  extends Reducer<FloatWritable, IntWritable, FloatWritable, IntWritable> {

	public void reduce(FloatWritable key, IntWritable value, Context context) throws IOException, InterruptedException {
		context.write(key, value);
	}
}
