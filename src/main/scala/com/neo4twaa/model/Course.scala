package com.neo4twaa.model

import org.neo4j.ogm.annotation.{Index, NodeEntity}

@NodeEntity("Course")
class Course() {
  @Index(unique = true, primary = true)
  var name: String = _
}
