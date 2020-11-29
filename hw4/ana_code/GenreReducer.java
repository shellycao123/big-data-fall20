import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class GenreReducer
  extends Reducer<Text, IntWritable, Text, IntWritable> {
  
    @Override
  public void reduce(Text key, Iterable<IntWritable> values,
      Context context)
      throws IOException, InterruptedException {
    int Genre = 0;
    for (IntWritable value : values) {
      Genre++;
    }
    context.write(key, new IntWritable(Genre));
  }
}