package graphqldemo.data

import org.springframework.data.repository.CrudRepository
import graphqldemo.domain.Post

interface PostRepository: CrudRepository<Post, Long>
