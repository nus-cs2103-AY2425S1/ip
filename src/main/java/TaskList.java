import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private final ArrayList<Task> list;
    private final String filePath;

    public TaskList(String filePath) {
        this.list = new ArrayList<>();
        this.filePath = filePath;
    }

    public String addTask(String taskType, String input) {
        Task newTask;
        String response;
        try {
            switch (taskType) {
                case "todo":
                    newTask = Todo.handleInput(input);
                    response = "Ok! I've added a new todo task:\n" + newTask +
                            "\n";
                    break;
                case "deadline":
                    newTask = Deadline.handleInput(input);
                    response = "Ok! I've added a new task with a deadline:\n" + newTask +
                            "\n";
                    break;
                case "event":
                    newTask = Event.handleInput(input);
                    response = "Ok! I've added a new event:\n" + newTask +
                            "\n";
                    break;
                default:
                    response = "";
                    newTask = null;
                    break;
            }
            list.add(newTask);
            if (newTask !=  null) {
                saveToFile(newTask.toFileFormat());
            }
            return response + countTasks();
        } catch (WrongInputException e) {
            return e.toString();
        }
    }

    public String countTasks() {
        return "You have " + list.size() + " tasks in total.";
    }

    public String listItems() {
        if (list.isEmpty()) {
            return "You haven't added anything to the list, dummy!";
        }
        StringBuilder fullList = new StringBuilder("Here are your tasks:\n");
        for (Task t : list) {
            String currItem = list.indexOf(t) + 1 + "." + t + "\n";
            fullList.append(currItem);
        }
        fullList.append("That's all, you can do this!");
        return fullList.toString();
    }

    public String markItem(int place) {
        try {
            Task curr = list.get(place - 1);
            return curr.mark()
                ? "Well done! I have checked this item off the list:\n" + curr
                : "This item was already marked as done previously!";
        } catch (IndexOutOfBoundsException e) {
            return "This task does not exist!";
        }
    }

    public String unmarkItem(int place) {
        try {
            Task curr = list.get(place - 1);
            return curr.unmark()
                ? "Oh no! It's ok, I will uncheck this item for now:\n" + curr
                : "This item was already unchecked previously!";
        } catch (IndexOutOfBoundsException e) {
            return "This task does not exist!";
        }
    }

    public String deleteItem(int place) {
        try {
            Task deleted = list.remove(place - 1);
            return "I have taken this item off the list:\n" + deleted +
                    "\n" + countTasks();
        } catch (IndexOutOfBoundsException e) {
            return "This task does not exist!";
        }
    }

    public void onStart() {
        try {
            File loadFile = new File(filePath);
            Scanner scanner = new Scanner(loadFile);
            while (scanner.hasNext()) {
                String currLine = scanner.nextLine();
                String[] split = currLine.split(",");
                switch (split.length) {
                    case 1:
                        list.add(new Todo(split[0]));
                        break;
                    case 2:
                        list.add(new Deadline(split[0], split[1]));
                        break;
                    case 3:
                        list.add(new Event(split[0], split[1], split[2]));
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            if (!Files.isDirectory(Path.of("./data"))) {
                System.out.println("Directory './data' does not exist. Please create it first.");
                System.exit(0);
            } else {
                System.out.println("File './data/tasks.txt' does not exist. Please create it first.");
                System.exit(0);
            }
        }
    }

    private void saveToFile(String content) {
        try {
            FileWriter writer = new FileWriter(filePath, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
