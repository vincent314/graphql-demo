package graphqldemo.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import graphqldemo.domain.User
import graphqldemo.services.UserService

@RestController
@RequestMapping("/users")
class UserResource
@Autowired constructor(val userService: UserService) {

    @GetMapping("/count")
    fun userCount() = userService.userCount()

    @GetMapping
    fun users(): List<User> = userService.listUsers()

    @GetMapping("/{userId}")
    fun user(@PathVariable userId:Long) = userService.getUser(userId)
}