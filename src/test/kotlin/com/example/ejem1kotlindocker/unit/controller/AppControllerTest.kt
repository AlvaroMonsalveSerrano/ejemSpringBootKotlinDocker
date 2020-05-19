package com.example.ejem1kotlindocker.unit.controller


import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.example.ejem1kotlindocker.service.BusinessService
import com.example.ejem1kotlindocker.service.BusinessServiceResponse
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@RunWith(SpringRunner::class)
@AutoConfigureMockMvc
@SpringBootTest
class AppControllerTest {

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var businessService: BusinessService


    @org.junit.Before
    fun setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .build()
    }

    @Test
    fun `AppControllerTest statusEntity`(){

        val result = mockMvc.perform( MockMvcRequestBuilders.get("/business/checkdto").accept(MediaType.APPLICATION_JSON) )
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()

        MatcherAssert.assertThat(result.response.status, CoreMatchers.`is`(HttpStatus.OK.value()))

    }

    @Test
    fun `AppControllerTest operation1`(){

        val responseOperation1 = BusinessServiceResponse(message = "MessageTest")

        whenever(businessService.operationBusiness1( any() )).thenReturn(
                responseOperation1
        )

        val result = mockMvc.perform( MockMvcRequestBuilders.get("/business/operation1").accept(MediaType.APPLICATION_JSON) )
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()

        MatcherAssert.assertThat(result.response.status, CoreMatchers.`is`(HttpStatus.OK.value()))

        verify(businessService).operationBusiness1( any() )

    }

}
