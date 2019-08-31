package com.neo4twaa.model

import java.util

import org.neo4j.ogm.annotation.{GeneratedValue, Id, Labels, NodeEntity}

@NodeEntity(label = "School")
class School() {
  @Id
  @GeneratedValue
  var id: java.lang.Long = _

  @Labels
  val labels = new util.ArrayList[String]()

  var name:String = _

  var startDate:Long = _

  var endDate:Long = _

  var courses:java.util.ArrayList[Course] = new util.ArrayList[Course]()
}
