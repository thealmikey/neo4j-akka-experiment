package com.neo4twaa.service.MentorService

import java.util
import java.util.Date

import com.neo4twaa.TwaaRecoMain.configuration
import com.neo4twaa.model.{Availability, Cause, ConnectionMethod, Country, Course, Industry, MentorRating, Profession, School, Skill, State, User}
import com.neo4twaa.service.MentorService.api.request.{CompleteRawMentorRequest, RawMentorInteraction}
import com.neo4twaa.service.util.{UserTypes, Util}
import org.neo4j.ogm.session.SessionFactory
import org.neo4j.ogm.transaction.Transaction

import collection.JavaConverters._


object MentorMenteeService {

  def addFullUser(person: CompleteRawMentorRequest, userType: String): Unit = {
    val sessionFactory = Util.sessionFactory
    val session = sessionFactory.openSession()
    var tx: Transaction = session.beginTransaction()
    val userParams = new util.HashMap[String, Int](1)
    userParams.put("user_id", person.user_id)
    try {
      var user: User = session.queryForObject(classOf[User], "MATCH(n:User) where n.user_id=$user_id return n limit 1", userParams)
      user.user_id = person.user_id.toInt
      user.labels.add(userType)
      person.profile.map { profile =>
        profile.Date_of_birth.map { dob =>
          user.date_of_birth = dob
        }
        profile.Country.map { countryName =>
          var userCountry = new Country()
          userCountry.name = countryName
          user.country = userCountry
          profile.State.map { state =>
            var userState = new State()
            userState.name = state
            userCountry.states.add(userState)
            user.state = userState
            session.save(userCountry)
            session.save(userState)
          }
        }.getOrElse {
          profile.State.map { state =>
            var userState = new State()
            userState.name = state
            user.state = userState
            session.save(userState)
          }
        }

        profile.Industry.map { industryName =>
          var userIndustry = new Industry()
          userIndustry.name = industryName
          user.industry.add(userIndustry)
        }
        profile.Profession.map { professionName =>
          var userProfession = new Profession()
          userProfession.name = professionName
          user.profession.add(userProfession)
        }
      }

      person.connection_methods.map { connectionMethods =>
        var connMethods = connectionMethods.map { connection =>
          var conn = new ConnectionMethod()
          conn.name = connection
          conn
        }.asJava
        user.connectionMethods.addAll(connMethods)
      }

      person.goals.map { goal =>
        user.goals = goal
      }

      person.causes_of_interest.map { causeList =>
        var causes = causeList.map { cause =>
          var newCause = new Cause()
          newCause.name = cause
          newCause
        }.asJava
        user.causesOfInterest.addAll(causes)
      }

      user.created_at = new Date().getTime

      person.education_background.map { schools =>
        var schoolList = schools.map { school =>
          var theSchool = new School()
          theSchool.name = school.school
          var course = new Course()
          course.name = school.course
          user.courses.add(course)
          theSchool.courses.add(course)
          theSchool
        }.asJava
        user.schools.addAll(schoolList)
      }

      person.availability.map { availabilityTypes =>
        var availabilities = availabilityTypes.map { availability =>
          var newAvailability = new Availability()
          newAvailability.name = availability
          newAvailability
        }
        user.availability.addAll(availabilities.asJava)
      }

      person.skills.map { skillTypes =>
        var skills = skillTypes.map { availability =>
          var newSkill = new Skill()
          newSkill.name = availability
          newSkill
        }
        user.skills.addAll(skills.asJava)
      }

      person.services.map { skillTypes =>
        var skills = skillTypes.map { availability =>
          var newSkill = new Skill()
          newSkill.name = availability
          newSkill
        }
        user.services.addAll(skills.asJava)
      }

      person.years_of_experience.map{years=>
        user.years_of_experience = years
      }
      //      mentor.user_id = person.user_id
      session.save(user)
      tx.commit()
    } catch {
      case e: Exception => {
        println(e.getMessage)
        e.printStackTrace()
      }

    } finally if (tx != null) {
      println("I closed the connection")
      tx.close()
    }
  }

  def addUser(person: RawMentorRequest, userType: String): Unit = {
    val sessionFactory = Util.sessionFactory
    val session = sessionFactory.openSession()
    var tx: Transaction = session.beginTransaction()
    try {
      var mentor = new User()
      person.user_type match {
        case UserTypes.MENTOR => {
          mentor.labels.add("Mentor")
        }
        case UserTypes.MENTEE => {
          mentor.labels.add("Mentee")
        }
      }

      mentor.name = person.profile.name
      mentor.date_of_birth = person.profile.Date_of_birth
      mentor.created_at = new Date().getTime

      mentor.user_id = person.user_id
      session.save(mentor)
      tx.commit()
      tx.close()
    } catch {
      case e: Exception => {
        println(e.getMessage)
      }

    } finally if (tx != null) {
      println("I closed the connection")
      tx.close()
    }
  }

  def addMentee(person: RawMentorRequest): Unit = {
    val sessionFactory = Util.sessionFactory
    val session = sessionFactory.openSession()
    var tx: Transaction = session.beginTransaction()
    try {
      var mentor = new User()
      mentor.labels.add("Mentee")
      mentor.name = person.profile.name
      mentor.date_of_birth = person.profile.Date_of_birth
      mentor.user_id = person.user_id
      session.save(mentor)
      tx.commit()
      tx.close()
    } catch {
      case e: Any => {
        println("Got an error")
      }

    } finally if (tx != null) {
      println("I closed the connection")
      tx.close()
    }
  }


  def addMentorInteraction(interaction: RawMentorInteraction): Unit = {
    val sessionFactory = Util.sessionFactory
    val session = sessionFactory.openSession()
    var tx: Transaction = session.beginTransaction()
    val queryParams = new util.HashMap[String, Int](1)
    queryParams.put("mentor_id", interaction.mentor_id)
    //    val menteeParams = new util.HashMap[String, Int](1)
    queryParams.put("mentee_id", interaction.mentee_id)


    try {
      var mentor: User = session.queryForObject(classOf[User], "MATCH(n:User) where n.user_id=$mentor_id return n limit 1", queryParams)
      var mentee: User = session.queryForObject(classOf[User], "MATCH(n:User) where n.user_id=$mentee_id return n limit 1", queryParams)

      if (interaction.rate > 0) {

        //        val ratingParams = new util.HashMap[String, Int](1)
        queryParams.put("rating", interaction.rate)

        println("i am in the raating", interaction.rate)
        //        session.query(
        //          """
        //            |MATCH (mentee:User),(mentor:User)
        //            |WHERE mentor.user_id=$mentor_id  AND  mentee.user_id=$mentee_id
        //            |MERGE (mentee)-[r:RATED]->(mentor)
        //            |SET r.rating = $rating
        //            |RETURN type(r), r.name
        //          """.stripMargin, queryParams)
        var mentorRating = new MentorRating()
        mentorRating.mentee = mentee
        mentorRating.mentor = mentor
        mentorRating.rating = interaction.rate
        mentee.ratedUsers.add(mentorRating)
        session.save(mentee)
        //        session.save(mentorRating)
        //        session.save(mentor)
        //        session.qu
      }
      if (interaction.view) {
        mentee.viewedUser.add(mentor)
      }
      if (interaction.connect) {
        mentee.connectedUsers.add(mentor)
      }

      session.save(mentor)
      session.save(mentee)
      tx.commit()
      tx.close()
    }
    catch {
      case e: Exception => {
        println(e.getMessage)
        e.printStackTrace()
      }

    } finally if (tx != null) {
      println("I closed the connection")
      tx.close()
    }
  }

}
