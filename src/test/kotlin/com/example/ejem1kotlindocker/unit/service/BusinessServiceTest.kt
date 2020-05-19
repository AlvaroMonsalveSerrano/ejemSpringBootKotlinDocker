package com.example.ejem1kotlindocker.unit.service

import com.example.ejem1kotlindocker.entity.Author
import com.example.ejem1kotlindocker.repositories.AuthorRepository
import com.example.ejem1kotlindocker.service.BusinessService
import com.example.ejem1kotlindocker.service.BusinessServiceRequest
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BusinessServiceTest {

    @Mock
    private lateinit var authorRepository: AuthorRepository

    @InjectMocks
    private lateinit var businessService: BusinessService

    @Test
    fun `operation1`(){

        val authorMock = Author(login = "loginTest", firstname = "firstNameTest", lastname = "lastnameTest")
        whenever(authorRepository.findByLogin( any() )).thenReturn(
                authorMock
        )

        val request = BusinessServiceRequest(login = "Param1")
        val result = businessService.operationBusiness1(request)

        MatcherAssert.assertThat(result.message, CoreMatchers.`is`( "Param1-" + authorMock.login ))

        verify(authorRepository).findByLogin( any() )

    }

}
