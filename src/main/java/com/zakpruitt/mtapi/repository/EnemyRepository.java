package com.zakpruitt.mtapi.repository;

import com.zakpruitt.mtapi.domain.Enemy.Enemy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnemyRepository extends JpaRepository<Enemy, Long> {
}
