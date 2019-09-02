package com.neo4twaa.service.MentorService.api.request

case class Profile(
                    Date_of_birth: Option[String],
                    Industry: Option[String],
                    Profession: Option[String],
                    Country: Option[String],
                    State:Option[String]
                  )
case class Education_background(
                                 id: Double,
                                 school: String,
                                 course: String,
                                 years: Double
                               )
case class CompleteRawMentorRequest(
                           user_id: Int,
                           profile: Option[Profile],
                           connection_methods: Option[List[String]],
                           availability: Option[List[String]],
                           goals: Option[String],
                           skills: Option[List[String]],
                           causes_of_interest: Option[List[String]],
                           services: Option[List[String]],
                           education_background: Option[List[Education_background]],
                           years_of_experience: Option[Double]
                         )