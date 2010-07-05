# summary 
This is a translation of the WordCount example from the 
[Apache Hadoop Map/Reduce Tutorial](http://hadoop.apache.org/common/docs/r0.20.1/mapred_tutorial.html)
to scala. I ran into a few snags making this work myself so I thought I'd bundle
up a working example and hopefully save other people some trouble.

I've tried to follow the java example as closely as possible
in the scala version, so I haven't tried to impose any higher level of
abstraction on the code, even though you can imagine building something
much more expressive on top of this with scala.

I chopped out the extra argument parsing logic that was in the java
example because I think it just obscures the point of the example. Adding
it back to the scala version is left as an exercise for the reader.

*Note: this example requires Scala 2.8.*

# running the scala WordCount example
1. install hadoop and make sure the hadoop script is on your path
2. install scala and make sure scalac is on your path
3. copy the hadoop-core jar from the root directory of the hadoop distribution to
   the directory in which you've checked out this tutorial
4. copy the commons-logging jar from the lib directory of the hadoop distribution to
   the directory in which you've checked out this tutorial
4. copy the scala-library.jar jar from the lib directory of the scala distribution to
   the directory in which you've checked out this tutorial
5. run the scala version of WordCount with the run.sh script included here


