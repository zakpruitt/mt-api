package com.zakpruitt.mtapi.repository;

import com.zakpruitt.mtapi.domain.Sin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SinRepository extends JpaRepository<Sin, Long> {
    Sin findBySinName(String sinName);
}
