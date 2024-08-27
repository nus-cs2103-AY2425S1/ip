import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BotimusPrime {
    private static List<Task> taskList = new ArrayList<>();

    private static final String FILENAME = "todolist.txt";
    private static final String DIRECTORY = "./data";

    private static final String greetingMessage =
            "____________________________________________________________\n" +
                    " Greetings, human. I am BotimusPrime.\n" +
                    " What can I do for you?\n" +
                    "____________________________________________________________";
    private static final String byeMessage =
            "____________________________________________________________\n" +
                    "Autobots, ROLL OUT!!!\n" +
                    "____________________________________________________________\n";

    private static void handleBye() {
        System.out.println(byeMessage);
    }

    private static void handleList() {
        if (taskList.isEmpty()) {
            System.out.println("congrats bro u got nth to do in the todolist, respect 100");
            return;
        }
        System.out.println("____________________________________________________________\n" + "Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, taskList.get(i));
        }
        System.out.println("____________________________________________________________\n");
    }

    private static void handleMark(String input) {
        String[] numFinder = input.split(" ");

        if (numFinder.length != 2 || !(numFinder[1].matches("\\d+"))) {
            System.out.println("eh bro u need to put the index of the item uw to mark");
        }

        int idx = Integer.parseInt(numFinder[1]) - 1;
        taskList.get(idx).markAsDone();
        saveToDisk();
        System.out.println("____________________________________________________________\n" +
                "Nice! I've marked this task as done:\n" +
                taskList.get(idx));
    }

    private static void handleUnmark(String input) {
        String[] numFinder = input.split(" ");

        if (numFinder.length != 2 || !(numFinder[1].matches("\\d+"))) {
            System.out.println("eh bro u need to put the index of the item uw to unmark");
        }

        int idx = Integer.parseInt(numFinder[1]) - 1;
        taskList.get(idx).markAsUndone();
        saveToDisk();
        System.out.println("____________________________________________________________\n" +
                "OK, I've marked this task as not done yet:\n" +
                taskList.get(idx));
    }

    private static void handleDelete(String input) {
        String[] numFinder = input.split(" ");

        if (numFinder.length != 2 || !(numFinder[1].matches("\\d+"))) {
            System.out.println("eh bro u need to put the index of the item uw to delete");
        }

        int idx = Integer.parseInt(numFinder[1]) - 1;
        Task task = taskList.get(idx);
        taskList.remove(idx);
        saveToDisk();
        System.out.println("____________________________________________________________\n" +
                "Noted. I've removed this task:\n" +
                task +
                "\nNow you have " + taskList.size() + " tasks in the list.");
    }

    private static void handleToDo(String input) {
        if (input.length() <= 5 || input.substring(5).isEmpty()) {
            System.out.println("eh bro udw to put ur description of ur task issit");
            return;
        }

        ToDo task = new ToDo(input.substring(5), false);

        taskList.add(task);
        saveToDisk();

        System.out.println(
                "____________________________________________________________\n" +
                        String.format("Alright, I've added the task:\n %s\nNow you have %d tasks in the list.\n", task, taskList.size()) +

                        "____________________________________________________________\n");
    }

    private static void handleDeadline(String input) {
        if (input.length() <= 9 || input.substring(9).isEmpty() || !input.contains("/by")) {
            System.out.println("brother u forgot to type all the deadline task details plz.");
        }
        String[] parser = input.split("/by ");

        if (parser.length < 2 || parser[1].trim().isEmpty()) {
            System.out.println("wheres the deadline!!");
            return;
        }
        String description = parser[0].substring(9);
        String deadline = parser[1];

        if (deadline.isEmpty()) {
            System.out.println("eh bro u got due date anot ");
            return;
        } else if (description.isEmpty()) {
            System.out.println("eh bro ur task no description leh wake up ur idea");
        }

        Deadline task = new Deadline(description, false, stringToDateTime(deadline));

        taskList.add(task);
        saveToDisk();

        System.out.println(
                "____________________________________________________________\n" +
                        String.format("Alright, I've added the task:\n %s\nNow you have %d tasks in the list.\n", task, taskList.size()) +

                        "____________________________________________________________\n");
    }

    private static void handleEvent(String input) {
        if (input.length() <= 6 || input.substring(6).trim().isEmpty()) {
            System.out.println("brother u forgot to type all the event task details");
            return;
        } else if (!input.contains("/from") || !input.contains("/to")) {
            System.out.println("eh bro ur event no time issit");
            return;
        }

        String[] parser = input.split("/from ");

        if (parser.length < 2 || parser[1].trim().isEmpty()) {
            System.out.println("hi plz actually put a time");
            return;
        }

        String description = parser[0].substring(6);
        String times = parser[1];

        String[] fromAndTo = times.split("/to ");

        if (fromAndTo.length < 2 || fromAndTo[0].trim().isEmpty() || fromAndTo[1].trim().isEmpty()) {
            System.out.println("hi plz actually put times in ur EVENT");
            return;
        }

        String from = fromAndTo[0].trim();
        String to = fromAndTo[1].trim();

        Event task = new Event(description, false, stringToDateTime(from), stringToDateTime(to));

        taskList.add(task);
        saveToDisk();

        System.out.println(
                "____________________________________________________________\n" +
                        String.format("Alright, I've added the task:\n %s\nNow you have %d tasks in the list.\n", task, taskList.size()) +

                        "____________________________________________________________\n");
    }

    private static void saveToDisk() {
        File dir = new File(DIRECTORY);
        File file = new File(dir, FILENAME);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        try (FileWriter writer = new FileWriter(file)) {
            for (Task task : taskList) {
                writer.write(task.saveString() + System.lineSeparator());
            }
            System.out.println("Tasks saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadFromDisk() {
        File file = new File(DIRECTORY, FILENAME);

        if (!file.exists()) {
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Task.fromString(line);
                taskList.add(task);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleUnknown() {
        System.out.println("bro out here speaking nonsense issit");
    }

    private static LocalDateTime stringToDateTime(String timeString) {
        DateTimeFormatter withTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DateTimeFormatter withoutTime = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter slashWithTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        DateTimeFormatter slashWithoutTime = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try {
            return LocalDateTime.parse(timeString, withTime);
        } catch (DateTimeParseException e1) {
            try {
                LocalDate date = LocalDate.parse(timeString, withoutTime);
                return date.atStartOfDay();
            } catch (DateTimeParseException e2) {
                try {
                    LocalDate date = LocalDate.parse(timeString, slashWithoutTime);
                    return date.atStartOfDay();
                } catch (DateTimeParseException e3) {
                    try {
                        return LocalDateTime.parse(timeString, slashWithTime);
                    } catch (DateTimeParseException e4) {
                        throw new IllegalArgumentException("Wrong date" + timeString);
                    }
                }
            }
        }
    }



    public static void main(String[] args) {

        loadFromDisk();

        Scanner sc = new Scanner(System.in);
        System.out.println(greetingMessage);

        while (true) {
            String input = sc.nextLine().trim();
            System.out.println("\n");
            if (input.equals("bye")) {
                handleBye();
                break;
            } else if (input.equals("list")) {
                handleList();
            } else if (input.startsWith("mark")) {
                handleMark(input);
            } else if (input.startsWith("unmark")) {
                handleUnmark(input);
            } else if (input.startsWith("todo")) {
                handleToDo(input);
            } else if (input.startsWith("deadline")) {
                handleDeadline(input);
            } else if (input.startsWith("event")) {
                handleEvent(input);
            } else if (input.startsWith("delete")) {
                handleDelete(input);
            } else {
                handleUnknown();
            }
        }
        sc.close();
    }
}
