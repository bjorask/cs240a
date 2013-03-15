package invertedIndex;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import dos.*;

public class ReduceInvert extends Reducer<IntWritable, WeightWritable, IntWritable, WeightArrayWritable> {
	
	@Override
	public void reduce(IntWritable key, Iterable<WeightWritable> values, Context context) throws IOException, InterruptedException {
		ArrayList<WeightWritable> weights = new ArrayList<WeightWritable>();
		
		for (WeightWritable weight : values) {			
			weights.add(new WeightWritable(weight));
		}

		WeightArrayWritable output = new WeightArrayWritable(); 
		output.set(weights.toArray(new WeightWritable[weights.size()]));
		
		context.write(key, output);
	}
}
