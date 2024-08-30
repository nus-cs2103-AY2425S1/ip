package ava.files;

import ava.task.Task;
import jdk.jshell.spi.ExecutionControl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileManager {


    //TODO: Switch to environment variables
    //TODO: optimize serialization
    private String path;
    private File file;

    /**
     * Default path used to store tasks
     */
    private static final String DEFAULT_PATH="./../data/tasks.txt";

    /**
     * Create a new FileManager with default path
     * <br>
     * Calls {@link FileManager#FileManager(String)} internally to create the FileManager
     * Only use this constructor if user doesn't want to set file path
     * @see FileManager#FileManager(String)
     */
    public FileManager(){
        this(DEFAULT_PATH);
    }

    /**
     * Create a new FileManager with given path
     *
     * if no file exists at the given path one is created
     * @param path Path to the file
     */
    public FileManager(String path){
        this.path = path;
        this.file = new File(path);
        try {
            boolean isDirCreated = file.getParentFile().mkdirs();
            if(isDirCreated){
                //TODO: log info
                System.out.println("new dir created");
            }
            boolean fileExists = file.createNewFile();
            if(!fileExists){
                //TODO: log error
                System.out.println("Error creating file");
            }


        } catch (IOException | SecurityException e){
            //TODO: use error system
            System.out.println("Invalid path");
        }
    }

    public List<Task> getTasks(){
        // TODO: implement reading from file
        return new ArrayList<Task>(){
            @Override
            public String toString() {
                StringBuilder out = new StringBuilder();
                for(int i = 1; i <=size();i++){
                    out.append(i);
                    out.append(". ");
                    out.append(get(i - 1));
                    out.append("\n");
                }
                return out.toString();
            }
        };
    }

    public static void main(String[] args) {
        FileManager fileManager = new FileManager();
        System.out.println(fileManager.getTasks());
    }
}
