import java.util.Scanner;

public class Muffin {
    enum Command {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task[] list = new Task[100];
        String logo = " __  __       __  __ _\n" +
                "|  \\/  |_  _ / _|/ _(_)_ _  \n" +
                "| |\\/| | || |  _|  _| | ' \\ \n" +
                "|_|  |_|\\_,_|_| |_| |_|_||_|\n";

        String helloMsg = "Hello~ I'm Muffin \n" +
                "What can I do for you?";

        System.out.println(logo + "\n" + helloMsg);
        command(sc, 0, list);
    }

    public static void command(Scanner sc, int count, Task[] list) {
        String userInput = sc.nextLine();

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
            return;
        }

        switch(command) {
            case BYE:
                System.out.println("Goodbye~ Hope to see you again soon!");
                break;

            case LIST:
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < count; i++) {
                    System.out.println((i+1) + "." + list[i]);
                }
                command(sc, count, list);
                break;

            case MARK:
                Task t = list[Integer.parseInt(remainingString) - 1];
                t.isDone = true;
                System.out.println("Yay! Marked as done:\n" + "\t" + t);
                command(sc, count, list);
                break;

            case UNMARK:
                Task m = list[Integer.parseInt(remainingString) - 1];
                m.isDone = false;
                System.out.println("Ok. Marked as not done yet:\n" + "\t" + m);
                command(sc, count, list);
                break;

            case TODO:
                list[count] = new Todo(remainingString);
                System.out.println("Ok. Added this task:\n" + "\t" + list[count]);
                System.out.println("Now you have " + (count + 1) + " tasks in your list.");
                command(sc, ++count, list);
                break;

            case DEADLINE:
                list[count] = new Deadline(parts[0], parts[1]);
                System.out.println("Ok. Added this task:\n" + "\t" + list[count]);
                System.out.println("Now you have " + (count + 1) + " tasks in your list.");
                command(sc, ++count, list);
                break;

            case EVENT:
                list[count] = new Event(parts[0], parts[1], parts[2]);
                System.out.println("Ok. Added this task:\n" + "\t" + list[count]);
                System.out.println("Now you have " + (count + 1) + " tasks in your list.");
                command(sc, ++count, list);
                break;
        }
    }
}
