package lectures.part1basics

import scala.annotation.tailrec

object Recurssion extends App {
  def factorialFunction(number:Int):Int = {
    if(number <= 0) 1
    else{
      println("Computing factorial of "+number+" - I first Need factorial of "+(number-1))
      val result = number*factorialFunction(number-1);
      println("Computed factorial of "+number)
      result
    }
  }
  //println(factorialFunction(5000))

  def anotherFactorial(n:Int):BigInt = {
    @tailrec
    def factHelper(x:Int,accumulator:BigInt):BigInt =
      if (x<=1) accumulator
      else factHelper(x-1,x*accumulator) // TAIL RECURSION

    factHelper(n,1)
  }
  println(anotherFactorial(5000))

  def contactStringUsingTailRecursive(string: String,num:Int,accumulator:String):String =
    if(num<=0) accumulator
    else contactStringUsingTailRecursive(string,num-1,string+accumulator)

  println(contactStringUsingTailRecursive("sreenu",2,""))

  def isPrime(n:Int):Boolean = {
    def isPrimeTailRec(t:Int,isStillPrime:Boolean):Boolean =
      if(!isStillPrime) false
      else if(t<=1) true
      else isPrimeTailRec(t-1,n%t!=0 && isStillPrime)
    isPrimeTailRec(n/2,true)

  }
  println(isPrime(2003))
  println(isPrime(629))

  def fibonaaci(n:Int):Int = {
    def fiboTailRec(i:Int,last:Int,nextToLast:Int):Int = {
      if (i>=n) last
      else fiboTailRec(i+1,last+nextToLast,last)
    }
    if (n<=2) 1
    else fiboTailRec(2,1,1);
  }
  println(fibonaaci(8))
}
