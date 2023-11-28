package com.example.wordcount;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @Author: zurichscud
 * @Date: 2023/11/27 8:55
 * @Description: TODO
 */
public class WordMap extends Mapper<LongWritable, Text,Text, IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] lines = value.toString().split(" ");
        for (String word : lines) {
            context.write(new Text(word),new IntWritable(1));
        }
    }
}
