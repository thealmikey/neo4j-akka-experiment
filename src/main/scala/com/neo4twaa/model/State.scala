package com.neo4twaa.model

import org.neo4j.ogm.annotation.{Index, NodeEntity}

@NodeEntity("State")
class State() {
  @Index(unique = true, primary = true)
  var name: String = _
}
