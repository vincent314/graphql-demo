package org.vince.graphqldemo.graphqldemo.domain

import javax.persistence.*

@Entity
data class Post(
  @Id var id: Long,

  var title: String,

  var body: String,

  @Transient
  var userId: Long,

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "USER_ID")
  var user: User?
)
