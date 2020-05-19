package com.example.ejem1kotlindocker

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication


@SpringBootApplication
@EnableConfigurationProperties(Ejem1kotlindockerProperties::class)
class Ejem1kotlindockerApplication{

}

fun main(args: Array<String>) {
    runApplication<Ejem1kotlindockerApplication>(*args){
    }
}
