import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class wordCount{
    public static void main(String[] args){
        try{
            Scanner scn = new Scanner(new File(args[0]));
            new File("output.txt");
            FileWriter writer = new FileWriter("output.txt");
            String[] originalKeys = {"hackathon", "Dec", "Chicago", "Java"};
            String[] keys =  {"hackathon", "dec", "chicago", "java"};
            int[] counts = new int[4];
    
            String cur; 
            while(scn.hasNextLine()){
                cur = scn.nextLine().toLowerCase();
                for(int i = 0 ; i < 4; i++){
                    if(cur.contains(keys[i])){
                        counts[i]++;
                    }
                }
            }
            for(int i = 0 ; i < 4; i++){
                writer.write(originalKeys[i] + " "+ counts[i]+"\n");
            }
            writer.close();
            scn.close();
        }
        catch(IOException e){
            System.out.println(e);
            System.exit(-1);
        }

    }
}