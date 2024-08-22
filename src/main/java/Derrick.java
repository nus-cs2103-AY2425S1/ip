import java.awt.geom.NoninvertibleTransformException;
import java.lang.reflect.Array;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Derrick {
    private final ArrayList<Task> toDo = new ArrayList<>();
    private static final String FOLDER_PATH = Paths.get(System.getProperty("user.home"), "ip","data").toString();
    private static final String FILE_NAME = "DATA.TXT";
    private static final Path FILE_PATH = Paths.get(FOLDER_PATH, FILE_NAME);

    public Derrick() {
        createDirectory();
        loadTasksFromFile();
    }
    public void greetings() {
        System.out.println("Hello, I am Derrick");
        System.out.println("What can I do for you?");
    }


    public void createDirectory() {
        Path folderPath = Paths.get(FOLDER_PATH);
        System.out.println("Folder path is " + folderPath);
        if (!java.nio.file.Files.exists(folderPath)) {
            try {
                Files.createDirectories(folderPath);
            } catch (IOException e) {
                System.out.println("An error occurred while creating the directory: " + e.getMessage());
            }
        }

        if (!Files.exists(FILE_PATH)) {
            try {
                Files.createFile(FILE_PATH);
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file: " + e.getMessage());
            }
        }
    }


    public void saveTasksToFile() {
        try (BufferedWriter writer = Files.newBufferedWriter(FILE_PATH)) {
            for (Task task : toDo) {
                writer.write(task.changeFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks to file: " + e.getMessage());
        }
    }

    public void loadTasksFromFile() {
        if (!Files.exists(FILE_PATH)) {
            System.out.println("No previous task data found. Starting with an empty list.");
            return;
        }

        try (BufferedReader reader = Files.newBufferedReader(FILE_PATH)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                Task task = null;
                switch (type) {
                    case "T":
                        task = new Todo(description);
                        break;
                    case "D":
                        String by = parts[3];
                        task = new Deadline(description, by);
                        break;
                    case "E":
                        String start = parts[3];
                        String end = parts[4];
                        task = new Event(description, start, end);
                        break;
                }

                if (task != null) {
                    if (isDone) {
                        task.markStatus();
                    }
                    toDo.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks from file: " + e.getMessage());
        }
    }



    public void list(ArrayList<Task> list) {
        if (this.toDo.isEmpty()) {
            System.out.println("You have nothing in your list.");
        } else {
            System.out.println(("Here are the items in your list:"));
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + ". " + list.get(i));
            }
        }
    }


    public void markItem(ArrayList<Task> list, String input) throws MissingPositionException, MissingItemException {
        int position;
        try {
            position = Integer.parseInt(input.split(" ")[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingPositionException("You are missing a specific position. Please try again.");
        } catch (NumberFormatException e) {
            throw new MissingPositionException("Position must be an integer. Please try again.");
        }

        if (position <= 0 || position > list.size()) {
            throw new MissingItemException("Item does not exist in the list. Please try again.");
        }

        Task task = list.get(position - 1);
        task.markStatus();
        System.out.println("I have marked this task as done!");
        System.out.println(task);
    }

    public void unmarkItem(ArrayList<Task> list, String input) throws MissingPositionException, MissingItemException {
        int position;
        try {
            position = Integer.parseInt(input.split(" ")[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingPositionException("You are missing a specific position. Please try again.");
        } catch (NumberFormatException e) {
            throw new MissingPositionException("Position must be an integer. Please try again.");
        }

        if (position <= 0 || position > list.size()) {
            throw new MissingItemException("Item does not exist in the list. Please try again.");
        }

        Task task = list.get(position - 1);
        task.unmarkStatus();
        System.out.println("I have marked this task as not done yet!");
        System.out.println(task);
    }


    public void delete(ArrayList<Task> list, String input) throws MissingPositionException, MissingItemException, EmptyListException {
        int position;
        try {
            position = Integer.parseInt(input.split(" ")[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MissingPositionException("You are missing a specific position. Please try again.");
        } catch (NumberFormatException e) {
            throw new MissingPositionException("Position must be an integer. Please try again.");
        }

        if (list.isEmpty()) {
            throw new EmptyListException("You are trying to delete from an empty list.");
        } else if (position <= 0 || position > list.size()) {
            throw new MissingItemException("Item does not exist in the list. Please try again.");
        }
        Task task = list.get(position - 1);
        list.remove(task);
        System.out.println("I have removed this task:");
        System.out.println(task);
        System.out.println("You have " + list.size() + " items in your list.");
    }

    public void addTodo(ArrayList<Task> list, String input) throws InvalidDescriptionException {
        Todo todo;
        try {
            todo = new Todo(input.split(" ", 2)[1]);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidDescriptionException("You have not added any description for the todo task. Please try again.");
        }
        this.toDo.add(todo);
        System.out.println("Got it. I have added this todo.");
        System.out.println(todo);
        System.out.println("You have " + list.size() + " items in your list.");

    }


    public void addDeadline(ArrayList<Task> list, String input) throws InvalidDescriptionException {
        String time;
        String description;

        try {
            time = input.split("/by")[1];
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidDescriptionException("You have not added any deadline for the todo task. Please try again.");
        }

        try {
            description = input.split("/by")[0].split(" ", 2)[1];
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidDescriptionException("You have not added any description for the todo task. Please try again.");
        }

        Deadline deadline = new Deadline(description, time);
        this.toDo.add(deadline);
        System.out.println("Got it. I have added this deadline.");
        System.out.println(deadline);
        System.out.println("You have " + list.size() + " items in your list");
    }


    public void addEvent(ArrayList<Task> list, String input) throws InvalidDescriptionException {
        String start;
        String end;
        String description;

        try {
            description = input.split("/from")[0].split(" ", 2)[1];
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidDescriptionException("You are missing the description for the event. Please try again.");
        }

        try {
            start = input.split("/from")[1].split("/to")[0];
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidDescriptionException("You are missing the start time for the event. Please try again.");
        }

        try {
            end = input.split("/from")[1].split("/to")[1];
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidDescriptionException("You are missing the end time for the event. Please try again.");
        }



        Event event = new Event(description, start, end);
        list.add(event);
        System.out.println("Got it. I have added this event.");
        System.out.println(event);
        System.out.println("You have " + list.size() + " items in your list.");
    }


    public void addItems() {

        Scanner scanner = new Scanner(System.in);
        label:
        while (true) {
            System.out.println("List contains " + toDo.toString());
            String input = scanner.nextLine();
            String instructions = input.split(" ")[0];
            Commands command = Commands.fromString(instructions);
            switch (command) {
                case BYE:
                    exit();
                    break label;
                case LIST:
                    this.list(this.toDo);
                    break;
                case MARK:
                    try {
                        this.markItem(this.toDo, input);
                        saveTasksToFile();
                    } catch (MissingPositionException | MissingItemException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case UNMARK: {
                    try {
                        this.unmarkItem(this.toDo, input);
                        saveTasksToFile();
                    } catch (MissingPositionException | MissingItemException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case DELETE: {
                    try {
                        this.delete(this.toDo, input);
                        saveTasksToFile();
                    } catch (MissingItemException | MissingPositionException | EmptyListException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case TODO: {
                    try {
                        this.addTodo(this.toDo, input);
                        saveTasksToFile();
                    }
                    catch (InvalidDescriptionException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case DEADLINE: {
                    try {
                        this.addDeadline(this.toDo, input);
                        saveTasksToFile();
                    }
                    catch(InvalidDescriptionException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case EVENT: {
                    try {
                        this.addEvent(this.toDo, input);
                        saveTasksToFile();
                    } catch (InvalidDescriptionException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case UNKNOWN: {
                    System.out.println("Please specify the type of item that you wish to add ( Todo / Event / Deadline )");
                    break;
                }
            }
        }
    }


    public static void exit() {
        System.out.println("Goodbye!");
    }
    public static void main(String[] args) {
        Derrick chatbot = new Derrick();
        chatbot.greetings();
        chatbot.addItems();
    }
}
