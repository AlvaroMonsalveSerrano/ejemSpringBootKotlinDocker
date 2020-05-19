package com.example.ejem1kotlindocker

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("kotlindocker")
data class Ejem1kotlindockerProperties(
        @Value("title")
        var title: String,
        val banner: Banner){

    data class Banner(
            @Value("banner.title")
            val title: String? = null,
            @Value("banner.content")
            val content: String)
}
