package com.example.ejem1kotlindocker.service

import com.example.ejem1kotlindocker.repositories.AuthorRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.lang.StringBuilder

@Service
class BusinessService {
    companion object {
        @Suppress("JAVA_CLASS_ON_COMPANION")
        private val logger = LoggerFactory.getLogger(javaClass.enclosingClass)
    }

    @Autowired
    private lateinit var authorRepository: AuthorRepository

    @Transactional
    fun operationBusiness1(request: BusinessServiceRequest): BusinessServiceResponse {
        logger.info("[**] BusinessService.operation1. Request=${request}")

        val loginRequest = authorRepository.findByLogin(request.login)

        val stringResponse = StringBuilder()
        stringResponse.append(request.login)
        stringResponse.append("-")
        stringResponse.append(loginRequest!!.login)

        val result = BusinessServiceResponse(message = stringResponse.toString())
        logger.info("[**] BusinessService.operation1. result=${result}")

        return result
    }

}

data class BusinessServiceRequest(
        val login: String
)

data class BusinessServiceResponse(
        val message: String
)
