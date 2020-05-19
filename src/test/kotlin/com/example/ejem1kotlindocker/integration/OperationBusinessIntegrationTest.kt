//package com.example.ejem1kotlindocker.integration
//
//import com.example.ejem1kotlindocker.Ejem1kotlindockerApplication
//import com.example.ejem1kotlindocker.service.BusinessServiceResponse
//import org.assertj.core.api.Assertions.*
//import org.hamcrest.CoreMatchers
//import org.hamcrest.MatcherAssert
//import org.junit.jupiter.api.AfterAll
//import org.junit.jupiter.api.BeforeAll
//import org.junit.jupiter.api.Test
//import org.junit.runner.RunWith
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.boot.test.web.client.TestRestTemplate
//import org.springframework.boot.test.web.client.getForEntity
//import org.springframework.http.HttpStatus
//import org.springframework.test.context.ActiveProfiles
//import org.springframework.test.context.junit4.SpringRunner
//
//@RunWith(SpringRunner::class)
//@SpringBootTest(
//        classes = arrayOf(Ejem1kotlindockerApplication::class),
//        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
//)
//class OperationBusinessIntegrationTest {
//
//    @Autowired
//    lateinit var restTemplate: TestRestTemplate
//
//    @BeforeAll
//    fun setup() {
//        println(">> Setup")
//    }
//
//    @Test
//    fun `Integration test, business operation 1`(){
//        val entity = restTemplate.getForEntity<BusinessServiceResponse>("/business/operation1")
//        println("Status=${entity}")
//        MatcherAssert.assertThat(entity.statusCode, CoreMatchers.`is`(HttpStatus.OK))
//    }
//
//    @Test
//    fun `Integration test, health`(){
//        val entity = restTemplate.getForEntity<String>("/checkdto") //  , BusinessServiceResponse::class
//        println("Status=${entity}")
//        println("Body=${entity.body}")
//        MatcherAssert.assertThat(entity.statusCode, CoreMatchers.`is`( HttpStatus.OK  ))
//    }
//
//    @AfterAll
//    fun teardown() {
//        println(">> Tear down")
//    }
//}
