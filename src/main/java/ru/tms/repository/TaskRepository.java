package ru.tms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tms.entity.TaskEntity;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {
}
