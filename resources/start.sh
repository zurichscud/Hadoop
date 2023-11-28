#!/bin/bash

# 设置Hadoop安装目录
HADOOP_HOME="/usr/local/hadoop-3.2.4"

# 检查标志文件是否存在，如果不存在，则执行格式化操作
if [ ! -f "formatted.flag" ]; then
    echo "Formatting HDFS NameNode on master..."
    ssh master "$HADOOP_HOME/bin/hdfs namenode -format"
    # 创建标志文件，表示已经执行过格式化操作
    touch formatted.flag
fi

# 启动Hadoop集群
echo "Starting HDFS on master..."
ssh master "$HADOOP_HOME/sbin/start-dfs.sh"

echo "Starting YARN on slave01..."
ssh slave01 "$HADOOP_HOME/sbin/start-yarn.sh"

# 在master上启动HistoryServer
echo "Starting MapReduce HistoryServer on master..."
$HADOOP_HOME/bin/mapred --daemon start historyserver

# 打印每台服务器的jps输出
echo "########################master##########################"
ssh master "jps | awk '{print \$1, \$2}'"

echo "########################slave01#########################"
ssh slave01 "jps | awk '{print \$1, \$2}'"

echo "########################slave02#########################"
ssh slave02 "jps | awk '{print \$1, \$2}'"
