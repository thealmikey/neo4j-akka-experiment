package com.neo4twaa.service.MentorService.api.request

case class RawMentorInteraction(
                                 var mentor_id: Int,
                                 var mentee_id: Int,
                                 connect: Boolean = false,
                                 rate: Int = 0,
                                 view: Boolean = true)