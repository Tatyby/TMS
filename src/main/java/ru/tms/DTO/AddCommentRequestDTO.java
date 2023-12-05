package ru.tms.DTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddCommentRequestDTO {
    private Integer task_id;
    private String name;
}
