package com.neo4twaa.service.ContentService.api.request

case class RawArticleInteractionRequest(
                                         var user_id: Int,
                                         var post_id: Int,
                                         var interactions: String
                                       )