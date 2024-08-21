import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rob {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        int taskCount = 0;

        String greet = "Hello! I'm Rob\n" +
                "What can I do for you?\n";
        String exit = "Bye. Hope to see you again soon!";

        System.out.println(greet);

        while (true) {
            String input = scanner.nextLine();

            if (input.isEmpty()) {
                System.out.println("Invalid input! Please enter a task.");
                continue;
            }

            // exit
            if (Objects.equals(input, "bye")) {
                System.out.println(exit);
                break;
            } else if (Objects.equals(input, "list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i));
                }
                System.out.println("____________________________________________________________\n");
            } else if (input.startsWith("mark")) {
                int taskNum = findTaskNum(input);
                if (taskNum > 0 && taskNum <= taskCount) {
                    if (tasks.get(taskNum - 1).isDone) {
                        System.out.println("Already done!");
                    } else {
                        tasks.get(taskNum - 1).markAsDone();
                        String marked = "____________________________________________________________\n" +
                                "Nice! I've marked this task as done:\n" + tasks.get(taskNum - 1) + "\n" +
                                "____________________________________________________________\n";
                        System.out.println(marked);
                    }
                } else {
                    System.out.println("Invalid task number... Try another?");
                }
            } else if (input.startsWith("unmark")) {
                int taskNum = findTaskNum(input);

                if (taskNum > 0 && taskNum <= taskCount) {
                    if (!tasks.get(taskNum - 1).isDone) {
                        System.out.println("Already unmarked!");
                    } else {
                        tasks.get(taskNum - 1).unmark();
                        String unmarked = "____________________________________________________________\n" +
                                "OK, I've marked this task as not done yet:\n" + tasks.get(taskNum - 1) + "\n" +
                                "____________________________________________________________\n";
                        System.out.println(unmarked);
                    }
                } else {
                    System.out.println("Invalid task number... Try another?");
                }
            } else if (input.startsWith("delete")) {
                try {
                    int taskNum = findTaskNum(input);
                    if (taskNum < 1 || taskNum > taskCount) {
                        throw new DukeException("Invalid task number... Unable to delete");
                    } else {
                        tasks.remove(taskNum - 1);
                        taskCount--;
                    }
                    // echo
                    String echo = "____________________________________________________________\n" +
                            "deleted: " + tasks.get(taskCount - 1) + "\n" +
                            "____________________________________________________________\n";
                    String numTaskInList = "Now you have " + taskCount + " task(s) in the list.\n";
                    System.out.println(echo + numTaskInList);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }

            } else {
                try {
                    if (input.startsWith("deadline")) {
                        if (input.split(" ", 2).length < 2) {
                            throw new DukeException("Invalid format... What deadline would you like to add?");
                        }

                        if (!input.contains(" /by")) {
                            throw new DukeException("Missing '/by' in deadline command.");
                        }
                        String rem = input.split(" ", 2)[1].trim(); // ignore first keyword of input
                        String desc = rem.split(" /by")[0].trim();
                        String day = rem.split(" /by")[1].trim();
                        tasks.add(new Deadline(desc, day));
                        taskCount++;

                    } else if (input.startsWith("event")) {
                        if (input.split(" ", 2).length < 2) {
                            throw new DukeException("Invalid format... What event would you like to add?");
                        }

                        if (!input.contains(" /from") || !input.contains(" /to")) {
                            throw new DukeException("Missing '/from' or '/to' in event command.");
                        }

                        String rem = input.split(" ", 2)[1].trim(); // ignore first keyword of input
                        String desc = rem.split(" /from")[0].trim();
                        String from = rem.split(" /from")[1].split(" /to")[0].trim();
                        String to = rem.split(" /from")[1].split(" /to")[1].trim();
                        tasks.add(new Event(desc, from, to));
                        taskCount++;

                    } else if (input.startsWith("todo")) {
                        if (input.split(" ", 2).length < 2) {
                            throw new DukeException("Invalid format... What todo would you like to add?");
                        }

                        String rem = input.split(" ", 2)[1].trim(); // ignore first keyword of input
                        tasks.add(new Todo(rem));
                        taskCount++;
                    } else {
                        throw new DukeException("I'm sorry... I don't seem to understand.");
                    }
                    // echo
                    String echo = "____________________________________________________________\n" +
                            "added: " + tasks.get(taskCount - 1) + "\n" +
                            "____________________________________________________________\n";
                    String numTaskInList = "Now you have " + taskCount + " task(s) in the list.\n";
                    System.out.println(echo + numTaskInList);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        scanner.close();
    }

    // method to get the task number using regex
    private static int findTaskNum(String input) {
        String regex = "\\d+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        String lastNum = null;

        while (matcher.find()) {
            lastNum = matcher.group();
        }
        return (lastNum != null) ? Integer.parseInt(lastNum) : -1;
    }

}
