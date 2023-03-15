package org.example;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;


public class Logger {
    private static final String fileLog = "file.log"; //файл для записи

    public static boolean log(String message) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(fileLog, true));
            writer.write(LocalDateTime.now() + " " + message + "\n");
            writer.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}

