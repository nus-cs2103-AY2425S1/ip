import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.io.IOException;
import java.nio.file.Files;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Cook {
    public static void main(String[] args) {

        // Solution below adapted from https://www.patorjk.com/software/taag/#p=author&v=0&f=Avatar&t=Cook
        String logo = """ 
                          ____  ____  ____  _  __
                         /   _\\/  _ \\/  _ \\/ |/ /
                         |  /  | / \\|| / \\||   /\s
                         |  \\__| \\_/|| \\_/||   \\\s
                         \\____/\\____/\\____/\\_|\\_\\
                                                \s
                         """;
        System.out.print(logo);
        formatting("Hello, I'm Cook!\nWhat can I do for you?");

        // Solution below inspired by https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/ArrayList.html
        ArrayList<Task> taskList = new ArrayList<>();

        // Solution below inspired by https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Scanner.html
        Scanner input = new Scanner(System.in);
        while (true) {

            String userInput = input.nextLine();
            String[] commands = userInput.split(" ");

            // Breaks loop
            if (userInput.equalsIgnoreCase("bye")) {
                formatting("Bye. Hope to see you again soon!");
                break;
            }

            // Prints out to do list
            else if (userInput.equalsIgnoreCase("list")) {
                StringBuilder taskListString = new StringBuilder();
                for (int i = 0; i < taskList.size(); i++) {
                    int taskNo = i + 1;
                    Task task = taskList.get(i);
                    taskListString.append(taskNo).append(".").append(task.toString()).append("\n");
                }
                formatting(taskListString.toString().stripTrailing());
            }

            // Mark/Unmark tasks
            else if (commands.length == 2 && (commands[0].equalsIgnoreCase("mark") ||
                    commands[0].equalsIgnoreCase("unmark"))) {

                try {
                    int taskIndex = Integer.parseInt(commands[1]) - 1;
                    Task taskToMark = taskList.get(taskIndex);
                    boolean isMarking = commands[0].equalsIgnoreCase("mark");
                    boolean isSuccessful = taskToMark.mark(isMarking);
                    String done = isMarking ? "done" : "not done";

                    if (isSuccessful) {
                        formatting("Alright, I've marked this task as " + done + ":\n   " + taskToMark);
                    } else {
                        formatting("Oh no! The task is already marked as " + done + ":\n" +
                                "Did you intend to do something else?");
                    }

                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    formatting("Oh no! I can't find the task you are referring to... Sorry!");
                }
            }

            // Delete tasks
            else if (commands.length == 2 && commands[0].equalsIgnoreCase("delete")) {
                try {
                    int taskIndex = Integer.parseInt(commands[1]) - 1;
                    Task taskToMark = taskList.remove(taskIndex);
                    formatting("Alright, I've deleted the following task:\n   " + taskToMark.toString());
                } catch (NumberFormatException | IndexOutOfBoundsException e) {
                    formatting("Oh no! I can't find the task you are referring to... Sorry!");
                }
            }

            // Tasks
            else {
                Task newTask;
                if (commands[0].equalsIgnoreCase("todo")) {
                    if (commands.length == 1) {
                        formatting("Oh no! The description of a todo cannot be empty. Please try again!");
                        continue;
                    }

                    // 5 to get everything after "todo "
                    String taskName = userInput.substring(5);
                    newTask = new ToDo(taskName);
                }
                else if (commands[0].equalsIgnoreCase("deadline")) {
                    int indexOfBy = findFirstCommand(commands, "/by");
                    if (indexOfBy == -1) {
                        formatting("Oh no! I can't see the deadline of the task... Perhaps you can try again?");
                        continue;
                    }

                    StringBuilder deadlineDesc = new StringBuilder();
                    for (int i = 1; i < indexOfBy; i++) {
                        deadlineDesc.append(commands[i]).append(" ");
                    }

                    if (deadlineDesc.isEmpty()) {
                        formatting("Oh no! The description of a deadline cannot be empty. Please try again!");
                        continue;
                    }
                    deadlineDesc.deleteCharAt(deadlineDesc.length() - 1);

                    // Solution below inspired by https://www.baeldung.com/java-8-date-time-intro
                    try {
                        LocalDateTime deadlineDateTime = LocalDateTime.parse(commands[indexOfBy + 1] + "T" +
                                commands[indexOfBy + 2]);
                        newTask = new Deadline(deadlineDesc.toString(), deadlineDateTime);
                    } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
                        formatting("Oh no! The deadline date and time must be " +
                                "in a valid format such as YYYY-MM-DD HH:mm. Please try again!");
                        continue;
                    }
                }
                else if (commands[0].equalsIgnoreCase("event")) {
                    int indexOfFrom = findFirstCommand(commands, "/from");
                    int indexOfTo = findFirstCommand(commands, "/to");
                    if (indexOfFrom == -1 || indexOfTo == -1) {
                        formatting("Oh no! I don't know when the event is held... Perhaps you can try again?");
                        continue;
                    }

                    StringBuilder eventDesc = new StringBuilder();
                    for (int i = 1; i < indexOfFrom; i++) {
                        eventDesc.append(commands[i]).append(" ");
                    }

                    if (eventDesc.isEmpty()) {
                        formatting("Oh no! The description of an event cannot be empty. Please try again!");
                        continue;
                    }
                    eventDesc.deleteCharAt(eventDesc.length() - 1);

                    // Solution below inspired by https://www.baeldung.com/java-8-date-time-intro
                    try {
                        LocalDateTime startDateTime = LocalDateTime.parse(commands[indexOfFrom + 1] + "T" +
                                commands[indexOfFrom + 2]);
                        LocalDateTime endDateTime = LocalDateTime.parse(commands[indexOfTo + 1] + "T" +
                                commands[indexOfTo + 2]);
                        if (startDateTime.isAfter(endDateTime)) {
                            formatting("Oh no! Your starting date and time cannot be " +
                                    "after your ending date and time of the event.");
                            continue;
                        }
                        newTask = new Event(eventDesc.toString(), startDateTime, endDateTime);
                    } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
                        formatting("Oh no! The starting and end date of an event must be " +
                                "in a valid format such as YYYY-MM-DD HH:mm. Please try again!");
                        continue;
                    }

                }
                else {
                    formatting("I don't understand what you mean... Could you say it again?");
                    continue;
                }

                // Add task to task list
                taskList.add(newTask);

                // Solution below adapted from https://www.w3schools.com/java/java_files_create.asp
                File taskListFile = new File("data","Task List.txt");

                StringBuilder taskListString = new StringBuilder();
                for (Task t : taskList) {
                    taskListString.append(t.toString()).append("\n");
                }
                try {
                    // Solution below adapted from https://stackoverflow.com/questions/3634853/how-to-create-a-directory-in-java
                    boolean isDirCreated = new File("data").mkdir();
                    boolean isFileCreated = taskListFile.createNewFile();
                    Files.writeString(taskListFile.toPath(), taskListString.toString());

                    if (isFileCreated) {
                        formatting("I've created a new file under \"" + taskListFile + "\""
                                + " to store your task list!");
                    }
                    else {
                        formatting("Your task list file has been updated with the " + commands[0]
                                + "! It can be found under \"" + taskListFile + "\".\n" +
                                "You now have " + taskList.size() + " task(s) in the list!");
                    }

                } catch (IOException e) {
                    formatting("Oh no! I can't find save the task list at the specified path... " +
                            "perhaps its elsewhere?\nYour latest task has not been saved.");
                    taskList.remove(newTask);
                }
            }
        }
    }

    public static void formatting(String text) {
        System.out.println("____________________________________________________________");
        System.out.println(text);
        System.out.println("____________________________________________________________");
    }

    public static int findFirstCommand(String[] commands, String command) {
        for (int i = 0; i < commands.length; i++) {
            if (commands[i].equalsIgnoreCase(command)) {
                return i;
            }
        }
        return -1;
    }
}
