package org.vince.graphqldemo.graphqldemo.graphql

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.vince.graphqldemo.graphqldemo.services.UserService

@Component
class UserQuery
@Autowired constructor(val userService: UserService)
    : GraphQLQueryResolver {

    fun userCount() = userService.userCount()

    fun users() = userService.listUsers()

    fun usersPaginated(page:Int=0, count:Int=100, sortedBy:String="id") = userService.listUsersPaginated(page, count, sortedBy)
}