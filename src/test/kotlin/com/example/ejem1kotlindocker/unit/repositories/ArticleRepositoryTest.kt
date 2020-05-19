package com.example.ejem1kotlindocker.unit.repositories

import com.example.ejem1kotlindocker.entity.Article
import com.example.ejem1kotlindocker.entity.Author
import com.example.ejem1kotlindocker.repositories.ArticleRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
class ArticleRepositoryTest  {

    @Autowired
    private lateinit var entityManager: TestEntityManager

    @Autowired
    private lateinit var articleRepository: ArticleRepository

    @Test
    fun `When findByIdOrNull then return Article`() {
        val juergen = Author("loginUserTest", "firstnameTest", "lastnameTest")
        val juergenInserted = entityManager.persist(juergen)
        assertThat(juergenInserted).isNotNull

        val article = Article("titleTest", "headLineTest", "ContentTest", juergen)
        val articleInserted = entityManager.persist(article)
        assertThat(articleInserted).isNotNull
        entityManager.flush()

        val found = articleRepository.findByIdOrNull(article.id!!)
        assertThat(found).isEqualTo(article)
    }

}
