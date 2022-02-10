package com.example.kbeproject.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvWriter {
    public CsvWriter() {
    }

    public void writeToCsvFile(List<String> list, File file) throws IOException {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (String line : list) {
                bw.write(line);
                bw.newLine();
            }
        }

    }
}

