package com.neo4twaa.service.MentorService

import com.neo4twaa.TwaaRecoMain.configuration
import com.neo4twaa.model.User
import com.neo4twaa.service.util.Util
import org.neo4j.ogm.session.SessionFactory
import org.neo4j.ogm.transaction.Transaction

object MentorMenteeService {

  def addMentor(person: RawMentorRequest): Unit = {
    val sessionFactory = Util.sessionFactory
    val session = sessionFactory.openSession()
    var tx: Transaction = session.beginTransaction()
    try {
      var mentor = new User()
      mentor.labels.add("Mentor")
      mentor.name = person.profile.name
      mentor.date_of_birth = person.profile.Date_of_birth
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
