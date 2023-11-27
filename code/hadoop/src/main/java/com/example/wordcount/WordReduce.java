package com.example.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @Author: zurichscud
 * @Date: 2023/11/27 8:56
 * @Description: TODO
 */
public class WordReduce extends Reducer<Text, IntWritable, Text,IntWritable> {
    protected void reduce(Text key,Iterable<IntWritable>values,Context context) throws IOException, InterruptedException {
        int sum=0;
        for (IntWritable value : values) {
            sum=sum+value.get();
        }
        context.write(key, new IntWritable(sum));
        System.out.println(key.toString() + ": " + sum);

    }
}
