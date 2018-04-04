package graphqldemo.domain

import javax.persistence.*
import javax.persistence.CascadeType.ALL

@Entity
data class Address(
        @Id @GeneratedValue var id: Long?,
        var street: String,
        var suite: String,
        var city: String,
        var zipcode: String,
        @OneToOne(cascade = [ALL])
        @JoinColumn(name = "GEO_ID")
        var geo: Geo
)