package com.example.writablecomparable;

import com.example.bean.Flow;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * @Author: zurichscud
 * @Date: 2023/11/30 11:26
 * @Description: 根据第一次MapReduce（writable）得到的结果继续进行一次MapReduce
 */
public class FlowMapper extends Mapper<LongWritable, Text,Flow, Text> {
    private final Flow flow=new Flow();
    private final Text keyOut=new Text();

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Flow, Text>.Context context) throws IOException, InterruptedException {
        String[] line = value.toString().split("\t");
        flow.setUpFlow(Long.parseLong(line[1]));
        flow.setDownFlow(Long.parseLong(line[2]));
        flow.setSumFlow();
        keyOut.set(line[0]);
        context.write(flow,keyOut);
    }
}
