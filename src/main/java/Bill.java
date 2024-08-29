import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.format.DateTimeParseException;

public class Bill {
    private boolean directoryExists;
    java.nio.file.Path pathStorageDirectory;
    java.nio.file.Path pathStorageFile;


    private ArrayList<Task> userList;
    private enum Route {
        LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, INVALID
    }

    public Bill() {
        userList = new ArrayList<>();
        String home = System.getProperty("user.home");
        // run on desktop, but can omit depending on user saved location
        pathStorageDirectory = java.nio.file.Paths.get(home, "Desktop", "CS2103T_IP", "data");
        pathStorageFile = java.nio.file.Paths.get(home, "Desktop", "CS2103T_IP", "data", "bill.txt");
    }

    private void introduce() {
        System.out.println("Hello! I'm Bill");
        System.out.println("What can I do for you?");
    }

    private void conclude() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private void showList() {
        if (userList.isEmpty()) {
            System.out.println("List is empty");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < userList.size(); i++) {
                System.out.println((i + 1) + "." + userList.get(i));
            }
        }
    }

    private void saveList() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(String.valueOf(pathStorageFile)));
        for (int i = 0; i < userList.size(); i++) {
            writer.write((i + 1) + "." + userList.get(i));
            writer.newLine();
        }
        writer.close();
    }

    private void addTask(Task newTask) throws IOException {
        userList.add(newTask);
        System.out.println("added: " + newTask);
        System.out.println("Now you have " + userList.size() + " tasks in the list.");
        //update hardisk list
        saveList();
    }

    private void handleMarkOfTask(String[] parsedInput) throws BillException, IOException {
        // data validation
        if (parsedInput.length < 2) {
            throw new BillException("Please provide a second argument when marking or unmarking a task");
        }
        // ensure task number is within the range of the task list
        if (Integer.parseInt(parsedInput[1]) > userList.size() || Integer.parseInt(parsedInput[1]) < 1) {
            throw new BillException("There is no task of that number in the current list");
        }

        int targetTaskNumber = Integer.parseInt(parsedInput[1]) - 1;
        Task targetTask = userList.get(targetTaskNumber);

        if (parsedInput[0].equals("mark")) {
            targetTask.mark();
        } else {
            // unmarked
            targetTask.unmark();
        }
        System.out.println(targetTask);
        //update hardisk list
        saveList();

    }

    private void handleToDo(String userCommand) throws BillException, IOException {
        // data validation
        String[] parsedInput = userCommand.split(" ");
        if (parsedInput.length < 2) {
            throw new BillException("Please provide a second argument for the todo, such as a description");
        }
        String trimmedUserCommand = userCommand.replaceFirst("todo", "").trim();
        addTask(new ToDo(trimmedUserCommand));
    }

    private void handleDeadline(String userCommand) throws BillException, IOException {
        // data validation
        String[] parsedInput = userCommand.split(" ");
        if (parsedInput.length < 4) {
            throw new BillException("4 Arguments needed minimum for deadline command, following the format: deadline <task> /by <date>, where <> suggest user input");
        }
        if (!userCommand.contains(" /by ")) {
            throw new BillException("Missing /by with spaces around it, ensure to follow the format: deadline <task> /by <date> where <> suggest user input");
        }
        // data parsing
        // remove deadline, trim white spaces and delimit by /by
        try {
            String[] trimmedUserCommand = userCommand.replaceFirst("deadline", "").trim().split(" /by ");
            String deadlineDescription = trimmedUserCommand[0];
            String deadlineBy = trimmedUserCommand[1];

            addTask(new Deadline(deadlineDescription, deadlineBy));
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new BillException("Please ensure to follow the format: deadline <task> /by <date> where <> suggest user input.");
        } catch (DateTimeParseException ex) {
            throw new BillException(ex.getMessage() + ". Please ensure to follow the format yyyy-MM-dd");
        }
    }

    private void handleEvent(String userCommand) throws BillException, IOException {
        // data validation
        String[] parsedInput = userCommand.split(" ");
        if (parsedInput.length < 6) {
            throw new BillException("6 Arguments needed minimum for deadline command, following the format: event <task> /from <date1> /to <date2>, where <> suggest user input");
        }
        if (!userCommand.contains(" /from ")) {
            throw new BillException("Missing /from with spaces around it, ensure to follow the format: event <task> /from <date1> /to <date2>, where <> suggest user input");
        }
        if (!userCommand.contains(" /to ")) {
            throw new BillException("Missing /to with spaces around it, ensure to follow the format: event <task> /from <date1> /to <date2>, where <> suggest user input");
        }
        // data parsing
        // remove event, trim white spaces and delimit by /from and /to
        try {
            String[] trimmedUserCommand = userCommand.replaceFirst("event", "").trim().split(" /from ");
            String eventDescription = trimmedUserCommand[0];
            String[] furtherTrimmedUserCommand = trimmedUserCommand[1].trim().split(" /to ");
            String eventFrom = furtherTrimmedUserCommand[0];
            String eventTo = furtherTrimmedUserCommand[1];

            addTask(new Event(eventDescription, eventFrom, eventTo));
        } catch (ArrayIndexOutOfBoundsException ex) {
            throw new BillException("Please ensure to follow the format: event <task> /from <date1> /to <date2>, where <> suggest user input");
        }
    }

    private void handleDelete(String[] parsedInput) throws BillException {
        // data validation
        if (parsedInput.length < 2) {
            throw new BillException("Please provide a second argument when deleting a task");
        }
        if (parsedInput.length > 2) {
            throw new BillException("Please provide only one additional argument after the delete word, ensure to follow the format: delete <number>, where <> is your input");
        }
        // ensure task number is within the range of the task list
        if (Integer.parseInt(parsedInput[1]) > userList.size() || Integer.parseInt(parsedInput[1]) < 1) {
            throw new BillException("There is no task of that number in the current list, unable to delete, please try again with a valid number");
        }

        int targetTaskNumber = Integer.parseInt(parsedInput[1]) - 1;
        Task targetTask = userList.get(targetTaskNumber);

        userList.remove(targetTaskNumber);
        System.out.println("Noted. I've removed this task:");
        System.out.println(targetTask);
        System.out.println("Now you have " + userList.size() + " tasks in the list.");
        //update hardisk list
    }

    private Route getRouteEnum(String route) {
        String routeValue = route.toUpperCase();
        try {
            return Route.valueOf(routeValue);
        } catch (IllegalArgumentException ex) {
            return Route.INVALID;
        }
    }

    private void loadStorage() throws IOException, BillException {
        boolean directoryExists = java.nio.file.Files.exists(pathStorageDirectory);
        boolean fileExists  = java.nio.file.Files.exists(pathStorageFile);
        // if directory doesn't exist
        if (!directoryExists) {
            // make the directory and the file
            Files.createDirectory(pathStorageDirectory);
        }
        // if directory exists but file doesn't
        if (!fileExists) {
            // make the file
            Files.createFile(pathStorageFile);
        }

        File file = new File(String.valueOf(pathStorageFile));
        // if text file empty return early to main function
        if (file.length() == 0) {
            return;
        }

        // while loop until finish reading bill.txt or error
        BufferedReader lineReader = new BufferedReader(new FileReader(String.valueOf(pathStorageFile)));

        String line;
        while ((line = lineReader.readLine()) != null) {
            // get 4 char the route, note index from 0
            char route = line.charAt(3);


            // get 7 char the mark
            char mark = line.charAt(6);
            boolean isMarked = mark == 'X';

            // get index of first char after marking [ ] or [X] and the space after
            int firstSpace = Math.max(line.indexOf("[ ]"), line.indexOf("[X]")) + 4;

            // get index
            int index = Integer.parseInt(String.valueOf(line.charAt(0)));

            // load storage into list var
                switch (route) {
                    case 'T':
                        System.out.println("todo " + line.substring(firstSpace));
                        handleToDo("todo " + line.substring(firstSpace));
                        break;
                    case 'D':
                        handleDeadline("deadline " + line.substring(firstSpace).replace("(", "").replace(")", "").replace("by:", "/by"));
                        break;
                    case 'E':
                        handleEvent("event " + line.substring(firstSpace).replace("(", "").replace(")", "").replace("from:", "/from").replace("to:", "/to"));
                        break;
                    default:
                        throw new BillException("Not a recognised command in bill.txt, please ensure that all lines in bill.txt have the template of the expected output based on user commands");
                }
                if (isMarked) {
                    handleMarkOfTask(new String[]{"mark", String.valueOf(index)});
                }
        }
    }

    public void start() {
        PrintStream originalOutput = System.out;
        // temp output to hide print messages, so can leverage previously built methods which have print statements
        OutputStream silence = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                //do nothing
            }
        };

        try {
            System.setOut(new PrintStream(silence));
            loadStorage();
        } catch (IOException ex) {
            System.setOut(originalOutput);
            System.out.println(ex.getMessage());
            return;
        } catch (BillException ex) {
            System.setOut(originalOutput);
            System.out.println("There is a formatting issue with the bill.txt file, it is related to:");
            System.out.println(ex.getMessage());
            return;
        }

        System.setOut(originalOutput);

        introduce();
        Scanner userScanner = new Scanner(System.in);
        // remove leading and trailing whitespaces
        String userCommand = userScanner.nextLine().trim();

        while (!userCommand.equals("bye")) {
            String[] parsedInput = userCommand.split(" ");
            Route route = getRouteEnum(parsedInput[0]);
            try {
                switch (route) {
                    case LIST:
                        showList();
                        break;
                    case MARK:
                    case UNMARK:
                        handleMarkOfTask(parsedInput);
                        break;
                    case TODO:
                        handleToDo(userCommand);
                        break;
                    case DEADLINE:
                        handleDeadline(userCommand);
                        break;
                    case EVENT:
                        handleEvent(userCommand);
                        break;
                    case DELETE:
                        handleDelete(parsedInput);
                        break;
                    default:
                        throw new BillException("Not a recognised command, please try again");
                }
            } catch (BillException ex) {
                System.out.println(ex.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            userCommand = userScanner.nextLine();
        }
        conclude();
    }

    public static void main(String[] args) {
        new Bill().start();
    }
}