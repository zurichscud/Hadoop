package com.example.writablecomparable;

import com.example.bean.Flow;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.filecache.DistributedCache;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URI;

/**
 * @Author: zurichscud
 * @Date: 2023/11/30 12:08
 * @Description: 根据totalFlow 倒序
 */
public class FlowMain {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration config = new Configuration();
        Job job = Job.getInstance(config);
        job.setJarByClass(FlowMain.class);
        job.setMapperClass(FlowMapper.class);
        job.setReducerClass(FlowReducer.class);
        //map输出的kv类型
        job.setMapOutputKeyClass(Flow.class);
        job.setMapOutputValueClass(Text.class);


        //reduce的K,V
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Flow.class);

        //注册分区器
        job.setPartitionerClass(MyPartitioner.class);
        job.setNumReduceTasks(5);


        //设置输入和输出的Format路径
        FileInputFormat.setInputPaths(job, new Path("/output"));
        FileOutputFormat.setOutputPath(job, new Path("/output/step2"));
        //提交job
        boolean result = job.waitForCompletion(true);
        System.exit(result?0:1);
    }
}
