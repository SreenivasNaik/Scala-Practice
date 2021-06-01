package lectures.part3FunctionalProgramming

import scala.util.Random

object Sequences extends App {

  // Seq
  val aSequence = Seq(1,3,4,2)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence.apply(3))
  println(aSequence++Seq(5,6))
  println(aSequence.sorted)

  //Range
  val range :Seq[Int] = 1 until 10
  range.foreach(print)

  // LISTs
  println()
  println("===============Lists==================")
  val list = List(1,2,3)
  val prepended = 43 +: list :+89
  println(prepended)
  val apple5 = List.fill(5)("apple")
  println(apple5)
  println(list.mkString("-|-"))
  println("===============List End ==================")
println("============== Arrays ===============")
  val numbers = Array(1,2,3,4)
  val threeNumbers = Array.ofDim[Int](3)
  threeNumbers.foreach(print)
  println()
  numbers(3) =0
  println(numbers.mkString(" "))
  val numberSeq:Seq[Int] = numbers
  println(numberSeq)
  println("============== Arrays End===============")
  println("============== Vectors ===============")
    val vector:Vector[Int] = Vector(1,3,4)
  println(vector)

  // vectors vs Lists
  val maxRuns = 1000
  val maxCapacity = 100000
  def getWriteTime(collection:Seq[Int]):Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity),r.nextInt())
      System.nanoTime() - currentTime
    }
    times.sum*1.0/maxRuns
  }

  val numberList = (1 to maxCapacity).toList
  val numberVector = (1 to maxCapacity).toVector

  /* keeps reference to tail
  * updating an element in middle takes long
  * */

  println("List Average update Time:"+getWriteTime(numberList))
  /*
  * depth of the tree is small
  * needs to replace an entire 32 element chunk
  * */
  println("Vector Average update Time::"+getWriteTime(numberVector))
  println("============== Vector End===============")
}
