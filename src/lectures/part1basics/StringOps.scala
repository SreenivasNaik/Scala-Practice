package lectures.part1basics

object StringOps extends App {

   val str :String = "Hello I am Learning Scala"
  println(str.charAt(2))
  println(str.substring(7,11))
  println(str.split(" ").toList)
  println(str.startsWith("Hello"))
  println(str.replace(" ","-"))
  println(str.toLowerCase())
  println(str.length)

  val numString = "45"
  val num = numString.toInt
  println(numString)
  println(num)
  println('a'+:numString :+'z')
  println(str.reverse)
  println(str.take(5))

  // Scala Specific :: String InterPolators
  // S- InterPolators
  val name ="Sreenu";val age =25
  val greeting = s"Hello My name is $name and I am $age years of old"
  println(greeting)
  val anotherGreeting = s"Hello My name is $name and I am ${age+1} years of old"
  println(anotherGreeting)

  // F - Interpolator
  val speed = 1.2f
  val math = f"$name can eat $speed%2.2f per mint"
  println(math)

  // Raw Interpolator
  println(raw"this is a \n new line")
  val escpaed = "THis is a \n new line"
  println(raw"$escpaed")
}
