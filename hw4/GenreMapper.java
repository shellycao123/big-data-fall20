import java.io.IOException;
import java.util.Arrays;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.io.IntWritable;


public class GenreMapper
  extends Mapper<LongWritable, Text, Text, IntWritable> {

  
  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
    String[] info = value.toString().split(",")[3].split("[\",]");
    for(String genre : info){
        if(genre.length() != 0){
            context.write(new Text(genre), new IntWritable(1));
        }
    }

    
  }

}