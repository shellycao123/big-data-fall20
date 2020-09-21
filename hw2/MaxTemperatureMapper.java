import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MaxTemperatureMapper
  extends Mapper<LongWritable, Text, Text, IntWritable> {

  private static final int MISSING = 9999;
  
  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
    
    String line = value.toString();
    String year = line.substring(0, 4);
    int airTemperature = MISSING;
    if (line.charAt(5) == '+') { // parseInt doesn't like leading plus signs
      airTemperature = Integer.parseInt(line.substring(5, 9));
    } else {
      airTemperature = Integer.parseInt(line.substring(4, 9));
    }
    System.out.println(airTemperature);
    String quality = line.substring(9, 10);
    if (airTemperature != MISSING && quality.matches("[01459]")) {
      context.write(new Text(year), new IntWritable(airTemperature));
    }
  }
}