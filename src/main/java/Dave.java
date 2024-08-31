import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Dave {

    private void writeToFile(String filePath, String textToAdd) throws IOException, FileNotFoundException
    {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    String file = "C:\\Users\\thamy\\OneDrive\\data\\daveData.txt";
    public static void main(String[] args) {
        String logo = " ____    _    __     ______\n"
                + "|  _ \\  / \\   \\ \\   / / ___|\n"
                + "| | | |/ _ \\   \\ \\ / /|  _|\n"
                + "| |_| / ___ \\   \\ V / | |___\n"
                + "|____/_/   \\_\\   \\_/  |_____|\n";
        String horizontal = "__________________________________________________________";
        enum Command {
            bye, list, mark, unmark, todo, deadline, event, delete
        }
        Command command;
        ArrayList<Task> dataList  = new ArrayList<>();
        String statement;
        System.out.println("Hello from\n" + logo);
        System.out.println(horizontal);
        System.out.println("Hello! I'm Dave.");
        System.out.println("What can I do for you?");
        System.out.println(horizontal);

        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            try {
                String[] reply = scanner.nextLine().trim().split(" ", 2);
                userInput = reply[0];

                if (userInput.isEmpty()) {
                    continue;
                }

                try {
                    command = Command.valueOf(userInput);  // Match directly with enum
                } catch (IllegalArgumentException e) {
                    throw new InvalidCommandException("I'm not sure what you mean. Here are the commands I understand: todo, deadline, event, list, mark, unmark, delete, bye");
                }

                switch (command) {
                    case bye:
                        try {
                            System.out.println(horizontal);
                            System.out.println("Bye. Hope to see you again soon!");
                            System.out.println(horizontal);
                            scanner.close();
                            return;
                        } catch (Exception e) {
                            System.out.println(horizontal);
                            System.out.println("An unexpected error occurred while trying to say goodbye.");
                            System.out.println(horizontal);
                        }
                        break;

                    case list:
                        try {
                            System.out.println(horizontal);
                            for (int i = 0; i < dataList.size(); i++) {
                                statement = String.format("%d.%s", i + 1, dataList.get(i));
                                System.out.println(statement);
                            }
                            System.out.println(horizontal);
                        } catch (Exception e) {
                            System.out.println(horizontal);
                            System.out.println("An unexpected error occurred while listing tasks.");
                            System.out.println(horizontal);
                        }
                        break;

                    case mark:
                        try {
                            int taskNumber = Integer.parseInt(reply[1]);
                            if (dataList.get(taskNumber - 1) == null) {
                                throw new IndexOutOfBoundsException();
                            }
                            dataList.get(taskNumber - 1).markAsDone();
                            System.out.println(horizontal);
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println(dataList.get(taskNumber - 1));
                        } catch (IndexOutOfBoundsException | NumberFormatException e) {
                            System.out.println(horizontal);
                            System.out.println("Oh no! You have entered an invalid number. Please try again.");
                        } catch (Exception e) {
                            System.out.println(horizontal);
                            System.out.println("An unexpected error occurred while marking the task.");
                        }
                        System.out.println(horizontal);
                        break;

                    case unmark:
                        try {
                            int taskNumber = Integer.parseInt(reply[1]);
                            if (dataList.get(taskNumber - 1) == null) {
                                throw new IndexOutOfBoundsException();
                            }
                            dataList.get(taskNumber - 1).markAsNotDone();
                            System.out.println(horizontal);
                            System.out.println("Ok, I've marked this task as not done yet:");
                            System.out.println(dataList.get(taskNumber - 1));
                        } catch (IndexOutOfBoundsException | NumberFormatException e) {
                            System.out.println(horizontal);
                            System.out.println("Oh no! You have entered an invalid number. Please try again.");
                        } catch (Exception e) {
                            System.out.println(horizontal);
                            System.out.println("An unexpected error occurred while unmarking the task.");
                        }
                        System.out.println(horizontal);
                        break;

                    case todo:
                        try {
                            if (reply.length < 2 || reply[1].trim().isEmpty()) {
                                throw new InvalidDescriptionException("Oh No! Please provide a todo task in the format: todo <task>");
                            }
                            Task todoTask = new Todo(reply[1].trim());
                            dataList.add(todoTask);
                            System.out.println(horizontal);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(dataList.get(dataList.size() - 1));
                            System.out.println("Now you have " + dataList.size() + " tasks in the list.");
                        } catch (InvalidDescriptionException e) {
                            System.out.println(horizontal);
                            System.out.println(e.getMessage());
                        } catch (Exception e) {
                            System.out.println(horizontal);
                            System.out.println("An unexpected error occurred while adding the todo task.");
                        }
                        System.out.println(horizontal);
                        break;

                    case deadline:
                        try {
                            if (reply.length < 2 || reply[1].trim().isEmpty()) {
                                throw new InvalidDescriptionException("Oh No! Please provide a deadline task in the format: deadline <task> /by <date>");
                            }
                            String[] deadlineParts = reply[1].split(" /by ");
                            if (deadlineParts.length < 2) {
                                throw new InvalidDescriptionException("Oh No! Please provide a deadline task in the format: deadline <task> /by <date>");
                            }
                            dataList.add(new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim()));
                            System.out.println(horizontal);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(dataList.get(dataList.size() - 1));
                            System.out.println("Now you have " + dataList.size() + " tasks in the list.");
                        } catch (InvalidDescriptionException e) {
                            System.out.println(horizontal);
                            System.out.println(e.getMessage());
                        } catch (Exception e) {
                            System.out.println(horizontal);
                            System.out.println("An unexpected error occurred while adding the deadline task.");
                        }
                        System.out.println(horizontal);
                        break;

                    case event:
                        try {
                            if (reply.length < 2 || reply[1].trim().isEmpty()) {
                                throw new InvalidDescriptionException("Please provide an event task in the format: event <task> /from <start> /to <end>");
                            }
                            String[] eventParts = reply[1].split(" /from | /to ");
                            if (eventParts.length < 3) {
                                throw new InvalidDescriptionException("Please provide an event task in the format: event <task> /from <start> /to <end>");
                            }
                            dataList.add(new Event(eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim()));
                            System.out.println(horizontal);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(dataList.get(dataList.size() - 1));
                            System.out.println("Now you have " + dataList.size() + " tasks in the list.");
                        } catch (InvalidDescriptionException e) {
                            System.out.println(horizontal);
                            System.out.println(e.getMessage());
                        } catch (Exception e) {
                            System.out.println(horizontal);
                            System.out.println("An unexpected error occurred while adding the event task.");
                        }
                        System.out.println(horizontal);
                        break;

                    case delete:
                        try {
                            int taskNumber = Integer.parseInt(reply[1]) - 1;
                            if (taskNumber < 0 || taskNumber >= dataList.size()) {
                                throw new IndexOutOfBoundsException();
                            }
                            Task removedTask = dataList.remove(taskNumber);
                            System.out.println(horizontal);
                            System.out.println("Noted. I've removed this task:");
                            System.out.println(removedTask);
                            System.out.println("Now you have " + dataList.size() + " tasks in the list.");
                        } catch (IndexOutOfBoundsException | NumberFormatException e) {
                            System.out.println(horizontal);
                            System.out.println("Oh no! You have entered an invalid number. Please try again.");
                        } catch (Exception e) {
                            System.out.println(horizontal);
                            System.out.println("An unexpected error occurred while deleting the task.");
                        }
                        System.out.println(horizontal);
                        break;

                    default:
                        throw new InvalidCommandException("I'm not sure what you mean. Here are the commands I understand: todo, deadline, event, list, mark, unmark, bye");
                }
            } catch (InvalidCommandException e) {
                System.out.println(horizontal);
                System.out.println(e.getMessage());
                System.out.println(horizontal);
            } catch (Exception e) {
                System.out.println(horizontal);
                System.out.println("An unexpected error occurred.");
                System.out.println(horizontal);
            }
        }
    }
}
