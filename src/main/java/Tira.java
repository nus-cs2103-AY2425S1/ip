import java.util.Scanner;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.lang.Exception;

public class Tira {

    public static void main(String[] args) throws TiraException {
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
            String firstWord = splitCommand[0];
            if (command.equals("bye")) { //BYE
                break;
            } else if (firstWord.equals("list")) { //LIST
                for (int i = 0; i < taskList.size(); i++) {
                    Task currTask = taskList.get(i);
                    printer.print((i + 1) + "." +
                            currTask.getStatusIcon() +
                            taskList.get(i).toString() + "\n");
                    printer.flush();
                }
            } else if (firstWord.equals("mark")) { //MARK
                if (splitCommand.length < 2) {
                    throw new TiraException("MRAW?? WHERE IS THE TASK?");
                }
                int currNum = Integer.parseInt(splitCommand[1]) - 1;
                taskList.get(currNum).markStatus();
                printer.print("NYA! Good job on this task: " + "\n" +
                        taskList.get(currNum).toString() + "\n");
                printer.flush();
            } else if (firstWord.equals("unmark")) { //UNMARK
                if (splitCommand.length < 2) {
                    throw new TiraException("MRAW?? WHERE IS THE TASK?");
                }
                int currNum2 = Integer.parseInt(splitCommand[1]) - 1;
                taskList.get(currNum2).unmarkStatus();
                printer.print("MRAWWW! Don't forget to return to this task: " + "\n" +
                        taskList.get(currNum2).toString() + "\n");
                printer.flush();
            } else if (firstWord.equals("todo")) {//todo
                if (splitCommand.length < 2) {
                    throw new TiraException("MRAW?? WHERE IS THE TASK?");
                }
                String description = "";
                for (int i = 1; i < splitCommand.length; i++) {
                    description += (splitCommand[i]);
                    if (i != splitCommand.length - 1) {
                        description += " ";
                    }
                }
                Task newTask = new ToDo(description);
                Tira.addTask(newTask, taskList);
            } else if (firstWord.equals("deadline")) { // DEADLINE
                if (splitCommand.length < 2) {
                    throw new TiraException("MRAW?? WHERE IS THE TASK?");
                }
                String[] dateCommands = command.split("/");
                Task deadlineTask = new Deadline(dateCommands[0].substring(9), dateCommands[1]);
                Tira.addTask(deadlineTask, taskList);
            } else if (firstWord.equals("event")) { // EVENT
                if (splitCommand.length < 2) {
                    throw new TiraException("MRAW?? WHERE IS THE TASK?");
                }
                String[] dateCommands2 = command.split("/");
                Task eventTask = new Event(dateCommands2[0].substring(6), dateCommands2[1], dateCommands2[2]);
                Tira.addTask(eventTask, taskList);
            } else {
                throw new TiraException("MRA..OW? I think your brain is not braining. Rethink what you want of me...");
            }
        }
            // NO SPECIFIC COMMAND
//            Task task = new Task(command);
//            printer.print("added: " + task.toString() + "\n");
//            printer.flush();
//            taskList.add(task);
        printer.println("Bye. Come back with treats, MIAO!");
        printer.close();
    }

    public static void addTask(Task task, ArrayList<Task> taskList) {
        taskList.add(task);
        System.out.println("Miao! Got it. I've added this task to my cat brain:\n" +
                task.toString() + "\nNow you have " + taskList.size() + " task(s) in the list!");
    }
}

