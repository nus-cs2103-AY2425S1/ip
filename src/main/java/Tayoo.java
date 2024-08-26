import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Tayoo {
    private static final Logger logger = Logger.getLogger(Tayoo.class.getName());
    private static final String TASKLIST_FILEPATH = "./tasklist.txt";
    private static final ArrayList<Task> tasklist = new ArrayList<>(100);
    private static final String[] exitCodes = {"EXIT", "BYE", "GOODBYE", "CLOSE", "QUIT"};
    public static void main(String[] args) {
        String name = "Tayoo";
        Scanner scanner = new Scanner(System.in);

        //Introduce self
        printText("Hello! I'm " + name + "\nAt your service! O7");

        //Initialise bot
        botInit(scanner);



        //Exit programme
        scanner.close();
        System.exit(0);
    }

    private static void botInit(Scanner scanner) {
        //contains all initialisation
        File f = new File(TASKLIST_FILEPATH);
        try {
            if (f.createNewFile()) {
                printText("Creating a new tasklist file for you!");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        readFromTasklist();
        awaitCommand(scanner);

    }

    private static void printHoriLine() {
        System.out.println("\t_______________________________________________________________________");
    }

    private static void printText(String text) {
        System.out.println("\n");
        System.out.println(text);
        printHoriLine();
    }

    private static void exitBot(Scanner scanner) {
        printText("\tBye. Hope to see you again soon!\n");
        scanner.close();
        System.exit(0);
    }

    private static void awaitCommand(Scanner scanner) {
        while(true) {
            String command = scanner.nextLine().trim();
            String input = command.toUpperCase();

            if (Arrays.asList(exitCodes).contains(input)) {
                exitBot(scanner);
            } else if (input.equals("LIST")) {
                printTaskList();
            } else if (input.startsWith("MARK ")) {
                int taskNumber = Integer.parseInt(input.substring(5).trim()) - 1;
                markTask(taskNumber);
            } else if (input.startsWith("UNMARK ")) {
                int taskNumber = Integer.parseInt(input.substring(7).trim()) - 1;
                unmarkTask(taskNumber);
            } else if (input.startsWith("TODO")){
                try {
                    addTask(new ToDo(command.substring(5).trim()));
                } catch (IndexOutOfBoundsException e) {
                    printText("Add a description to your todo!");
                }
            } else if (input.startsWith("DEADLINE")) {
                try {
                    int deadlineIndex = input.indexOf("/BY ");

                    if (deadlineIndex < 9) {
                        printText("Deadline format incorrect. Format: \"deadline [taskName] /by [deadline]\"." +
                                " Try again please");
                        continue;
                    }
                    if (deadlineIndex == 9) {
                        printText("I see the deadline but no task :(");
                        continue;
                    }

                    addTask(new Deadline(command.substring(9, deadlineIndex - 1).trim(),
                            command.substring(deadlineIndex + 4).trim()));
                } catch (IndexOutOfBoundsException e) {
                    printText("You've made a fatal error! Report it to the developer or face eternal DOOM!!");
                }
            } else if (input.startsWith("EVENT")) {
                try {
                    int startIndex = input.indexOf("/FROM ");
                    int endIndex = input.indexOf("/TO ");
                    String parsedStart = command.substring(startIndex + 5, endIndex - 1).trim();
                    String parsedEnd = command.substring(endIndex + 4).trim();
                    addTask(new Event(command.substring(6, startIndex - 1), parsedStart, parsedEnd));
                } catch (IndexOutOfBoundsException e) {
                    printText("Event format incorrect. Format: \"Event [taskName] /from [start] /to [end]\". " +
                            "Try again please");
                }
            } else if (input.startsWith("DELETE") || input.startsWith("REMOVE")) {
                try {
                    if (input.substring(7).trim().equals("ALL")) {
                        deleteAll();
                        continue;
                    }

                    int taskNumber = Integer.parseInt(input.substring(7).trim()) - 1;
                    deleteTask(tasklist.get(taskNumber));
                } catch (IndexOutOfBoundsException e) {
                    printText("Hey, that task doesn't exist for me to delete!");
                } catch (NumberFormatException e) {
                    printText("Hey, that's not a task number! Give me a number please!");
                }
            } else {
                printText("I'm not sure what that means :(");
            }
        }
    }

    private static void addTask(Task task) {
        if (tasklist.size() >= 100) {
            printText("Too many tasks! Complete some first! >:( ");
            return;
        }

        tasklist.add(task);
        try {
            appendToFile(TASKLIST_FILEPATH, task.toTxt() + System.lineSeparator());
        } catch (IOException e) {
            logger.warning("Something went wrong. Could not write to file.");
        }

        String toPrint = "Got it. I've added this task: \n" + task.toString();

        if (tasklist.size() > 1) {
            toPrint += "\n Now you have " + tasklist.size() + " tasks in your list";
        } else {
            toPrint += "\n Now you have " + tasklist.size() + " task in your list";
        }

        printText(toPrint);
    }

    private static void deleteTask(Task task) {
        tasklist.remove(task);
        String toPrint = "Noted. I've removed this task:\n" + task;

        if (tasklist.size() > 1) {
            toPrint += "\n Now you have " + tasklist.size() + " tasks in your list";
        } else {
            toPrint += "\n Now you have " + tasklist.size() + " task in your list";
        }

        printText(toPrint);
    }

    private static void deleteAll() {
        int length = tasklist.size();
        printText("Removing all tasks");
        for (int i = length - 1; i >= 0; i--) {
            tasklist.remove(i);
        }
    }

    private static void printTaskList() {
        StringBuilder toPrint = new StringBuilder("Here are the tasks in your list: \n");
        int length = tasklist.size();

        for (int i = 0; i < length; i++) {
            toPrint.append(i+1 + ". " + tasklist.get(i) + "\n");
        }

        printText(toPrint.toString());
    }

    private static void appendToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd);
        fw.close();
    }

    private static void readFromTasklist() {
        File f = new File(TASKLIST_FILEPATH);
        StringBuilder tasklistStr = new StringBuilder("Added tasks:\n");
        try {
            Scanner s = new Scanner(f);
            if (s.hasNextLine()) {
                printText("I see you have some tasks from the last time we chatted! Pulling them up now :)");
            }
            while (s.hasNextLine()) {
                //read file and add task to tasklist
                String taskStr = s.nextLine();
                Task taskToAdd = parseTask(taskStr);
                tasklistStr.append(taskToAdd).append("\n");
                tasklist.add(taskToAdd);
            }

            printText(tasklistStr.toString());
            s.close();
        } catch (FileNotFoundException e) {
            logger.warning("No tasklist found.");
        }
    }

    private static Task parseTask(String str) {
        Scanner scanner = new Scanner(str);
        scanner.useDelimiter("\\|");
        boolean isComplete;
        String title;

        switch(scanner.next().trim()) {
        case ("Task"):
            isComplete = Boolean.parseBoolean(scanner.next().trim());
            title = scanner.next().trim();
            scanner.close();
            return new Task(title, isComplete);
        case ("Todo"):
            isComplete = Boolean.parseBoolean(scanner.next().trim());
            title = scanner.next().trim();
            scanner.close();
            return new ToDo(title, isComplete);
        case ("Event"):
            isComplete = Boolean.parseBoolean(scanner.next().trim());
            title = scanner.next().trim();
            String start = scanner.next().trim();
            String end = scanner.next().trim();
            scanner.close();
            return new Event(title, start, end, isComplete);
        case ("Deadline"):
            isComplete = Boolean.parseBoolean(scanner.next().trim());
            title = scanner.next().trim();
            String deadline = scanner.next().trim();
            scanner.close();
            return new Deadline(title, deadline, isComplete);
        default:
            //should not ever reach here
            scanner.close();
            logger.warning("Reached end of parse task. Invalid input.");
            return null;
        }
    }

    private static void markTask(int taskNumber) {
        try {
            if (tasklist.get(taskNumber).markAsDone()) {
                updateTxt(taskNumber, true);
                printText("Nice! I've marked this task as done:\n" + tasklist.get(taskNumber));
            } else {
                printText("Hey! You've done that one already!\n" + tasklist.get(taskNumber));
            }
        } catch (IndexOutOfBoundsException e) {
            if (taskNumber < 0) {
                System.out.println("Dude, your task list starts from 1! Input a number that's above 0!");
            } else if (taskNumber > 100) {
                System.out.println("My task list can't go that high! Try a smaller number");
            } else {
                System.out.println("Hmm... my task list doesn't contain that number... try again");
            }
        } catch (NumberFormatException e) {
            printText("Hey, that's not a task number! Give me a number please!");
        }
    }

    private static void unmarkTask(int taskNumber) {
        try {
            if (tasklist.get(taskNumber).unmark()) {
                updateTxt(taskNumber, false);
                printText("OK, I've marked this task as not done yet:\n" + tasklist.get(taskNumber));
            } else {
                printText("Hey! You haven't even done that one yet!\n" + tasklist.get(taskNumber));
            }
        } catch (IndexOutOfBoundsException e) {
            if (taskNumber <= 0) {
                System.out.println("Dude, your task list starts from 1! Input a number that's above 0!");
            } else if (taskNumber > 100) {
                System.out.println("My task list can't go that high! Try a smaller number");
            } else {
                System.out.println("Hmm... my task list doesn't contain that number... try again");
            }
        } catch (NumberFormatException e) {
            printText("Hey, that's not a task number! Give me a number please!");
        }
    }

    private static void updateTxt(int taskNumber, boolean isCompleted) {
        List<String> lines = new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(TASKLIST_FILEPATH));
            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            logger.warning("Cannot find tasklist.txt");
        } catch (IOException e) {
            logger.warning("An error ocurred while reading the file");
        }

        String line = lines.get(taskNumber);
        String[] parts = line.split(" \\| ");
        parts[1] = Boolean.toString(isCompleted);
        lines.set(taskNumber, String.join(" | ", parts));

        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(TASKLIST_FILEPATH));
            for (String updatedLine : lines) {
                writer.write(updatedLine);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            logger.warning("An error occurred while updating the task");
        }
    }


}
