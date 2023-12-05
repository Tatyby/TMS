package ru.tms.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tms.DTO.AddCommentRequestDTO;
import ru.tms.DTO.AddCommentResponseDTO;
import ru.tms.DTO.EditTaskRequestDTO;
import ru.tms.DTO.EditTaskResponseDTO;
import ru.tms.DTO.NewTaskRequestDTO;
import ru.tms.DTO.NewTaskResponseDTO;
import ru.tms.entity.CommentEntity;
import ru.tms.entity.TaskEntity;
import ru.tms.exception.TaskException;
import ru.tms.repository.CommentRepository;
import ru.tms.repository.TaskRepository;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public NewTaskResponseDTO createNewTask(NewTaskRequestDTO newTaskRequestDTO) {
        TaskEntity taskEntity = taskRepository.save(TaskEntity.builder()
                .head(newTaskRequestDTO.getHead())
                .description(newTaskRequestDTO.getDescription())
                .build());
        return NewTaskResponseDTO.builder()
                .id(taskEntity.getId())
                .head(taskEntity.getHead())
                .description(taskEntity.getDescription())
                .build();
    }

    @Transactional
    public EditTaskResponseDTO editTask(EditTaskRequestDTO editTaskRequestDTO) {
        TaskEntity taskEntity = taskRepository.findById(editTaskRequestDTO.getId())
                .orElseThrow(() -> new TaskException("Id задачи " + editTaskRequestDTO.getId() + " не найдено в БД"));
        taskEntity.setExecutor(editTaskRequestDTO.getExecutor());
        taskEntity.setStatus(editTaskRequestDTO.getStatus());
        taskRepository.save(taskEntity);
        return EditTaskResponseDTO.builder()
                .id(taskEntity.getId())
                .description(taskEntity.getDescription())
                .head(taskEntity.getHead())
                .priority(taskEntity.getPriority())
                .status(taskEntity.getStatus())
                .executor(taskEntity.getExecutor())
                .build();
    }

    @Transactional
    public void deleteTask(Integer idTask) {
        TaskEntity taskEntity = taskRepository.findById(idTask)
                .orElseThrow(() -> new TaskException("Id задачи " + idTask + " не найдено в БД"));
        taskRepository.delete(taskEntity);
    }

    @Transactional
    public AddCommentResponseDTO addComment(AddCommentRequestDTO addCommentRequestDTO) {
        TaskEntity taskEntity = taskRepository.findById(addCommentRequestDTO.getTask_id())
                .orElseThrow(() -> new TaskException("Id задачи " + addCommentRequestDTO.getTask_id() + " не найдено в БД"));
        CommentEntity commentEntity = commentRepository.save(CommentEntity.builder()
                .task(taskEntity)
                .name(addCommentRequestDTO.getName())
                .build());
        return AddCommentResponseDTO.builder()
                .id(commentEntity.getId())
                .name(commentEntity.getName())
                .build();
    }
}
