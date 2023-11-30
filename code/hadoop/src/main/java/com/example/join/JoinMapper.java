package com.example.join;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;

import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zurichscud
 * @Date: 2023/11/30 20:39
 * @Description: TODO
 */
public class JoinMapper extends Mapper<LongWritable, Text,Text, NullWritable> {
    private Map<String, String> pdMap = new HashMap<>();
    private final Text text = new Text();

    @Override
    protected void setup(Mapper<LongWritable, Text, Text, NullWritable>.Context context) throws IOException, InterruptedException {
        URI[] cacheFiles = context.getCacheFiles();
        Path path = Paths.get(cacheFiles[0]);


        //获取文件系统对象,并开流
//        FileSystem fs = FileSystem.get(context.getConfiguration());
//        FSDataInputStream fis = fs.open(path);

//        //通过包装流转换为reader,方便按行读取
//        BufferedReader reader = new BufferedReader(new InputStreamReader(fis, StandardCharsets.UTF_8));
//
//        //逐行读取，按行处理
//        String line;
//        while (StringUtils.isNotEmpty(line = reader.readLine())) {
//            //切割一行
//            String[] split = line.split("\t");
//            pdMap.put(split[0], split[1]);
//        }
//        //关流
//        IOUtils.closeStream(reader);
        // 使用新的文件读取方式
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            // 逐行读取，按行处理
            String line;
            while ((line = reader.readLine()) != null) {
                // 切割一行
                String[] split = line.split("\t");
                pdMap.put(split[0], split[1]);
            }
        }
    }

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, NullWritable>.Context context) throws IOException, InterruptedException {
        String[] line = value.toString().split("\t");
        String pName = pdMap.get(line[1]);
        text.set(line[0]+"\t"+pName+"\t"+line[2]);
        context.write(text,NullWritable.get());

    }
}
