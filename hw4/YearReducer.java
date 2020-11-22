import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class YearReducer
  extends Reducer<Text, IntWritable, Text, IntWritable> {
  
    @Override
  public void reduce(Text key, Iterable<IntWritable> values,
      Context context)
      throws IOException, InterruptedException {
    int Year = 0;
    for (IntWritable value : values) {
      Year++;
    }
    context.write(key, new IntWritable(Year));
  }
}