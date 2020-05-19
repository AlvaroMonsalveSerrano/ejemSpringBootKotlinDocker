package com.example.ejem1kotlindocker.controller

import com.example.ejem1kotlindocker.dto.OkDTO
import com.example.ejem1kotlindocker.service.BusinessService
import com.example.ejem1kotlindocker.service.BusinessServiceRequest
import com.example.ejem1kotlindocker.service.BusinessServiceResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("business")
class AppController {

    companion object {
        @Suppress("JAVA_CLASS_ON_COMPANION")
        private val logger = LoggerFactory.getLogger(javaClass.enclosingClass)
    }

    @Autowired
    private lateinit var businessService: BusinessService

    @GetMapping("operation1")
    fun operation1(): BusinessServiceResponse {
        logger.info("[*] AppController.operation1.")
        val request = BusinessServiceRequest("ParamTest")
        return businessService.operationBusiness1(request)
    }

    @GetMapping("checkdto")
    fun statusEntity(): OkDTO =
            OkDTO(status = "OK", message = "Ok message")

}
