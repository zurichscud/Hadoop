

# HDFS概述

## 概述

Hadoop Distributed File System，Hadoop分布式文件系统。HDFS用于解决数据的海量存储问题

HFDS适合一次写入，多次读出的场景。一个文件经过HDFS存储后，就无法对其在进行修改，但是可以在末尾进行追加数据。

## HDFS优点

- 高容错性

数据自动保存多个副本，他通过增加副本的形式，提高容错率

当某一个副本丢失以后，它会从其他副本复制进行自动修复

<img src="assets/image-20231125173532326.png" alt="image-20231125173532326" style="zoom:50%;" />

- 适合处理大数据

数据规模：能够处理PB级别的数据

文件规模：能够处理百万规模以上的文件数量

- 由于hafs的副本机制，其可以部署在廉价服务器上，因为服务器的稳定性要求不高

## HDFS缺点

- 不适合低延时的数据访问，无法做到毫秒级别的读取数据
- 无法高效地对大量小文件进行存储。小文件的存储会占用namenode大量的存储来存储元数据，namenode的内存总是有限的
- 不支持并发的读写，一个文件只能有一个写，不允许多个线程同时写
- 仅支持数据的append操作，不支持文件的随机修改

## HDFS组成

<img src="assets/image-20231125175815010.png" alt="image-20231125175815010" style="zoom:50%;" />

### NameNode

Master，HDFS的管理者。他负责：

- 管理HDFS的名称空间
- 配置副本策略
- 管理block的映射信息
- 处理客户端（web、CLI）的读写请求



### DataNode

NameNode下达命令，DataNode执行实际的操作

- 负责存储实际的数据块
- 执行数据块的读/写操作

### Client

客户端

- 文件上传至HDFS的时候，Client将文件切分成一个个的block，然后进行上传
- 客户端可以从namenode处获得文件的位置信息
- 客户端与datanode交互，执行读写操作
- 客户端供用户输入命令对HDFS进行管理

### SecondaryNameNode

NameNode的冷备份，当NameNode挂掉后，并不能替换NameNode提供服务

他只能辅助NameNode，分担其工作量。在紧急情况下，辅助NameNode恢复有限的数据

## Block

数据在HDFS中以块的形式存储，一个数据可以被分成多个块，如果没有达到一个块大小，其就占用一个块。

在Hadoop 1中 Block的默认大小为64MB，Hadoop2/3提升到了128MB

>  为什么块大小默认设置成128MB？
>
> 寻址的最佳时间为10ms，寻址时间为传输时间的1%时为最佳状态，因此数据传输的时间应该为1s
>
> 而目前磁盘的传输速率普遍在100MB/s
>
> 则一个block大小应该为100MB，最终选择128MB



HDFS的块设置过小会造成寻址时间的增加。HDFS的块设置过大，会导致分布式计算停滞在某一个块

HDFS的大小主要取决于磁盘的传输速度

# Shell

将hadoop fs 设置别名：hdfs

```sh
alias hdfs='hadoop fs'
```

## 上传

```sh
hdfs -moveFromLocal  loaclpath hdfspath
```



```sh
hdfs -copyFromLocal  loaclpath hdfspath
```

```sh
hdfs -put loaclpath hdfspath
```



## 下载

```sh
hdfs -get  hdfspath loaclpath
```



## CRUD



```sh
hdfs -ls  hdfspath
```

```sh
hdfs -cat hdfspath
```



# API



# NN和2NN



# DataNode



