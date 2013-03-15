package invertedIndex;
import importers.Drawing;

import java.io.IOException;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import dos.*;


public class MapInvert extends Mapper<LongWritable, Text, IntWritable, WeightWritable> {
	private static float[] weights;
	private final static IntWritable id = new IntWritable(1);
	private final static IntWritable colorid = new IntWritable(1);
	
	public static final int NUM_FIELDS = 9;
	int i = 0;
	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		Drawing drawingF = new Drawing();
		
		String line = value.toString();
		drawingF.getDrawing(line);

		if (drawingF.isValid){

			float[] weights = drawingF.getColorWeights();
	
			if(drawingF.isValid){

				id.set(drawingF.id);
				
				for (int i = 0; i < drawingF.getColorWeights().length; i++) {

					if (weights[i] > 0){
						colorid.set(i);
						context.write(colorid, new WeightWritable(drawingF.id, weights[i]));					
					}
				}
			}
		}
		
		if(!drawingF.isValid){
			System.out.println("INCORRECT DRAWING FORMAT DETECTED ["+(i++)+"]");
		}
	}
}