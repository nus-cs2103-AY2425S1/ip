import java.util.ArrayList;
import java.util.Scanner;

public class Muffin {
    String filePath = "../taskList.txt";
    Scanner sc = new Scanner(System.in);
    FileProcessor fp = new FileProcessor();
    Parser parser = new Parser();
    TaskList list = new TaskList(fp.readFromFile(filePath));
    enum Command {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT
    }

    public void run() {
        String logo = " __  __       __  __ _\n" +
                "|  \\/  |_  _ / _|/ _(_)_ _\n" +
                "| |\\/| | || |  _|  _| | ' \\\n" +
                "|_|  |_|\\_,_|_| |_| |_|_||_|\n";

        String helloMsg = "Hello~ I am Muffin\n" +
                "What can I do for you?";

        System.out.println(logo + "\n" + helloMsg);

        try {
            command();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Muffin().run();
    }

    public void command() throws MuffinException {
        try {
            String userInput = sc.nextLine();
            int len = list.length();
            String[] parts = parser.parseInput(userInput);

            Command command;
            try {
                command = Command.valueOf(parts[0].toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new MuffinException("Um... Not sure what you mean...");
            }

            if (command == Command.MARK || command == Command.UNMARK || command == Command.DELETE) {
                try {
                    int index = Integer.parseInt(parts[1]);
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
                list.list();
                command();
                break;

            case MARK:
                Task t = list.mark(Integer.parseInt(parts[1]) - 1);
                System.out.println("Yay! Marked as done:\n" + "\t" + t);
                fp.writeToFile(filePath, list.list);
                command();
                break;

            case UNMARK:
                Task s = list.unmark(Integer.parseInt(parts[1]) - 1);
                System.out.println("Ok. Marked as not done yet:\n" + "\t" + s);
                fp.writeToFile(filePath, list.list);
                command();
                break;

            case DELETE:
                Task r = list.delete(Integer.parseInt(parts[1]) - 1);
                System.out.println("Ok. Task has been removed:\n" + "\t" + r);
                System.out.println("Now you have " + list.length() + " tasks in your list.");
                fp.writeToFile(filePath, list.list);
                command();
                break;

            case TODO:
                if (parts[1].isEmpty()) {
                    throw new MuffinException("Oh no! You must have a description for a todo task!");
                }
                list.add(new Todo(parts[1]));
                System.out.println("Ok. Added this task:\n" + "\t" + list.get(len));
                System.out.println("Now you have " + (len + 1) + " tasks in your list.");
                fp.writeToFile(filePath, list.list);
                command();
                break;

            case DEADLINE:
                if (parts.length < 3) {
                    throw new MuffinException("Oh no! You must have a description and a deadline for a deadline task!");
                }
                list.add(new Deadline(parts[1], parts[2]));
                System.out.println("Ok. Added this task:\n" + "\t" + list.get(len));
                System.out.println("Now you have " + (len + 1) + " tasks in your list.");
                fp.writeToFile(filePath, list.list);
                command();
                break;

            case EVENT:
                if (parts.length < 4) {
                    throw new MuffinException("Oh no! You must have a description and a timeframe for an event task!");
                }
                list.add(new Event(parts[1], parts[2], parts[3]));
                System.out.println("Ok. Added this task:\n" + "\t" + list.get(len));
                System.out.println("Now you have " + (len + 1) + " tasks in your list.");
                fp.writeToFile(filePath, list.list);
                command();
                break;
            }
        } catch (MuffinException e) {
            System.out.println(e.getMessage());
            command();
        }
    }
}
