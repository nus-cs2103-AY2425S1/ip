import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;

import java.nio.file.Paths;

public class Dumpling {

    private static final String DIVIDER = "    ____________________________________________________________";

    private TaskList todoList;
    private String saveDataPath;

    public Dumpling() {
        this.todoList = new TaskList();
        try {
            this.initialiseSaveFile();
        } catch (EmptyDescriptionException | InvalidTaskType e) {
            System.out.println(e.getMessage());
        }
    }

    private void initialiseSaveFile() throws EmptyDescriptionException, InvalidTaskType {
        String projectRootDir = System.getProperty("user.dir");
        String dataDirPath = Paths.get(projectRootDir, "data").toString();
        File dataDirFile = new File(dataDirPath);
        if (!dataDirFile.exists()) {
            dataDirFile.mkdir();
        }
        this.saveDataPath = Paths.get(dataDirPath, "dumplingData.txt").toString();
        File dataFile = new File(this.saveDataPath);
        try {
            // load data
            Scanner scanner = new Scanner(dataFile);
            while (scanner.hasNext()) {
                String[] line = scanner.nextLine().split(" \\| ");
                switch (line[0]) {
                case "T":
                    this.todoList.add(String.format("todo %s", line[2]), Command.TODO);
                    break;
                case "D":
                    this.todoList.add(
                            String.format("deadline %s /by %s", line[2], line[3]), Command.DEADLINE);
                    break;
                case "E":
                    String[] fromAndTo = line[3].split("-");
                    this.todoList.add(
                            String.format("event %s /from %s /to %s", line[2], fromAndTo[0], fromAndTo[1]),
                            Command.EVENT
                    );
                    break;
                default:
                    throw new InvalidTaskType(
                            String.format("%s is not a valid task type. Data might be corrupted.", line[0]));
                }
                if (line[1].equals("1")) {
                    this.todoList.mark(this.todoList.getNumItems());
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            // no data to load
        }
    }

    private void saveData() throws IOException {
        FileWriter fileWriter = new FileWriter(this.saveDataPath);
        String data = this.todoList.getTasksForSaving();
        if (data.isEmpty()) {
            fileWriter.close();
            File dataFile = new File(this.saveDataPath);
            dataFile.delete();
            return;
        }
        fileWriter.write(data);
        fileWriter.close();
    }

    private boolean commandRouter(String userInput) throws InvalidCommandException,
            EmptyDescriptionException, NumberFormatException, IndexOutOfBoundsException, IOException,
            DateTimeParseException {
        String operationMessage = "";
        Command command = Command.getCommand(userInput.split(" ")[0]);
        switch (command) {
        case BYE:
            this.exit();
            this.saveData();
            return false;
        case LIST:
            operationMessage += this.todoList.list();
            break;
        case MARK:
            operationMessage += this.todoList.mark(
                    Integer.parseInt(userInput.split(" ")[1]));
            break;
        case UNMARK:
            operationMessage += this.todoList.unmark(
                    Integer.parseInt(userInput.split(" ")[1]));
            break;
        case TODO:
            // Fallthrough
        case DEADLINE:
            // Fallthrough
        case EVENT:
            // Fallthrough
            operationMessage += this.todoList.add(userInput, command);
            break;
        case DELETE:
            operationMessage += this.todoList.delete(
                    Integer.parseInt(userInput.split(" ")[1]));
            break;
        default:
            // invalid commands would have been thrown when getting the command
            break;
        }
        if (!operationMessage.isEmpty()) {
            System.out.println(operationMessage);
        }
        return true;
    }

    private void greet() {
        String greetingMessage = "    Hello! I'm Dumpling\n" +
                "    What can I do for you?\n" +
                DIVIDER;
        System.out.println(greetingMessage);
    }

    private void exit() {
        String exitMessage = "    Bye. Hope to see you again soon!\n" +
                DIVIDER;
        System.out.println(exitMessage);
    }

    private void echo(String message) {
        String updatedMessage = "    " + message + "\n" +
                DIVIDER;
        System.out.println(updatedMessage);
    }

    public void run() {
        System.out.println(DIVIDER);
        this.greet();
        Scanner scanner = new Scanner(System.in);
        boolean toContinue = true;
        String userInput;
        while (toContinue) {
            userInput = scanner.nextLine();
            System.out.println(DIVIDER);
            try {
                toContinue = commandRouter(userInput);
            } catch (InvalidCommandException | EmptyDescriptionException e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println(
                    "     There was an issue when marking / unmarking a task! The argument provided was not a number.");
            } catch (IndexOutOfBoundsException e) {
                if (this.todoList.getNumItems() == 0) {
                    System.out.println("     The task list is still empty! Fill it up with some tasks before marking / unmarking.");
                } else {
                    System.out.println(
                        String.format(
                                "     There was an issue with indexing a task. Please ensure the index is 1-based and <= %d.",
                                this.todoList.getNumItems()
                        )
                    );
                }
            } catch (IOException e) {
                System.out.println("     There was an issue saving the data!");
            } catch (DateTimeParseException e) {
                System.out.println("     Please enter the date in the correct format of YYYY-MM-DD.");
            } finally {
                if (toContinue) {
                    System.out.println(DIVIDER);
                }
            }
        }
        scanner.close();
    }
}
