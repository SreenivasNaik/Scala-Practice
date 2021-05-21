package lectures.part1basics

object ValuesVariablesTypes extends App {
  val x:Int = 90
  println(x)
  // Val are IMMUTABLE -> Final
  // x =10;
  val y = 20
  // type of Val is optional
  println(y)
  val string:String = "hello"
  val anotherString = "Sreenu"
  val aBoolean:Boolean = true
  val aChar:Char = 'a'
  val anInt: Int = x
  val aShort:Short = 3433
  val aLong:Long = 343433243234L
  val aFloat:Float = 2.0f
  val aDouble:Double = 3.14

  // Variable
  var aVariable:Int = 29
  aVariable = 20
  println(aVariable)

}
