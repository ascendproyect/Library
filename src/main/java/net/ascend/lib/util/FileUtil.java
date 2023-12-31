package net.ascend.lib.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created By LeandroSSJ
 * Created on 26/02/2023
 * Protect Name Library
 */
public class FileUtil {

    public static String readJSON(File file){
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            StringBuilder builder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            return builder.length() != 0 ? builder.toString() : null;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}