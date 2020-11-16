import java.io.IOException;
import java.util.Arrays;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Mapper;

public class CleanMapper
  extends Mapper<LongWritable, Text, Text, Text> {

  
  @Override
  public void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
    
    String[] row = value.toString().split(",");
    //irregular data
    if(row.length != 52){
      return;
    }
    int year = parseInt(row[2]);
    if( year <= 1900 || year > 2020){
      return;
    }

    //parse
    StringBuilder sb  = new StringBuilder();
    sb.append(row[0]);
    sb.append(',');
    if(row[1].length() == 0){
      sb.append("Movie");
    }
    else{
      sb.append(row[1]);
    }
    sb.append(',');
    sb.append(row[2]);
    sb.append(",\"");
    String[] list = {"Action", "Animation","Anime", "Biography", "Children", "Comedy", 
    "Crime", "Cult", "Documentary", "Drama", "Family", "Fantasy", "Food", "Game Show", 
    "History", "Home Garden", "Horror", "Independent", "LGBTQ", "Musical", "Mistery", 
    "Reality", "Romance", "Science Fiction", "Sport", "Standup Talk", "Thriller", "Travel"};

    int index = 8;
    for(int i = 0 ; i < list.length; i++){
      if(parseInt(row[i + 8]) == 1){
        sb.append(list[i]);
        sb.append(' ');
      }
    }
    sb.append("\"");

    context.write(new Text(row[0]), new Text(sb.toString()));
    
    
  }

  public int parseInt(String s){
    char[] chs = s.toCharArray();
    int cur = 0;
    for(char c : chs){
      if(!isInt(c)){
        return -1;
      }
      cur  = cur * 10 + (c - '0');
    }
    return cur; 
  }

  public boolean isInt(char c){
    return c - '0' >= 0 && c - '0' <= 9;
  }
}