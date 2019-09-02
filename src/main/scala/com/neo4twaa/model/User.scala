package com.neo4twaa.model

import java.util

import io.circe.generic.JsonCodec
import org.neo4j.ogm.annotation.{EndNode, GeneratedValue, Id, Index, Labels, NodeEntity, Relationship, RelationshipEntity, StartNode}


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
case class User() extends EventNode() {

  @Id
  @GeneratedValue
  var id: java.lang.Long = _

  @Index(unique = true, primary = true)
  var user_id: Int = _

  var created_at: java.lang.Long = _

  var date_of_birth: String = _

  @Labels
  val labels = new util.ArrayList[String]()

  var name: String = _
  @Relationship("MENTORS")
  var mentees: java.util.ArrayList[User] = new util.ArrayList[User]()

  @Relationship("SCHOOLED_AT")
  var schools: java.util.ArrayList[School] = new util.ArrayList[School]()

  @Relationship("INTERESTED_IN")
  var interests: java.util.ArrayList[Category] = new util.ArrayList[Category]()

  @Relationship("SKILLED_IN")
  var skills: java.util.ArrayList[Skill] = new util.ArrayList[Skill]()

  @Relationship("SERVICES")
  var services: java.util.ArrayList[Skill] = new util.ArrayList[Skill]()

  @Relationship("COURSE")
  var courses: java.util.ArrayList[Course] = new util.ArrayList[Course]()

  @Relationship("CAUSE_OF_INTEREST")
  var causesOfInterest: java.util.ArrayList[Cause] = new util.ArrayList[Cause]()

  @Relationship("RATED")
  var ratedArticle: java.util.ArrayList[Article] = new util.ArrayList[Article]()

  @Relationship("RATED")
  var ratedPost: java.util.ArrayList[Post] = new util.ArrayList[Post]()

  @Relationship("RATED")
  var ratedVideo: java.util.ArrayList[Video] = new util.ArrayList[Video]()

  @Relationship(`type` = "RATED", direction = Relationship.OUTGOING)
  var ratedUsers:util.HashSet[MentorRating] = new util.HashSet[MentorRating]

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

  @Relationship("VIEWED")
  var viewedUser: java.util.ArrayList[User] = new util.ArrayList[User]()

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

  @Relationship("AVAILABLE_ON")
  var availability: java.util.ArrayList[Availability] = new util.ArrayList[Availability]()

  @Relationship("CONNECTED")
  var connectedUsers: java.util.ArrayList[User] = new util.ArrayList[User]()

  @Relationship("CONNECTION_METHOD")
  var connectionMethods: java.util.ArrayList[ConnectionMethod] = new util.ArrayList[ConnectionMethod]()

  @Relationship("COUNTRY")
  var country: Country = _

  @Relationship("STATE")
  var state: State = _

  @Relationship("PROFESSION")
  var profession: util.ArrayList[Profession] =  new util.ArrayList[Profession]()

  @Relationship("INDUSTRY")
  var industry: util.ArrayList[Industry] = new util.ArrayList[Industry]()

  var goals: String = _

  var years_of_experience:java.lang.Double = _
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