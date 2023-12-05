package ru.tms.DTO;

import lombok.Data;

@Data
public class NewTaskRequestDTO {
    private String head;
    private String description;
}
