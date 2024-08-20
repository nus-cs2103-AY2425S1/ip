import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Stream;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();

        Scanner reader = new Scanner(System.in);

        String greet = "Hello! I'm Bob\nWhat can I do for you?\n";
        String bye = "Bye. Hope to see you again soon!";
        String completed = "Nice! I've marked this task as done:";
        String notCompleted = "OK, I've marked this task as not done yet:";
        String input = "";

        System.out.println(greet);

        while (reader.hasNextLine()) {
            input = reader.nextLine();
            if (input.contains("bye")) {
                System.out.println(bye);
                return;
            } else if (input.contains("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i+1 + ". " + tasks.get(i).toString());
                }
            } else if (input.contains("unmark")) {
                try {
                    Task chosen = getTaskForMark(input, tasks, tasks.size() - 1);
                    chosen.unMark();
                    System.out.println(notCompleted);
                    System.out.println(chosen);
                } catch (DukeException e) {
                    System.out.println(e);
                }

            } else if (input.contains("mark")) {
                try {
                    Task chosen = getTaskForMark(input, tasks, tasks.size() - 1);
                    chosen.mark();
                    System.out.println(completed);
                    System.out.println(chosen);
                } catch (DukeException e) {
                    System.out.println(e);
                }
            } else if (input.contains("todo")) {
                try {
                    tasks.add(new ToDo(getTask(input)));
                    printDetails(tasks.get(tasks.size() - 1), tasks.size());
                } catch (DukeException e) {
                    System.out.println(e);
                }
            } else if (input.contains("deadline")) {
                try{
                    String [] pieces = getTaskAndDeadLine(input);
                    tasks.add(new DeadLine(pieces[0], pieces[1]));
                    printDetails(tasks.get(tasks.size() - 1), tasks.size());
                } catch (DukeException e) {
                    System.out.println(e);
                }
            }else if (input.contains("event")) {
                try{
                    String [] pieces = getTaskAndEvent(input);
                    tasks.add(new Event(pieces[0], pieces[1], pieces[2]));
                    printDetails(tasks.get(tasks.size() - 1), tasks.size());
                } catch (DukeException e) {
                    System.out.println(e);
                }
            } else if (input.contains("delete")) {
                try {
                    int index = getIndexForMark(input, tasks.size() - 1);
                    Task t = tasks.remove(index);
                    printRemoveDetails(t, tasks.size());

                } catch (DukeException e) {
                    System.out.println(e);
                }
            }else {
                System.out.println("Command does not exist!");
            }
        }
    }

    private static int getIndexForMark(String input, int current) throws DukeException{
        String [] pieces = input.split(" ");
        if (pieces.length <= 1) {
            throw new DukeException("Index must be specified!");
        }
        int index = Integer.parseInt(pieces[1]) - 1;
        if (index > current || index < 0) {
            throw new DukeException("Task does not exist");
        }
        return index;
    }

    private static Task getTaskForMark(String input, ArrayList<Task> tasks, int current) throws DukeException{
        int index = getIndexForMark(input, current);
        return tasks.get(index);
    }

    private static String getTask(String input) throws DukeException {
        String [] pieces = input.split(" ");
        if(pieces.length <= 1) {
            throw new DukeException("Task must be specified!");
        }
        Stream<String> a = Arrays.stream(pieces).skip(1);
        return a.reduce("", (s1, s2) -> s1  + s2 + " ").trim();
    }

    private static String[] getTaskAndDeadLine(String input) throws DukeException {
        String noCommand = getTask(input);
        String [] pieces = noCommand.split("/by");
        if(Objects.equals(pieces[0], "")) {
            throw new DukeException("Task is empty");
        }
        if(pieces.length == 1) {
            throw new DukeException("Time must be specified!");
        }
        return new String[] {pieces[0].trim() , pieces[1].trim()};
    }

    private static String[] getTaskAndEvent(String input) throws DukeException {
        String noCommand = getTask(input); //removes event etc
        String [] splitByFrom = noCommand.split("/from");

        if(Objects.equals(splitByFrom[0], "")) {
            throw new DukeException("Task is empty");
        }

        if(splitByFrom.length == 1) {
            throw new DukeException("Time must be specified");
        }

        String task = splitByFrom[0];
        String noFrom = Arrays.stream(splitByFrom).skip(1).reduce("", (s1, s2) -> s1  + s2 + " ").trim();
        String [] splitByTo = noFrom.split("/to");

        if(splitByTo.length == 1) {
            throw new DukeException("Time must be specified");
        }

        return new String[] {task , splitByTo[0].trim(), splitByTo[1].trim()};
    }

    private static void printDetails(Task task, int length) {
        System.out.println("Got it. I've added this task:\n" + task.toString().trim());
        System.out.println("Now you have " + length + " tasks in the list.");
    }

    private static void printRemoveDetails(Task task, int length) {
        System.out.println("Noted. I've removed this task:\n" + task.toString().trim());
        System.out.println("Now you have " + length + " tasks in the list.");
    }
}
