package lectures.part1basics

object Functions extends App {

  def aFunction(a:String,b:Int):String = {
    a+" "+b
  }
  println(aFunction("Sreenu",10))
  def aparameterlessFuntion()= 23
  println(aparameterlessFuntion())
  println(aparameterlessFuntion)

  def repeatedFunction(aString:String ,n:Int): String = {
    if(n==1) aString
    else aString+repeatedFunction(aString,n-1)
  }
  println(repeatedFunction("Sreenu",2))


  def aFuntionWithSideEffects(aStr:String):Unit = println(aStr)

  def greating(name:String,age: Int):String = "Hi "+name+" Iam "+age+"years old"
  println(greating("sreenu",25))

  /*
  * Factorial Funcation 1*2*3
  * */
  def factorialFunction(number:Int):Int = {
    if(number <= 0) 1
    else  number*factorialFunction(number-1);
  }
  println(factorialFunction(3))

  def fabonacci(n:Int): Int = {
    if(n<=2) 1
    else fabonacci(n-1)+fabonacci(n-2);

  }
  println(fabonacci(8))

  def isPrime(n:Int):Boolean={
    def isPrimeUnitl(t:Int):Boolean =
      if(t<=1) true
      else n%t!=0 && isPrimeUnitl(t-1)

    isPrimeUnitl(n/2)
  }
  println(isPrime(37))
}
