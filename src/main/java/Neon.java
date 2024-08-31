import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.io.FileWriter;


public class Neon {
    private static final String NAME = "Neon";
    private static final String DASH_BREAK = "-----------------------------------";
    private static ArrayList<Task> list = new ArrayList<>();
    private static int lastListIndex = 0;

    private static void greetingLine() {
        System.out.println(DASH_BREAK);
        String greeting = "hello i'm " + NAME + "!\n"
                + "what can i help you with?\n";
        System.out.println(greeting);
        System.out.println(DASH_BREAK);
    }

    private static void closingLine() {
        System.out.println(DASH_BREAK);
        String closing = "byeee! nice to meet you :)\n";
        System.out.println(closing);
        System.out.println(DASH_BREAK);
    }

    private static void mainChat(String answer) {
        System.out.println(DASH_BREAK);

        Scanner ansObj = new Scanner(answer);
        String first = ansObj.next();

        switch (first) {
            case "todo":
                String taskTodo = removeSpace(answer.replace("todo ", ""));
                if (taskTodo.isEmpty()) {
                    System.out.println("found no description of task! do try again");
                    break;
                }
                Todo newTodo = new Todo(taskTodo, false);
                list.add(newTodo);
                lastListIndex++;
                System.out.println("adding todo to list : " + taskTodo);
                break;

            case "deadline":
                String taskDeadline = answer.replace("deadline ", "");
                String[] partsDeadline = taskDeadline.split("\\s*/by\\s*");
                if (partsDeadline.length == 1) {
                    System.out.println("found no description of task! do try again");
                    break;
                }
                Deadline newDeadline = new Deadline(partsDeadline[0],
                        false,
                        removeSpace(partsDeadline[1]));
                    list.add(newDeadline);
                lastListIndex++;
                System.out.println("adding deadline to list : " + newDeadline.getName());
                break;

            case "event":
                String taskEvent = answer.replace("event ", "");
                String[] partsEvent = taskEvent.split("\\s*/from\\s*|\\s*/to\\s*");
                if (partsEvent.length == 1) {
                    System.out.println("found no description of task! do try again");
                    break;
                } else if (partsEvent.length < 3 || partsEvent[1].isEmpty()) {
                    System.out.println("dates are incomplete! do try again");
                    break;
                }

                Event newEvent = new Event(partsEvent[0], false,
                        partsEvent[1], removeSpace(partsEvent[2]));
                list.add(newEvent);
                lastListIndex++;
                System.out.println("adding event to list : " + newEvent.getName());
                break;

            default:
                System.out.println("cannot read : " + first);
                break;
        }

        System.out.println(DASH_BREAK);
    }

    private static void saveTasks(ArrayList<Task> tasks) throws IOException {
        try (FileWriter writer = new FileWriter("./data/data.txt")) {
            for (Task task : tasks) {
                writer.write(task.toTask() + "\n");
            }
            System.out.println("file saved!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadTasks() throws IOException {
        try {
            Scanner scanner = new Scanner(new File("./data/data.txt"));
        } catch (FileNotFoundException e) {
            File directory = new File("data");
            File file = new File(directory, "data.txt");

            directory.mkdir();
            file.createNewFile();
        }

        Scanner scanner = new Scanner(new File("./data/data.txt"));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] content = line.split("/");

            boolean completed;
            switch (content[0]) {
                case "T":
                    completed = (Objects.equals(content[1], "1"));
                    Todo tempTodo = new Todo(content[2], completed);
                    list.add(tempTodo);
                    lastListIndex++;
                    break;
                case "D":
                    completed = (Objects.equals(content[1], "1"));
                    Deadline tempDeadline = new Deadline(content[2], completed, content[3]);
                    list.add(tempDeadline);
                    lastListIndex++;
                    break;
                case "E":
                    completed = (Objects.equals(content[1], "1"));
                    Event tempEvent = new Event(content[2], completed, content[3], content[4]);
                    list.add(tempEvent);
                    lastListIndex++;
                    break;

            }
        }
    }

    private static String removeSpace(String line) {
        if (line.isEmpty()) {
            return line;
        }
        return line.substring(0, line.length() - 1);
    }

    private static void printList() {
        System.out.println(DASH_BREAK);

        String message = "";
        if (lastListIndex == 1) {
            message = "nothing in the list!";
        } else {
            message = "printing list:";
        }

        System.out.println(message);

        for(int i = 0; i < lastListIndex; i++) {
            System.out.println(i + 1 + ". " + list.get(i).toString());
        }
        System.out.println(DASH_BREAK);
    }

    private static void markItem(int taskNumber) {
        Task currTask = list.get(taskNumber - 1);
        currTask.check();

        System.out.println(DASH_BREAK);
        System.out.println("checking task number : " + taskNumber);
        System.out.println(DASH_BREAK);
    }

    private static void unmarkItem(int taskNumber) {
        Task currTask = list.get(taskNumber - 1);
        currTask.uncheck();

        System.out.println(DASH_BREAK);
        System.out.println("unchecking task number : " + taskNumber);
        System.out.println(DASH_BREAK);
    }

    private static void deleteItem(int taskNumber) {
        System.out.println(DASH_BREAK);
        System.out.println("deleting task number : " + taskNumber);
        System.out.println(DASH_BREAK);

        list.remove(taskNumber - 1);
        lastListIndex--;
    }

    public static void main(String[] args) throws IOException {
        boolean chatting = true;
        greetingLine();
        StringBuilder answer = new StringBuilder();
        int number = -1;

        loadTasks();

        Scanner ansObj = new Scanner(System.in);
        String allInput = ansObj.useDelimiter("\\Z").next(); // Reads until end of input (CTRL+D or an equivalent)
        String[] lines = allInput.split("\\r?\\n");
        for (String line : lines) {
            Scanner lineSc = new Scanner(line);
            while (lineSc.hasNext()) {
                if (lineSc.hasNextInt()) {
                    number = lineSc.nextInt();
                    break;
                } else {
                    answer.append(lineSc.next()).append(" ");
                }
            }

            switch (answer.toString().substring(0, answer.toString().length() - 1)) {
                case "bye":
                    chatting = false;
                    break;
                case "list":
                    printList();
                    break;
                case "mark":
                    markItem(number);
                    break;
                case "unmark":
                    unmarkItem(number);
                    break;
                case "delete":
                    deleteItem(number);
                    break;
                default:
                    mainChat(String.valueOf(answer));
                    break;
            }
            answer = new StringBuilder();
        }
        saveTasks(list);
        closingLine();
    }
}
