package duke;

import java.io.*;
import java.util.ArrayList;

public class Storage {

    public static int TODO = 0;
    public static int DEADLINE = 1;
    public static int EVENT = 2;

    /**
     * Returns a substring of message from [startIndex, z) where z is the index
     * of the first escapeCharacter that occurs in message from startIndex onwards.
     *
     * @param message the entire message we want to analyze
     * @param startIndex the starting index for our substring
     * @param escapeCharacter the character that we wish to use as a terminating character
     * @return the desired substring from startIndex up until the first escapeCharacter
     */
    public static String getCommand(String message, int startIndex, char escapeCharacter) {
        int len = message.length();
        int ptr = startIndex;

        while(ptr < len) {
            if(message.charAt(ptr) == escapeCharacter) {
                break;
            }
            ptr++;
        }

        return message.substring(startIndex, ptr++);
    }

    /**
     * Returns a storage object for saving and loading Task Lists
     *
     * @param filepath the path to the storage file where we store
     * the task list data
     * @param ui a ui object for printing to terminal
     */
    Storage(String filepath, Ui ui) {
        this.filepath = filepath;
        this.ui = ui;
    }

    /**
     * Returns void, saves a taskList to a storage file.
     * The data format is TASK_TYPE|MARKED|TASK_NAME|DEADLINE1|DEADLINE2|
     *
     * @param taskList the task list that we wish to save
     * to a storage file
     */
    public void save(TaskList taskList) {

        ArrayList<Task> tasks = taskList.tasks;
        try {
            File myObj = new File(filepath);
            myObj.createNewFile();

            FileWriter fw = new FileWriter(filepath);

            int tasksLen = tasks.size();

            for(int i=0; i<tasksLen; ++i) {
                Task task = tasks.get(i);
                String toWrite = "";
                if (task.type == TODO) {
                    toWrite += "T|";
                } else if (task.type == DEADLINE) {
                    toWrite += "D|";
                } else if (task.type == EVENT) {
                    toWrite += "E|";
                }

                if(task.isMarked) {
                    toWrite += "1|";
                } else {
                    toWrite += "0|";
                }

                toWrite += task.name;
                toWrite += "|";

                if(task.type == TODO) {
                    toWrite += "||";
                }

                if(task.type == DEADLINE) {
                    toWrite += task.deadline + "||";
                }

                if(task.type == EVENT) {
                    toWrite += task.eventTimings[0] + "|" + task.eventTimings[1] + "|";
                }

                toWrite += '\n';

                fw.write(toWrite);
            }

            fw.close();

        } catch (IOException e) {

            System.out.println("An error occurred.");
            e.printStackTrace();

        };
    }

    /**
     * Returns a TaskList, loads a taskList from a storage file into an arraylist.
     * The data format is TASK_TYPE|MARKED|TASK_NAME|DEADLINE1|DEADLINE2|
     *
     * @return a TaskList that stores tasks matching the data stored in
     * the storage file
     */
    public TaskList load() {
        TaskList taskList = new TaskList(ui);

        try {
            File myObj = new File(filepath);
            myObj.createNewFile();

            FileReader fr = new FileReader(filepath);

            BufferedReader br = new BufferedReader(fr);

            String strLine;

            while((strLine = br.readLine()) != null) {
                int ptr = 0;
                int len = strLine.length();
                String taskType = "" + strLine.charAt(ptr);
                ptr += 2;
                String isMarked = "" + strLine.charAt(ptr);
                ptr += 2;
                String taskDescription = getCommand(strLine, ptr, '|');
                ptr += taskDescription.length() + 1;
                String deadline1 = getCommand(strLine, ptr, '|');
                ptr += deadline1.length() + 1;
                String deadline2 = getCommand(strLine, ptr, '|');
                ptr += deadline2.length() + 1;

                Task task;

                if(taskType.equals("T")) {
                    task = new Task(taskDescription, TODO);
                } else if (taskType.equals("D")) {
                    task = new Task(taskDescription, DEADLINE, deadline1);
                } else {
                    String[] eventTimings = new String[] {deadline1, deadline2};
                    task = new Task(taskDescription, EVENT, eventTimings);
                }

                if(isMarked.equals("1")) {
                    task.mark();
                }

                taskList.addTaskWithoutPrinting(task);
            }

        } catch (IOException e) {

            System.out.println("An error occurred.");
            e.printStackTrace();

        };

        return taskList;
    }

    String filepath;
    Ui ui;
}
