package org.vince.graphqldemo.graphqldemo.services

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ClassPathResource
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.vince.graphqldemo.graphqldemo.data.UserRepository
import org.vince.graphqldemo.graphqldemo.domain.User
import javax.annotation.PostConstruct

@Service
class UserService
@Autowired constructor(
        val objectMapper: ObjectMapper,
        val userRepository: UserRepository) {

    fun userCount(): Long = userRepository.count()

    fun listUsers(): List<User> = userRepository.findAll().toList()

    fun listUsersPaginated(page:Int, count:Int, sortedBy:String): List<User> =
            userRepository.findAll(PageRequest.of(
                    page,
                    count,
                    Sort.by(sortedBy)
            ))
                    .toList()

    fun getUser(userId: Long): User? = userRepository.findById(userId).orElseGet(null)

    fun update(id:Long, name:String?, email:String?): Long {
        val newUser = userRepository.findById(id).orElseThrow { Exception("User not found") }
                .also {
                    if(name != null) it.name = name
                    if(email != null) it.email = email
                }

        return userRepository.save(newUser).id
    }

    @PostConstruct
    fun initDatabase() {
        val input = ClassPathResource("/json/users.json").inputStream
        val userList = objectMapper.readValue<List<User>>(input, object : TypeReference<List<User>>() {})

        userList.forEach { user ->
            userRepository.save(user)
        }
    }
}