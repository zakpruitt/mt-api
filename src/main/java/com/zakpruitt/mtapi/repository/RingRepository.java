package com.zakpruitt.mtapi.repository;

import com.zakpruitt.mtapi.domain.Enemy.Ring;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RingRepository extends JpaRepository<Ring, Long> {
}
