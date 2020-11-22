import java.io.IOException;
import java.util.Arrays;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.IntWritable;
import java.util.Integer;
import java.Exception;


public class YearMapper
  extends Mapper<LongWritable, Text, Text, IntWritable> {

  
  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
    try{
        String[] info = value.toString().split(",");
        int year = Integer.parseInt(info[2]);
        context.write(new Text(info[2]), new IntWritable(1));
    }
    catch(Exception e){
        context.write(new Text(info), new IntWritable(1));
    }

    
  }

}