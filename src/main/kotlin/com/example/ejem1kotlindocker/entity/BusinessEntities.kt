package com.example.ejem1kotlindocker.entity

import com.example.ejem1kotlindocker.extensions.toSlug
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class Author(
        var login: String,
        var firstname: String,
        var lastname: String,
        @Id @GeneratedValue var id: Long? = null)

@Entity
class Article(
        var title: String,
        var headline: String,
        var content: String,
        @ManyToOne var author: Author,
        var slug: String = title.toSlug(),
        var addedAt: LocalDateTime = LocalDateTime.now(),
        @Id @GeneratedValue var id: Long? = null)
