import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Gray {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        say("""
                Hello! I'm Gray.
                What can I do for you?""");

        while (true) {

            String user = reader.nextLine();

            if (user == null) break;

            if (user.equals("bye")) break;

            if (user.equals("list")) {
                StringJoiner joiner = new StringJoiner("\n");
                for (int i = 0; i < taskList.size(); i++) {
                    joiner.add(String.format("%d. %s", i + 1, taskList.get(i)));
                }
                say(joiner.toString());
                continue;
            }

            { // Add _todo task
                Pattern pattern = Pattern.compile("todo (.*)");
                Matcher matcher = pattern.matcher(user);
                if (matcher.matches()) {
                    String description = matcher.group(1);
                    if (description.isEmpty()) {
                        say("OOPS!!! The description cannot be empty.");
                        continue;
                    }
                    TodoTask task = new TodoTask(description);
                    taskList.add(task);
                    say(String.format("""
                            Got it. I've added this task:
                                %s
                            Now you have %d tasks in the list.""",
                            task, taskList.size()));
                    continue;
                }
            }

            { // Add deadline task
                Pattern pattern = Pattern.compile("deadline (.*) /by (.*)");
                Matcher matcher = pattern.matcher(user);
                if (matcher.matches()) {
                    String description = matcher.group(1);
                    String deadline = matcher.group(2);
                    if (description.isEmpty() || deadline.isEmpty()) {
                        say("OOPS!!! The description and deadline cannot be empty.");
                        continue;
                    }
                    DeadlineTask task = new DeadlineTask(description, deadline);
                    taskList.add(task);
                    say(String.format("""
                            Got it. I've added this task:
                                %s
                            Now you have %d tasks in the list.""",
                            task, taskList.size()));
                    continue;
                }
            }

            { // Add event task
                Pattern pattern = Pattern.compile("event (.*) /from (.*) /to (.*)");
                Matcher matcher = pattern.matcher(user);
                if (matcher.matches()) {
                    String description = matcher.group(1);
                    String start = matcher.group(2);
                    String end = matcher.group(3);
                    if (description.isEmpty() || start.isEmpty() || end.isEmpty()) {
                        say("OOPS!!! The description, start and end cannot be empty.");
                        continue;
                    }
                    EventTask task = new EventTask(description, start, end);
                    taskList.add(task);
                    say(String.format("""
                            Got it. I've added this task:
                                %s
                            Now you have %d tasks in the list.""",
                            task, taskList.size()));
                    continue;
                }
            }

            { // Mark/Unmark Task
                Pattern pattern = Pattern.compile("(mark|unmark) (-?\\d+)");
                Matcher matcher = pattern.matcher(user);
                if (matcher.matches()) {
                    String command = matcher.group(1);
                    int index = Integer.parseInt(matcher.group(2));
                    if (index <= 0 || index > taskList.size()) {
                        say("Not a valid index");
                        continue;
                    }
                    Task task = taskList.get(index - 1);
                    if (command.equals("mark")) {
                        task.mark();
                        say(String.format("Nice! I've marked this task as done\n\t%s", task));
                    } else {
                        task.unmark();
                        say(String.format("Ok, I've unmarked this task as not done yet:\n\t%s", task));
                    }
                    continue;
                }
            }

            { // Delete Task
                Pattern pattern = Pattern.compile("delete (-?\\d+)");
                Matcher matcher = pattern.matcher(user);
                if (matcher.matches()) {
                    int index = Integer.parseInt(matcher.group(1));
                    if (index <= 0 || index > taskList.size()) {
                        say("Not a valid index");
                        continue;
                    }
                    Task task = taskList.remove(index - 1);
                    say(String.format("""
                            Noted. I've removed this task:
                                %s
                            Now you have %d tasks in the list.""",
                            task, taskList.size()));
                    continue;
                }
            }

            say("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        say("Bye. Hope to see you again soon!");
        reader.close();
    }

    private static void say(String text) {
        System.out.println("\t____________________________________________________________");
        System.out.print("\t");
        System.out.println(text.replace("\n", "\n\t"));
        System.out.println("\t____________________________________________________________");
        System.out.println();
    }
}
