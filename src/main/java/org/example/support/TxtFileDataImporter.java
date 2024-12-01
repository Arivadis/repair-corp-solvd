package support;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class TxtFileDataImporter implements AutoCloseable {

    private static TxtFileDataImporter instance;

    private TxtFileDataImporter() {
    }

    public static TxtFileDataImporter getInstance() {
        if (instance == null) {
            instance = new TxtFileDataImporter();
        }
        return instance;
    }

    @Override
    public String fileProcess(String filename) throws IOException, InvalidFormatException {
        fileTxtFormatValid(filename);

        Path path = Paths.get(filename);


        if (!Files.exists(path)) {
            throw new IOException("Could not find file");
        }
        StringBuilder lines = new StringBuilder();
        try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {

            String currentLine = "";
            while (true) {
                currentLine = bufferedReader.readLine();

                if (currentLine == null) {
                    break;
                }

                if (currentLine.isEmpty()) {
                    continue;
                }

                if (!stringValid(currentLine)) {
                    throw new InvalidFormatException("File contains not only numbers");
                }

                lines.append('/').append(currentLine);
            }
            // skip catching runtime exception to give CalculationRuntimeException do it
        } finally {
            System.out.println("The invoice consists of -> " + lines);
        }
        return lines.toString();
    }

    private boolean stringValid(String string) {
        return string.matches("[0-9\\.]+");
    }

    private void fileTxtFormatValid(String filename) throws InvalidFormatException {
        ArrayList<String> splitted = new ArrayList<>(Arrays.asList(filename.split("\\.")));
        if (!(splitted.getLast().equals("txt"))) {
            throw new InvalidFormatException("Invalid type, use .txt format");
        }
    }
}
