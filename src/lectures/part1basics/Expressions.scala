package lectures.part1basics

object Expressions extends App {
  val x = 1+2
  println(x)
  println(2+ 3*4)

  // Instructions DO vs Expressions (Value)
  val aCondition = true
  val aConditionValue = if(aCondition) 5 else 3
  println(aConditionValue)
  var i=0
  while(i<10)
{
  print(i);i+=1
}
  // Everything in Scala is Expression
  val aval= (i=3)
  println(aval)

  // Code Block
  val aCodeBlock = {
    val  y =2;
    val z = y+1;
    if(z>2) "hello" else "hai"
  }
  println(aCodeBlock)

  val someValue = {
    2>3
  }
  println(someValue)
  val someOtherValue = {
    if(someValue) 123 else 233
    42
  }
  println(someOtherValue)
}
