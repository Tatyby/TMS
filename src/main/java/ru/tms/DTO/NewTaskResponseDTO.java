package ru.tms.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewTaskResponseDTO {
    private Integer id;
    private String head;
    private String description;
}
