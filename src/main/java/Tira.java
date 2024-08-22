import java.util.Scanner;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Tira {
    public static void main(String[] args) {
        // variable declarations
        Scanner scanner = new Scanner(new InputStreamReader(System.in));
        PrintWriter printer = new PrintWriter(System.out);
        String logo = "TIRAMISU THE CAT (TIRA)";
        ArrayList<Task> userInputs = new ArrayList<Task>();
        // printing
        System.out.println("MIAO (Hello) from\n" + logo + "\n" +
                "What can I do for you, miao?\n");
        // check the user input
        while (true) {
            String command = scanner.nextLine();
            String[] splitCommand = command.split(" ");
            if (command.equals("bye")) {
                break;
            } else { // check other commands
                if (splitCommand[0].equals("list")) { // check if it wants to list down
                    for (int i = 0; i < userInputs.size(); i++) {
                        Task currTask = userInputs.get(i);
                        printer.print((i + 1) + ".[" +
                                currTask.getStatusIcon() +
                                "] " + userInputs.get(i).toString() + "\n");
                        printer.flush();
                    }
                } else // check for mark or unmarked
                    if (splitCommand[0].equals("mark")) {
                        int currNum = Integer.parseInt(splitCommand[1]) - 1;
                        userInputs.get(currNum).markStatus();
                        printer.print("NYA! Good job on this task: " + "\n[" +
                                userInputs.get(currNum).getStatusIcon() +
                                "] " + userInputs.get(currNum).toString() + "\n");
                        printer.flush();
                    } else
                        if (splitCommand[0].equals("unmark")) {
                            int currNum2 = Integer.parseInt(splitCommand[1]) - 1;
                            userInputs.get(currNum2).unmarkStatus();
                            printer.print("MRAWWW! Don't forget to return to this task: " + "\n[" +
                                    userInputs.get(currNum2).getStatusIcon() +
                                    "] " + userInputs.get(currNum2).toString() + "\n");
                            printer.flush();
                        } else { // if it is not a specific command
                    Task task = new Task(command);
                    printer.print("added: " + task + "\n");
                    printer.flush();
                    userInputs.add(task);
                }
            }
        }
       printer.println("Bye :( Come back with treats, MIAO!");
        printer.close();
        // add scanner to take inputs (in string)
        // change all the commands into a lower case
        // have a list of words that could be a command
    }
}
