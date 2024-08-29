package sam;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
/**
 * The DataHandler class is responsible for loading and updating data from a file.
 */
public class DataHandler {
    
    private String filePath;    

    public DataHandler() {
        this.filePath = "./data/Sam.txt";   
    }

    /**
     * Loads data from the file and populates the given ArrayList with items.
     *
     * @param items The ArrayList to populate with items.
     */
    public void loadData(ArrayList<Item> items){
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File does not exist at path: " + filePath);
            System.exit(0);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                switch (parts[0]) {
                case "T":
                    ToDo todo = new ToDo(parts[2]);
                    if (parts[1].equals("1")) {
                        todo.markAsDone();
                    }
                    items.add(todo);
                    break;
                case "D":
                    Deadline deadline = new Deadline(parts[2], parts[3]);
                    if (parts[1].equals("1")) {
                        deadline.markAsDone();
                    } 
                    items.add(deadline);
                    break;
                case "E":
                    Event event = new Event(parts[2], parts[3], parts[4]);
                    if (parts[1].equals("1")) {
                        event.markAsDone();
                    }
                    items.add(event); 
                    break;
                default:
                    throw new IOException("Data in file is not in correct format");
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Updates the file with the data from the given ArrayList of items.
     *
     * @param items The ArrayList of items to update the file with.
     */
    public void updateData(ArrayList<Item> items) {
        try (FileWriter writer = new FileWriter(this.filePath)) {
            for (Item item : items) {
                String toAdd = item.toData();
                writer.write(toAdd);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
