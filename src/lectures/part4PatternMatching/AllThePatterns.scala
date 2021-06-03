package lectures.part4PatternMatching

import exercises.Empty1

object AllThePatterns extends App {

  // 1. Constants
  val x:Any = "Scala"
  val constants = x match {
    case 1 => "a number"
    case "Scala" => "The scala"
    case true => "The Truth"
    case AllThePatterns => "A singleton object"

  }
  println("Constacts: "+constants)
  // 2 - match anything
    // 2.1 wildcard
  val matchAnything = x match {
      case _ =>
    }
  //2.2 variable
  val matchVariable = x match {
    case something => s"I have found $something"
  }

  //3 - tuples
  val tuple = (1,2)
  val matchTple = tuple match {
    case (1,1) =>
    case (something,2) => s"I have found $something"
  }

  val nestedTuple = (1,(2,3))
  val matchNestedTuple = nestedTuple match {
    case (_,(2,v)) =>
  }
  // Pattern matching can be nested
  // pattern matching can be nested with case classes

  // 5 .List matching
  val list = List(1,2,3,45)
  val listmatch = list match {
    case List(1,_,_,_) => // Extractor - advanced
    case List(1,_*) => //list of arbitary length
    case 1::List(_) => // infix patttern
    case List(1,2,3):+43 => // Infix  pattern

  }
  // 6. Type specifiers
  val unknown :Any = 2
  val unknownMatch = unknown match {
    case list:List[Int] => // explicit type specifier
    case _ =>
  }

  // 7 . Name binding
//
//  val nameBindingMatch = list match {
//  //  case nonEmptyList @ Con(_,_) =>
//  }
//
//  // 8. Multi patterns
//  val multiPattern = list match {
//    //case Empty1 | Cons =>
//  }

  val numberMatch = list match {
    case listOString:List[String] => "a list of strings"
    case listOfint : List[Int] => " A list of int"
  }

  println(numberMatch)
  // JVM trick question

}
