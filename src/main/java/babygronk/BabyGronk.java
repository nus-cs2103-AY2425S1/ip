package babygronk;

import java.util.Scanner;

import parser.EmptyInputException;
import parser.Instruction;
import parser.InvalidInputException;
import parser.Parser;
import task.Deadline;
import task.Event;
import task.TaskList;
import task.ToDo;

/**
 * BabyGronk is the main class where all the program logic is handled.
 */
public class BabyGronk {
    /** Stores info and handles data of the file containing the task list **/
    private final Storage storage;

    /** Prints formatted message **/
    private final Ui ui;

    private final TaskList taskList;
    private String commandType;

    /**
     * Constructor for babyGronk, takes a String as input.
     * String should be a relative file pathname to init and store the tasks to.
     *
     * @param fileName File pathname string.
     */
    public BabyGronk(String fileName) {
        ui = new Ui();
        storage = new Storage(fileName);
        taskList = new TaskList(storage.init());
    }

    private String handleInput(String input) {
        commandType = ""; // Set to default for default response color in GUI
        assert input != null;
        Parser parser = new Parser();
        Instruction instruction;
        try {
            instruction = parser.parseInstruction(input);
        } catch (InvalidInputException e) {
            return (e.getMessage());
        }
        String command = instruction.getInstruction();
        if (instruction.getInstruction().equals("hi")) {
            return (Ui.sayHi());
        }
        if (instruction.getInstruction().equals("bye")) {
            storage.saveData(taskList.getTasks());
            ui.logOff();
        }
        if (Instruction.isListCommand(command)) {
            return (taskList.toString());
        }
        if (command.equals("mark") || command.equals("m")) {
            commandType = "ChangeMarkCommand";
            return (taskList.markTask(instruction.getArgs(), true));
        }
        if (command.equals("unmark") || command.equals("um")) {
            commandType = "ChangeMarkCommand";
            return (taskList.markTask(instruction.getArgs(), false));
        }
        if (Instruction.isDeleteCommand(command)) {
            commandType = "DeleteCommand";
            return (taskList.delete(instruction.getArgs()));
        }
        if (Instruction.isFindCommand(command)) {
            return (taskList.find(instruction.getArgs()[0]));
        }
        if (Instruction.isTaskCommand(command)) {
            commandType = "AddCommand";
            return (addTask(instruction));
        }
        return ("Error occurred, program shouldn't reach here");
    }

    private String addTask(Instruction instruction) {
        String task = instruction.getInstruction();
        String[] args = instruction.getArgs();
        switch (task) {
        case "todo":
            return (taskList.add(new ToDo(args[0])));
        case "deadline":
            return (taskList.add(new Deadline(args[0], args[1])));
        case "event":
            return (taskList.add(new Event(args[0], args[1], args[2])));
        default:
            return ("Invalid task: " + task + " (parser failed probably)");
        }
    }

    /**
     * Starts by displaying welcome message.
     * Runs the program on a loop waiting for user input using scanner and handling it.
     */
    public void run() {
        ui.greet();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (scanner.hasNextLine()) {
                try {
                    String input = Parser.parseInput(scanner.nextLine());
                    ui.printMessage(handleInput(input));
                } catch (EmptyInputException e) {
                    ui.printMessage(e.getMessage());
                }
            } else {
                scanner.close();
                break;
            }
        }
    }

    public String getResponse(String input) {
        try {
            return (handleInput(Parser.parseInput(input)));
        } catch (EmptyInputException e) {
            return (e.getMessage());
        }
    }

    public String getCommandType() {
        return (commandType);
    }

    public static void main(String[] args) {
        new BabyGronk("./data/BabyGronk.txt").run();
    }
}
