package parallelai.spyglass.hbase

import cascading.tap.{SinkMode, Tap}
import cascading.tuple.Fields
import com.twitter.scalding._
import org.apache.hadoop.hbase.io.{ImmutableBytesWritable => IBW}
import parallelai.spyglass.hbase.HBaseConstants.{SourceMode, SplitType}

trait TypedHBaseSource[T]
  extends Source
  with Mappable[T]
  with TypedSink[T]
  with FieldConversions {

  val hBaseSource: HBaseSource

  override def createTap(readOrWrite: AccessMode)(implicit mode: Mode): Tap[_, _, _] = {
    hBaseSource.createTap(readOrWrite)
  }
}

trait TypedHBaseSource1 extends TypedHBaseSource[Tuple1[IBW]] with Mappable1[IBW] with TypedSink1[IBW]
trait TypedHBaseSource2 extends TypedHBaseSource[Tuple2[IBW, IBW]] with Mappable2[IBW, IBW] with TypedSink2[IBW, IBW]
trait TypedHBaseSource3 extends TypedHBaseSource[Tuple3[IBW, IBW, IBW]] with Mappable3[IBW, IBW, IBW] with TypedSink3[IBW, IBW, IBW]
trait TypedHBaseSource4 extends TypedHBaseSource[Tuple4[IBW, IBW, IBW, IBW]] with Mappable4[IBW, IBW, IBW, IBW] with TypedSink4[IBW, IBW, IBW, IBW]
trait TypedHBaseSource5 extends TypedHBaseSource[Tuple5[IBW, IBW, IBW, IBW, IBW]] with Mappable5[IBW, IBW, IBW, IBW, IBW] with TypedSink5[IBW, IBW, IBW, IBW, IBW]
trait TypedHBaseSource6 extends TypedHBaseSource[Tuple6[IBW, IBW, IBW, IBW, IBW, IBW]] with Mappable6[IBW, IBW, IBW, IBW, IBW, IBW] with TypedSink6[IBW, IBW, IBW, IBW, IBW, IBW]
trait TypedHBaseSource7 extends TypedHBaseSource[Tuple7[IBW, IBW, IBW, IBW, IBW, IBW, IBW]] with Mappable7[IBW, IBW, IBW, IBW, IBW, IBW, IBW] with TypedSink7[IBW, IBW, IBW, IBW, IBW, IBW, IBW]

object TypedHBaseSource {

  def apply(
    tableName: String = null,
    quorumNames: String = "localhost",
    keyFields: Fields = null,
    familyNames: List[String] = null,
    valueFields: List[Fields] = null,
    timestamp: Long = 0L,
    sourceMode: SourceMode = SourceMode.SCAN_ALL,
    startKey: String = null,
    stopKey: String = null,
    keyList: List[String] = null,
    versions: Int = 1,
    useSalt: Boolean = false,
    prefixList: String = null,
    autoFlush: Boolean = true,
    sinkMode: SinkMode = SinkMode.UPDATE,
    inputSplitType: SplitType = SplitType.GRANULAR
  ): TypedHBaseSource[_] = {
    val hBaseSource = HBaseSource()
    valueFields.size match {
      case 1 => new TypedHBaseSource1 { override val hBaseSource: HBaseSource = hBaseSource }
      case 2 => new TypedHBaseSource2 { override val hBaseSource: HBaseSource = hBaseSource }
      case 3 => new TypedHBaseSource3 { override val hBaseSource: HBaseSource = hBaseSource }
      case _ => throw new IllegalArgumentException()
    }
  }
}
