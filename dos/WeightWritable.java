package dos;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class WeightWritable implements Writable {
	public int id;
	public float value;
	
	public void write (DataOutput out) throws IOException{
		out.writeInt(id);
		out.writeFloat(value);
	}
	
	public void readFields(DataInput in) throws IOException{
		id = in.readInt();
		value = in.readFloat();
	}
	
	public WeightWritable(int id, float value){
		this.id = id;
		this.value = value;
	}
	
	public WeightWritable(){
		
	}
	public WeightWritable(WeightWritable weight){
		this.id = weight.id;
		this.value = weight.value;
	}
	
	public WeightWritable read(DataInput in) throws IOException{
		WeightWritable v = new WeightWritable();
		v.readFields(in);
		return v;
	}
	
	@Override
	public String toString(){
		return "WeightWritable: " + id+","+value;
	}
}
