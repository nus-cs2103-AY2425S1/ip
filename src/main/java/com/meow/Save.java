package com.meow;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import com.meow.com.tasks.Task;

import java.nio.file.Path;
import java.nio.file.Paths;
public class Save {
    // Reads a file and returns the contents as a List of tasks
    File file;
    TaskList taskList;
    /*
     * Constructor for Save class
     * @Params TaskList tasks
     * @Return None
    */
    public Save(TaskList tasks) throws java.io.IOException, Meowception {
        file = new File("meow.txt");
        taskList = tasks;
        if (file.exists()) {
            load();
        } else {
            file.createNewFile();
        }
    }

    /*
     * Saves a list of tasks to a file
     * @Params List<Task> tasks
     * @Return None
     */  
    public void saveTasks(TaskList tasks) {
        try {
            this.taskList = tasks;
            java.io.FileWriter writer = new java.io.FileWriter(file);
            for (int i = 0; i < tasks.getSize(); i++) {
                Task task = tasks.get(i);
                String completed = task.isDone() ? "1" : "0";
                writer.write(completed + " " + task.getType() + " " + task.getTaskName() + " " + task.getExtra() +"\n");
            }
            // for (Task task : tasks) {
            //     String completed = task.isDone() ? "1" : "0";
            //     writer.write(completed + " " + task.getType() + " " + task.getTaskName() + " " + task.getExtra() +"\n");
            // }
            writer.close();
        } catch (java.io.IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    

    /*
     * Reads a file and returns the contents as a List of tasks
     * @Params None
     * @Return List<String>
     */
    public List<String> read() throws Meowception, IOException {
        
        try {
            Path path = Paths.get("meow.txt");
            List<String> tasks_String = Files.readAllLines(path);
            //java.util.Scanner reader = new java.util.Scanner(file);
            System.out.println(tasks_String);
            
            //reader.close();
            return tasks_String;
        } catch (java.io.FileNotFoundException e) {
            throw new Meowception("007");
        } 
        
    }

    /*
     * Loads the tasks from the file
     * @Params None
     * @Return void
     */
    public void load() throws Meowception, java.io.IOException {
        Parser parser = new Parser(taskList);
        List<String> tasks = read();
        for (String task : tasks) {
            try {
                String type = task.substring(0, 1);
                
                parser.commandValidation(task.substring(2));
                if (type.equals("1")) {
                    taskList.get(taskList.getSize() - 1).setDone(true);
                } 
            } catch (Meowception e) {
                System.out.println(e.getMessage());
            } catch (java.lang.StringIndexOutOfBoundsException e) {
                //pass
            }
        }

    }
    
}
