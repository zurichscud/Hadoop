package com.example.writable;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @Author: zurichscud
 * @Date: 2023/11/30 11:43
 * @Description: TODO
 */
public class FlowReducer extends Reducer<Text,Flow,Text,Flow> {
    Flow flow=new Flow();
    @Override
    protected void reduce(Text key, Iterable<Flow> values, Reducer<Text, Flow, Text, Flow>.Context context) throws IOException, InterruptedException {
        long totalUpFlow = 0;
        long totalDownFlow = 0;
        for (Flow value : values) {
            totalUpFlow+=value.getUpFlow();
            totalDownFlow+=value.getDownFlow();
        }

        flow.setUpFlow(totalUpFlow);
        flow.setDownFlow(totalDownFlow);
        flow.setSumFlow();
        context.write(key,flow);


    }
}
