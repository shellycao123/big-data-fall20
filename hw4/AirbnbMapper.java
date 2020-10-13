import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AirbnbMapper
  extends Mapper<LongWritable, Text, Text, IntWritable> {

  
  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
    
    String[] lines = value.toString().split("\n");
    String[] info;
    for(int i = 1 ; i < lines.length; i++){
        info = lines[i].split(",");
        context.write(new Text(info[4] + " "+info[5]), new IntWritable(1));
    }
    
  }
}