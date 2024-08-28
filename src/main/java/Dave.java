import java.util.Scanner;

public class Dave {

    public static void main(String[] args) {
        String logo = " ____    _    __     ______\n"
                + "|  _ \\  / \\   \\ \\   / / ___|\n"
                + "| | | |/ _ \\   \\ \\ / /|  _|\n"
                + "| |_| / ___ \\   \\ V / | |___\n"
                + "|____/_/   \\_\\   \\_/  |_____|\n";
        String horizontal = "__________________________________________________________";
        Task[] dataList = new Task[100];
        int dataIndex = 0;
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

                switch (userInput) {
                    case "bye":
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

                    case "list":
                        try {
                            System.out.println(horizontal);
                            for (int i = 0; i < dataIndex; i++) {
                                statement = String.format("%d.%s", i + 1, dataList[i]);
                                System.out.println(statement);
                            }
                            System.out.println(horizontal);
                        } catch (Exception e) {
                            System.out.println(horizontal);
                            System.out.println("An unexpected error occurred while listing tasks.");
                            System.out.println(horizontal);
                        }
                        break;

                    case "mark":
                        try {
                            int taskNumber = Integer.parseInt(reply[1]);
                            if (dataList[taskNumber - 1] == null) {
                                throw new IndexOutOfBoundsException();
                            }
                            dataList[taskNumber - 1].markAsDone();
                            System.out.println(horizontal);
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println(dataList[taskNumber - 1]);
                        } catch (IndexOutOfBoundsException | NumberFormatException e) {
                            System.out.println(horizontal);
                            System.out.println("Oh no! You have entered an invalid number. Please try again.");
                        } catch (Exception e) {
                            System.out.println(horizontal);
                            System.out.println("An unexpected error occurred while marking the task.");
                        }
                        System.out.println(horizontal);
                        break;

                    case "unmark":
                        try {
                            int taskNumber = Integer.parseInt(reply[1]);
                            if (dataList[taskNumber - 1] == null) {
                                throw new IndexOutOfBoundsException();
                            }
                            dataList[taskNumber - 1].markAsNotDone();
                            System.out.println(horizontal);
                            System.out.println("Ok, I've marked this task as not done yet:");
                            System.out.println(dataList[taskNumber - 1]);
                        } catch (IndexOutOfBoundsException | NumberFormatException e) {
                            System.out.println(horizontal);
                            System.out.println("Oh no! You have entered an invalid number. Please try again.");
                        } catch (Exception e) {
                            System.out.println(horizontal);
                            System.out.println("An unexpected error occurred while unmarking the task.");
                        }
                        System.out.println(horizontal);
                        break;

                    case "todo":
                        try {
                            if (reply.length < 2 || reply[1].trim().isEmpty()) {
                                throw new InvalidDescriptionException("Oh No! Please provide a todo task in the format: todo <task>");
                            }
                            dataList[dataIndex] = new Todo(reply[1].trim());
                            dataIndex++;
                            System.out.println(horizontal);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(dataList[dataIndex - 1]);
                            System.out.println("Now you have " + dataIndex + " tasks in the list.");
                        } catch (InvalidDescriptionException e) {
                            System.out.println(horizontal);
                            System.out.println(e.getMessage());
                        } catch (Exception e) {
                            System.out.println(horizontal);
                            System.out.println("An unexpected error occurred while adding the todo task.");
                        }
                        System.out.println(horizontal);
                        break;

                    case "deadline":
                        try {
                            if (reply.length < 2 || reply[1].trim().isEmpty()) {
                                throw new InvalidDescriptionException("Oh No! Please provide a deadline task in the format: deadline <task> /by <date>");
                            }
                            String[] deadlineParts = reply[1].split(" /by ");
                            if (deadlineParts.length < 2) {
                                throw new InvalidDescriptionException("Oh No! Please provide a deadline task in the format: deadline <task> /by <date>");
                            }
                            dataList[dataIndex] = new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim());
                            dataIndex++;
                            System.out.println(horizontal);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(dataList[dataIndex - 1]);
                            System.out.println("Now you have " + dataIndex + " tasks in the list.");
                        } catch (InvalidDescriptionException e) {
                            System.out.println(horizontal);
                            System.out.println(e.getMessage());
                        } catch (Exception e) {
                            System.out.println(horizontal);
                            System.out.println("An unexpected error occurred while adding the deadline task.");
                        }
                        System.out.println(horizontal);
                        break;

                    case "event":
                        try {
                            if (reply.length < 2 || reply[1].trim().isEmpty()) {
                                throw new InvalidDescriptionException("Please provide an event task in the format: event <task> /from <start> /to <end>");
                            }
                            String[] eventParts = reply[1].split(" /from | /to ");
                            if (eventParts.length < 3) {
                                throw new InvalidDescriptionException("Please provide an event task in the format: event <task> /from <start> /to <end>");
                            }
                            dataList[dataIndex] = new Event(eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim());
                            dataIndex++;
                            System.out.println(horizontal);
                            System.out.println("Got it. I've added this task:");
                            System.out.println(dataList[dataIndex - 1]);
                            System.out.println("Now you have " + dataIndex + " tasks in the list.");
                        } catch (InvalidDescriptionException e) {
                            System.out.println(horizontal);
                            System.out.println(e.getMessage());
                        } catch (Exception e) {
                            System.out.println(horizontal);
                            System.out.println("An unexpected error occurred while adding the event task.");
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
