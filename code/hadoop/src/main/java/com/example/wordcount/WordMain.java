package com.example.wordcount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;


/**
 * @Author: zurichscud
 * @Date: 2023/11/27 8:46
 * @Description: TODO
 */
public class WordMain {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {

        Configuration config = new Configuration();
        Job job = Job.getInstance(config);
        job.setJarByClass(WordMain.class);
        job.setMapperClass(WordMap.class);
        job.setReducerClass(WordReduce.class);
        //map输出的kv类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //reduce的K,V
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        //设置输入和输出的Format路径
        FileInputFormat.setInputPaths(job, new Path("/usr/hello.txt"));
        FileOutputFormat.setOutputPath(job, new Path("/output"));
        //提交job
        boolean result = job.waitForCompletion(true);
        System.exit(result?0:1);
    }
}
