package com.zakpruitt.mtapi.repository;

import com.zakpruitt.mtapi.domain.Mutator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MutatorRepository extends JpaRepository<Mutator, Long> {
    Mutator findByMutatorName(String mutatorName);
}
