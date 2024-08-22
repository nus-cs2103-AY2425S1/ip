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
        ArrayList<Task> taskList = new ArrayList<Task>();
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
                // LIST COMMAND
                if (splitCommand[0].equals("list")) { // check if it wants to list down
                    for (int i = 0; i < taskList.size(); i++) {
                        Task currTask = taskList.get(i);
                        printer.print((i + 1) + "." +
                                currTask.getStatusIcon() +
                                taskList.get(i).toString() + "\n");
                        printer.flush();
                    }
                } else {
                    String firstWord = splitCommand[0];
                    // MARK OR UNMARK COMMAND
                    if (firstWord.equals("mark")) {
                        int currNum = Integer.parseInt(splitCommand[1]) - 1;
                        taskList.get(currNum).markStatus();
                        printer.print("NYA! Good job on this task: " + "\n" +
                                taskList.get(currNum).toString() + "\n");
                        printer.flush();
                    } else if (firstWord.equals("unmark")) {
                        int currNum2 = Integer.parseInt(splitCommand[1]) - 1;
                        taskList.get(currNum2).unmarkStatus();
                        printer.print("MRAWWW! Don't forget to return to this task: " + "\n" +
                                taskList.get(currNum2).toString() + "\n");
                        printer.flush();
                    } else if (firstWord.equals("todo")) {//check if it is a todo task
                        String description = "";
                        for (int i = 1; i < splitCommand.length; i++) {
                            description += (splitCommand[i]);
                            if (i != splitCommand.length - 1) {
                                description += " ";
                            }
                        }
                        Task newTask = new ToDo(description);
                        Tira.addTask(newTask, taskList);
                    } else if (firstWord.equals("deadline")) { //
                        String[] dateCommands = command.split("/");
                        Task deadlineTask = new Deadline(dateCommands[0].substring(9), dateCommands[1]);
                        Tira.addTask(deadlineTask, taskList);
                    } else if (firstWord.equals("event")) {
                        String[] dateCommands2 = command.split("/");
                        Task eventTask = new Event(dateCommands2[0].substring(6), dateCommands2[1], dateCommands2[2]);
                        Tira.addTask(eventTask, taskList);
                    } else {
                        Tira.addTask(new Task(command), taskList);
                    }
                }
            }
            // NO SPECIFIC COMMAND
//            Task task = new Task(command);
//            printer.print("added: " + task.toString() + "\n");
//            printer.flush();
//            taskList.add(task);

        }
        printer.println("Bye. Come back with treats, MIAO!");
        printer.close();
    }

    public static void addTask(Task task, ArrayList<Task> taskList) {
        taskList.add(task);
        System.out.println("Miao! Got it. I've added this task to my cat brain:\n" +
                task.toString() + "\nNow you have " + taskList.size() + " in the list!");
    }
}

