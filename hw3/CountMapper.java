import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CountMapper
  extends Mapper<LongWritable, Text, Text, IntWritable> {

  
  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
    
    String line = value.toString();
    String[] originalKeys = {"hackathon", "Dec", "Chicago", "Java"};
    String[] keys =  {"hackathon", "dec", "chicago", "java"};
    context.write(new Text(line), new IntWritable(1));
   /* for(int i = 0; i < 4; i++){
        if(line.contains(keys[i])){
            System.out.println(originalKeys[i]);
            context.write(new Text(originalKeys[i]), new IntWritable(1));
        }
    }*/
    
  }
}