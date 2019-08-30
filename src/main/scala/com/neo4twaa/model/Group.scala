package com.neo4twaa.model

import java.util

import org.neo4j.ogm.annotation.{GeneratedValue, Id, Labels, NodeEntity, Relationship}

@NodeEntity("Group")
class Group() {
  @Id
  @GeneratedValue
  var id: java.lang.Long = _

  @Labels val labels = new util.ArrayList[String]()

  var title: String = _

  @Relationship("CATEGORY")
  var category: java.util.ArrayList[Category] = new util.ArrayList[Category]()
}
