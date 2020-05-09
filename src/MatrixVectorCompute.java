import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class MatrixVectorCompute {
    public static void main(String[] args)throws Exception{
        Configuration conf = new Configuration();

        Job job = new Job(conf, "word count11");
        job.setJarByClass(MatrixVectorCompute.class);

        job.setMapperClass(MyMapper.class);
        job.setCombinerClass(MyReducer.class);
        job.setReducerClass(MyReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.addInputPath(job, new Path("hdfs://0.0.0.0:19000/input"));
        FileOutputFormat.setOutputPath(job, new Path("hdfs://0.0.0.0:19000/output"));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }

}
