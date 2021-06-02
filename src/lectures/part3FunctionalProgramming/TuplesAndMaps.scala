package lectures.part3FunctionalProgramming

import scala.annotation.tailrec

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
  val phoneBook = Map(("Jim",1212),"Naik"->1232,"JIM"->342).withDefaultValue(-1)
    // a->b is sugar for tuple (a,b)"
  println("PhoneBook::"+phoneBook)

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

  /*
  * 1. what would happen if the keys Jim-> 555 and JIM->3434
  *     !!! Care full with mapping keys
  *
  * 2. Overly simplified social network based on maps
  *   - Person = String
  *   - add or remove a person to network
  *   - friend ( mutual)
  *   - unfriend
  *
  *   - Number of friends of a person
  *   - Person with most friends
  *   - how many persons have NO friends
  *   - if there is a social connection between two people (  direct or not)
  *
  * */

    def add(network:Map[String,Set[String]],person:String):Map[String,Set[String]] =
      network+(person->Set())

    def friend(network:Map[String,Set[String]],a:String,b:String):Map[String,Set[String]] = {
      val friendsA = network(a)
      val friendsB = network(b)
      network +(a-> (friendsA +b)) + (b->(friendsB+a))
    }

    def unfriend(network:Map[String,Set[String]],a:String,b:String):Map[String,Set[String]] = {
      val friendsA = network(a)
      val friendsB = network(b)
      network +(a-> (friendsA - b)) + (b->(friendsB - a))
    }

  def remove(network:Map[String,Set[String]],a:String):Map[String,Set[String]] = {
    def removeAux(friends:Set[String],networkAcc:Map[String,Set[String]]): Map[String,Set[String]] = {
      if (friends.isEmpty) networkAcc
      else removeAux(friends.tail,unfriend(networkAcc,a,friends.head))
    }
    val unfriended = removeAux(network(a),network)
    unfriended - a
  }


  val empty: Map[String,Set[String]] = Map()
  val network = add(add(empty,"Bob"),"Marry")

  println("Network :: "+network)
  println("Friends Networks :: "+friend(network,"Bob","Marry"))
  println("UnFriends Networks ::"+unfriend(friend(network,"Bob","Marry"),"Bob","Marry"))
  println("RemovedFriends Networks :: "+remove(friend(network,"Bob","Marry"),"Bob"))

  // Jim,Bob,Marry
  val people = add( add(add(empty,"Bob"),"Marry"),"Jim")
  val jimBob = friend(people,"Bob","Jim")
  val testNet = friend(jimBob,"Bob","Marry")

  println("Test Net::"+testNet)
  def nFriends(network:Map[String,Set[String]] ,person:String):Int =
    if(!network.contains(person)) 0
    else (network(person).size)

  println("Friends Count:"+nFriends(testNet,"Bob"))

  def mostFriends(network:Map[String,Set[String]]):String = {
    network.maxBy(pair=>pair._2.size)._1

  }
  println("mostFriends :"+mostFriends(testNet))

  def nPeopleWithNoFriends(network:Map[String,Set[String]]):Int = {
    network.filterKeys(k=> network(k).size == 0).size
  }
  println("nPeopleWithNoFriends:"+nPeopleWithNoFriends(testNet))

  def socialCOnnection(network:Map[String,Set[String]],a:String,b:String):Boolean = {
    @tailrec
    def bfs(target:String,considerPeople:Set[String],discoveredPeoper:Set[String]) : Boolean = {
      if(discoveredPeoper.isEmpty) false
      else {
        val person = discoveredPeoper.head
        if(person == target) true
        else if(considerPeople.contains(person)) bfs(target,considerPeople,discoveredPeoper.tail)
        else bfs(target,considerPeople+person,discoveredPeoper.tail++network(person))
      }
    }
    bfs(b,Set(),network(a)+a)
  }
  println("socialCOnnection: "+socialCOnnection(testNet,"Marry","Jim"))
  println("socialCOnnection: "+socialCOnnection(network,"Marry","Bob"))
}
