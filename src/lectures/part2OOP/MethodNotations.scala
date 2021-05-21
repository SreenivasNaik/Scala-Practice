package lectures.part2OOP

object MethodNotations extends  App {

  class Person(val name :String,favMovie:String,val age:Int = 0){
    def likes(movie:String):Boolean = movie==favMovie
    def hangoutWith(person: Person):String = s"${this.name} is hanging out wiht ${person.name}"
    def +(person: Person):String = s"${this.name} is hanging out wiht ${person.name}"
    // Over loaded method
    def +(nickName:String):Person = new Person(s"$name ($nickName)",favMovie)
    def unary_! : String = s"${this.name} what the hack"
    def unary_+ :Person = new Person(name,favMovie,age+1)
    def isAlive:Boolean = true
    def apply():String = s"${this.name} what the Apply"
    def learns(thing:String): String = s"$name is learning $thing"
    def leransScala = this learns "Scala"

    def apply(n:Int):String = s"$name watched $favMovie $n times"
  }
  val mary = new Person("Sreenu","Inception")
  println(mary.likes("Inception"))
  println(mary likes "Inception") // INFIX NOTATION == OPERATOR EXPRESSION
  val tom = new Person("Tom","fight")
  println(mary hangoutWith tom)
  println(mary+tom)
  println(1+2)


  // ALL OPERATORS ARE METHODS
    // Prefix Notation
  val x = -1 // equivalent to 1.unary_-
  val y = 1.unary_-

  println(!mary)
  println(mary.unary_!)


  // PostFix Notation
  println(mary.isAlive)
  println(mary isAlive)

    // Apply method
  println(mary.apply())
  println(mary())

  // Overloaded method call and apply method
  println((mary +"Naik")())

  println((+mary).age)

  println(mary leransScala)

  println(mary(10))
}
