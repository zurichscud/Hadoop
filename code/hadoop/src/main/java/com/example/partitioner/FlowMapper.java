package com.example.partitioner;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @Author: zurichscud
 * @Date: 2023/11/30 11:26
 * @Description: TODO
 */
public class FlowMapper extends Mapper<LongWritable, Text,Text, Flow> {
    private final Flow flow=new Flow();
    private final Text keyOut=new Text();
    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Flow>.Context context) throws IOException, InterruptedException {
        String[] split = value.toString().split("\t");
        String phone=split[1];

        flow.setUpFlow(Long.parseLong(split[split.length-3]));
        flow.setDownFlow(Long.parseLong(split[split.length-2]));
        keyOut.set(phone);
        context.write(keyOut,flow);
    }
}
