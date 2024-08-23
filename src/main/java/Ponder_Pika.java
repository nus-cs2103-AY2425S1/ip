import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Ponder_Pika {

    private final List<Task> myList = new ArrayList<>();

    private void greet() {
        String logo = "Ponder_Pika";

        System.out.println("------------------------------------------------");
        System.out.println("Hello I'm " + logo);
        System.out.println("\nIt is a great day to ponder! How may I help you?");
        System.out.println("------------------------------------------------");
    }

    private static void bidBye() {
        System.out.println("\nBye! See you real soon!");
    }

    public void echo() {
        greet();
        Scanner scan = new Scanner(System.in);
        String userCommand = "";

        while(scan.hasNext()) {
            userCommand = scan.nextLine().trim();

            String[] commands = userCommand.split(" ");

            switch (commands[0]) {
                case "list":
                    for (int i = 0; i < myList.size(); i++) {
                        System.out.println(i+1 + ". " + myList.get(i).toString());
                    }
                    System.out.println("................................................");
                    break;
                case "bye":
                    System.out.println("------------------------------------------------");
                    Ponder_Pika.bidBye();
                    System.out.println("\n------------------------------------------------");
                    break;
                case "mark":
                    int markIndex = Integer.parseInt(commands[1]);
                    myList.get(markIndex - 1).markDone();
                    System.out.println("Your Task has been marked as done.");
                    System.out.println(myList.get(markIndex- 1).toString());
                    System.out.println("------------------------------------------------");
                    break;
                case "unmark":
                    int unMarkIndex = Integer.parseInt(commands[1]);
                    myList.get(unMarkIndex - 1).markUndone();
                    System.out.println("Your Task has been marked as done.");
                    System.out.println(myList.get(unMarkIndex - 1).toString());
                    System.out.println("------------------------------------------------");
                    break;
                default:
                    Task newTask = new Task(userCommand);
                    myList.add(newTask);
                    System.out.println("        Pika! I have added your task: " + userCommand);
                    System.out.println("................................................");
                    break;
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
