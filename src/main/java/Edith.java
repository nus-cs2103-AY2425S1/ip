import java.util.*;

public class Edith {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String horizontal = "____________________________________________________________";
        String greeting = " heyyy im edith!\n what can I do for you?";
        String farewell = " bye!! see you soon love <3";
        String linebreak = "\n";

        ToDoList todoList = new ToDoList();

        // print out greeting when bot first starts up
        System.out.println(horizontal + linebreak +
                greeting + linebreak +
                horizontal);

        String command = scanner.nextLine();

        // break out of loop when user inputs bye
        while (!Objects.equals(command, "bye")) {
            List<String> words = Arrays.asList(command.split(" ")); // check first command ie words[0]

            if (Objects.equals(command, "list")) { // check if user wants the todo list
                System.out.println(horizontal + linebreak +
                        " tasks in your todo list!" + linebreak +
                        todoList.toString() +
                        horizontal);
                command = scanner.nextLine();
            }

            else if (Objects.equals(words.get(0), "mark")) { // check if user wants to mark a task
                int taskNumber = Integer.parseInt(words.get(1));
                todoList.mark(taskNumber);
                System.out.println(horizontal + linebreak +
                        " " + "yay! i've marked this task as done #productive:" + linebreak +
                        " " + todoList.getTask(taskNumber) + linebreak +
                        horizontal);
                command = scanner.nextLine();
            }

            else if (Objects.equals(words.get(0), "unmark")) { // check if user wants to unmark a task
                int taskNumber = Integer.parseInt(words.get(1));
                todoList.unmark(taskNumber);
                System.out.println(horizontal + linebreak +
                        " " + "aw, i've marked this task as undone:" + linebreak +
                        " " + todoList.getTask(taskNumber) + linebreak +
                        horizontal);
                command = scanner.nextLine();
            }

            else { // everything else is taken as a task
                // System.out.println("task block running");
                Task task = null;

                if (Objects.equals(words.get(0), "todo")) {
                    // System.out.println("todo block running");
                    List<String> commandToArray = Arrays.asList(command.split("todo "));
                    // System.out.println(commandToArray);
                    String taskName = commandToArray.get(1);
                    task = new ToDoTask(taskName);

                }

                else if (Objects.equals(words.get(0), "deadline")) {
                    // System.out.println("deadline block running");
                    List<String> commandToArray = Arrays.asList(command.split("deadline "));
                    // System.out.println(commandToArray);
                    String taskAndDeadline = commandToArray.get(1);
                    String taskName = taskAndDeadline.split(" /by ")[0];
                    String deadline = taskAndDeadline.split(" /by ")[1];
                    task = new DeadlineTask(taskName, deadline);
                }

                else if (Objects.equals(words.get(0), "event")) {
                    // System.out.println("event block running");
                    List<String> commandToArray = Arrays.asList(command.split("event "));
                    // System.out.println(commandToArray);
                    String taskAndDuration = commandToArray.get(1);
                    List<String> taskAndDurationArray = Arrays.asList(taskAndDuration.split(" /from "));
                    // System.out.println(taskAndDurationArray);
                    String taskName = taskAndDurationArray.get(0);
                    String duration = taskAndDurationArray.get(1);
                    List<String> durationArray = Arrays.asList(duration.split(" /to "));
                    // System.out.println(durationArray);
                    String start = durationArray.get(0);
                    String end = durationArray.get(1);
                    task = new EventTask(taskName, start, end);
                }

                todoList.add(task);
                System.out.println(horizontal + linebreak +
                        " " + "nice! i've added this task:" + linebreak +
                        " " + task.toString() + linebreak +
                        " there are currently " + todoList.getNumberofTasks() + " tasks in your todo list." + linebreak +
                        horizontal);
                command = scanner.nextLine();

            }
        }

        System.out.println(horizontal + linebreak + farewell + linebreak + horizontal);
    }
}
