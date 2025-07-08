package com.example.InstructionExtracter.service;

import com.example.InstructionExtracter.entity.Instruction;
import com.example.InstructionExtracter.repository.InstructionRepository;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

import java.io.InputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class InstructionServiceImpl implements InstructionService {

    private final InstructionRepository repository;

    public InstructionServiceImpl(InstructionRepository repository) {
        this.repository = repository;
    }

    @Override
    public Instruction extractAndSave(MultipartFile file) throws Exception {
        String header = null;
        String deadline = null;

        try (InputStream is = file.getInputStream(); XWPFDocument doc = new XWPFDocument(is)) {
            for (XWPFParagraph para : doc.getParagraphs()) {
                String text = para.getText().trim();

                if (header == null && text.toUpperCase().startsWith("INSTRUCTION FROM")) {
                    header = text.replace(":", "").trim();
                }

                if (deadline == null) {
                    Matcher matcher = Pattern.compile("\\d{4}-\\d{2}-\\d{2}").matcher(text);
                    if (matcher.find()) {
                        deadline = matcher.group();
                    }
                }

                if (header != null && deadline != null) break;
            }
        }

        if (header == null || deadline == null) {
            throw new IllegalArgumentException("Header or deadline not found in document.");
        }

        Instruction instruction = new Instruction(header, deadline);
        return repository.save(instruction);
    }
}
