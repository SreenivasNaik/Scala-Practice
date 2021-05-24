package lectures.part2OOP

object Exceptions extends App {

  val x:String = null
   /*println(x.length)
    this will throw NPE
    */
  // 1. throwing exceptions
  //val aWeiredValue = throw new NullPointerException

  // 2 . catching excpetions
  def getInt(withExecption:Boolean):Int =
    if(withExecption) throw new RuntimeException("No int for you")
    else 43

  val potentinalFail = try{
    getInt(true)
  }catch {
    case e:RuntimeException => println(s"Caught Runtime exeption${e.getCause}")
  } finally {
    /* Code that will get executed no matter what
    *  optional
    * does not influence the return type of this expression
    * use finnaly for side effects
    * */

    println("Finally")
  }

  println(potentinalFail)

  // 3. How to define user defined excpetions
    class MyException extends Exception
  val myExceptions = new MyException
  //throw myExceptions

  /*
  * 1. crash your program with OutOfMemoryError
  *     Exception in thread "main" java.lang.OutOfMemoryError: Requested array size exceeds VM limit
  *           val array  = Array.ofDim(Int.MaxValue)
  *
  * 2. Crash with SOError
  *     Exception in thread "main" java.lang.StackOverflowError
  *          def  infinte:Int = 1+infinte
             val noLimit = infinte

  * 3. Pocket calculator
  *   - add(x,y)
  *   - subtract(x,y)
  *   - multiply(x,y)
  *   - divide(x,y)
  *   throw
  *     - OverFlowException if add(x,y) exceeds INT.Max_Value
  *     - UnderFlowException if substract(x,y) exceeds Int_MinValue
  *     -  MathCalculation exception for division by 0
  * */
  class OverFlowExecption extends Exception
  class UnderFlowExecption extends Exception
  class MathCalculationException extends RuntimeException("Divistion By 0 not possiable")

  object PocketCalculator{
    def add(x:Int,y:Int):Int = {
      val result = x + y
      if (x > 0 && y > 0 && result < 0) throw new OverFlowExecption
      else if (x < 0 && y < 0 && result > 0) throw new UnderFlowExecption
      else result
    }
    def substract(x:Int,y:Int):Int = {
      val result = x - y
      if(x>0 && y<0 && result<0) throw new OverFlowExecption
      else if (x<0 && y>0 && result>0) throw new UnderFlowExecption
      else result

    }

    def multiply(x:Int,y:Int):Int = {
      val result = x * y
      if(x>0 && y>0 && result<0) throw new OverFlowExecption
      else if (x<0 && y<0 && result<0) throw new OverFlowExecption
      else if(x>0 && y<0 && result>0) throw new UnderFlowExecption
      else if (x<0 && y>0 && result>0) throw new UnderFlowExecption
      else result

    }
    def divide(x:Int,y:Int):Int = {
      if(y==0) throw new MathCalculationException
      else x/y

    }

  }
  println(PocketCalculator.add(10,10))
  println(PocketCalculator.divide(3,0))
}
