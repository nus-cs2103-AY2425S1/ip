package buddybot;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This is the class for the chat bot with OOP added
 */
public class BuddyBot {
    private static String FILEPATH = "./data/BuddyBot.txt";
    private final FileStorage storage;
    private TaskList taskList;

    private final Ui ui;
    private boolean isRunning;

    /**
     * Constructor for BuddyBot
     */
    public BuddyBot() {
        this.storage = new FileStorage(BuddyBot.FILEPATH);
        this.ui = new Ui();
        this.isRunning = true;
        try {
            this.taskList = new TaskList(this.storage.readFileContents());
        } catch (BuddyBotException e) {
            this.ui.showBuddyBotException(e);
            this.taskList = new TaskList();
        }
    }

    private void run() {
        System.out.println(this.ui.welcomeMsg());
        while (this.isRunning) {
            String input = this.ui.readInput();
            System.out.println(this.getResponse(input));
        }
    }

    /**
     * Method to run BuddyBot, taking in the user input and making sense of the commands
     */
    protected String getResponse(String userInput) {
            try {
                //String userInput = this.ui.readInput();
                switch (Parser.parseCmd(userInput)) {
                    case BYE:
                        this.exit();
                        return this.ui.goodbyeMsg();
                    case LIST:
                        return this.ui.showList(this.taskList);
                    case TODO:
                        return this.addTodo(Parser.parseArgs(userInput));
                    case EVENT:
                        return this.addEvent(Parser.parseArgs(userInput));
                    case DEADLINE:
                        return this.addDeadline(Parser.parseArgs(userInput));
                    case DONE:
                        return this.markAsDone(Parser.parseArgs(userInput));
                    case DELETE:
                        return this.delete(Parser.parseArgs(userInput));
                    case FIND:
                        return this.find(Parser.parseArgs(userInput));
                    default:
                        throw new BuddyBotException("Unknown input");
                }
            } catch (BuddyBotException e) {
                return this.ui.showBuddyBotException(e);
            }
    }

    /**
     * Method to exit the chatbot
     * Saves the existing list to a txt file
     */
    private void exit() {
        this.ui.closeInput();
        this.storage.writeToTxt(this.taskList.toFile());
    }

    /**
     * Method to add a new To Do to the tasklist
     * @param description
     * @throws BuddyBotException
     */
    private String addTodo(String description) throws BuddyBotException {
        try {
            Task todo = new Todo(description);
            this.taskList.add(todo);
            return this.ui.addTask(todo, this.taskList.size());
        } catch (IndexOutOfBoundsException e) {
            throw new BuddyBotException("BuddyBot.Todo error");
        }
    }

    /**
     * Method to add a new event to the tasklist
     * @param args
     * @throws BuddyBotException
     */
    private String addEvent(String args) throws BuddyBotException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String[] splits = args.split(" /from ", 2);
            String[] timestamps = splits[1].split(" /to ", 2);
            String start = timestamps[0];
            String end = timestamps[1];
            LocalDate startTime = LocalDate.parse(start, formatter);
            LocalDate endTime = LocalDate.parse(end, formatter);
            if (startTime.isAfter(endTime)) {
                return this.ui.showInvalidDateRange();
            }

            Task event = new Event(splits[0], startTime, endTime);
            this.taskList.add(event);
            return this.ui.addTask(event, this.taskList.size());
        } catch (IndexOutOfBoundsException e) {
            throw new BuddyBotException("BuddyBot.Event error");
        } catch (DateTimeParseException e) {
            return this.ui.showInvalidDateFormat();
        }
    }

    /**
     * Method to add a new deadline to the tasklist
     * @param args
     * @throws BuddyBotException
     */
    private String addDeadline(String args) throws BuddyBotException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String[] splits = args.split(" /by ", 2);
            LocalDate time = LocalDate.parse(splits[1], formatter);

            Task deadline = new Deadline(splits[0], time);
            this.taskList.add(deadline);
            return this.ui.addTask(deadline, this.taskList.size());
        } catch (IndexOutOfBoundsException e) {
            throw new BuddyBotException("BuddyBot.Deadline error");
        } catch (DateTimeParseException e) {
            return this.ui.showInvalidDateFormat();
        }
    }

    /**
     * Marks the given task as done with an "X"
     * @param args
     * @throws BuddyBotException
     */
    private String markAsDone(String args) throws BuddyBotException {
        try {
            int taskNum = Integer.parseInt(args);
            if (taskNum > this.taskList.size()) {
                throw new BuddyBotException("test");
            }
            this.taskList.get(taskNum).mark();
            return this.ui.showDone(this.taskList.get(taskNum));
        } catch (NumberFormatException e) {
            throw new BuddyBotException("This is not a number");
        } catch (IndexOutOfBoundsException e) {
            throw new BuddyBotException("This task doesn't exist");
        }
    }

    /**
     * Method to remove a task from the tasklist
     * @param args
     * @throws BuddyBotException
     */
    private String delete(String args) throws BuddyBotException {
        try {
            int taskNum = Integer.parseInt(args);
            if (taskNum > this.taskList.size()) {
                throw new BuddyBotException("Your list is too small");
            }
            Task taskToDelete = this.taskList.get(taskNum);
            String message = this.ui.showDelete(taskToDelete, this.taskList.size());
            this.taskList.delete(taskNum);
            return message;
        } catch (NumberFormatException e) {
            throw new BuddyBotException("This is not a number");
        } catch (IndexOutOfBoundsException e) {
            throw new BuddyBotException("This task doesn't exist.");
        }
    }

    private String find(String args) throws BuddyBotException {
        String descript = args.split("find ")[0];
        return this.ui.showFound(taskList, descript);
    }

    /**
     * Main method for BuddyBot
     * @param args
     */
    public static void main(String[] args) {
        new BuddyBot().run();
    }

}
