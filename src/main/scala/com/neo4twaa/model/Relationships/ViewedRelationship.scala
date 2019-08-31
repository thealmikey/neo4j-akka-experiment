package com.neo4twaa.model.Relationships

import java.util.Date

import org.neo4j.ogm.annotation.GeneratedValue
import org.neo4j.ogm.annotation.Id
import com.neo4twaa.model.{Article, Podcast, Post, User, Video}
import org.neo4j.ogm.annotation.{EndNode, RelationshipEntity, StartNode}

@RelationshipEntity("VIEWED")
class ViewedRelationship() {
  @Id
  @GeneratedValue
  var relationshipId: java.lang.Long = _

  @StartNode
  var user: User = _


  @EndNode
  var article: Article = _

  var viewed_at:Long = new Date().getTime

//  @EndNode
//  var video: Video = _
//
//  @EndNode
//  var podcast: Podcast = _
//
//  @EndNode
//  var post: Post = _

}
