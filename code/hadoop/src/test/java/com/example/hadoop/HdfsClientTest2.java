package com.example.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

/**
 * @Author: zurichscud
 * @Date: 2023/11/26 15:44
 * @Description: TODO
 */
public class HdfsClientTest2 {

    private FileSystem fs;
    @Before
    public void init() throws URISyntaxException, IOException, InterruptedException {
        //获取客户端对象
        URI uri = new URI("hdfs://centos:8020");
        Configuration config = new Configuration();
        config.set("dfs.replication","10");
        config.set("dfs.client.use.datanode.hostname", "true");
        fs = FileSystem.get(uri, config,"root");
        System.out.println("获取客户端");

    }
    @After
    public void close() throws IOException {
        fs.close();
    }

    @Test
    public void mkdir() throws IOException {
        //执行操作
        fs.mkdirs(new Path("/input/idea"));
    }

    @Test
    public void put() throws IOException {
        /**
         * @Description:
         * @Param:
         * @Return:
         **/
        fs.copyFromLocalFile(false,true,new Path("E:\\Download\\hadoop-3.2.4.tar.gz"),new Path("/input/idea"));
        System.out.println("Put Completed");
    }

    @Test
    public void get() throws IOException {
        fs.copyToLocalFile(false,new Path("/input/idea/hello.txt"),new Path("./get"),true);
        System.out.println("Get Completed");
    }

    @Test
    public void delete() throws IOException {
        fs.delete(new Path("/input/idea/hello.txt"),false);
        System.out.println("Delete Completed");
    }

    @Test
    public void move() throws IOException {
        fs.rename(new Path("/input/demo"),new Path("/input/demo2"));
        System.out.println("Rename Completed");

    }

    @Test
    public void getInfo() throws IOException {
        //获取迭代器
        RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), true);
        //遍历
        while (listFiles.hasNext()){
            LocatedFileStatus fileStatus=listFiles.next();
            System.out.println(fileStatus.getPath());
            System.out.println(fileStatus.getPermission());
            System.out.println(fileStatus.getOwner());
            System.out.println(fileStatus.getLen());
            System.out.println(fileStatus.getModificationTime());
            System.out.println(fileStatus.getReplication());
            System.out.println(fileStatus.getBlockSize());
            System.out.println(fileStatus.getPath().getName());
            System.out.println(Arrays.toString(fileStatus.getBlockLocations()));

        }
    }

    @Test
    public void listStatus() throws IOException {
        FileStatus[] fileStatuses = fs.listStatus(new Path("/input/idea"));
        for (FileStatus status : fileStatuses) {
            System.out.println(status.getPath());
            System.out.println("是否目录"+status.isDirectory());
            System.out.println("是否文件"+status.isFile());
        }

    }
}
