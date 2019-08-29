package com.neo4twaa.model

import org.neo4j.ogm.annotation.{GeneratedValue, Id, NodeEntity, Relationship}

@NodeEntity(label = "User")
abstract class User {

}

@NodeEntity(label = "Mentor")
class Mentor() extends User {
  @Id
  @GeneratedValue
  var id: java.lang.Long = null
  var name: String = null
  @Relationship()
  var mentees:java.util.ArrayList[Mentee]=_
}

@NodeEntity(label = "Mentee")
class Mentee() extends User {
  @Id
  @GeneratedValue
  var id: java.lang.Long = null
  var name: String = null
}