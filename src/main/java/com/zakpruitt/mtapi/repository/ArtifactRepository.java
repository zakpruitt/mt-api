package com.zakpruitt.mtapi.repository;

import com.zakpruitt.mtapi.domain.Artifact.Artifact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtifactRepository extends JpaRepository<Artifact, Long> {
    Artifact findByArtifactName(String artifactName);
}
