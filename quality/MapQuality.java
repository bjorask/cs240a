package quality;

import importers.Drawing;

import java.io.IOException;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import dos.*;

public class MapQuality extends Mapper<LongWritable, Text, FloatWritable, IntWritable> {
	private static float[] weights;
	private final static FloatWritable qualityvalue = new FloatWritable();
	private final static IntWritable id = new IntWritable(1);
	
	public static final int NUM_FIELDS = 9;
	int i = 0;
	
	@Override
	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		Drawing drawingF = new Drawing();
		
		String line = value.toString();
		drawingF.getDrawing(line);

		if(drawingF.isValid){
			float quality = drawingF.getQuality();
			
			id.set(drawingF.id);
			
			if (quality > 0.05f){
				qualityvalue.set(quality);
				context.write(qualityvalue, id);
			}
		}
		if(!drawingF.isValid){
			System.out.println("INCORRECT DRAWING FORMAT DETECTED ["+(i++)+"]");
		}
	}
}
