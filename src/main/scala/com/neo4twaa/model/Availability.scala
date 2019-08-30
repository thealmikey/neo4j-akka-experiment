package com.neo4twaa.model

import java.util

import org.neo4j.ogm.annotation.{GeneratedValue, Id, Labels}

class Availability {
  @Id
  @GeneratedValue
  var id: java.lang.Long = _

  @Labels val labels = new util.ArrayList[String]()
}
