package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.random.RandomGenerator;

/**
 * DataHandler class that handles Files I/O
 */
public class DataHandler {
    public static String saveDelimiter = "|";
    public static Path programRoot = Paths.get("").toAbsolutePath();
    public static DateTimeFormatter dateSaveFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static DateTimeFormatter dateTimeSaveFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    public static Path cheerPath;

    /**
     * Method to write data to a file
     * Precondition: File already exists in the system
     *
     * @param filePath Path object of the file
     * @param payLoad  List of strings to write to the file, each item will be separated by \n
     * @param isAppend Flag to append data instead of rewriting the file
     * @throws IOException IO fails
     */
    public static void writeFile(Path filePath, List<String> payLoad, boolean isAppend) throws IOException {
        //file exists
        if (isAppend) {
            Files.writeString(filePath, String.join("\n", payLoad), StandardOpenOption.APPEND);
        } else {
            Files.writeString(filePath, String.join("\n", payLoad), StandardOpenOption.TRUNCATE_EXISTING);
        }
    }

    /**
     * Method to read data from a file
     * Precondition: No precondition
     * Guaranteed result:
     * 1. Data directory is created at the level of the program root
     * 2. userName.txt is created
     * @param filePath Path of the file to be read from
     * @return List of strings that contains the file contents
     * @throws IOException When either the directory or file is not created or when the file does not exists
     */
    public static List<String> readFile(Path filePath) throws IOException {
        if (!Files.exists(programRoot.resolve("data"))) {
            Files.createDirectory(programRoot.resolve("data"));
        }

        if (!Files.exists(filePath)) {
            Files.createFile(filePath);
        }
        return Files.readAllLines(filePath);
    }

    /**
     * Reads the saved cheer resource
     * @return List of String that contains the cheer messages
     */
    public static List<String> getSavedCheerMessages() {
        InputStream cheerResourceAsStream = DataHandler.class.getResourceAsStream("/cheer.txt");
        assert cheerResourceAsStream != null;
        BufferedReader resourceReader = new BufferedReader(new InputStreamReader(cheerResourceAsStream));

        return resourceReader.lines().toList();
    }
}
