import java.util.Objects;
import java.util.Scanner;

public class Edith {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String horizontal = "____________________________________________________________";
        String greeting = " heyyy im edith! \n what can I do for you?";
        String farewell = " bye!! see you soon love <3";
        String linebreak = "\n";

        ToDo todoList = new ToDo();

        // print out greeting when bot first starts up
        System.out.println(horizontal + linebreak +
                greeting + linebreak +
                horizontal);

        String command = scanner.nextLine();

        // break out of loop when user inputs bye
        while (!Objects.equals(command, "bye")) {
            if (Objects.equals(command, "list")) { // check if user wants the todo list
                System.out.println(horizontal + linebreak +
                        todoList.toString() +
                        horizontal);
            }
            else if (Objects.equals(command.split(" ")[0], "mark")) { // check if user wants to mark a task
                int taskNumber = Integer.parseInt(command.split(" ")[1]);
                todoList.mark(taskNumber);
                System.out.println(horizontal + linebreak +
                        " " + "yay! i've marked this task as done #productive:" + linebreak +
                        " " + todoList.getTask(taskNumber) + linebreak +
                        horizontal);
            }
            else if (Objects.equals(command.split(" ")[0], "unmark")) { // check if user wants to unmark a task
                int taskNumber = Integer.parseInt(command.split(" ")[1]);
                todoList.unmark(taskNumber);
                System.out.println(horizontal + linebreak +
                        " " + "aw, i've marked this task as undone:" + linebreak +
                        " " + todoList.getTask(taskNumber) + linebreak +
                        horizontal);
            }
            else { // everything else is taken as a task
                Task task = new Task(command);
                todoList.add(task);
                System.out.println(horizontal + linebreak +
                        " " + "added: " + task.getTaskName() + linebreak +
                        horizontal);
            }
            command = scanner.nextLine();
        }

        System.out.println(horizontal + linebreak + farewell + linebreak + horizontal);
    }
}
