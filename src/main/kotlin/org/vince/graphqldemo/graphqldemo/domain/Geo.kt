package org.vince.graphqldemo.graphqldemo.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class Geo (
        @Id @GeneratedValue var id:Long?,
        var lat:String,
        var lng:String)