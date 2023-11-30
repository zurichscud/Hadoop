package com.example.writablecomparable;

import com.example.bean.Flow;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @Author: zurichscud
 * @Date: 2023/11/30 17:36
 * @Description: TODO
 */
public class WordCombiner extends Reducer<Text, IntWritable,Text,IntWritable> {
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
        int sum=0;
        for (IntWritable value : values) {
            sum+=value.get();
        }
        IntWritable res = new IntWritable();
        res.set(sum);
        context.write(key,res);
    }
}
