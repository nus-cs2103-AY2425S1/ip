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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Tira {
    private static final String Directory = "./data";
    private static final String FileName = "./data/Tira.txt";
    private static DateTimeFormatter IN_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) throws TiraException, IOException {
        // variable declarations
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        PrintWriter printer = new PrintWriter(System.out);
        TaskList tasks = new TaskList();
        String logo = "TIRAMISU THE CAT (TIRA)";
        System.out.println("MIAO (Hello) from\n" + logo + "\n" +
                "What can I do for you, miao?\n");
        // check the user input
        /*
        ArrayList<Task> taskList = new ArrayList<Task>();
         */
        //Solution for Save below (Level-7)  inspired by https://github.com/hansneddyanto/ip/blob/master/src/main/java/Hana.java
        File directory = new File(Directory);
        File file = new File(FileName);
        FileReader fileReader = null;
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {// if file not found, create a new file
        } catch (IOException a) {
            System.out.println(a.getMessage());
        }
        try {
            FileWriter fileWriter = new FileWriter(file, false);
            // try loading the file to the list, if it fails, then the file doesn't exist
            // if it doesn't exist, then create a new file.
            // printing
            while (true) {
                String command = scanner.nextLine();
                String[] splitCommand = command.split(" ");
                String firstWord = splitCommand[0];
                if (command.equals("bye")) { //BYE
                    break;
                } else if (firstWord.equals("list")) { //LIST
                    tasks.getList();
                } else if (firstWord.equals("mark")) { //MARK
                    tasks.markTask(splitCommand);
                    fileWriter.write(tasks.toString());
                } else if (firstWord.equals("unmark")) { //UNMARK
                    tasks.unmarkTask(splitCommand);// ;
                    fileWriter.write(tasks.toString());
                } else if (firstWord.equals("todo")) {//todo
                    tasks.addToDo(splitCommand);
                    fileWriter.write(tasks.toString());
                } else if (firstWord.equals("deadline")) { // DEADLINE
                    tasks.addDeadline(command, splitCommand);
                    fileWriter.write(tasks.toString());
                } else if (firstWord.equals("event")) { // EVENT
                    tasks.addEvent(command, splitCommand);
                    fileWriter.write(tasks.toString());
                } else if (firstWord.equals("delete")) {
                    tasks.delete(splitCommand);
                    fileWriter.write(tasks.toString());
                } else {
                    throw new TiraException("MRA..OW? I think your brain is not braining. Rethink what you want of me...");
                }
                fileWriter.write(tasks.toString());
                fileWriter.flush();
            }
        printer.println("Bye. Come back with treats, MIAO!");
        printer.close();
    } catch (IOException e) {
            System.out.println(e.getMessage());
        }
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
        try {
            LocalDate endDate = LocalDate.parse(dateCommands[1].substring(3).trim(), IN_FORMATTER);
            Task deadlineTask = new Deadline(dateCommands[0], endDate);
            Tira.addTask(deadlineTask, taskList);
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addEvent(ArrayList<Task> taskList, String[] splitCommand, String command) throws TiraException {

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


