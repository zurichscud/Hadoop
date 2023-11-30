package com.example.partitioner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @Author: zurichscud
 * @Date: 2023/11/30 12:08
 * @Description: TODO
 */
public class FlowMain {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration config = new Configuration();
        Job job = Job.getInstance(config);
        job.setJarByClass(FlowMain.class);
        job.setMapperClass(FlowMapper.class);
        job.setReducerClass(FlowReducer.class);
        //map输出的kv类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Flow.class);

        //reduce的K,V
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Flow.class);

        //注册partitioner
        job.setPartitionerClass(MyPartitioner.class);
        job.setNumReduceTasks(5);

        //设置输入和输出的Format路径
        FileInputFormat.setInputPaths(job, new Path("/usr/phone_data.txt"));
        FileOutputFormat.setOutputPath(job, new Path("/output"));
        //提交job
        boolean result = job.waitForCompletion(true);
        System.exit(result?0:1);
    }
}
