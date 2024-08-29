import java.util.Scanner;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.File;

public class Tira {
    private static final String Directory = "./data";
    private static final String FileName = "./data/Tira.txt";
    public static void main(String[] args) throws TiraException, IOException {
        // variable declarations
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        PrintWriter printer = new PrintWriter(System.out);
        String logo = "TIRAMISU THE CAT (TIRA)";
        ArrayList<Task> taskList = new ArrayList<Task>();
        //Solution for Save below (Level-7)  inspired by https://github.com/hansneddyanto/ip/blob/master/src/main/java/Hana.java
        File directory = new File(Directory);
        File file = new File(FileName);
        FileReader fileReader = null;
        try {
            if (!file.exists()) { // if the file exists;
                file.mkdirs();
            }
            fileReader = new FileReader(file);
            System.out.println("File can be opened");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            // if file not found, create a new file
            file.createNewFile();
            System.out.println("File is being created");
        } catch (IOException a) {
            System.out.println(a.getMessage());
        }
        // try loading the file to the list, if it fails, then the file doesn't exist
        // if it doesn't exist, then create a new file.
        // printing
        System.out.println("MIAO (Hello) from\n" + logo + "\n" +
                "What can I do for you, miao?\n");
        // check the user input
        while (true) {
            String command = scanner.nextLine();
            String[] splitCommand = command.split(" ");
            String firstWord = splitCommand[0];
            if (command.equals("bye")) { //BYE
                break;
            } else if (firstWord.equals("list")) { //LIST
                Tira.getList(printer, taskList);
            } else if (firstWord.equals("mark")) { //MARK
                Tira.markTask(printer, taskList, splitCommand);
            } else if (firstWord.equals("unmark")) { //UNMARK
                Tira.unmarkTask(printer, taskList, splitCommand);// ;
            } else if (firstWord.equals("todo")) {//todo
                Tira.addToDo(taskList, splitCommand);
            } else if (firstWord.equals("deadline")) { // DEADLINE
                Tira.addDeadline(taskList, splitCommand, command);
            } else if (firstWord.equals("event")) { // EVENT
                Tira.addEvent(taskList, splitCommand, command);
            } else if (firstWord.equals("delete")){
                Tira.delete(printer, taskList, splitCommand);
            } else {
                throw new TiraException("MRA..OW? I think your brain is not braining. Rethink what you want of me...");
            }
        }
        // NO SPECIFIC COMMAND
//            Task task = new Task(command);
//            printer.print("added: " + task.toString() + "\n");
//            printer.flush();
//            taskList.add(task);
        printer.println("Bye. Come back with treats, MIAO!");
        printer.close();
    }
    private static void getList(PrintWriter printer, ArrayList<Task> taskList) {
        printer.println("HERE ARE THE CURRENT TASKS:");
        printer.flush();
        for (int i = 0; i < taskList.size(); i++) {
            Task currTask = taskList.get(i);
            printer.print((i + 1) + ". " +
                    taskList.get(i).toString() + "\n");
            printer.flush();
        }
    }

    private static void markTask(PrintWriter printer, ArrayList<Task> taskList, String[] splitCommand) throws TiraException {
        if (splitCommand.length < 2) {
            throw new TiraException("MRAW?? WHERE IS THE TASK?");
        }
        int currNum = Integer.parseInt(splitCommand[1]) - 1;
        taskList.get(currNum).markStatus();
        printer.print("NYA! Good job on this task:" + "\n" +
                taskList.get(currNum).toString() + "\n");
        printer.flush();
    }

    private static void unmarkTask(PrintWriter printer, ArrayList<Task> taskList, String[] splitCommand) throws TiraException {
        if (splitCommand.length < 2) {
            throw new TiraException("MRAW?? WHERE IS THE TASK?");
        }
        int currNum2 = Integer.parseInt(splitCommand[1]) - 1;
        taskList.get(currNum2).unmarkStatus();
        printer.print("MRAWWW! Don't forget to return to this task:" + "\n" +
                taskList.get(currNum2).toString() + "\n");
        printer.flush();
    }

    private static void addToDo(ArrayList<Task> taskList, String[] splitCommand) throws TiraException {
        if (splitCommand.length < 2) {
            throw new TiraException("MRAW?? WHERE IS THE TASK?");
        }
        String description = "";
        for (int i = 1; i < splitCommand.length; i++) {
            description += (splitCommand[i]);
            if (i != splitCommand.length - 1) {
                description += " ";
            }
        }
        Task newTask = new ToDo(description);
        Tira.addTask(newTask, taskList);
    }

    private static void addDeadline(ArrayList<Task> taskList, String[] splitCommand, String command) throws TiraException {
        if (splitCommand.length < 2) {
            throw new TiraException("MRAW?? WHERE IS THE TASK?");
        }
        String[] dateCommands = command.split("/");
        Task deadlineTask = new Deadline(dateCommands[0].substring(9), dateCommands[1]);
        Tira.addTask(deadlineTask, taskList);
    }

    private static void addEvent(ArrayList<Task> taskList, String[] splitCommand, String command) throws TiraException {
        if (splitCommand.length < 2) {
            throw new TiraException("MRAW?? WHERE IS THE TASK?");
        }
        String[] dateCommands2 = command.split("/");
        Task eventTask = new Event(dateCommands2[0].substring(6), dateCommands2[1], dateCommands2[2]);
        Tira.addTask(eventTask, taskList);
    }

    private static void delete(PrintWriter printer, ArrayList<Task> taskList, String[] splitCommand) throws TiraException {
        if (splitCommand.length < 2) {
            throw new TiraException("MRAW?? WHERE IS THE TASK?");
        }
        int indexToDelete = Integer.parseInt(splitCommand[1]);
        Task taskToRemove = taskList.get(indexToDelete - 1);
        taskList.remove(indexToDelete - 1);
        printer.println("Noted, Miao! I've removed this task:\n" + taskToRemove +
                "\nNow you have " + taskList.size() + " task(s) in the list!");
    }

    private static void addTask(Task task, ArrayList<Task> taskList) {
        taskList.add(task);
        System.out.println("Miao! Got it. I've added this task to my cat brain:\n" +
                task.toString() + "\nNow you have " + taskList.size() + " task(s) in the list!");
    }
}


