package similarity.sort;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import dos.*;

public class ReduceSort extends Reducer<FloatWritable, Text, Text, FloatWritable> {
	int i = 0;

	public void reduce(FloatWritable value, Text key, Context context) throws IOException, InterruptedException {
		if(i < 30)	//Print top 10
			value.set(-1*value.get());
			context.write(key, value);
			i++;
	}
}
