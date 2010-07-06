#!/bin/sh -x

set -e

export HADOOP_CLASSPATH=$(dirname $0)/scala-library.jar

rm -rf input output classes wordcount.jar
mkdir -p input classes
echo "Hello World Bye World" > input/file01
echo "Hello Hadoop Goodbye Hadoop" > input/file02

scalac -cp hadoop-*.jar:commons-logging-*.jar:commons-cli-*.jar -d classes WordCount.scala
jar -cvf wordcount.jar -C classes/ .
hadoop jar wordcount.jar WordCount input output
hadoop dfs -cat output/part-r-00000
hadoop dfs -rmr output
