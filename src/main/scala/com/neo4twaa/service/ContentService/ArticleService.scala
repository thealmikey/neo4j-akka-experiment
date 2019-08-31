package com.neo4twaa.service.ContentService

import java.util
import java.util.Date

import com.neo4twaa.model.Relationships.ViewedRelationship
import com.neo4twaa.model.{Article, Category, Comment, User}
import com.neo4twaa.service.ContentService.api.request.{RawArticleInteractionRequest, RawArticleRequest}
import com.neo4twaa.service.MentorService.RawMentorRequest
import com.neo4twaa.service.util.{ContentInteractions, Util}
import org.neo4j.ogm.transaction.Transaction

import collection.JavaConverters._

object ArticleService {

  def addArticleInteraction(interaction: RawArticleInteractionRequest): Unit = {
    import java.util
    val userParams = new util.HashMap[String, Int](1)
    userParams.put("user_id", interaction.user_id)

    val articleParams = new util.HashMap[String, Int](1)
    articleParams.put("article_id", interaction.post_id)

    val sessionFactory = Util.sessionFactory
    val session = sessionFactory.openSession()
    var tx: Transaction = session.beginTransaction()
    try {
      var user: User = session.queryForObject(classOf[User], "MATCH(n:User) where n.user_id=$user_id return n limit 1", userParams)
      var article: Article = session.queryForObject(classOf[Article], "MATCH(n:Article) where n.article_id=$article_id return n limit 1", articleParams)

      interaction.interactions match {
        case ContentInteractions.COMMENT => {
          var comment = new Comment()
          comment.created_at = new Date().getTime
          article.comments.add(comment)
          user.commentedContent.add(comment)
          user.commentedArticle.add(article)
        }
        case ContentInteractions.LIKE => {
          user.likedArticle.add(article)
        }
        case ContentInteractions.SHARE => {
          user.sharedArticle.add(article)
        }
        case ContentInteractions.VIEW => {
          var viewInteraction = new ViewedRelationship()
          viewInteraction.user = user
          viewInteraction.article = article
          session.save(viewInteraction)
        }
      }
      session.save(user)
      tx.commit()
    } catch {
      case e: Exception => {
        println(e.getMessage)
      }

    } finally if (tx != null) {
      println("I closed the connection after article interaction")
      tx.close()
    }
  }

  def addArticle(rawArticle: RawArticleRequest): Unit = {
    val sessionFactory = Util.sessionFactory
    val session = sessionFactory.openSession()
    var tx: Transaction = session.beginTransaction()
    try {
      val userParams = new util.HashMap[String, Int](1)
      userParams.put("user_id", rawArticle.postedBy)
      var user: User = session.queryForObject(classOf[User], "MATCH(n:User) where n.user_id=$user_id return n limit 1", userParams)
      var article = new Article()
      article.created_at = new Date().getTime
      article.article_id = rawArticle.article_id
      var theInterests: List[Category] = rawArticle.category.map { x =>
        var one = new Category()
        one.name = x
        one
      }
      article.title = rawArticle.title
//      article.category.addAll(theInterests.asJava)
//      article.metatags.addAll(rawArticle.category.asJava)
      article.postedBy = rawArticle.postedBy
      user.postedArticle.add(article)
      session.save(user)
      tx.commit()
      tx.close()
    } catch {
      case e: Exception => {
        println(e.getMessage)
        e.printStackTrace()
      }

    } finally if (tx != null) {
      println("I closed the connection")
      tx.close()
    }
  }
}
