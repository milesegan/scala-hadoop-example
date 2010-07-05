import java.util.Iterator
import org.apache.hadoop.mapred._
import org.apache.hadoop.conf.{Configuration,Configured}
import org.apache.hadoop.fs.Path
import org.apache.hadoop.io.{IntWritable,LongWritable,Text}
import org.apache.hadoop.util.{Tool,ToolRunner}

// This class performs the map operation, translating raw input into the key-value
// pairs we will feed into our reduce operation.
class MapClass extends MapReduceBase with Mapper[LongWritable,Text,Text,IntWritable] {
  val one = new IntWritable(1)
  val word = new Text
  
  def map(key:LongWritable, value:Text, output:OutputCollector[Text,IntWritable], reporter:Reporter) = {
    for (t <-  value.toString().split("\\s")) {
      word.set(t)
      output.collect(word, one)
    }
  }
}
  
// This class performs the reduce operation, iterating over the key-value pairs
// produced by our map operation to produce a result. In this case we just
// calculate a simple total for each word seen.
class Reduce extends MapReduceBase with Reducer[Text,IntWritable,Text,IntWritable] {
  def reduce(key:Text, values:Iterator[IntWritable], output:OutputCollector[Text,IntWritable], reporter:Reporter) = {
    var sum = 0
    while (values.hasNext) {
      sum += values.next.get
    }
    output.collect(key, new IntWritable(sum))
  }
}
  
// This class configures and runs the job with the map and reduce classes we've
// specified above.
class WordCount extends Configured with Tool {
  
  def run(args:Array[String]):Int = {
    val conf = new JobConf(getConf, classOf[WordCount])
    conf.setJobName("wordcount")
    conf.setOutputKeyClass(classOf[Text])
    conf.setOutputValueClass(classOf[IntWritable])
    conf.setMapperClass(classOf[MapClass])
    conf.setCombinerClass(classOf[Reduce])
    conf.setReducerClass(classOf[Reduce])
    
    FileInputFormat.setInputPaths(conf, args(0))
    FileOutputFormat.setOutputPath(conf, new Path(args(1)))
        
    JobClient.runJob(conf)
    return 0
  }
}

object WordCount {  
  def main(args:Array[String]) = {
    val res = ToolRunner.run(new Configuration(), new WordCount(), args)
    System.exit(res)
  }
}
