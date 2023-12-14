package com.example.search;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @Author: zurichscud
 * @Date: 2023/12/4 13:56
 * @Description: TODO
 */
public class SearchMapper extends Mapper<LongWritable, Text,Text,Text> {
    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context) throws IOException, InterruptedException {
        String[] line = value.toString().split("\t");
        if (line.length == 6){
            String uid=line[1];
            String keyword = line[2];
            if (keyword.contains("仙剑奇侠传")){
                context.write(new Text(uid), new Text(keyword));
            }
        }
    }
}
