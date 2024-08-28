
import java.util.Scanner;

import java.time.DateTimeException;




public class Rasputin {

    private Storage storage;
    private TaskList tasks;


    public Rasputin(String filePath) {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(this.storage.readFile());

    }

    public static void main(String[] args) {

        Rasputin rasputin = new Rasputin("src/main/data/rasputin.txt");
        boolean isTerminated = false;

        // scanner to read user input
        Scanner scanner = new Scanner(System.in);

        // greeting
        Ui.printGreeting();

        while (!isTerminated) {
            String input = scanner.nextLine().trim();
            String command = input.split(" ")[0];

            switch (command) {
                case "bye":
                    isTerminated = true;
                    break;
                case "list":
                    Ui.printList(rasputin.tasks);
                    break;
                case "mark":
                    if (rasputin.tasks.isEmpty()) {
                        Ui.printText("No tasks in list!");
                        break;
                    }

                    try {
                        int index = Character.getNumericValue(input.charAt(5)) - 1;
                        rasputin.tasks.mark(index);
                        String output = "Marked that as done for you.\n" +
                                rasputin.tasks.get(index).toString();
                        Ui.printText(output);
                    } catch (StringIndexOutOfBoundsException e) {
                        Ui.printText("ERROR! Task to be marked not specified.");
                    } catch (IndexOutOfBoundsException e) {
                        Ui.printText("ERROR! Task not found.");
                    }
                    break;

                case "unmark":
                    if (rasputin.tasks.isEmpty()) {
                        Ui.printText("No tasks in list!");
                        break;
                    }
                    try {
                        int index = Character.getNumericValue(input.charAt(7)) - 1;
                        rasputin.tasks.unmark(index);
                        String output = "Task has been unmarked.\n" +
                                rasputin.tasks.get(index).toString();
                        Ui.printText(output);
                    } catch (StringIndexOutOfBoundsException e) {
                        Ui.printText("ERROR! Task to be unmarked not specified.");
                    } catch (IndexOutOfBoundsException e) {
                        Ui.printText("ERROR! Task not found.");
                    }
                    break;

                case "todo":
                    try {
                        String desc = input.substring(5);
                        Todo task = new Todo(desc);
                        rasputin.tasks.add(task);
                        String output = "Added Todo task:\n" + task.toString();
                        output += "\nYou currently have " + rasputin.tasks.size() + " task/s in your list.";
                        Ui.printText(output);
                    } catch (StringIndexOutOfBoundsException e) {
                        Ui.printError("ERROR! The description of a todo cannot be empty.");
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
                        Ui.printText(output);

                    } catch (StringIndexOutOfBoundsException e) {
                        Ui.printError("ERROR! The description of a deadline cannot be empty.");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        Ui.printError("ERROR! Deadline tasks require a deadline to be completed by.");
                    } catch (IllegalArgumentException e) {
                        Ui.printError("ERROR! Invalid deadline format.");
                    } catch (DateTimeException e) {
                        Ui.printError("ERROR! Invalid deadline format.");
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
                        Ui.printText(output);
                    } catch (StringIndexOutOfBoundsException e) {
                        Ui.printError("ERROR! The description of an event cannot be empty.");
                    } catch (ArrayIndexOutOfBoundsException e) {
                        Ui.printError("ERROR! Event tasks require a duration for the event.");
                    } catch (IllegalArgumentException e) {
                        Ui.printError(e.getMessage());
                    } catch (DateTimeException e) {
                        Ui.printError("ERROR! Invalid deadline format.");
                    }finally {
                        break;
                    }
                case "delete":
                    try {
                        int index = (input.charAt(7) - '0' - 1);
                        String output = "Done, removed that task for you.\n" +
                                rasputin.tasks.get(index).toString();
                        rasputin.tasks.remove(index);
                        Ui.printText(output);
                    } catch (StringIndexOutOfBoundsException e) {
                        Ui.printError("ERROR! Task to be deleted not specified.");
                    } catch (IndexOutOfBoundsException e) {
                        Ui.printError("ERROR! Task not found.");
                    } finally {
                        break;
                    }
                default:
                    Ui.printInvalidCommand();
                    break;
            }

        }

        rasputin.storage.writeFile(rasputin.tasks);
        Ui.printFarewell();
    }
}
