package org.vince.graphqldemo.graphqldemo.data

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import org.vince.graphqldemo.graphqldemo.domain.User

@Repository
interface UserRepository :CrudRepository<User,Long>{
}