package com.neo4twaa.model

import java.util

import io.circe.generic.JsonCodec
import org.neo4j.ogm.annotation.{EndNode, GeneratedValue, Id, Labels, NodeEntity, Relationship, RelationshipEntity, StartNode}


//@RelationshipEntity("INTEREST")
//class UserInterest() {
//  @StartNode
//  var user:User = _
//
//  @EndNode
//  var interest: Interest = _
//}
//
//
//@RelationshipEntity("SKILL")
//class UserSkill() {
//  @StartNode
//  var user: User = _
//
//  @EndNode
//  var interest: Interest = _
//}
//


//@JsonCodec
@NodeEntity(label = "User")
case class User() {
  @Id
  @GeneratedValue
  var id: java.lang.Long = _

  var user_id:Int = _

  var created_on:String = _

  var date_of_birth:String = _

  @Labels
  val labels = new util.ArrayList[String]()

  var name: String = _
  @Relationship("MENTORS")
  var mentees: java.util.ArrayList[User] = new util.ArrayList[User]()

  @Relationship("INTERESTED_IN")
  var interests: java.util.ArrayList[Category] = new util.ArrayList[Category]()

  @Relationship("RATED")
  var ratedArticle: java.util.ArrayList[Article] = new util.ArrayList[Article]()

  @Relationship("RATED")
  var ratedPost: java.util.ArrayList[Post] = new util.ArrayList[Post]()

  @Relationship("RATED")
  var ratedVideo: java.util.ArrayList[Video] = new util.ArrayList[Video]()


  @Relationship("SHARED")
  var sharedArticle: java.util.ArrayList[Article] = new util.ArrayList[Article]()

  @Relationship("SHARED")
  var sharedPost: java.util.ArrayList[Post] = new util.ArrayList[Post]()

  @Relationship("SHARED")
  var sharedVideo: java.util.ArrayList[Video] = new util.ArrayList[Video]()

  @Relationship("POSTED")
  var postedArticle: java.util.ArrayList[Article] = new util.ArrayList[Article]()

  @Relationship("POSTED")
  var postedPost: java.util.ArrayList[Post] = new util.ArrayList[Post]()

  @Relationship("POSTED")
  var postedVideo: java.util.ArrayList[Video] = new util.ArrayList[Video]()


  @Relationship("LIKED")
  var likedArticle: java.util.ArrayList[Article] = new util.ArrayList[Article]()

  @Relationship("LIKED")
  var likedPost: java.util.ArrayList[Post] = new util.ArrayList[Post]()

  @Relationship("LIKED")
  var likedVideo: java.util.ArrayList[Video] = new util.ArrayList[Video]()


  @Relationship("COMMENTED_ON")
  var commentedArticle: java.util.ArrayList[Article] = new util.ArrayList[Article]()

  @Relationship("COMMENTED_ON")
  var commentedPost: java.util.ArrayList[Post] = new util.ArrayList[Post]()

  @Relationship("COMMENTED_ON")
  var commentedVideo: java.util.ArrayList[Video] = new util.ArrayList[Video]()


  @Relationship("SKILLED_AT")
  var skills: java.util.ArrayList[Category] = new util.ArrayList[Category]()

  @Relationship("TWAAD")
  var twaadArticle: java.util.ArrayList[Post] = new util.ArrayList[Post]()

  @Relationship("TWAAD")
  var twaadVideo: java.util.ArrayList[Video] = new util.ArrayList[Video]()

  @Relationship("TWAAD")
  var twaadPost: java.util.ArrayList[Post] = new util.ArrayList[Post]()

  @Relationship("VIEWED")
  var viewedArticle: java.util.ArrayList[Post] = new util.ArrayList[Post]()

  @Relationship("VIEWED")
  var viewedVideo: java.util.ArrayList[Video] = new util.ArrayList[Video]()

  @Relationship("VIEWED")
  var viewedPost: java.util.ArrayList[Post] = new util.ArrayList[Post]()

  @Relationship("VIEWED")
  var viewedAdvert: java.util.ArrayList[Advert] = new util.ArrayList[Advert]()


  @Relationship("CREATED")
  var createdGroups: java.util.ArrayList[Group] = new util.ArrayList[Group]()

  @Relationship("IS_MEMBER_OF")
  var memberOfGroups: java.util.ArrayList[Group] = new util.ArrayList[Group]()

  @Relationship("IS_ADMIN_OF")
  var adminGroups: java.util.ArrayList[Group] = new util.ArrayList[Group]()

  @Relationship("BELONGS_TO_TWAA_GROUP")
  var twaaUserGroup: java.util.ArrayList[TwaaUserGroup] = new util.ArrayList[TwaaUserGroup]()

  @Relationship("CREATED")
  var commentedContent: java.util.ArrayList[Comment] = new util.ArrayList[Comment]()

  var goals: String = _
}

//@NodeEntity(label = "Mentee")
//class Mentee() extends User {
//  @Id
//  @GeneratedValue
//  var id: java.lang.Long = null
//  var name: String = null
//
//  @Relationship("AVAILABILITY")
//  var availabilityjava: util.ArrayList[Availability] = new util.ArrayList[Availability]
//
//}