package lectures.part3FunctionalProgramming

object AnonymousFunctions extends App {

  val doubler = new Function1[Int,Int] {
    override def apply(v1: Int): Int = v1*2
  }

  val doublerLamda = (x:Int)=>x*2 // Anonymous Functions LAMDA

  // Multiple params in lambda
  val multipleParams:(Int,Int) => Int = (a:Int,b:Int) => a+b

  // No Params
  val justDo:()=>Int = ()=>3

  println(justDo) // Functions
  println(justDo()) // function calling

    // curly braces with lamdbas
  val stringToInt = {
    (str:String) =>str.toInt
  }

  // More Syntacial Sugar

  val incrementer :Int=>Int = _+1 // Equivalent to  val incrementer :Int=>Int = (x:Int) =>x+1

  val niceAdded :(Int,Int)=>Int = _+_ // Equivalent to val niceAdder:(Int,Int)=>Int = (x:Int,y:Int) => x+y

  val superAdd = (x:Int)=>(y:Int) => x+y
  println(superAdd(2)(2))
}
