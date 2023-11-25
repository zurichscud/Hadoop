# 安装JDK

hadoop-env.sh

```bash
export JAVA_HOME=/usr/local/java/jdk1.8.0_351
```



# 安装 Hadoop





# 配置环境变量

当在命令行输入hadoop出现信息，表示hadoop安装成功

```bash
hadoop
```



# SSH配置



# Hadoop其他

## hadoop 目录结构

- bin：存放对Hadoop相关服务（hdfs，yarn，mapred）进行操作的脚本
- etc：Hadoop的配置文件目录，存放Hadoop的配置文件
- lib：存放Hadoop的本地库（对数据进行压缩解压缩功能）
- sbin：存放启动或停止Hadoop相关服务的脚本
- share：存放Hadoop的依赖jar包、文档、和官方案例

## Hadoop 运行模式

- 本地模式：单机运行，数据存储在本地，用于测试share中的案例
- 伪分布式：单机运行，但是具备Hadoop集群的所有功能，一台服务器模拟一个分布式的环境
- 完全分布式：多台服务器组成分布式环境。生产环境使用



## 本地模式
