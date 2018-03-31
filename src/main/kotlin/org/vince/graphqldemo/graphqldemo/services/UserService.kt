package org.vince.graphqldemo.graphqldemo.services

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Service
import org.vince.graphqldemo.graphqldemo.data.UserRepository
import org.vince.graphqldemo.graphqldemo.domain.User
import javax.annotation.PostConstruct
import javax.transaction.Transactional

@Service
class UserService
@Autowired constructor(
        val objectMapper: ObjectMapper,
        val userRepository: UserRepository) {
    fun listUsers(): List<User> = userRepository.findAll().toList()

    fun getUser(userId: Long): User? = userRepository.findById(userId).orElseGet(null)

    @PostConstruct
    fun initDatabase() {
        val input = ClassPathResource("/json/users.json").inputStream
        val userList = objectMapper.readValue<List<User>>(input, object : TypeReference<List<User>>() {})

        userList.forEach { user ->
            userRepository.save(user)
        }
    }
}