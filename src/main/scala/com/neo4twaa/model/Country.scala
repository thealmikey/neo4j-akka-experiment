package com.neo4twaa.model

import org.neo4j.ogm.annotation.{Index, NodeEntity, Relationship}

@NodeEntity("Country")
class Country() {

  @Index(unique = true, primary = true)
  var name: String = _

  var countryCode: Long = _

  @Relationship("IN_STATE")
  var states:java.util.ArrayList[State] = _
}
