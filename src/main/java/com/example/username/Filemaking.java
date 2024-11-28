package com.example.username;

import java.io.*;

public class Filemaking {
    public static void createFile(String filePath) {


    String filepath="C:\\Users\\HP\\IdeaProjects\\username\\file.txt";
    File file = new File(filepath);
        try {
            file.getParentFile().mkdirs(); // Ensure parent directories exist
            if (file.createNewFile()) {
                System.out.println("File created successfully: " + file.getAbsolutePath());
            } else {
                System.out.println("File already exists: " + file.getAbsolutePath());
            }

            // Optional: Write some content to the file
            try (FileWriter writer = new FileWriter(file)) {
                writer.write("Maryam:12345\n");
                writer.write("Asfar:4567\n");
                System.out.println("Content written to file.");
            }
        } catch (IOException e) {
            System.err.println("Error occurred while creating the file.");
            e.printStackTrace();
        }


}

    }
