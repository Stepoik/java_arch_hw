package org.example.pr6

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AutoRepository: JpaRepository<Auto, Long> {
}