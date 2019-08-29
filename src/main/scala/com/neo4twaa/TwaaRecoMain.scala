package com.neo4twaa

import java.util

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives.{complete, get, onComplete, pathEnd, pathPrefix}
import akka.stream.ActorMaterializer
import com.neo4twaa.model.Mentor
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport
import org.neo4j.driver.GraphDatabase
import org.neo4j.ogm.config.Configuration
import org.neo4j.ogm.driver.Driver
import org.neo4j.ogm.session.SessionFactory

import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global

object TwaaRecoMain {
  implicit val actorSystem: ActorSystem = ActorSystem("mikesys")
  implicit val actorMaterializer: ActorMaterializer = ActorMaterializer()

  var configuration = new Configuration.Builder()
    .uri("http://neo4j:twaaroot@localhost:7474")
    .build();

  val sessionFactory: SessionFactory = new SessionFactory(configuration, "com.neo4twaa.model")
  val (host, port) = ("0.0.0.0", 9091)

  var publicRoutes = pathPrefix("user") {
    pathEnd {
      get {
        var query = new Mentor()
        query.name = "Ngene the I"

        var session = sessionFactory.openSession()
        session.beginTransaction()
        sessionFactory.openSession().save(query)

        complete("Hey")
      }
    }

  }

  Http().bindAndHandle(publicRoutes, host, port)

  def main(args: Array[String]): Unit = {
    println("Running the server")

  }

}