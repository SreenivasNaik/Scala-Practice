package lectures.part2OOP

object OOBasics extends App {

  val person = new Person("Sreenu",25)
  println(person)
  println(person.age)
  println(person.x)
  person.greet("Naik")
  person.greet()

val author = new Writer("Sreenivas","Naik",1995)
val author1 = new Writer("Naik","Naik",1995)
val novel = new Novel("Great",2021,author);
  println(novel.autherAge)
  println(novel.isWrittenBy(author1))

  val counter = new Counter
  counter.inc.print
  counter.inc(10).print
}

//Constructer
class Person(name:String,val age:Int){
  val x=3;

  println(1+2)
    // Over loading
  def greet(name:String):Unit = println(s"${this.name} says Hai to $name")
  def greet():Unit = println(s"I am $name ")
  // Multiple COnstructer
  def this(name:String) = this(name,0)
  def this() = this("name")

}

class Writer(firstName:String,surName:String,val year:Int){
  def fullName:String = firstName+" "+surName
}
class Novel(name:String,year:Int,author:Writer){
  def autherAge = year - author.year
  def isWrittenBy(author:Writer)= author == this.author
  def copy(newYear:Int):Novel = new Novel(name,newYear,author)
}

class Counter(val count:Int = 0){
  def inc = new Counter(count+1)
  def dec = new Counter(count-1)

  def inc(n:Int):Counter = {
    if(n<=0) this
    else inc.inc(n-1)
  }
  def dec(n:Int) :Counter = {
    if(n<=0) this
    else dec.inc(n-1);
  }
  def print = println(count)
}
