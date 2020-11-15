import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class CountRecsReducer
  extends Reducer<Text, Text, NullWritable, IntWritable> {
  
  public void reduce(Text key, Iterable<IntWritable> values,
      Context context)
      throws IOException, InterruptedException {
    int count = 0;
    for (IntWritable value : values) {
      count++;
    }
    context.write(NullWritable.get(), new IntWritable(count));
  }
}