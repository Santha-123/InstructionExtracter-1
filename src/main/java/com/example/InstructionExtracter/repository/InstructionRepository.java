package com.example.InstructionExtracter.repository;

import com.example.InstructionExtracter.entity.Instruction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface InstructionRepository extends MongoRepository<Instruction, String> {}
