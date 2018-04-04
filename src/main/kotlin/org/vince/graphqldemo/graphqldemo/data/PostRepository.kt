package org.vince.graphqldemo.graphqldemo.data

import org.springframework.data.repository.CrudRepository
import org.vince.graphqldemo.graphqldemo.domain.Post

interface PostRepository: CrudRepository<Post, Long>
