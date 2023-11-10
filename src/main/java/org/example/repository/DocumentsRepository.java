package org.example.repository;

import org.example.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentsRepository extends JpaRepository<DocumentEntity, Long> {
}
