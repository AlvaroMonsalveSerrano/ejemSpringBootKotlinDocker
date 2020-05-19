package com.example.ejem1kotlindocker

import com.example.ejem1kotlindocker.entity.Article
import com.example.ejem1kotlindocker.entity.Author
import com.example.ejem1kotlindocker.repositories.ArticleRepository
import com.example.ejem1kotlindocker.repositories.AuthorRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile


@Configuration
@ConfigurationProperties("spring.datasource")
@SuppressWarnings("unused")
class Ejem1kotlindockerConfiguration{

    companion object {
        @Suppress("JAVA_CLASS_ON_COMPANION")
        private val logger = LoggerFactory.getLogger(javaClass.enclosingClass)
    }

    @Value("\${spring.datasource.driver-class-name}")
    private lateinit var driverClassName: String

    @Value("\${spring.datasource.url}")
    private lateinit var url: String

    @Value("\${spring.datasource.username}")
    private lateinit var username: String

    @Value("\${spring.datasource.password}")
    private lateinit var password: String

    @Profile("local")
    @Bean
    fun localDatabaseConnection(): String {
        println("DB connection form Local - MySQL")
        logger.info("[*] DriverClassName=${driverClassName}")
        logger.info("[*] URL=${url}")
        logger.info("[*] UserName=${username}")
        return "DB Connection for Local - MySQL"
    }

    @Profile("dev")
    @Bean
    fun devDatabaseConnection(): String {
        println("DB connection form Local - MySQL")
        logger.info("[*] DriverClassName=${driverClassName}")
        logger.info("[*] URL=${url}")
        logger.info("[*] UserName=${username}")
        return "DB Connection for Local - MySQL"
    }


    @Bean
    fun databaseInitializer(authorRepository: AuthorRepository,
                            articleRepository: ArticleRepository) = ApplicationRunner {

        val smaldini = authorRepository.save(Author("ParamTest", "AuthorTest", "lastNameTest"))

        articleRepository.save(Article(
                title = "Articule1",
                headline = "Headline1",
                content = "Content1",
                author = smaldini
        ))

        articleRepository.save(Article(
                title = "Articule2",
                headline = "Headline2",
                content = "Content3",
                author = smaldini
        ))

    }

}
