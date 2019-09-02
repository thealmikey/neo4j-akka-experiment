package com.neo4twaa.service.MentorService

import com.neo4twaa.service.MentorService.api.request
import io.circe.generic.auto._
import io.circe.syntax._

case class RawMentorRequest(var user_id: Int = 0, profile: Profile, var user_type: String)

case class Profile(name: String, Date_of_birth: String)

class TheUser {
  var user_id: Int = _
  var profile: request.Profile = _
}