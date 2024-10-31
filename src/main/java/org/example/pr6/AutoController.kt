package org.example.pr6

import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import kotlin.jvm.optionals.getOrNull

@Controller
class AutoController(
    private val repository: AutoRepository
) {

    @QueryMapping
    fun automobiles(): List<Auto> {
        val autos = repository.findAll()
        println(autos.toString())
        return autos
    }

    @MutationMapping
    fun addAutomobile(@Argument auto: AutoInput): Auto? {
        println(auto)
        return runCatching {
            repository.save(auto.toAuto())
        }.getOrNull()
    }

    @MutationMapping
    @Transactional
    fun deleteById(@Argument id: Long): Auto? {
        val automobile = repository.findById(id)
        repository.deleteById(id)
        return automobile.getOrNull()
    }

    @QueryMapping
    fun automobile(@Argument id: Long): Auto? {
        return repository.findById(id).getOrNull()
    }
}