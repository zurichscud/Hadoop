package com.example.fiter;

import com.example.bean.Flow;
import com.example.writablecomparable.FlowMain;
import com.example.writablecomparable.FlowMapper;
import com.example.writablecomparable.FlowReducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * @Author: zurichscud
 * @Date: 2023/11/30 14:34
 * @Description: TODO
 */
public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
        Configuration config = new Configuration();
        Job job = Job.getInstance(config);
        job.setJarByClass(Main.class);
        job.setMapperClass(UIDFilterMapper.class);
        job.setReducerClass(UIDFilterReducer.class);
        //map输出的kv类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        //reduce的K,V
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);



        //设置输入和输出的Format路径
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        //提交job
        boolean result = job.waitForCompletion(true);
        System.exit(result?0:1);
    }
}
