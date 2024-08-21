import java.util.Scanner;

public class Hyperion {
    public static void main(String[] args) {
        String solidLine = "_".repeat(50) + "\n";
        String greet1 = "Hello! I'm Hyperion\n";
        String greet2 = "What can I do for you?\n";
        String exit = "Bye. Hope to see you again soon!";
        // initialize an array of size 100 to store user input
        Task[] allUserInputs = new Task[100];
        int count = 0;
        // greets the user
        System.out.println(solidLine + greet1 + greet2 + solidLine);
        // obtains user input and exits only when user types bye
        String input = "";
        do {
            Scanner scannerObj = new Scanner(System.in);
            input = scannerObj.nextLine().toLowerCase();
            System.out.print(solidLine);
            if (input.equals("list")) {
                for (int i = 0; i < count; i++) {
                    String index = String.format("%d. ", i + 1);
                    System.out.println(index + allUserInputs[i].toString());
                }
                System.out.print(solidLine);
            } else if (input.startsWith("mark") || input.startsWith("unmark")) {
                try {
                    String[] stringsInInput = input.split(" ");
                    String command = " ";
                    int index = -1;
                    for (int i = 0; i < stringsInInput.length; i++) {
                        if (i == 0) {
                            command = stringsInInput[i];
                        }
                        if (i == 1) {
                            index = Integer.parseInt(stringsInInput[i]) - 1;
                        }
                    }
                    if (command.equals("mark")) {
                        if (index >= 0 && index < count) {
                            allUserInputs[index].markAsDone();
                            System.out.println(
                                    "Nice! I've marked this task as done:\n " +
                                            allUserInputs[index].toString());
                        }
                    }
                    if (command.equals("unmark")) {
                        if (index >= 0 && index < count) {
                            allUserInputs[index].markAsNotDone();
                            System.out.println(
                                    "OK, I've marked this task as not done yet:\n " +
                                            allUserInputs[index].toString());
                        }
                    }
                    System.out.print(solidLine);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Input");
                    System.out.print(solidLine);
                }
            } else {
                if (!input.equals("bye")) {
                    System.out.println("added: " + input);
                    allUserInputs[count] = new Task(input);
                    count++;
                    System.out.print(solidLine);
                }
            }
        } while (!input.equals("bye"));
        System.out.println(exit + "\n" + solidLine);
    }
}
