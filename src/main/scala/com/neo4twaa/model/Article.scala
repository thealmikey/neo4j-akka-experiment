package com.neo4twaa.model

import java.util

import org.neo4j.ogm.annotation.{GeneratedValue, Id, Labels, NodeEntity, Relationship}

@NodeEntity("Article")
class Article() extends EventNode {
  @Id
  @GeneratedValue
  var id: java.lang.Long = _

  @Labels val labels = new util.ArrayList[String]()

  var article_id: Int = _

  var title: String = _

//  var metatags = new util.ArrayList[String]()
//
//  @Relationship("CATEGORY")
//  var category: java.util.ArrayList[Category] = new util.ArrayList[Category]()

  @Relationship(`type` ="COMMENT",direction="INCOMING")
  var comments: java.util.ArrayList[Comment] = new util.ArrayList[Comment]()

  var created_at:java.lang.Long = _

  var postedBy:Int = _

}