import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.time.LocalDateTime;

public class Duke {
    public static void main(String[] args) throws ParseException {
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner reader = new Scanner(System.in);

        try {
            tasks = getData("./data/duke.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        }

        String greet = "Hello! I'm Bob\nWhat can I do for you?\n";
        String bye = "Bye. Hope to see you again soon!";
        String completed = "Nice! I've marked this task as done:";
        String notCompleted = "OK, I've marked this task as not done yet:";
        String input = "";

        while (reader.hasNextLine()) {
            input = reader.nextLine();
            if (input.contains("bye")) {
                System.out.println(bye);
                try {
                    writeData("./data/duke.txt", tasks);
                } catch (IOException e) {
                    System.out.println(e);
                }
                return;
            } else if (input.contains("list")) {
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i+1 + ". " + tasks.get(i).toString());
                }
            } else if (input.contains("unmark")) {
                try {
                    Task chosen = getTaskForUnMark(input, tasks);
                    chosen.unMark();
                    System.out.println(notCompleted);
                    System.out.println(chosen);
                } catch (DukeException e) {
                    System.out.println(e);
                }

            } else if (input.contains("mark")) {
                try {
                    Task chosen = getTaskForMark(input, tasks);
                    chosen.mark();
                    System.out.println(completed);
                    System.out.println(chosen);
                } catch (DukeException e) {
                    System.out.println(e);
                }
            } else if (input.contains("todo")) {
                try {
                    tasks.add(getTodo(input));
                    printDetails(tasks.get(tasks.size() - 1), tasks.size());
                } catch (DukeException e) {
                    System.out.println(e);
                }
            } else if (input.contains("deadline")) {
                try{
                    Task deadline = getDeadLine(input);
                    tasks.add(deadline);
                    printDetails(tasks.get(tasks.size() - 1), tasks.size());
                } catch (DukeException e) {
                    System.out.println(e);
                }
            }else if (input.contains("event")) {
                try{
                    Task event = getEvent(input);
                    tasks.add(event);
                    printDetails(tasks.get(tasks.size() - 1), tasks.size());
                } catch (DukeException e) {
                    System.out.println(e);
                }
            } else if (input.contains("delete")) {
                try {
                    int index = getTaskIndexForDelete(input, tasks);
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

    private static ArrayList<Task> getData(String path) throws FileNotFoundException {
        ArrayList<Task> data = new ArrayList<>();

        File f = new File(path);
        Scanner s = new Scanner(f);

        while (s.hasNext()) {
            String[] split = Arrays.stream(s.nextLine().split("\\|")).map(String::trim).toArray(String[]::new);
            String type = split[0];

            switch (type) {
                case "T" -> data.add(new ToDo(split[2]));
                case "D" -> data.add(new DeadLine(split[2], split[3]));
                case "E" -> data.add(new Event(split[2], split[3], split[4]));
            }

            if(Objects.equals(split[1], "1")) {
                data.get(data.size() -1).mark();
            }

        }

        return data;
    }

    private static void writeData(String path, ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(path);

        for (Task task : tasks) {
            fw.write(task.saveFormat());
        }

        fw.close();
    }

    private static String[] matchTask(String input, ArrayList<String> splitters) {
        String[] split = input.split(splitters.get(0));
        if (split.length == 0) {
            return new String[]{};
        }
        if (splitters.size() == 1) {
            return split;
        }else {
            splitters.remove(0);
            return Stream
                    .of(new String[]{split[0].trim()},
                            split.length == 2 ? matchTask(split[1], splitters) : new String[]{}) // Stream<String[]>
                    .flatMap(x -> Stream.of(x)) // flattens to Stream<String>
                    .toArray(String[]::new);
        }
    }

    private static Task getTodo(String input) throws DukeException{
        ArrayList<String> commands = new ArrayList<>();
        commands.add("todo");

        String[] todoItems = Arrays.stream(matchTask(input, commands)).map(String::trim).toArray(String[]::new);;

        if(todoItems.length == 0 || Objects.equals(todoItems[1], "")) {
            throw new DukeException("Task must be specified!");
        }

        return new ToDo(todoItems[1]);
    }

    private static LocalDateTime parseDate(String dateString) throws ParseException {
        DateFormat format = new SimpleDateFormat("dd-MM-yyyy HHmm", Locale.ENGLISH);
        Date date = format.parse(dateString);
        return new java.sql.Timestamp(date.getTime()).toLocalDateTime();
    }

    private static Task getDeadLine(String input) throws DukeException{
        ArrayList<String> commands = new ArrayList<>();
        commands.add("deadline");
        commands.add("/by");

        String[] deadLineItems = Arrays.stream(matchTask(input, commands)).map(String::trim).toArray(String[]::new);

        if(deadLineItems.length == 0 || Objects.equals(deadLineItems[1], "")) {
            throw new DukeException("Task must be specified!");
        }

        if(deadLineItems.length < 3) {
            throw new DukeException("DeadLine must be specified!");
        }

        return new DeadLine(deadLineItems[1], deadLineItems[2]);
    }

    private static Task getEvent(String input) throws DukeException{
        ArrayList<String> commands = new ArrayList<>();
        commands.add("event");
        commands.add("/from");
        commands.add("/to");

        String[] eventItems = Arrays.stream(matchTask(input, commands)).map(String::trim).toArray(String[]::new);;

        if(eventItems.length == 0 || Objects.equals(eventItems[1], "")) {
            throw new DukeException("Task must be specified!");
        }

        if(eventItems.length == 2) {
            throw new DukeException("from must be specified!");
        }

        if(eventItems.length == 3) {
            throw new DukeException("to must be specified!");
        }

        return new Event(eventItems[1], eventItems[2], eventItems[3]);
    }

    private static Task getTaskForMark(String input, ArrayList<Task> tasks) throws DukeException{
        ArrayList<String> commands = new ArrayList<>();
        commands.add("mark");

        int index = getIndex(input, tasks, commands);
        return tasks.get(index);
    }

    private static Task getTaskForUnMark(String input, ArrayList<Task> tasks) throws DukeException{
        ArrayList<String> commands = new ArrayList<>();
        commands.add("unmark");

        int index = getIndex(input, tasks, commands);
        return tasks.get(index);
    }

    private static int getTaskIndexForDelete(String input, ArrayList<Task> tasks) throws DukeException {
        ArrayList<String> commands = new ArrayList<>();
        commands.add("delete");
        return getIndex(input, tasks, commands);
    }

    private static int getIndex(String input, ArrayList<Task> tasks, ArrayList<String> commands) throws DukeException {
        String[] markItems = Arrays.stream(matchTask(input, commands)).map(String::trim).toArray(String[]::new);

        if(markItems.length == 0 ||Objects.equals(markItems[1], "")) {
            throw new DukeException("Task number must be specified!");
        }

        int index = Integer.parseInt(markItems[1]) - 1;
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Invalid task number!");
        }
        return index;
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
