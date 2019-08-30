package com.neo4twaa.service.ContentService.api.request

case class RawArticleRequest(
                            var article_id:Int,
                            var category:List[String]=List.empty[String],
                            var title:String="",
                            var metatags:List[String]=List.empty[String],
                            var postedBy:Int
                            )
