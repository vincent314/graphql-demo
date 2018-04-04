package graphqldemo.data

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import graphqldemo.domain.User

@Repository
interface UserRepository :CrudRepository<User,Long>, PagingAndSortingRepository<User, Long>