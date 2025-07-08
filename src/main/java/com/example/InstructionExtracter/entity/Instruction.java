package com.example.InstructionExtracter.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "instructions")
public class Instruction {

    @Id
    private String id;
    private String header;
    private String deadline;

    public Instruction() {}

    public Instruction(String header, String deadline) {
        this.header = header;
        this.deadline = deadline;
    }

    public String getId() { return id; }
    public String getHeader() { return header; }
    public void setHeader(String header) { this.header = header; }
    public String getDeadline() { return deadline; }
    public void setDeadline(String deadline) { this.deadline = deadline; }
}
