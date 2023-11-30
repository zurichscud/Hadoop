package com.example.writablecomparable;

import com.example.bean.Flow;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * @Author: zurichscud
 * @Date: 2023/11/30 10:32
 * @Description: TODO
 */
public class MyPartitioner extends Partitioner<Flow, Text> {

    @Override
    public int getPartition(Flow flow, Text text, int numPartitions) {
        String phone = text.toString();
        String prePhone = phone.substring(0, 3);
        int partition;
        switch (prePhone) {
            case "136":
                partition = 0;
                break;
            case "137":
                partition = 1;
                break;
            case "138":
                partition = 2;
                break;
            case "139":
                partition = 3;
                break;
            default:
                partition = 4;
                break;
        }
        return partition;
    }
}
