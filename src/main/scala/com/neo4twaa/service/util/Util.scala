package com.neo4twaa.service.util

import com.neo4twaa.TwaaRecoMain.configuration
import org.neo4j.ogm.session.SessionFactory

object Util {
  val sessionFactory: SessionFactory = new SessionFactory(configuration, "com.neo4twaa.model")
}
