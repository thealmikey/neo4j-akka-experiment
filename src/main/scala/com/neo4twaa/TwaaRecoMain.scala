package com.neo4twaa

import java.util

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives.{complete, get, onComplete, pathEnd, pathPrefix}
import akka.stream.ActorMaterializer
import com.neo4twaa.model.User
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport
import org.neo4j.driver.GraphDatabase
import org.neo4j.ogm.config.Configuration
import org.neo4j.ogm.driver.Driver
import org.neo4j.ogm.session.SessionFactory
import akka.http.scaladsl.server.Directives._

import collection.JavaConverters._
import akka.http.scaladsl.model.{ContentTypes, DateTime, HttpEntity, HttpResponse, StatusCodes}
import com.google.gson.Gson
import com.neo4twaa.service.ContentService.ArticleService
import com.neo4twaa.service.ContentService.api.request.{RawArticleInteractionRequest, RawArticleRequest}
import com.neo4twaa.service.MentorService.api.request.{CompleteRawMentorRequest, RawMentorInteraction}
import com.neo4twaa.service.MentorService.{MentorMenteeService, RawMentorRequest, TheUser}
import com.neo4twaa.service.util.UserTypes
import io.circe.{Decoder, Encoder}
import io.circe.generic.auto._
import io.circe.syntax._
import io.circe.generic.semiauto._

import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global

object TwaaRecoMain extends App with FailFastCirceSupport {

  implicit val userDecoder: Decoder[User] = deriveDecoder[User]
  implicit val userEncoder: Encoder[User] = deriveEncoder[User]


  implicit val actorSystem: ActorSystem = ActorSystem("mikesys")
  implicit val actorMaterializer: ActorMaterializer = ActorMaterializer()

  var configuration = new Configuration.Builder()
    .uri("http://neo4j:twaaroot@localhost:7474")
    .autoIndex("update")
    .build();


  val (host, port) = ("0.0.0.0", 9091)

  var publicRoutes = pathPrefix("userapi") {
    pathEnd {
      post {
        entity(as[RawMentorRequest]) { person =>
          MentorMenteeService.addUser(person, person.user_type)
          complete(s"Person name is: ${person.profile.name} ")
        }
      } ~ put {
        entity(as[CompleteRawMentorRequest]) { person =>
          MentorMenteeService.addFullUser(person, "mentor")

          complete(s"Person name is")
        }
      } ~ get {
        //        var session = sessionFactory.openSession()
        //        var gson = new Gson()
        //        var users = session.loadAll(classOf[User]).asScala.toList
        //        var res = HttpResponse(entity = HttpEntity(ContentTypes.`application/json`, gson.toJson(users(0))))
        //        for(usr <- users){
        //          var gson = new Gson()
        //          var theRes = gson.toJson(usr)
        //          println(theRes)
        //        }
        //        var theResponse =
        complete("works")
      }
    }

  } ~ pathPrefix("menteeapi") {
    post {
      entity(as[RawMentorRequest]) { person =>


        //        var mentee = new User()
        //        mentee.labels.add("Mentee")
        //        mentee.name = person.profile.name
        //        mentee.date_of_birth = person.profile.Date_of_birth
        //        mentee.user_id = person.user_id

        MentorMenteeService.addUser(person, UserTypes.MENTOR)
        complete(s"Person name is: ${person.profile.name} ")
        //          var mentee1 = new User()
        //          mentor1.name = "Mentor One"
        //          mentor1.labels.add("Mentor")
        //          mentee1.name = "Mentee Moja"
        //          mentee1.labels.add("Mentee")
        //          mentor1.mentees.add(mentee1)
        //
        //          var session = sessionFactory.openSession()
        //          session.beginTransaction()
        //          sessionFactory.openSession().save(mentor1, -1)
        complete(s"Person name is: ${person.profile.name} ")
      }
    } ~ put {
      entity(as[CompleteRawMentorRequest]) { person =>
        MentorMenteeService.addFullUser(person, UserTypes.MENTEE)

        complete(s"Person name is")
      }
    }
  } ~ pathPrefix("articleapi") {
    entity(as[RawArticleRequest]) { article =>
      ArticleService.addArticle(article)
      complete("received an articel")
    }
  } ~ pathPrefix("articlePostInteraction") {
    pathEnd {
      post {
        entity(as[RawArticleInteractionRequest]) { interaction =>
          ArticleService.addArticleInteraction(interaction)
          complete("Interacted")
        }
      }
    }
  } ~ pathPrefix("mentorInteraction") {
    pathEnd {
      post {
        entity(as[RawMentorInteraction]) { interaction =>
          MentorMenteeService.addMentorInteraction(interaction)
          complete("Interacted")
        }
      }
    }
  }

  Http().bindAndHandle(publicRoutes, host, port)

  println("Running the server")


}