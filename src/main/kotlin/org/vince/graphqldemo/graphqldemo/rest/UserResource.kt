package org.vince.graphqldemo.graphqldemo.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import org.vince.graphqldemo.graphqldemo.domain.User
import org.vince.graphqldemo.graphqldemo.services.UserService

@RestController
class UserResource
@Autowired constructor(val userService: UserService) {

    @GetMapping("/users")
    fun users(): List<User> = userService.listUsers()

    @GetMapping("/users/{userId}")
    fun user(@PathVariable userId:Long) = userService.getUser(userId)
}