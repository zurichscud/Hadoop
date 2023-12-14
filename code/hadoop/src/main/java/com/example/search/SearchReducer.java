package com.example.search;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @Author: zurichscud
 * @Date: 2023/12/4 13:56
 * @Description: TODO
 */
public class SearchReducer extends Reducer<Text,Text,Text, IntWritable> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, IntWritable>.Context context) throws IOException, InterruptedException {
        int sum = 0;
        for(Text keyword: values){
            sum++;
        }
        context.write(key, new IntWritable(sum));

    }
}
