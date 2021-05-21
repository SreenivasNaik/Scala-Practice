package lectures.part1basics

object DefaultArgs  extends  App {

  def trfract(n:Int,acc:Int=1):Int =
    if (n<=1) acc
    else trfract(n-1,n*acc)

  var fact10 = trfract(10,1)
  println(fact10)
   fact10 = trfract(10)
  println(fact10)
def savePicture(format:String ="jpg",width:Int =1920,height:Int = 1080):Unit = println("Saving picture")
  savePicture(width = 199)
  savePicture(height = 100,format = "sreenu",width = 199)

}
