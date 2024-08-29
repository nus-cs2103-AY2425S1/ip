package neuro;

import neuro.command.Command;
import neuro.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.Arrays;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Neuro {
    private static Task getTaskToAdd(String taskType, String[] taskComponents, String taskIsDone) {
        Task taskToAdd = null;

        switch (taskType) {
            case ("T"):
                taskToAdd = new Todo(taskComponents[2]);
                break;
            case ("D"):
                taskToAdd = new Deadline(taskComponents[2], LocalDateTime.parse(taskComponents[3]));
                break;
            case ("E"):
                taskToAdd = new Event(taskComponents[2], taskComponents[3], taskComponents[4]);
                break;
            default:

                break;
        }

        if (taskIsDone.equals("1") && taskToAdd != null) {
            taskToAdd.markDone();
        }
        return taskToAdd;
    }

    private static ArrayList<Task> loadOrCreateTaskFile(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        try {
            if (!f.exists()) {
                f.getParentFile().mkdirs();
                f.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error encountered: " + e);
        }

        Scanner s = new Scanner(f);
        ArrayList<Task> taskList = new ArrayList<>();

        while (s.hasNext()) {
            String nextLine = s.nextLine();
            String[] taskComponents = nextLine.split(" \\| ");
            String taskType = taskComponents[0];
            String taskIsDone = taskComponents[1];
            Task taskToAdd = getTaskToAdd(taskType, taskComponents, taskIsDone);

            taskList.add(taskToAdd);
        }

        return taskList;
    }

    private static void updateTaskFile(String filePath, ArrayList<Task> taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);

        for (Task task : taskList) {
            fileWriter.write(task.toSaveData() + System.lineSeparator());
        }

        fileWriter.close();
    }

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Neuro(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {

        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            // TODO: Switch to specific or custom exception
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }


    public static void main(String[] args) {
        new Neuro("data/Neuro.txt").run();
    }

//    public static void main(String[] args) {
//        ArrayList<Task> taskList;
//        try {
//            taskList = loadOrCreateTaskFile("./data/neuro.Neuro.txt");
//        } catch (FileNotFoundException e) {
//            System.out.println("No save file found");
//            System.out.println("Error encountered: " + e);
//            return;
//        }
//
//        // Scanner creation format inspired by https://www.w3schools.com/java/java_user_input.asp
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("    ___________________________________________________");
//        System.out.println("    Hii, I'm neuro.Neuro, your chatbot assistant!");
//        System.out.println("    How can I help you?");
//        System.out.println("    ___________________________________________________");
//
//        while (true) {
//            String input = scanner.nextLine();
//            if (input.equals("bye")) {
//                try {
//                    updateTaskFile("./data/neuro.Neuro.txt", taskList);
//                } catch (IOException e) {
//                    System.out.println("Error encountered: " + e);
//                }
//                break;
//            } else if (input.equals("list")) {
//                System.out.println("    ___________________________________________________");
//
//                if (taskList.isEmpty()) {
//                    System.out.println("    You currently have no tasks.");
//                } else {
//                    System.out.println("    Here is a list of all your tasks:");
//                    for (int i = 0; i < taskList.size(); i++) {
//                        System.out.println("    " + (i + 1) + ". " + taskList.get(i));
//                    }
//                }
//
//                System.out.println("    ___________________________________________________");
//            } else if (input.startsWith("mark")) {
//                // String split inspired by https://www.w3schools.com/java/ref_string_split.asp
//                String[] inputComponents = input.split(" ");
//
//                System.out.println("    ___________________________________________________");
//                try {
//                    int taskIndex = Integer.valueOf(inputComponents[1]);
//                    Task task = taskList.get(taskIndex - 1);
//                    task.markDone();
//
//                    System.out.println("    Nice! I've marked this task as done:");
//                    System.out.println("    " + task);
//                } catch (ArrayIndexOutOfBoundsException e) {
//                    System.out.println("    UH OH! Missing index for 'mark' command! Add a valid index for a task" +
//                            " to mark, like 'mark 2'.");
//                } catch (IndexOutOfBoundsException e) {
//                    System.out.println("    UH OH! Index out of bounds! Try calling the command 'list' to verify the" +
//                            " index of the desired task.");
//                } catch (NumberFormatException e) {
//                    System.out.println("    UH OH! Invalid index for 'mark' command! Add a valid index for a task" +
//                            " to mark, like 'mark 2'.");
//                }
//                System.out.println("    ___________________________________________________");
//
//            } else if (input.startsWith("unmark")) {
//                // String split inspired by https://www.w3schools.com/java/ref_string_split.asp
//                String[] inputComponents = input.split("[\s]");
//
//                System.out.println("    ___________________________________________________");
//                try {
//                    int taskIndex = Integer.valueOf(inputComponents[1]);
//                    Task task = taskList.get(taskIndex - 1);
//                    task.markUndone();
//
//                    System.out.println("    Ok, I've marked this task as not done:");
//                    System.out.println("    " + task);
//                } catch (ArrayIndexOutOfBoundsException e) {
//                    System.out.println("    UH OH! Missing index for 'unmark' command! Add a valid index for a task" +
//                            " to unmark, like 'unmark 2'.");
//                } catch (IndexOutOfBoundsException e) {
//                    System.out.println("    UH OH! Index out of bounds! Try calling the command 'list' to verify the" +
//                            " index of the desired task.");
//                } catch (NumberFormatException e) {
//                    System.out.println("    UH OH! Invalid index for 'mark' command! Add a valid index for a task" +
//                            " to mark, like 'mark 2'.");
//                }
//                System.out.println("    ___________________________________________________");
//            } else if (input.startsWith("delete")) {
//                // String split inspired by https://www.w3schools.com/java/ref_string_split.asp
//                String[] inputComponents = input.split("[\s]");
//
//                System.out.println("    ___________________________________________________");
//                try {
//                    int taskIndex = Integer.valueOf(inputComponents[1]);
//                    Task task = taskList.remove(taskIndex - 1);
//
//                    System.out.println("    Ok, I've removed this task:");
//                    System.out.println("    " + task);
//                    System.out.println("    Now you have " + taskList.size() + " tasks in the list.");
//                } catch (ArrayIndexOutOfBoundsException e) {
//                    System.out.println("    UH OH! Missing index for 'delete' command! Add a valid index for a task" +
//                            " to delete, like 'delete 2'.");
//                } catch (IndexOutOfBoundsException e) {
//                    System.out.println("    UH OH! Index out of bounds! Try calling the command 'list' to verify the" +
//                            " index of the desired task.");
//                }
//                System.out.println("    ___________________________________________________");
//            } else {
//                try {
//                    Task task = getTask(input);
//                    taskList.add(task);
//
//                    System.out.println("    ___________________________________________________");
//                    System.out.println("    Ok, I've added this task:");
//                    System.out.println("        " + task);
//                    System.out.println("    Now you have " + taskList.size() + " tasks in the list.");
//                    System.out.println("    ___________________________________________________");
//                } catch (IllegalArgumentException e) {
//                    System.out.println("    ___________________________________________________");
//                    System.out.println("    " + e.getMessage());
//                    System.out.println("    ___________________________________________________");
//                }
//            }
//        }
//    }
}
