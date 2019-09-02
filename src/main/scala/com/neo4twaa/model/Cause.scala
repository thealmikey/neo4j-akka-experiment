package com.neo4twaa.model

import java.util

import org.neo4j.ogm.annotation.{GeneratedValue, Id, Index, Labels, NodeEntity}

@NodeEntity(label = "Cause")
class Cause() {
  @Id
  @GeneratedValue
  var id: java.lang.Long = _

  @Labels
  val labels = new util.ArrayList[String]()

  @Index(unique=true, primary = true)
  var name:String = _
}
