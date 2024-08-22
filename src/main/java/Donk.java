import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Donk {

    public static void main(String[] args) {
        String greeting = " ____________________________________________________________\n" +
                " Hello! I'm Donk, the super intelligent chatbot\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\n";
        String byeMsg = "    Bye bro, catch 'ya later\n" +
                "____________________________________________________________\n";
        System.out.println(greeting);

        List<Task> tasks = new ArrayList<>();

        while (true) {
            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();
            String[] inputArray = userInput.split("\\s+");
            if (userInput.isBlank()) {
                continue;
            } else if (userInput.equals("bye")) {
                System.out.println(byeMsg);
                break;
            } else if (userInput.equals("list")) {
                for (int i = 1; i <= tasks.size(); i++) {
                    System.out.println("    " + i + ": " + tasks.get(i - 1).toString());
                }
            } else if (inputArray[0].equals("mark") && inputArray[1].matches("\\d+")) {
                int index = Integer.parseInt(inputArray[1]) - 1;
                System.out.println("    Yo I've marked this thingy as done");
                tasks.get(index).markDone();
                System.out.println("    " + tasks.get(index).toString());
            } else if (inputArray[0].equals("unmark") && inputArray[1].matches("\\d+")) {
                int index = Integer.parseInt(inputArray[1]) - 1;
                System.out.println("    Aights now it's unmarked again");
                tasks.get(index).unmarkDone();
                System.out.println("    " + tasks.get(index).toString());
            } else {
                tasks.add(new Task(userInput));
                System.out.println("    added: " + userInput);
            }
        }

    }
}

