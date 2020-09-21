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
    
    String[] lines = value.toString().toLowerCase().split("\n");
    String[] originalKeys = {"hackathon", "Dec", "Chicago", "Java"};
    String[] keys =  {"hackathon", "dec", "chicago", "java"};
    
    for(int i = 0 ; i < lines.length; i++){
        for(int j = 0; j < 4; j++){
            if(lines[i].contains(keys[j])){
                context.write(new Text(originalKeys[j]), new IntWritable(1));
            }
            else{
                context.write(new Text(originalKeys[j]), new IntWritable(0));
            }
        }
    }
    
  }
}