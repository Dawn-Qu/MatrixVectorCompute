import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class MyMapper extends Mapper<Object, Text,Text, IntWritable> {
    private static final int[] vector={1,2,3};
    private static int lineNum=-1;
    public void map(Object key,Text value,Context context)throws IOException,InterruptedException{
        ++lineNum;
        String line=value.toString();
        String[] nums=line.split(" ");
        int i=0;

        for(;i<nums.length;++i) {
            int result=vector[i]*Integer.parseInt(nums[i]);
            IntWritable resultWritable=new IntWritable(result);
            context.write(new Text(lineNum+""),resultWritable);
        }
    }
}