package org.vince.graphqldemo.graphqldemo.services

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.hibernate.Hibernate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ClassPathResource
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.vince.graphqldemo.graphqldemo.data.PostRepository
import org.vince.graphqldemo.graphqldemo.data.UserRepository
import org.vince.graphqldemo.graphqldemo.domain.Post
import org.vince.graphqldemo.graphqldemo.domain.User
import javax.annotation.PostConstruct
import javax.transaction.Transactional

@Service
class UserService
@Autowired constructor(
        val objectMapper: ObjectMapper,
        val userRepository: UserRepository,
        val postRepository: PostRepository
) {

    fun userCount(): Long = userRepository.count()

    fun listUsers(): List<User> = userRepository.findAll().toList()

    fun listUsersPaginated(page: Int, count: Int, sortedBy: String): List<User> =
            userRepository.findAll(PageRequest.of(
                    page,
                    count,
                    Sort.by(sortedBy)
            ))
                    .toList()

    fun getUser(userId: Long): User? = userRepository.findById(userId).orElseGet(null)

    fun update(id: Long, name: String?, email: String?): Long {
        val newUser = userRepository.findById(id).orElseThrow { Exception("User not found") }
                .also {
                    if (name != null) it.name = name
                    if (email != null) it.email = email
                }

        return userRepository.save(newUser).id
    }

    fun listPosts(withUser: Boolean = true) =
            postRepository
                    .findAll()
                    .also {
                        if (withUser) {
                            it.forEach { post ->
                                Hibernate.initialize(post.user) // Force fetching user
                            }
                        }
                    }

    @PostConstruct
    @Transactional
    fun initDatabase() {
        val input = ClassPathResource("/json/users.json").inputStream
        val userList = objectMapper.readValue<List<User>>(input, object : TypeReference<List<User>>() {})

        userList.forEach { user ->
            userRepository.save(user)
        }

        val postInput = ClassPathResource("/json/posts.json").inputStream
        val postList = objectMapper.readValue<List<Post>>(postInput, object : TypeReference<List<Post>>() {})

        postList.forEach { post ->
            post.user = userList.find { it.id == post.userId }
            postRepository.save(post)
        }
    }
}