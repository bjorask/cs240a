package similarity;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import dos.WeightArrayWritable;
import dos.WeightWritable;


public class MapSimilarity extends Mapper<IntWritable, WeightArrayWritable, Text, FloatWritable> {

	int outputKey1;
	int outputKey2;
	FloatWritable partialSum = new FloatWritable();
	
	@Override
	public void map(IntWritable key, WeightArrayWritable value, Context context) throws IOException, InterruptedException {

		Writable[] vector = value.get();

		for (int i = 0; i < vector.length; i++){
			for (int j = i + 1; j < vector.length; j++) {
				
			//	System.out.println(((WeightWritable)vector[i]).id + ","+((WeightWritable)vector[j]).id);
				
				if (((WeightWritable)vector[i]).id < ((WeightWritable)vector[j]).id) {
					outputKey1 = ((WeightWritable)vector[i]).id;
					outputKey2 = ((WeightWritable)vector[j]).id;
				} else {
					outputKey2 = ((WeightWritable)vector[i]).id;
					outputKey1 = ((WeightWritable)vector[j]).id;
				}
				
				partialSum.set(((WeightWritable)vector[i]).value * ((WeightWritable)vector[j]).value);
				
				//System.out.println(outputKey1+","+outputKey2+" : " + partialSum.get());
				
				context.write(new Text(outputKey1+","+outputKey2), partialSum);
			}
		}
	}
}