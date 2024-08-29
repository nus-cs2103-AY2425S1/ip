import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class Kitty {
    private static final String NAME = "Kitty";
    private static final String DIVISION_LINE = "--------------------------";
    private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
    private static final String DIR_PATH = "data";
    private static final String KITTY_DATA_PATH = "data/Kitty.txt";
    private static final File KITTY_TASKS_DATA = new File(KITTY_DATA_PATH);


    private static final ArrayList<Task> TASKS = new ArrayList<Task>(100);

    public static void main(String[] args) {
//        String logo = """
//                 ____        -
//                |  _ \\ _   _| | _____\s
//                | | | | | | | |/ / _ \\
//                | |_| | |_| |   <  __/
//                |____/ \\__,_|_|\\_\\___|
//                """;
//        System.out.println("Hello from\n" + logo);
        Greet();
    }

    private static void Greet() {
        System.out.println("Hello! I'm " + NAME);
        System.out.println("What can I do for you?\n");
        System.out.println(DIVISION_LINE);
        try {
            if (!KITTY_TASKS_DATA.exists()) {
                File dir = new File(DIR_PATH);
                boolean isCreated = dir.mkdirs();
//                System.out.println("create directory: " + isCreated);
                isCreated = KITTY_TASKS_DATA.createNewFile();
            }
        } catch (IOException e) {
            System.out.print("");
        }
        try {
            Scanner scanKittyTasks = new Scanner(KITTY_TASKS_DATA);
            while (scanKittyTasks.hasNext()) {
                createTaskFromInput(scanKittyTasks.nextLine());
            }
        } catch(FileNotFoundException e) {
            System.out.println("No file available");
            // deal with file/ folder not yet exist scenario
        }
        Echo();
    }

    private static void createTaskFromInput(String str) {
        String[] aux = str.split("~!!");
        Task tmp;
        switch (aux[0].trim()) {
            case "T" -> tmp = new Todo(aux[2]);
            case "D" -> tmp = new Deadline(aux[2],
                    LocalDateTime.parse(aux[3], DATE_TIME_FORMAT));
            case "E" -> tmp = new Event(aux[2],
                    LocalDateTime.parse(aux[3], DATE_TIME_FORMAT),
                    LocalDateTime.parse(aux[4], DATE_TIME_FORMAT));
            default -> {
                return;
            }
        }

        TASKS.add(tmp);
        if (aux[1].trim().equals("1")) {
            tmp.mark();
        }

    }

    private static void Echo() {
        String command = "";
        Scanner sc = new Scanner(System.in);
        while (true) {
            command = sc.nextLine();
            if (command.contains("bye")) {
                Exit();
                return;
            } else if (command.contains("list")) {
                List();
            } else if (command.contains("delete")) {
                delete(extractFirstNumber(command));
            } else if (command.contains("unmark")) {
                unmark(extractFirstNumber(command));
            } else if (command.contains("mark")) {
                mark(extractFirstNumber(command));
            } else if (command.contains("todo") || command.contains("deadline") || command.contains("event")){
                add(command);
            } else {
                System.out.println(DIVISION_LINE);
                System.out.println("Burrrrr~ What is this??? I have no idea about it...\n");
                System.out.println(DIVISION_LINE);
            }
        }
    }

    private static void add(String item) {
        String[] aux = item.split(" ", 2);
        String type = aux[0];
        Task tmp;
        try {
            // input check
            if (aux.length == 1 ||
                    (!type.equals("todo") && !type.equals("deadline") && !type.equals("event"))) {
                System.out.println(DIVISION_LINE);
                System.out.println("Oooops... I don't know what you want to do though...");
                throw new TaskException();
            }

            // create corresponding tasks according to valid input
            String name = aux[1].trim();
            tmp = type.equals("todo")
                    ? new Todo(name)
                    : type.equals("deadline")
                    ? createDeadline(name)
                    : createEvent(name);

            // update data structure and file
            TASKS.add(tmp);
            String data = tmp.taskData();
            try {
                addLine(data);
            } catch (IOException e) {
                System.out.println("Write failed");
            }

            // output in the terminal
            System.out.println(DIVISION_LINE);
            System.out.println("Okie, I added it into the list:");
            System.out.println("  " + tmp);
            System.out.printf("Now you have %d tasks in the list.\n\n", TASKS.size());
            System.out.println(DIVISION_LINE);
        } catch (KittyException e) {
            System.out.println(e);
            System.out.println("\n" + DIVISION_LINE);
        }
    }

    // Adapted from Week 3 Topic: File I/O
    private static void addLine(String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(KITTY_DATA_PATH, true);
        fw.write(textToAppend);
        fw.close();
    }

    private static void rewriteFile() {
        try {
            FileWriter fw = new FileWriter(KITTY_DATA_PATH);
            fw.write("");
            fw.close();
            for (Task task: TASKS) {
                addLine(task.taskData());
            }
        } catch (IOException e) {
            return;
        }
    }
    private static Task createDeadline(String str) throws DeadlineException, DateFormatException{
        String[] parts = str.split("/by");
        if (parts.length != 2)
            throw new DeadlineException();
        try {
            LocalDateTime time = LocalDateTime.parse(parts[1].trim(), DATE_TIME_FORMAT);
            return new Deadline(parts[0], time);
        } catch (DateTimeParseException e) {
            throw new DateFormatException();
        }
    }

    private static Task createEvent(String str) throws EventException, DateFormatException{
        String[] parts = str.split("/from|/to");
        if (parts.length != 3)
            throw new EventException();
        try {
            LocalDateTime from = LocalDateTime.parse(parts[1].trim(), DATE_TIME_FORMAT);
            LocalDateTime to = LocalDateTime.parse(parts[2].trim(), DATE_TIME_FORMAT);
            return new Event(parts[0], from, to);
        } catch (DateTimeParseException e) {
            throw new DateFormatException();
        }
    }

    private static void List() {
        int count = 1;
        Task[] tmp = new Task[0];
        System.out.println(DIVISION_LINE);
        System.out.println("Meow~ Here you are!");
        for (Task item: TASKS.toArray(tmp)) {
            System.out.println(count++ + "." + item);
        }
        System.out.println("\n" + DIVISION_LINE);
    }

    private static void delete(int index) {
        try {
            Task tmp = TASKS.get(index - 1);
            String note = tmp.toString();
            TASKS.remove(index - 1);
            rewriteFile();
            System.out.println(DIVISION_LINE);
            System.out.println("I have removed it from the list :)");
            System.out.println("  " + note);
            System.out.printf("Now you have %d tasks in the list\n\n", TASKS.size());
            System.out.println(DIVISION_LINE);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(DIVISION_LINE + "\nIndex out of bound, you can only input integer from 1 to "
                    + TASKS.size() + ".\n\n" + DIVISION_LINE);
        }
    }

    private static void mark(int index) {
        try {
            Task tmp = TASKS.get(index - 1);
            tmp.mark();
            rewriteFile();
            System.out.println(DIVISION_LINE);
            System.out.println("Well done! You have completed this task!");
            System.out.println(" " + tmp);
            System.out.println("\n" + DIVISION_LINE);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(DIVISION_LINE + "\nIndex out of bound, you can only input integer from 1 to "
                    + TASKS.size() + ".\n\n" + DIVISION_LINE);
        }
    }

    private static void unmark(int index) {
        try {
            Task tmp = TASKS.get(index - 1);
            tmp.unmark();
            rewriteFile();
            System.out.println(DIVISION_LINE);
            System.out.println("Meow~ Okay we can continue this task!");
            System.out.println("  " + tmp);
            System.out.println("\n" + DIVISION_LINE);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(DIVISION_LINE + "\nIndex out of bound, you can only input integer from 1 to "
                    + TASKS.size() + ".\n\n" + DIVISION_LINE);
        }
    }

    private static int extractFirstNumber(String input) {
        // Replace all non-digit characters with spaces
        String cleanedInput = input.replaceAll("\\D+", " ");

        // Split the cleaned string by spaces
        String[] parts = cleanedInput.trim().split("\\s+");

        // Check if there are any parts and parse the first one
        if (parts.length > 0) {
            try {
                return Integer.parseInt(parts[0]);
            } catch (NumberFormatException e) {
                // Handle the case where parsing fails
                return -1;
            }
        }

        // Return null if no number is found
        return -1;
    }

    private static void Exit() {
        System.out.println(DIVISION_LINE);
        System.out.println("Bye. Hope I can see you again soon!\nNext time bring me some cat food please!!!\n");
        System.out.println(DIVISION_LINE);
    }
}
