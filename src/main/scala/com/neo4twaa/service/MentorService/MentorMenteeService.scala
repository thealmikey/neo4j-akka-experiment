package com.neo4twaa.service.MentorService

import java.util
import java.util.Date

import com.neo4twaa.TwaaRecoMain.configuration
import com.neo4twaa.model.{Course, User}
import com.neo4twaa.service.MentorService.api.request.CompleteRawMentorRequest
import com.neo4twaa.service.util.Util
import org.neo4j.ogm.session.SessionFactory
import org.neo4j.ogm.transaction.Transaction

import collection.JavaConverters._


object MentorMenteeService {

  def addFullMentor(person: CompleteRawMentorRequest): Unit = {
    val sessionFactory = Util.sessionFactory
    val session = sessionFactory.openSession()
    var tx: Transaction = session.beginTransaction()
    val userParams = new util.HashMap[String, Int](1)
    userParams.put("user_id", person.user_id)
    try {
      var mentor: User = session.queryForObject(classOf[User], "MATCH(n:User) where n.user_id=$user_id return n limit 1", userParams)
      mentor.user_id = person.user_id.toInt
      mentor.labels.add("Mentor")
      //      mentor.name = person.profile

      mentor.date_of_birth = person.profile.Date_of_birth
      mentor.created_at = new Date().getTime
      mentor.courses.addAll(person.courses_of_interest.map { x =>
        var course = new Course()
        course.name = x
        course
      }.asJava)
      //      mentor.user_id = person.user_id
      session.save(mentor)
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

  def addMentor(person: RawMentorRequest): Unit = {
    val sessionFactory = Util.sessionFactory
    val session = sessionFactory.openSession()
    var tx: Transaction = session.beginTransaction()
    try {
      var mentor = new User()
      mentor.labels.add("Mentor")
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

}
