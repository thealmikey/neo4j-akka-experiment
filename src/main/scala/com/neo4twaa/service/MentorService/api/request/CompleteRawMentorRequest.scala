package com.neo4twaa.service.MentorService.api.request

case class Profile(
                    Date_of_birth: String,
                    Industry: String,
                    Profession: String,
                    Country: String,
                    State: String
                  )
case class Education_background(
                                 id: Double,
                                 school: String,
                                 course: String,
                                 years: Double
                               )
case class CompleteRawMentorRequest(
                           user_id: Int,
                           profile: Profile,
                           connection_methods: List[String],
                           availability: List[String],
                           goals: String,
                           skills: String,
                           courses_of_interest: List[String],
                           services: String,
                           education_background: Education_background,
                           years_of_experience: Double
                         )