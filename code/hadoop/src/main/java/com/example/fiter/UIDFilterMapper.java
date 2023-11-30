package com.example.fiter;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @Author: zurichscud
 * @Date: 2023/11/30 14:09
 * @Description: TODO
 */
public class UIDFilterMapper extends Mapper<LongWritable, Text,Text,Text> {
    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context) throws IOException, InterruptedException {
        String[] line = value.toString().split("\t");
        String uid= line[1];
        String keyword = line[2];
        if (keyword.matches(".*\\d+.*")){
            context.write(new Text(uid),new Text(keyword));
        }
    }
}
