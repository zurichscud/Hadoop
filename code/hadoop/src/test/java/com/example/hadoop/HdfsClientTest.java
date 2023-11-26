package com.example.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @Author: zurichscud
 * @Date: 2023/11/26 15:41
 * @Description: TODO
 */
public class HdfsClientTest {
    @Test
    public void mkdir() throws IOException, URISyntaxException, InterruptedException {
        //获取客户端对象
        URI uri = new URI("hdfs://master:8020");
        Configuration config = new Configuration();
        FileSystem fs = FileSystem.get(uri, config,"root");
        //执行操作
        fs.mkdirs(new Path("/input/idea"));
        fs.close();
    }


}

