package com.example.ejem1kotlindocker.unit.repositories

import com.example.ejem1kotlindocker.entity.Author
import com.example.ejem1kotlindocker.repositories.AuthorRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private lateinit var entityManager: TestEntityManager

    @Autowired
    private lateinit var userRepository: AuthorRepository

    @Test
    fun `When findByLogin then return User`() {
        val juergen = Author("UserTest", "firstNameTest", "lsatnameTest")
        entityManager.persist(juergen)
        entityManager.flush()
        val user = userRepository.findByLogin(juergen.login)
        assertThat(user).isEqualTo(juergen)
    }

}
