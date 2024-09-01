package nen.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class represents storage for Nen2
 * @author Gan Ren Yick (A0276246X)
 */
public class Storage {
    private final String dataAddr;

    public Storage(String address) {
        dataAddr = address;
    }

    /**
     * Reads and returns the string representation of tasks in file
     * @return Array of Task String representation
     */
    public String[] load() {
        ArrayList<String> out = new ArrayList<>();
        try {
            File f = new File(dataAddr);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                out.add(s.nextLine());
            }
            return out.toArray(new String[0]);
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found ");
            return new String[0];
        }
    }

    /**
     * Saves given array of string into file
     * @param arr of string to be stored into file
     */
    public void save(String[] arr) {
        try {
            FileWriter fw = new FileWriter(dataAddr);

            for (String s : arr) {
                fw.write(s + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Fail to save data");
        }
    }
}
