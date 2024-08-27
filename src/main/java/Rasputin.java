import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

import java.time.LocalDateTime;
import java.time.DateTimeException;

import java.io.FileNotFoundException;



public class Rasputin {

    private static void printText(String text) {
        System.out.println(lineBreak);
        System.out.println(text);
        System.out.println(lineBreak + "\n");
    }
    private Storage storage;
    private TaskList tasks;

    public Rasputin(String filePath) {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(this.storage.readFile());

    }


    private static final String lineBreak = "____________________________________";

    public static void main(String[] args) {
        String name = "Rasputin";
        Rasputin rasputin = new Rasputin("src/main/data/rasputin.txt");
        boolean isTerminated = false;
        // initialize task list


        // scanner to read user input
        Scanner scanner = new Scanner(System.in);

        // greeting
        printText("Hello, I'm " + name + "!\nWhat can I do for you?");

        while (!isTerminated) {
            String input = scanner.nextLine().trim();
            String command = input.split(" ")[0];

            switch (command) {
                case "bye":
                    isTerminated = true;
                    break;
                case "list":
                    printText(rasputin.tasks.toString());
                    break;
                case "mark":
                    if (rasputin.tasks.isEmpty()) {
                        printText("No tasks in list!");
                        break;
                    }

                    try {
                        int index = Character.getNumericValue(input.charAt(5)) - 1;
                        rasputin.tasks.mark(index);
                        String output = "Marked that as done for you.\n" +
                                rasputin.tasks.get(index).toString();
                        printText(output);
                    } catch (StringIndexOutOfBoundsException e) {
                        printText("ERROR! Task to be marked not specified.");
                    } catch (IndexOutOfBoundsException e) {
                        printText("ERROR! Task not found.");
                    }
                    break;

                case "unmark":
                    if (rasputin.tasks.isEmpty()) {
                        printText("No tasks in list!");
                        break;
                    }
                    try {
                        int index = Character.getNumericValue(input.charAt(7)) - 1;
                        rasputin.tasks.unmark(index);
                        String output = "Task has been unmarked.\n" +
                                rasputin.tasks.get(index).toString();
                        printText(output);
                    } catch (StringIndexOutOfBoundsException e) {
                        printText("ERROR! Task to be unmarked not specified.");
                    } catch (IndexOutOfBoundsException e) {
                        printText("ERROR! Task not found.");
                    }
                    break;

                case "todo":
                    try {
                        String desc = input.substring(5);
                        Todo task = new Todo(desc);
                        rasputin.tasks.add(task);
                        String output = "Added Todo task:\n" + task.toString();
                        output += "\nYou currently have " + rasputin.tasks.size() + " task/s in your list.";
                        printText(output);
                    } catch (StringIndexOutOfBoundsException e) {
                        printText("ERROR! The description of a todo cannot be empty.");
                    } finally {
                        break;
                    }
                case "deadline":
                    try {
                        String str = input.trim().substring(9);
                        String desc = str.split(" /by ")[0];
                        String deadline = str.split(" /by ")[1];
                        Deadline task = new Deadline(desc, deadline);
                        rasputin.tasks.add(task);
                        String output = "Added Deadline task:\n" + task.toString();
                        output += "\nYou currently have " + rasputin.tasks.size() + " task/s in your list.";
                        printText(output);

                    } catch (StringIndexOutOfBoundsException e) {
                        printText("ERROR! The description of a deadline cannot be empty.");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        printText("ERROR! Deadline tasks require a deadline to be completed by.");
                    } catch (IllegalArgumentException e) {
                        printText(e.getMessage());
                    } catch (DateTimeException e) {
                        printText("ERROR! Invalid deadline format.");
                    } finally {
                        break;
                    }

                case "event":
                    try {
                        String str = input.substring(6);
                        String desc = str.split(" /from ")[0];
                        String duration = str.split(" /from ")[1];
                        String from = duration.split(" /to ")[0];
                        String to = duration.split(" /to ")[1];

                        Event task = new Event(desc, from, to);
                        rasputin.tasks.add(task);
                        String output = "Added Event task:\n" + task.toString();
                        output += "\nYou currently have " + rasputin.tasks.size() + " task/s in your list.";
                        printText(output);
                    } catch (StringIndexOutOfBoundsException e) {
                        printText("ERROR! The description of an event cannot be empty.");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        printText("ERROR! Event tasks require a duration for the event.");
                    } catch (IllegalArgumentException e) {
                        printText(e.getMessage());
                    } catch (DateTimeException e) {
                        printText("ERROR! Invalid deadline format.");
                    }finally {
                        break;
                    }
                case "delete":
                    try {
                        int index = (input.charAt(7) - '0' - 1);
                        String output = "Done, removed that task for you.\n" +
                                rasputin.tasks.get(index).toString();
                        rasputin.tasks.remove(index);
                        printText(output);
                    } catch (StringIndexOutOfBoundsException e) {
                        printText("ERROR! Task to be deleted not specified.");
                    } catch (IndexOutOfBoundsException e) {
                        printText("ERROR! Task not found.");
                    } finally {
                        break;
                    }
                default:
                    printText("I'm sorry, I don't understand that command.");
                    break;
            }

        }

        rasputin.storage.writeFile(rasputin.tasks);
        printText("Bye. See you later!");
    }
}
