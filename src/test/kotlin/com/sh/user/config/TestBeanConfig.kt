package com.sh.user.config

import com.sh.user.common.id.IdGenerator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TestBeanConfig {
    @Bean
    fun idGenerator() : IdGenerator {
        return object : IdGenerator {
            var counter = 1L;
            override fun nextId(): Long {
                return counter++
            }
        }
    }
}