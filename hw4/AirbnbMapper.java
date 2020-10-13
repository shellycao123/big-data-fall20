import java.io.IOException;
import java.util.Arrays;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AirbnbMapper
  extends Mapper<LongWritable, Text, Text, IntWritable> {

  
  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
    
    String[] info = value.toString().split("\n");
    String[] row;
    for(String line : info){
      row = line.split(",");
      if(row.length == 16){
        context.write(new Text(row[4] + " "+ row[5]), new IntWritable(1));
      }
    }
    
    
  }
}