package lectures.part3FunctionalProgramming

object TuplesAndMaps extends App {

  // Tuples are finte orders lists
  val tuple= new Tuple2(2,"Sreenivas") // ==> Tuple2[Int,String] = (Int,String)

  val tuple2 = (2,"Sreenivas")
  println(tuple)
  println(tuple._1)
  println(tuple._2)
  println(tuple.copy(_2 = "Naik"))
  println(tuple.swap)
  println("===========Maps===========")

  val map:Map[String,Int] = Map()
  val phoneBook = Map(("Sreenivas",1212),"Naik"->1232).withDefaultValue(-1)
    // a->b is sugar for tuple (a,b)
  println(phoneBook)

  // operations
  println(phoneBook.contains("Naik"))
  println(phoneBook("Naik"))
  println(phoneBook("sds"))
  // adding paining
  val newpair = "diwa"->1123
  val newPhoneBook =phoneBook+newpair
  println(newPhoneBook)

  // functions - map,flatmap,filter
  println(phoneBook.map(pair=>pair._1.toLowerCase()->pair._2))
  // filter keys
  println(phoneBook.filterKeys(x=>x.startsWith("S")))
    // map values
  println(phoneBook.mapValues(num=>"+91 -"+num))

  //convertionns
  println(phoneBook.toList)
  println(List(("Naik",122)).toMap)

  // Group by
  val list = List("sree","Naik","abc","cnu","sreenivas")
  println(list.groupBy(name=>name.charAt(0)))


}
