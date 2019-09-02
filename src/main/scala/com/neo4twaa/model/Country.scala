package com.neo4twaa.model

import java.util

import org.neo4j.ogm.annotation.{GeneratedValue, Id, Index, Labels, NodeEntity, Relationship}

@NodeEntity("Country")
class Country() {
  @Id
  @GeneratedValue
  var id: java.lang.Long = _

  @Labels val labels = new util.ArrayList[String]()

  @Index(unique = true, primary = true)
  var name: String = _

  var countryCode: Long = _

  @Relationship("IN_STATE")
  var states:java.util.ArrayList[State] =  new util.ArrayList[State]()
}
