package ru.tms.DTO;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EditTaskRequestDTO {
    private Integer id;
    private String status;
    private String executor;
}
