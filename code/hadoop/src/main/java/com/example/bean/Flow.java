package com.example.bean;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @Author: zurichscud
 * @Date: 2023/11/30 10:34
 * @Description: TODO
 */
public class Flow  implements WritableComparable<Flow> {
    private long upFlow;//上行流量
    private long downFlow;//下行流量
    private long sumFlow;//总流量
    public Flow() {
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(upFlow);
        dataOutput.writeLong(downFlow);
        dataOutput.writeLong(sumFlow);

    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.upFlow = dataInput.readLong();
        this.downFlow = dataInput.readLong();
        this.sumFlow = dataInput.readLong();

    }

    public long getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(long upFlow) {
        this.upFlow = upFlow;
    }

    public long getDownFlow() {
        return downFlow;
    }

    public void setDownFlow(long downFlow) {
        this.downFlow = downFlow;
    }

    public long getSumFlow() {
        return sumFlow;
    }

    public void setSumFlow() {
        this.sumFlow =this.upFlow+this.downFlow;
    }

    @Override
    public String toString() {
        return upFlow + "\t" + downFlow + "\t" + sumFlow;
    }

    @Override
    public int compareTo(Flow o) {
        //总流量的倒序
        if (this.sumFlow>o.sumFlow){
            return -1;
        } else if (this.sumFlow<o.sumFlow) {
            return 1;
        }else {
            //根据upFlow进行正序排序
            if (this.upFlow>o.upFlow){
                return -1;
            }
            else if (this.upFlow<o.upFlow){
                return 1;
            }
            else {
                return 0;
            }
        }
    }
}
