package graphqldemo.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Company(
        @Id @GeneratedValue var id: Long?,
        var name: String,
        var catchPhrase: String,
        var bs: String
)