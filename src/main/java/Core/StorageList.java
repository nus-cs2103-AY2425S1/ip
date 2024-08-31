package Core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;

public class StorageList {
    private ArrayList<Task> storageList;
    private File saveFile;

    public StorageList() {
        this.storageList = new ArrayList<>();
    }

    public void addTask(String str) {
        if (str.startsWith("todo")) {
            String textString = str.substring(4);

            try {
                Todo newToDo = Todo.createNewTodo(textString);
                this.addToList(newToDo);
                this.writeToSave();
                System.out.println("Task added:\n  " + newToDo + "\nNow you have " + storageList.size() 
                        + " tasks in the list. HAVE FUN ^o^");
            } catch(InputMismatchException e) {
                System.out.println("Please ensure your todo has a description TT");
            }

        } else if (str.startsWith("event")) {
            String textString = str.substring(5);

            try {
                Event newEvent = Event.createNewEvent(textString);
                this.addToList(newEvent);
                this.writeToSave();
                System.out.println("Task added:\n  " + newEvent + "\nNow you have " + storageList.size() 
                        + " tasks in the list. HAVE FUN ^o^");
            } catch(InputMismatchException e) {
                System.out.println("Please ensure your event has a description, a start and end time TT");
            }
            
        } else {    /* remaining case is deadline task cases */
            String textString = str.substring(8);

            try {
                Deadline newDeadline = Deadline.createNewDeadline(textString);
                this.addToList(newDeadline);
                this.writeToSave();
                System.out.println("Task added:\n  " + newDeadline + "\nNow you have " + storageList.size() 
                        + " tasks in the list. HAVE FUN ^o^");
            } catch(InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void announceItems() {
        //i is used to print out the index of each item so it has to be incremented
        for (int i = 0; i < storageList.size(); i++) {
            System.out.println((i + 1) + ". " + storageList.get(i) + "\n");
        }
    }

    public void mark(int index) throws IndexOutOfBoundsException {
        //index - 1 because task list displayed to user starts from 1
        storageList.get(index - 1).completeTask();
        System.out.println("I've marked the task as done ^o^ :\n  " + storageList.get(index - 1).toString());
        this.writeToSave();
    }

    public void unmark(int index) throws IndexOutOfBoundsException {
        //index - 1 because task list displayed to user starts from 1
        storageList.get(index - 1).uncompleteTask();
        System.out.println("I've marked the task as not done T.T :\n  " + storageList.get(index - 1).toString());
        this.writeToSave();
    }

    public void deleteTask(int index) throws IndexOutOfBoundsException{
        Task targetTask = storageList.get(index - 1);
        storageList.remove(targetTask);
        System.out.println("Task removed:\n  " + targetTask + "\nNow you have " + storageList.size() 
                + " tasks in the list. SOLDIER ON! ^o^");
        this.writeToSave();
    }

    public void findSaveFile(String filePath) {
        try {
            saveFile = new File(filePath);
            if (saveFile.createNewFile()) {
                return;
            } else {
                try {
                    Scanner saveReader = new Scanner(saveFile);
                    while (saveReader.hasNextLine()) {
                        this.addTaskFromFile(saveReader.nextLine());
                    }
                    saveReader.close();
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                    saveFile.delete();
                    saveFile.createNewFile();
                }
            }
        } catch (IOException e) {
            System.out.println("Save file could not be created. Info will not be saved.");
        }
    }

    private void addTaskFromFile(String str) throws IOException {
        boolean isCompleted = false;
        try {
            if (str.charAt(4) == 'X') {
                isCompleted = true;
            }
        } catch (IndexOutOfBoundsException e) {
            throw new IOException("Save file has been corrupted. Save progress will be reset");
        }
        

        //format the task string properly using regex
        if (str.startsWith("[T]")) {
            //To-do case
            String description = str.replaceAll(".*]\\s", "");
            this.addToList(Todo.createNewTodo(description, isCompleted));
        } else if (str.startsWith("[D]")) {
            //Deadline case
            String description = str.replaceAll(".*]\\s|\\s\\(.*", "");
            String deadline = str.replaceAll(".*by:\\s|\\)", "");
            this.addToList(Deadline.createNewDeadline(description, deadline, isCompleted));
        } else if (str.startsWith("[E]")) {
            //Event case
            String description = str.replaceAll(".*]\\s|\\s\\(.*", "");
            String startTime = str.replaceAll(".*from:\\s|\\sto:.*", "");
            String endTime = str.replaceAll(".*to:\\s|\\)", "");
            this.addToList(Event.createNewEvent(description, startTime, endTime, isCompleted));
        } else {
            throw new IOException("Save file has been corrupted. Save progress will be reset");
        }
    }
    
    private void addToList(Task task) {
        storageList.add(task);
    }

    private void writeToSave() {
        //Replaces old file with a new file with updated contents
        try {
            File tmp = File.createTempFile("tmp", "");
            BufferedWriter writer = new BufferedWriter(new FileWriter(tmp));
            for (Task t : storageList) {
                writer.write(t.formatStringForSaving() + System.lineSeparator());
            }
            writer.close();
            if(saveFile.delete()) {
                tmp.renameTo(saveFile);
            }
        } catch (IOException e) {
            System.out.println("Unable to write to file. Information may not be saved for the next session.");
        }
    }
}
