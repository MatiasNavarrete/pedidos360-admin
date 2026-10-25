package com.example.admin_service.dto;


import jakarta.validation.constraints.NotBlank;

public class QueueRequestDto {
    @NotBlank(message = "El nombre de la cola es obligatorio")
    private String name;

    public QueueRequestDto() {}

    public QueueRequestDto(String name) { this.name = name; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
