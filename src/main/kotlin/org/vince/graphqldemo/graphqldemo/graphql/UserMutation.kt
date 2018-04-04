package org.vince.graphqldemo.graphqldemo.graphql

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.vince.graphqldemo.graphqldemo.services.UserService

@Component
class UserMutation
@Autowired constructor(val userService: UserService)
    : GraphQLMutationResolver {
    fun update(id: Long, name: String?, email: String?) =
            userService.update(id, name, email)
}