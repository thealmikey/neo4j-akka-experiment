package com.neo4twaa.model

import java.util.Date

import org.neo4j.ogm.annotation.{EndNode, GeneratedValue, Id, Index, Property, RelationshipEntity, StartNode}
import com.neo4twaa.model.{Article, Podcast, Post, User, Video}

@RelationshipEntity(`type` = "RATED")
@Index(unique = true)
class MentorRating() {
  @Id
  @GeneratedValue
  var id: java.lang.Long = _

  @StartNode
  var mentee: User = null



  @EndNode
  var mentor: User = null

  @Property
  var rating: java.lang.Integer = _

  @Property
  var rated_at:java.lang.Long = new Date().getTime()
}
