package lectures.part3FunctionalProgramming

object HofsCurriers extends  App {

  //val superFunction :(Int,(String,(Int=>Boolean))=>Int)=>(Int=>Int) = ???

  // Higher Order Functions => Map, flatmap, filter
  // Function that applies function  n times over value x
  // nTimes(f,n,x) =>
  // nTimes(f,3,x) => f(f(f(x))) = nTimes(f,2,f(x))
  // nTimes(f,n,x) = f(f(....f(x))) => nTimes(f,n-1,f(x))


  def nTimes(f: Int => Int, n: Int, x: Int): Int =
    if (n <= 0) x
    else nTimes(f, n - 1, f(x))

  val plusOne = (x: Int) => x + 1
  println(nTimes(plusOne, 10, 1))

  // Curried Functions
  val superAdder: Int => (Int => Int) = (X: Int) => (Y: Int) => X + Y
  val add3 = superAdder(3)
  println(add3(3))
  println(superAdder(3)(10))

  // Functions with Multiple parameters Lists
  def curriedFormatter(c: String)(x: Double): String = c.format(x)

  val standardFormat: (Double => String) = curriedFormatter("%4.2f")
  val preciseFormat: (Double => String) = curriedFormatter("%10.8f")

  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))
}

