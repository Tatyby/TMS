package ru.tms.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EditTaskResponseDTO {
    private Integer id;
    private String head;
    private String description;
    private String status;
    private String priority;
    private String executor;
}
