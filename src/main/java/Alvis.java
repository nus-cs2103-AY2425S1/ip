import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


public class Alvis {
    private static final String FILE_PATH = "./Save.txt";
    
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        List<Task> taskList = new ArrayList<>();
        loadTasks(taskList);
        System.out.println("So you require my assistance?");

        while (true) {
            String userInput = sc.nextLine();
            switch (userInput.split(" ")[0].toLowerCase()) {
            case "bye":
                System.out.println("May we meet again");
                System.exit(0);
                break;
            case "list":
                System.out.println("Let me help you to remember what you need to do ...");
                for (int i = 0; i < taskList.size(); ++i) {
                    System.out.println("" + (i + 1) + ". " + taskList.get(i).toString());
                }
                break;
            case "toggle":
                try {
                    int index = Integer.parseInt(userInput.substring(7)) - 1;
                    taskList.get(index).toggleStatus();
                    System.out.println("As requested, I have changed the status of your task");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("I cannot do that, the index is invalid");
                }
                writeToFile(taskList);
                break;
            case "todo":
                try {
                    Task toDo = new ToDo(userInput.substring(5));
                    addTask(toDo, taskList);
                } catch (StringIndexOutOfBoundsException e) {
                    System.out.println("Please input a body for the task");
                }
                writeToFile(taskList);
                break;
            case "delete":
                try {
                    int index = Integer.parseInt(userInput.substring(7)) - 1;
                    taskList.remove(index);
                    System.out.println("As requested, I have deleted the task");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("I cannot do that, the index is invalid");
                }
                writeToFile(taskList);
                break;
            case "deadline":
                int byIndex = userInput.indexOf("/by");
                Task deadline = new Deadline(userInput.substring(9, byIndex - 1), userInput.substring(byIndex + 4));
                addTask(deadline, taskList);
                writeToFile(taskList);
                break;
            case "event":
                int fromIndex = userInput.indexOf("/from");
                int toIndex = userInput.indexOf("/to");
                Task event = new Event(userInput.substring(6, fromIndex - 1), userInput.substring(fromIndex + 6, toIndex - 1),
                        userInput.substring(toIndex + 4));
                addTask(event, taskList);
                writeToFile(taskList);
                break;
            default:
                System.out.println("Refer to the webpage for the commands");
            }
        }
    }

    public static void addTask(Task task, List<Task> taskList) {
        taskList.add(task);
        System.out.println("Understood, I have added to your list:\n" + task.toString());
        System.out.println("You have " + taskList.size() + " tasks");
    }

    private static void loadTasks(List<Task> taskList) throws IOException {
        File file = new File(FILE_PATH);
        try {
            Scanner sc = new Scanner(file);
            Task task = null;
            while (sc.hasNext()) {
                String nextLine = sc.nextLine();
                System.out.println(nextLine);
                String[] taskData = nextLine.split("_");
                System.out.println(taskData[0]);
                System.out.println(taskData[1]);
                System.out.println(taskData[2]);
                switch (taskData[0]) {
                case "T":
                    task = new ToDo(taskData[2]);
                    taskList.add(task);
                    break;
                case "D":
                    task = new Deadline(taskData[2], taskData[3]);
                    taskList.add(task);
                    break;
                case "E":
                    task = new Event(taskData[2], taskData[3], taskData[4]);
                    taskList.add(task);
                    break;
                }
                System.out.println(task.toString());
                if (taskData[1].equals("1")) {
                    task.toggleStatus();
                }
            }
        } catch (FileNotFoundException e) {
            FileWriter fw = new FileWriter(FILE_PATH, true); // create a FileWriter in append mode
            fw.close();
            }
        }
    


    public static void writeToFile(List<Task> taskList) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (Task task : taskList) {
            System.out.println(task.toSaveFormat());
            fw.write(task.toSaveFormat() + "\n");
        }
        fw.close();
    }
}