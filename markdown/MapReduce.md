# MapReduce

## MapReduce定义

MapReduce是一个分布式运算程序的编程框架，是用户开发“基于Hadoop的数据分析应用”的核心框架。

MapReduce核心功能是将用户编写的业务逻辑代码和自带默认组件整合成一个完整的分布式运算程序，并发运行在一个Hadoop集群上。

## MapReduce优点

- MapReduce易于编程

它简单的实现一些接口，就可以完成一个分布式程序，这个分布式程序可以分布到大量廉价的PC机器上运行。也就是说你写一个分布式程序，跟写一个简单的串行程序是一模一样的。就是因为这个特点使得MapReduce编程变得非常流行。

- 良好的扩展性

当你的计算资源不能得到满足的时候，你可以通过简单的增加机器来扩展它的计算能力。

- 高容错性

MapReduce设计的初衷就是使程序能够部署在廉价的PC机器上，这就要求它具有很高的容错性。比如其中一台机器挂了，它可以把上面的计算任务转移到另外一个节点上运行，不至于这个任务运行失败，而且这个过程不需要人工参与，而完全是由Hadoop内部完成的。

- 适合PB级以上海量数据的离线处理

可以实现上千台服务器集群并发工作，提供数据处理能力。



## MapReduce 缺点

- 不擅长实时计算

MapReduce无法像MySQL一样，在毫秒或者秒级内返回结果。

- 不擅长流式计算

流式计算的输入数据是动态的，而MapReduce的输入数据集是静态的，不能动态变化。这是因为MapReduce自身的设计特点决定了数据源必须是静态的。常用*Flask*

- 不擅长DAG（有向无环图）计算

![image-20231127204136611](assets/image-20231127204136611.png)

多个应用程序存在依赖关系，后一个应用程序的输入为前一个的输出。在这种情况下，每个MapReduce作业的输出结果都会写入到磁盘，会造成大量的磁盘IO，导致性能非常的低下。常用*Flask*

## MapReduce 核心思想

以wordcount为例

统计一个文件中的单词出现次数，且a-p开头的字母一个文件，q-z开头的字母一个文件

![image-20231127205410483](assets/image-20231127205410483.png)

分布式计算需要2个阶段：

- *Map*

映射，Map阶段会并发MapTask，互不影响

- *Reduce*

聚合，ReduceTask是并发执行的，他的数据依赖于上一个阶段所有MapTask的结果

- *split*

切片，一个split对应一个mapTask，默认一个切片的Szie与HDFS的blockSize相同，可以根据实际情况设置split的大小。不同数量的split将影响MapTask的数量



## MapReduce进程

一个完整的**MapReduce**程序在分布式运行时有三类实例进程：

- **MrAppMaster**：负责整个程序的过程调度及状态协调。（Mr是MapReduce的缩写，也称*Job*）
- **MapTask**：负责Map阶段的整个数据处理流程。
- **ReduceTask**：负责Reduce阶段的整个数据处理流程。

一个MapReduce编程模型只能包含一个Map阶段和Reduce阶段。如果用户的业务非常复杂，需要编写多个MapReduce程序

## Hdoop数据类型

Hadoop中使用的是自己的数据类型，与Java中的类型的关系：

| **Java****类型** | **Hadoop Writable****类型** |
| ---------------- | --------------------------- |
| Boolean          | BooleanWritable             |
| Byte             | ByteWritable                |
| Int              | IntWritable                 |
| Float            | FloatWritable               |
| Long             | LongWritable                |
| Double           | DoubleWritable              |
| String           | Text                        |
| Map              | MapWritable                 |
| Array            | ArrayWritable               |
| Null             | NullWritable                |

## MapReduce 编程规范

