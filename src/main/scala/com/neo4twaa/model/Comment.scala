package com.neo4twaa.model

import java.util

import org.neo4j.ogm.annotation.{GeneratedValue, Id, Labels, NodeEntity, Relationship}

@NodeEntity("Comment")
class Comment() {
  @Id
  @GeneratedValue
  var id: java.lang.Long = _

  @Labels
  val labels = new util.ArrayList[String]()

  var body: String = _

  @Relationship(`type`="COMMENT",direction="INCOMING")
  var comments: java.util.ArrayList[Comment] = new util.ArrayList[Comment]()
}
