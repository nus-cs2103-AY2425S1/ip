import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Makima {

    public static final String LINE_SEPERATOR = "__________________";
    private boolean isRunning = true;
    private boolean editedTasks;
    private ArrayList<Task> tasks = new ArrayList<>();
    private Scanner sc;

    private void greeting() {
        System.out.println("Yahallo! I'm your friendly chatbot, Makima!");
        System.out.println("What can I do for you? *_*");
        System.out.println(LINE_SEPERATOR);
    }

    private void farewell() {
        System.out.println("Baibai. Hope to see you soon! ^_^");
        System.out.println(LINE_SEPERATOR);
    }

    private void displayList() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i+1 + ":" + tasks.get(i));
        }
    }

    private int getListIndex(String prompt) {
        System.out.println(prompt);
        System.out.println(LINE_SEPERATOR);
        return getListIndex();
    }

    private int getListIndex() {
        while (true) {
            String userInput = getInput();
            try {
                int index = Integer.parseInt(userInput);
                if (index < 1 || index > tasks.size()) {
                    System.out.println("Invalid index");
                } else {
                    return index-1;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number!");
            }
        }
    }

    private String getInput(String prompt) {
        System.out.println(prompt);
        System.out.println(LINE_SEPERATOR);
        return getInput();
    }

    private String getInput() {
        String userInput = sc.nextLine();
        System.out.println(LINE_SEPERATOR);
        while (userInput.isEmpty()) {
            System.out.println("Input cannot be empty!");
            System.out.println(LINE_SEPERATOR);
            userInput = sc.nextLine();
        }
        return userInput;
    }

    private LocalDateTime getDate(String prompt) {
        System.out.println(prompt);
        System.out.println(LINE_SEPERATOR);
        return getDate();
    }

    private LocalDateTime getDate() {
        while (true) {
            try {
                return LocalDateTime.parse(getInput());
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format! Please input the date as follows: YYYY-MM-DD HH:MM," +
                        "replacing the space with a T");
            }
        }
    }

    private void done() {
        editedTasks = true;
        System.out.println("Done!");
        System.out.println(LINE_SEPERATOR);
    }

    public void addTodo(ToDo toDo) {
        tasks.add(toDo);
    }

    public void addDeadline(Deadline deadline) {
        tasks.add(deadline);
    }

    public void addEvent(Event event) {
        tasks.add(event);
    }

    public String convertTaskstoFileString() {
        StringBuilder output = new StringBuilder();
        for (Task task : tasks) {
            output.append(task.toFileString());
        }
        return output.toString();
    }

    public static void main(String[] args) {
        new Makima();
    }

    public Makima() {
        sc = new Scanner(System.in);
        greeting();

        isRunning = FileManager.loadFile(this);

        while (isRunning) {

            editedTasks = false;

            switch (getInput()) {
                case "bye":
                    isRunning = false;
                    break;

                case "list":
                    displayList();
                    System.out.println(LINE_SEPERATOR);
                    break;

                case "mark":
                    displayList();
                    tasks.get(getListIndex("Which item would you like to mark?")).mark();
                    done();
                    break;

                case "unmark":
                    displayList();
                    tasks.get(getListIndex("Which item would you like to mark?")).unmark();
                    done();
                    break;

                case "todo":
                    tasks.add(new ToDo(getInput("What is the task name?")));
                    done();
                    break;

                case "deadline":
                    tasks.add(new Deadline(
                            getInput("What is the task name?"),
                            getDate("When is it due?")
                    ));
                    done();
                    break;

                case "event":
                    tasks.add(new Event(
                            getInput("What is the event name?"),
                            getDate("When does it start?"),
                            getDate("When does it end?")
                    ));
                    done();
                    break;

                case "delete":
                    tasks.remove(getListIndex("Which item would you like to delete?"));
                    done();
                    break;

                default:
                    System.out.println("Unknown command!");
                    System.out.println(LINE_SEPERATOR);
            }

            if (editedTasks) {
                FileManager.saveFile(this);
            }
        }

        farewell();
    }

}
