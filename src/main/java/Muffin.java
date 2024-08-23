import java.util.ArrayList;
import java.util.Scanner;

public class Muffin {
    enum Command {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();

        String logo = " __  __       __  __ _\n" +
                "|  \\/  |_  _ / _|/ _(_)_ _\n" +
                "| |\\/| | || |  _|  _| | ' \\\n" +
                "|_|  |_|\\_,_|_| |_| |_|_||_|\n";

        String helloMsg = "Hello~ I am Muffin\n" +
                "What can I do for you?";

        System.out.println(logo + "\n" + helloMsg);

        try {
            command(sc, list);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void command(Scanner sc, ArrayList<Task> list) throws MuffinException {
        try {
            String userInput = sc.nextLine();
            int len = list.size();

            // Find the index of the first space
            int firstSpaceIndex = userInput.indexOf(" ");

            // If a space is found, split the first word
            String firstWord = userInput;
            String remainingString = "";
            if (firstSpaceIndex != -1) {
                firstWord = userInput.substring(0, firstSpaceIndex);
                remainingString = userInput.substring(firstSpaceIndex + 1);
            }

            String[] parts = remainingString.split("/");
            for (int i = 1; i < parts.length; i++) {
                // Trim to remove any leading/trailing spaces
                parts[i] = parts[i].trim();

                // Remove the first word in each split part
                int spaceIndex = parts[i].indexOf(" ");
                if (spaceIndex != -1) {
                    parts[i] = parts[i].substring(spaceIndex + 1).trim();
                }
            }

            Command command;
            try {
                command = Command.valueOf(firstWord.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new MuffinException("Um... Not sure what you mean...");
            }

            if (command == Command.MARK || command == Command.UNMARK || command == Command.DELETE) {
                try {
                    int index = Integer.parseInt(remainingString);
                    if (index > len) {
                        throw new MuffinException("Oh no! There is only " + len + " tasks.");
                    } else if (index < 1) {
                        throw new MuffinException("Oh no! This task does not exist!");
                    }
                } catch (NumberFormatException e) {
                    throw new MuffinException("Oh no! You must state the number of the task you'd like to edit!");
                }
            }

            switch (command) {
                case BYE:
                    System.out.println("Goodbye~ Hope to see you again soon!");
                    break;

                case LIST:
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < len; i++) {
                        System.out.println((i + 1) + "." + list.get(i));
                    }
                    command(sc, list);
                    break;

                case MARK:
                    Task t = list.get(Integer.parseInt(remainingString) - 1);
                    t.isDone = true;
                    System.out.println("Yay! Marked as done:\n" + "\t" + t);
                    command(sc, list);
                    break;

                case UNMARK:
                    Task s = list.get(Integer.parseInt(remainingString) - 1);
                    s.isDone = false;
                    System.out.println("Ok. Marked as not done yet:\n" + "\t" + s);
                    command(sc, list);
                    break;

                case DELETE:
                    int index = Integer.parseInt(remainingString) - 1;
                    Task r = list.get(index);
                    list.remove(index);
                    System.out.println("Ok. Task has been removed:\n" + "\t" + r);
                    System.out.println("Now you have " + list.size() + " tasks in your list.");
                    command(sc, list);
                    break;

                case TODO:
                    if (remainingString.isEmpty()) {
                        throw new MuffinException("Oh no! You must have a description for a todo task!");
                    }
                    list.add(new Todo(parts[0]));
                    System.out.println("Ok. Added this task:\n" + "\t" + list.get(len));
                    System.out.println("Now you have " + (len + 1) + " tasks in your list.");
                    command(sc, list);
                    break;

                case DEADLINE:
                    if (parts.length < 2) {
                        throw new MuffinException("Oh no! You must have a description and a deadline for a deadline task!");
                    }
                    list.add(new Deadline(parts[0], parts[1]));
                    System.out.println("Ok. Added this task:\n" + "\t" + list.get(len));
                    System.out.println("Now you have " + (len + 1) + " tasks in your list.");
                    command(sc, list);
                    break;

                case EVENT:
                    if (parts.length < 3) {
                        throw new MuffinException("Oh no! You must have a description and a timeframe for an event task!");
                    }
                    list.add(new Event(parts[0], parts[1], parts[2]));
                    System.out.println("Ok. Added this task:\n" + "\t" + list.get(len));
                    System.out.println("Now you have " + (len + 1) + " tasks in your list.");
                    command(sc, list);
                    break;
            }
        } catch (MuffinException e) {
            System.out.println(e.getMessage());
            command(sc, list);
        }
    }
}
