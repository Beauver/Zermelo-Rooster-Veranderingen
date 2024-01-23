package com.beauver.zermelo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileFunctions {

    public static void writeToFile(String path, String value1, String value2) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // Create a Java object
        ZermeloData zermeloData = new ZermeloData(value1, value2);

        // Ensure that the parent directories exist before creating the file
        Path filePath = Paths.get(path);
        if (!Files.exists(filePath.getParent())) {
            Files.createDirectories(filePath.getParent());
        }

        // Create the file
        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }

        try (FileWriter fileWriter = new FileWriter(path)) {
            // Convert Java object to JSON and write to file
            gson.toJson(zermeloData, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static boolean readFromJSONFile(String filePath) {
        Gson gson = new Gson();

        try {
            // Read JSON file content into a string
            String content = new String(Files.readAllBytes(Paths.get(filePath)));

            // Convert JSON string to Java object
            ZermeloData zermeloData = gson.fromJson(content, ZermeloData.class);

            Main.schoolName = zermeloData.getSchool();
            Main.accessToken = zermeloData.getAccessCode();

            //trying to check wether your accessCode is your accessToken. This may happen when the ZermeloAPI messes up
            try{
                Long accessTokenL = Long.parseLong(Main.accessToken);
                return false;
            }catch (Exception e){
                return true;
            }

        } catch (IOException e) {
            return false;
        }
    }

    private static boolean doesPathExist(String path) {
        Path filePath = Paths.get(path);
        return Files.exists(filePath);
    }
}
