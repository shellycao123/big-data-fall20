import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class CleanReducer
  extends Reducer<Text, Text, NullWritable, Text> {
  
  @Override
  public void reduce(Text key, Iterable<Text> values,
      Context context)
      throws IOException, InterruptedException {
    String result;
    for (Text value : values) {
      result = value.toString();
    }
    context.write(NullWritable.get(), new Text(result));
  }
}