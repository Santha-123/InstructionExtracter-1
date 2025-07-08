package com.example.InstructionExtracter.controller;
import com.example.InstructionExtracter.entity.Instruction;
import com.example.InstructionExtracter.service.InstructionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/instructions")
public class InstructionController {

    private final InstructionService instructionService;

    public InstructionController(InstructionService instructionService) {
        this.instructionService = instructionService;
    }

    @PostMapping("/upload")
    public ResponseEntity<Instruction> uploadDoc(@RequestParam("file") MultipartFile file) {
        try {
            Instruction result = instructionService.extractAndSave(file);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
