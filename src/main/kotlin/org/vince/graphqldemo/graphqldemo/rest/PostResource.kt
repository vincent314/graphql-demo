package org.vince.graphqldemo.graphqldemo.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.vince.graphqldemo.graphqldemo.services.UserService

@RestController
@RequestMapping("/posts")
class PostResource
@Autowired constructor(val userService: UserService){

    @GetMapping
    fun list() = userService.listPosts()
}