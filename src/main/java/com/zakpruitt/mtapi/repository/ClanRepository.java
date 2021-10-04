package com.zakpruitt.mtapi.repository;

import com.zakpruitt.mtapi.domain.Clan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClanRepository extends JpaRepository<Clan, Long> {
    Clan findByClanName(String clanName);
}
