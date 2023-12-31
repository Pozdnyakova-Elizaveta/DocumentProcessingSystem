package org.example.repository;

import org.example.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Репозиторий для работы с БД
 */
public interface DocumentsRepository extends JpaRepository<DocumentEntity, Long> {
    /**
     * Метод для обновления значения стутуса
     *
     * @param id     id документа
     * @param status новое значение имени статуса
     */
    @Modifying
    @Query("UPDATE DocumentEntity e SET e.status = :status WHERE e.id = :id")
    void updateStatus(@Param("id") Long id, @Param("status") String status);
}
