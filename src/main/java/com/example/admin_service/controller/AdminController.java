package com.example.admin_service.controller;

import com.example.admin_service.dto.QueueRequestDto;
import com.example.admin_service.service.ServiceBusAdminService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/queues")

public class AdminController {

    private final ServiceBusAdminService adminService;

    public AdminController(ServiceBusAdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping
    public ResponseEntity<String> createQueue(@Valid @RequestBody QueueRequestDto request) {
        adminService.createQueue(request.getName());
        return  ResponseEntity.status(HttpStatus.CREATED)
                .body("Cola '" + request.getName() + "' creada exitosamente en Azure Service Bus. ");
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteQueue(@PathVariable String name) {
        adminService.deleteQueue(name);
        return ResponseEntity.ok("Cola '" + name + "' eliminada exitosamente de Azure Service Bus.");
    }
}
