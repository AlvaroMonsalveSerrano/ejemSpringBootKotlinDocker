package com.example.ejem1kotlindocker.controller

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class HealtController {

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    fun getStatus() =
            "UP!\n"

}
