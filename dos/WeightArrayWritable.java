package dos;

import java.util.ArrayList;

import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Writable;

public class WeightArrayWritable extends ArrayWritable {
	
	public WeightArrayWritable(){
		super(WeightWritable.class);
	}
	public WeightArrayWritable(WeightWritable[] values){
		super(WeightWritable.class, values);
	}
	/*public void fromList(ArrayList<WeightWritable> list){
		
		WeightWritable[] newWeightList = new WeightWritable[list.size()];
		int i = 0;
		for (WeightWritable el : list) {
			newWeightList[i++] = el;
			System.out.println(newWeightList[i-1]);
		}
		
		this.set(newWeightList);
	}*/
}
