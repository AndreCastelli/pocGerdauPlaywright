package br.com.gerdau.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.Objects;

import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

public class FileUtils {
    public static void deleteRealDirectory(Path directory) throws IOException {
        Files.walk(directory)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);
    }

    public static void deleteDirectory(String path) {
        File file = new File(path);

        try {
            deleteRealDirectory(file.toPath());
            System.out.println("Directory deleted successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void checkAndCreateFolder(String filePath){
        File folder = new File(filePath);

        if(!folder.exists()){
            folder.mkdirs();
        }
    }

}
