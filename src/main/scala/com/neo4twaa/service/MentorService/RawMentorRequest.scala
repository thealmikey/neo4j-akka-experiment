package com.neo4twaa.service.MentorService
import com.neo4twaa.service.MentorService.api.request
import io.circe.generic.auto._
import io.circe.syntax._

case class RawMentorRequest(var user_id:Int,profile: Profile)

case class Profile(name:String,Date_of_birth:String)

class TheUser{
  var user_id:Int=_
  var profile: request.Profile=_
}