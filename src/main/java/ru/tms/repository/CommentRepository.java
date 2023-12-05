package ru.tms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.tms.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
}
