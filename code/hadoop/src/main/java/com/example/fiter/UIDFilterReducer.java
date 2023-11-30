package com.example.fiter;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @Author: zurichscud
 * @Date: 2023/11/30 14:32
 * @Description: TODO
 */
public class UIDFilterReducer extends Reducer<Text,Text,Text,Text> {
    @Override
    protected void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, Text>.Context context) throws IOException, InterruptedException {
        for (Text value : values) {
            context.write(key,value);
        }
    }
}
