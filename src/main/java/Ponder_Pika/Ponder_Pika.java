package Ponder_Pika;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Ponder_Pika.Task.Event;
import Ponder_Pika.Task.Deadline;
import Ponder_Pika.Task.Task;
import Ponder_Pika.Task.Todo;

public class Ponder_Pika {

    private final List<Task> myList = new ArrayList<>();

    public void printDivider() {
        System.out.println("...........................................................");
    }

    private void greet() {
        String logo = "Ponder_Pika";

        System.out.println("----------------------------------------------------------");
        System.out.println("Hello I'm " + logo);
        System.out.println("\nIt is a great day to ponder! How may I help you?");
        System.out.println("----------------------------------------------------------");
    }

    private static void bidBye() {
        System.out.println("\nBye! See you real soon!");
    }

    public void echo() {
        greet();
        Scanner scan = new Scanner(System.in);
        String userCommand;

        while(scan.hasNext()) {
            userCommand = scan.nextLine().trim();

            String[] commands = userCommand.split(" ", 2);

            try {

                switch (commands[0].toLowerCase()) {
                    case "list":
                        for (int i = 0; i < myList.size(); i++) {
                            System.out.println(i + 1 + ". " + myList.get(i).toString());
                        }
                        printDivider();
                        break;
                    case "bye":
                        System.out.println("------------------------------------------------------------");
                        Ponder_Pika.bidBye();
                        System.out.println("\n----------------------------------------------------------");
                        break;
                    case "mark":
                        int markIndex = Integer.parseInt(commands[1]);

                        if (markIndex > 0 && markIndex <= myList.size()) {
                            myList.get(markIndex - 1).markDone();
                            System.out.println("Your task has been marked as done.");
                            System.out.println(myList.get(markIndex - 1).toString());
                            printDivider();
                        } else {
                            throw new PonderPikaException("No task available at given index!");
                        }

                        break;
                    case "unmark":
                        int unMarkIndex = Integer.parseInt(commands[1]);

                        if (unMarkIndex > 0 && unMarkIndex <= myList.size()) {
                            myList.get(unMarkIndex - 1).markUndone();
                            System.out.println("Your task has been undone.");
                            System.out.println(myList.get(unMarkIndex - 1).toString());
                            printDivider();
                        } else {
                            throw new PonderPikaException("No task available at given index!");
                        }

                        break;
                    case "todo":
                        if (commands.length == 1) {
                            throw new PonderPikaException("Missing Description for Todo Task!");
                        }

                        Task todo = new Todo(commands[1]);
                        myList.add(todo);
                        System.out.println("        Pika! I have added your todo: " + commands[1]);
                        printDivider();
                        break;
                    case "deadline":
                        String[] args = commands[1].split("/by ");

                        if (args.length == 1) {
                            throw new PonderPikaException("Missing Deadline time for Deadline Task!");
                        }

                        if (args[0].trim().isEmpty()) {
                            throw new PonderPikaException("Missing Description for Deadline Task!");
                        }

                        Task deadline = new Deadline(args[0].trim(), args[1].trim());
                        myList.add(deadline);
                        System.out.println("        Pika! I have added a deadline: " + args[0].trim() + " by " + args[1].trim());
                        printDivider();
                        break;
                    case "event":
                        String eventDetails = commands[1].trim();
                        String[] desc = eventDetails.split("/from");

                        if (desc[0].trim().isEmpty()) {
                            throw new PonderPikaException("Missing Description for Event Task!");
                        }

                        String detail = desc[1].trim();
                        String[] time = detail.split("/to");

                        if (time[0].trim().isEmpty()) {
                            throw new PonderPikaException("Missing \"From\" timeline for Event Task!");
                        }

                        if (time[1].trim().isEmpty()) {
                            throw new PonderPikaException("Missing \"To\" timeline for Event Task!");
                        }

                        Task event = new Event(desc[0].trim(), time[0].trim(), time[1].trim());
                        myList.add(event);
                        System.out.println("        Pika! I have added your event: " + desc[0].trim() + " from " + time[0].trim() + " to " + time[1].trim());
                        printDivider();
                        break;
                    default:
                        throw new PonderPikaException("Invalid Command, Please Try Again!");
                }
            } catch (PonderPikaException  e) {
                System.out.println(e.toString());
                printDivider();
            }

            if(userCommand.equals("bye")) {
                break;
            }
        }

    }
    public static void main(String[] args) {
        Ponder_Pika bot = new Ponder_Pika();
        bot.echo();
    }
}
