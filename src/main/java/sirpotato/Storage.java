package sirpotato;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;

import java.util.Scanner; 
import java.util.ArrayList; 


/**
 * Storage class handles the reading and writing of tasks to the file
 */
public class Storage {

    private String filePath;
    private DateTimeFormatter saveFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    
    /**
     * Initiates the storage class using the specified file name 
     * 
     * @param filePath the string form of the file path to the chat's history text file
     */
    public Storage(String filePath) {
        this.filePath = filePath;

        try {
            //File f = new File("../../../data/list.txt"); 
            File f = new File(filePath); 
            if (f.createNewFile()) {
                System.out.println("Creating your data file");
            } else {
                System.out.println("Initializing your data file");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Converts date in text file to LocalDate object
     * 
     * @return LocalDate object in the dd-MM-yyyy format
     */
    private LocalDate parseData(String dateToParse) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.parse(dateToParse, formatter);
    }

    /**
     * Loads the existing file data into the toDoList
     * 
     * @return a loaded to-do list
     * @throws FileNotFoundException If the data file is not found 
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> toDoList = new ArrayList<Task>();
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] sectionedString = line.split(" \\| ");
            String type = sectionedString[0];
            boolean isDone = sectionedString[1].equals("1");
            String description = sectionedString[2];
            Task task = null;
            if (type.equals("T")) {
                task = new Todo(description);
            } else if (type.equals("D")) {
                LocalDate dl = parseData(sectionedString[3]);
                task = new Deadline(description, dl);
            } else if (type.equals("E")) {
                LocalDate from = parseData(sectionedString[3]);
                LocalDate to = parseData(sectionedString[4]);
                task = new Event(description, from, to);
            }
            System.out.println(task);
            toDoList.add(task);
        }
        return toDoList;
    }

    /**
     * Saves the toDoList and writes it to the data file
     * 
     * @param toDoList the toDoList that will be saved 
     */
    public void writeToFile(ArrayList<Task> toDoList) throws IOException {
        FileWriter fw = new FileWriter(filePath);

        for (Task task : toDoList) {
            String type = task instanceof Todo ? "T" : task instanceof Deadline ? "D" : "E";
            String isDone = task.isDone ? "1" : "0";
            String textToAdd = type + " | " + isDone + " | " + task.description;
            if (task instanceof Deadline) {
                textToAdd += " | " + ((Deadline) task).getByDate().format(saveFormatter);
            } else if (task instanceof Event) {
                textToAdd += " | " + ((Event) task).getFromDate().format(saveFormatter) + " | " + 
                ((Event) task).getToDate().format(saveFormatter);
            }
            fw.write(textToAdd + System.lineSeparator());
        }
        fw.close();
    }



}