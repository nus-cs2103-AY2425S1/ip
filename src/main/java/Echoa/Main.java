package Echoa;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The Main class is the entry point to the application.
 */

public class Main {
    /**
     * The main method is the entry point to the application.
     * It catches any file related exception and handles them.
     *
     * @param args Arguments inputted into the command line interface.
     */
    public static void main(String[] args) {

        String filePath = "./data/echoa.txt";
        Echoa echoa = new Echoa(filePath);

        try {
            echoa.start();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("An error has occurred when writing to the file");
        } catch (Exception e) {
            System.out.println("System failed. Please contact administrator.");
        }
    }
}
