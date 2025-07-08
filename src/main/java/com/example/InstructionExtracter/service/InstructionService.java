package com.example.InstructionExtracter.service;

import java.io.IOException;

import com.example.InstructionExtracter.entity.Instruction;
import org.springframework.web.multipart.MultipartFile;

public interface InstructionService {
    Instruction extractAndSave(MultipartFile file) throws Exception;
}
