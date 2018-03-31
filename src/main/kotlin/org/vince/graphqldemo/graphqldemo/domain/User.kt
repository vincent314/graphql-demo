package org.vince.graphqldemo.graphqldemo.domain

import javax.persistence.*
import javax.persistence.CascadeType.ALL

@Entity
data class User(
        @Id var id: Long,
        var name: String,
        var username: String,
        var email: String,
        var phone: String,
        var website: String,
        @OneToOne(cascade = [ALL])
        @JoinColumn(name = "ADDRESS_ID")
        var address: Address,
        @OneToOne(cascade = [ALL])
        @JoinColumn(name = "COMPANY_ID")
        var company: Company
)