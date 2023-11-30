package com.example.writablecomparable;


import com.example.bean.Flow;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @Author: zurichscud
 * @Date: 2023/11/30 11:43
 * @Description: TODO
 */
public class FlowReducer extends Reducer<Flow, Text,Text, Flow> {

    @Override
    protected void reduce(Flow key, Iterable<Text> values, Reducer<Flow, Text, Text, Flow>.Context context) throws IOException, InterruptedException {
        for (Text value : values) {
            context.write(value,key);
        }
    }
}
