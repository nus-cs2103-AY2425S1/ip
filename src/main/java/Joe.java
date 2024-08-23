import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Joe {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();
        System.out.println("\t" + "Hello! I'm Joe");
        System.out.println("\t" + "What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\t____________________________________________________________");
            String userInput = scanner.nextLine();
            String[] inputArr = userInput.split(" ");
            if (inputArr.length == 0) continue;
            String commandStr = inputArr[0].toUpperCase();
            Commands command = Commands.valueOf(commandStr);
            switch (command) {
                case BYE:
                    System.out.println("\t" + "Bye. Hope to see you again soon!");
                    return;
                case LIST:
                    if (tasks.isEmpty()) System.out.println("\t\uD83C\uDF89 No tasks yet! Looks like you've got a clean slate. Time to add some tasks and conquer the day! \uD83D\uDE80");
                    for (int i = 0; i < tasks.size(); i++) {
                        Task task = tasks.get(i);
                        String msg = String.format("%d. %s", i + 1, task.toString());
                        System.out.println("\t" + msg);
                    }
                    break;
                case MARK:
                case UNMARK:
                    int idx = Integer.parseInt(inputArr[1]); // gets the task index to mark or unmark
                    if (idx > tasks.size() || idx < 1) { // check that task index is valid
                        System.out.println("\t" + "Please input a valid task index");
                        break;
                    }
                    Task task = tasks.get(idx - 1);
                    if (command == Commands.MARK) {
                        task.markDone();
                        System.out.printf("\tNice! I've marked this task as done:\n\t  %s\n", task);
                    } else {
                        task.unmarkDone();
                        System.out.printf("\tOK, I've marked this task as not done yet:\n\t  %s\n", task);
                    }
                    break;
                case TODO:
                case DEADLINE:
                case EVENT:
                    Task newTask = null;
                    switch (command) {
                        case TODO:
                            newTask = new Todo(String.join(" ", Arrays.copyOfRange(inputArr, 1, inputArr.length)));
                            break;
                        case DEADLINE:
                            int byIdx = Arrays.asList(inputArr).indexOf("/by");
                            if (byIdx == -1) {
                                System.out.println("Please input a valid command");
                                continue;
                            }
                            String taskDesc = String.join(" ", Arrays.copyOfRange(inputArr, 1, byIdx));
                            String taskBy = String.join(" ", Arrays.copyOfRange(inputArr, byIdx + 1, inputArr.length));
                            newTask = new Deadline(taskDesc, taskBy);
                            break;
                        case EVENT:
                            int fromIdx = Arrays.asList(inputArr).indexOf("/from");
                            int toIdx = Arrays.asList(inputArr).indexOf("/to");
                            if (fromIdx == -1) {
                                System.out.println("Please input a valid command");
                                continue;
                            }
                            String eventDesc = String.join(" ", Arrays.copyOfRange(inputArr, 1, fromIdx));
                            if (toIdx != -1) {
                                String eventFrom = String.join(" ", Arrays.copyOfRange(inputArr, fromIdx + 1, toIdx));
                                String eventTo = String.join(" ", Arrays.copyOfRange(inputArr, toIdx + 1, inputArr.length));
                                newTask = new Event(eventDesc, eventFrom, eventTo);
                            } else {
                                String eventFrom = String.join(" ", Arrays.copyOfRange(inputArr, fromIdx + 1, inputArr.length));
                                newTask = new Event(eventDesc, eventFrom);
                            }
                            break;
                    }
                    tasks.add(newTask);
                    System.out.println("\tGot it. I've added this task:\n\t  " + newTask);
                    System.out.printf("""
                                Now you have %d tasks in the list.
                            %n""", tasks.size());
            }
        }
    }
}
