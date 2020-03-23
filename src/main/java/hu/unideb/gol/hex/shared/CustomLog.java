package hu.unideb.gol.hex.shared;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class CustomLog {
    private String url;
    private String fileName;

    public void setURL(String url) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd_HH.mm");
        this.url = url + (url.charAt(url.length() - 1) == '/' ? "" : "/");
        this.fileName = LocalDateTime.now().format(formatter) + ".log";

        System.out.println("\nURL: " + this.url +
                "\nFormat: " + LocalDateTime.now().format(formatter) +
                "\nFile: " + this.fileName);

        try {

            File file = new File(this.url + fileName);
            file.createNewFile();
            file.setWritable(true);
            file.setReadable(true);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void log(String message) {
        try (FileWriter fw = new FileWriter(this.url + this.fileName, true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write(message);
            bw.newLine();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}