# 配置文件

在Hadoop的所有集群上的所有主机同步以下配置文件

## hadoop-env.sh

hadoop运行环境

主要配置`JAVA_HOME`

```sh
export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64/
```

如果存在用户权限问题，还需要添加：

```sh
export HDFS_NAMENODE_USER=root
export HDFS_DATANODE_USER=root
export HDFS_SECONDARYNAMENODE_USER=root
export YARN_RESOURCEMANAGER_USER=root
export YARN_NODEMANAGER_USER=root
```



## core-site.xml

-  描述集群中NameNode结点的URI(包括协议、主机名称、端口号)，hdfs为协议名

  ```xml
  <property>
            <name>fs.defaultFS</name>
            <value>hdfs://master:8020</value>
            <final>true</final>
  </property>
  ```

  `final`表示最终值，不允许改变

-  hadoop的临时存储路径

   ```xml
       <property>
           <name>hadoop.tmp.dir</name>
           <value>file:/usr/local/hadoop/tmp</value>
       </property>
   ```

   

## hdfs-site.xml

- NameNode的数据目录

```xml
    <property>
        <name>dfs.namenode.name.dir</name>
        <value>file:/usr/local/hadoop/hdfs/namenode</value>
    </property>
```

```xml
    <property>
        <name>dfs.datanode.data.dir</name>
        <value>file:/usr/local/hadoop/hdfs/datanode</value>
    </property>
```
- hdfs的副本（从节点）数量
```xml
    <property>
        <name>dfs.replication</name>
        <value>2</value>
    </property>
```

- block数据块大小，默认值为128M

```xml
    <property>
        <name>dfs.blocksize</name>
        <value>128m</value>
    </property>
```

- nameNode的web UI入口

```xml
    <property>
        <name>dfs.namenode.http-address</name>
        <value>master:50070</value>
    </property>
```



## workers

描述了所有的工作节点的主机名/IP地址。不能存在空格/回车

```
master
slave01
slave02
```

## mapred-site.xml

```xml
    <property>
        <name>mapreduce.framework.name</name>
        <value>yarn</value>
    </property>
```

mapreduce的Web UI：resourcemanager主机:port，默认为8088端口



## yarn-site.xml

```xml
    <property>
        <name>yarn.nodemanager.aux-services</name>
        <value>mapreduce_shuffle</value>
    </property>
    
    <!-- 指定ResourceManager的地址-->
    <property>
        <name>yarn.resourcemanager.hostname</name>
        <value>master</value>
    </property>
```



# 主机环境

1. **设置全局的环境变量**：`/etc/.profile` 中可以定义全局的环境变量，这些变量对所有用户都可见。例如，您可以在这里设置`PATH`变量，以确保系统中的所有用户都能够访问相同的命令。
2. **定义全局的命令别名**：您可以在此文件中为系统中的所有用户定义命令别名，以简化常用命令的使用。
3. 添加HADOOP_HOME和JAVA_HOME

```sh
export HADOOP_HOME=/usr/local/hadoop-3.2.3
export PATH=$PATH:$HADOOP_HOME/bin
export PATH=$PATH:$HADOOP_HOME/sbin
```



# HDFS format

首次启动HDFS时，必须对其进行格式化操作

foramt本质上是初始化，对HDFS清理和准备工作

```sh
hdfs namenode -format
```

管理界面：http://localhost:8088

NameNode界面：http://localhost:50070

HDFS NameNode界面：http://localhost:8042

# namenode运行失败

- 删除hadoop的tmp目录
- 删除hadoop的logs目录
- 重新进行namenode格式化

# docker-compose.yml

```yaml
version: "3"
services:
  master:
    container_name: master
    image: ubuntu/hadoop:1.6
    hostname: master
    ports:
      - 8088:8088
      - 9000:9000
      - 50070:50070
    command: sh -c "/etc/init.d/ssh start;tail -f /dev/null"
  slave01:
    image: ubuntu/hadoop:1.6
    container_name: slave01
    hostname: slave01
    command: sh -c "/etc/init.d/ssh start;tail -f /dev/null"
  slave02:
    image: ubuntu/hadoop:1.6
    container_name: slave02
    hostname: slave02
    command: sh -c "/etc/init.d/ssh start;tail -f /dev/null"
```

`tail -f /dev/null`保持容器持续运行

`/etc/init.d/ssh start`启动SSH服务

`/ etc / bash/.bashrc`

