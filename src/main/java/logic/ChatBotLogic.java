package logic;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import util.ListMapWriter;
import util.ListReader;

/**
 * The {@code ChatBotLogic} class is responsible for processing user input,
 * managing the state of the chatbot, and handling task-related operations such as
 * adding, viewing, marking, unmarking, deleting, sorting, and finding tasks.
 * <p>
 * It uses an event chain model where the chatbot transitions through different states
 * based on user commands and input.
 */
public class ChatBotLogic {
    /**
     * The current state of the chatbot's event chain.
     */
    private EventChainType eventChainType;
    /**
     * A map that holds tasks, with task names as keys and {@link Task} objects as values.
     */
    private Map<String, Task> taskList;
    /**
     * A utility for reading tasks from a file.
     */
    private ListReader reader = new ListReader();
    /**
     * A utility for writing tasks to a file.
     */
    private ListMapWriter writer = new ListMapWriter();
    /**
     * Temporary storage for task names during task creation.
     */
    private String tempName;
    /**
     * Temporary storage for task descriptions during task creation.
     */
    private String tempDescription;
    /**
     * Temporary storage for event start time during event creation.
     */
    private LocalDate tempStartTime;
    /**
     * Temporary storage for event end time during event creation.
     */
    private LocalDate tempEndTime;
    /**
     * Temporary storage for event location during event creation.
     */
    private String tempLocation;
    /**
     * A reference to a task for operations like marking, unmarking, or deleting.
     */
    private Task flaggedTask = null;
    /**
     * The file path where tasks are stored.
     */
    private String filePath = System.getProperty("user.home") + "/sigmaBotTasks.txt";
    /**
     * Constructs a new {@code ChatBotLogic} instance, initializes the event chain type,
     * and loads the storage file containing tasks.
     */
    private final List<String> jokeBank = List.of("Why don't programmers like nature? It has too many bugs.", "Why do Java developers wear glasses? Because they don’t C#.", "How many programmers does it take to change a light bulb? None, that's a hardware problem.", "I would tell you a UDP joke, but you might not get it.", "There are 10 types of people in the world: those who understand binary, and those who don’t.", "Why do programmers prefer dark mode? Because the light attracts bugs.", "What’s a programmer’s favorite hangout place? Foo Bar.", "Why did the programmer quit his job? Because he didn’t get arrays.", "A SQL query walks into a bar, walks up to two tables and asks, 'Can I join you?'", "Why do programmers hate nature? It has too many trees (and trees have nodes).", "What is a computer’s favorite snack? Microchips.", "I tried to make a computer joke, but it wasn’t funny enough for the cache.", "Why did the computer show up at work late? It had a hard drive.", "In C, you really have to watch out for buffer overflows. In Java, you just get a stack trace.", "Why don’t skeletons use Git? They don’t have enough backbone for version control.", "How do you comfort a JavaScript bug? You console it.", "Why can't a computer play tennis? It doesn’t like its server.", "What's a computer’s least favorite type of music? The bluescreen.", "Why did the developer go broke? Because he used up all his cache.", "Why is Java like a smartphone? Once you start using it, you have no memory left.", "What’s the object-oriented way to become wealthy? Inheritance.");

    /**
     * Constructs a new {@code ChatBotLogic} instance, initializes the default state,
     * and loads the task storage file from the system's default file path.
     * <p>
     * This constructor sets the initial {@link EventChainType} to {@code DEFAULT}, ensuring
     * the chatbot is ready to process commands. It also attempts to load tasks from a
     * previously saved storage file, creating a new one if none exists.
     */
    public ChatBotLogic() {
        eventChainType = EventChainType.DEFAULT;
        loadStorageFile();
    }

    private String loadStorageFile() {
        File taskFile = new File(filePath);
        if (!taskFile.exists()) {
            try {
                if (taskFile.createNewFile()) {
                    taskList = new HashMap<String, Task>();
                    return ("No tasks file found. Created a new file: " + filePath);
                } else {
                    return ("Failed to create a new tasks file.");
                }
            } catch (IOException e) {
                return ("Error creating tasks file: " + e.getMessage());
            }
        } else {
            try {
                taskList = reader.readTasksFromFile(filePath);
            } catch (IOException e) {
                return ("Error loading tasks: " + e.getMessage());
            }
        }
        return "Read list from" + filePath;
    }

    /**
     * Picks a random joke from the joke bank.
     *
     * @return A random joke as a string.
     */
    private String getRandomJoke() {
        Random random = new Random();
        int index = random.nextInt(jokeBank.size());
        return jokeBank.get(index);
    }

    /**
     * Processes the user's message and returns the bot's response. The response
     * depends on the current state of the chatbot, which is managed through an event chain.
     *
     * @param userMessage The message from the user.
     * @return The bot's response.
     */
    public String readInput(String userMessage) {
        switch (eventChainType) {
        case TERMINATE -> {
            return processTerminateState(userMessage);
        }
        case DEFAULT -> {
            return processDefaultState(userMessage);
        }
        case LIST -> {
            return processListState(userMessage);
        }
        case JOKE -> {
            return processJokeState(userMessage);
        }
        case ADD -> {
            return processAddState(userMessage);
        }
        case TODO -> {
            return processTodoState(userMessage);
        }
        case DEADLINE -> {
            return processDeadlineState(userMessage);
        }
        case EVENT -> {
            return processEventState(userMessage);
        }
        case TODO_NAMED -> {
            return processTodoNamedState(userMessage);
        }
        case DEADLINE_NAMED -> {
            return processDeadlineNamedState(userMessage);
        }
        case DEADLINE_DESCRIBED -> {
            return processDeadlineDescribedState(userMessage);
        }
        case EVENT_NAMED -> {
            return processEventNamedState(userMessage);
        }
        case EVENT_DESCRIBED -> {
            return processEventDescribedState(userMessage);
        }
        case EVENT_START_SET -> {
            return processEventStartSetState(userMessage);
        }
        case EVENT_END_SET -> {
            return processEventEndSetState(userMessage);
        }
        case VIEW -> {
            return processViewState(userMessage);
        }
        case FIND -> {
            return processFindState(userMessage);
        }
        case FOUND -> {
            return processFoundState(userMessage);
        }
        case MARK -> {
            return processMarkState(userMessage);
        }
        case UNMARK -> {
            return processUnmarkState(userMessage);
        }
        case DELETE -> {
            return processDeleteState(userMessage);
        }
        default -> {
            return "unknown state encountered";
        }
        }
    }

    private String processTerminateState(String userMessage) {
        switch (userMessage) {
        case "yes" -> {
            writer.writeMapToFile(taskList, filePath);
            System.exit(0);
        }
        default -> {
            EventChainType.setState(this, EventChainType.DEFAULT);
            return "We are so back.";
        }
        }
        return "";
    }

    private String processDefaultState(String userMessage) {
        switch (userMessage) {
        case "exit" -> {
            EventChainType.setState(this, EventChainType.TERMINATE);
            return "It's jover?";
        }
        case "list" -> {
            EventChainType.setState(this, EventChainType.LIST);
            return "Sure. What do you want to do with the list? (add, view, find, mark, unmark, sort, delete)";
        }
        case "joke" -> {
            EventChainType.setState(this, EventChainType.JOKE);
            return "You sure?";
        }
        default -> {
            return "At your service my queen.";
        }
        }
    }

    private String processListState(String userMessage) {
        assert taskList != null;
        switch (userMessage) {
        case "view" -> {
            EventChainType.setState(this, EventChainType.VIEW);
            return "You currently have " + taskList.size() + "tasks: \n" + taskList.keySet().stream().reduce("", (x, y) -> x + taskList.get(y) + y + "\n");
        }
        case "add" -> {
            EventChainType.setState(this, EventChainType.ADD);
            return "Enter the type of task to add: (todo, deadline, event)";
        }
        case "mark" -> {
            EventChainType.setState(this, EventChainType.MARK);
            return "enter name of the task to mark done";
        }
        case "unmark" -> {
            EventChainType.setState(this, EventChainType.UNMARK);
            return "enter name of the task to mark undone";
        }
        case "delete" -> {
            EventChainType.setState(this, EventChainType.DELETE);
            return "enter the name of task to delete";
        }
        case "sort" -> {
            return taskList.keySet().stream().sorted().reduce("", (x, y) -> x + taskList.get(y) + y + "\n");
        }
        case "find" -> {
            EventChainType.setState(this, EventChainType.FIND);
            return "enter keyword: ";
        }
        case "exit" -> {
            EventChainType.setState(this, EventChainType.DEFAULT);
            return "return to main";
        }
        default -> {
            return "unknown list command";
        }
        }
    }

    private String processJokeState(String userMessage) {
        switch (userMessage) {
        case "back" -> {
            EventChainType.setState(this, EventChainType.DEFAULT);
            return "return to main";
        }
        case "exit" -> {
            EventChainType.setState(this, EventChainType.DEFAULT);
            return "back to main";
        }
        default -> {
            EventChainType.setState(this, EventChainType.DEFAULT); // Reset state to default after the joke
            return getRandomJoke();
        }
        }
    }

    private String processAddState(String userMessage) {
        switch (userMessage) {
        case "todo" -> {
            EventChainType.setState(this, EventChainType.TODO);
            return "enter the name of todo task: ";
        }
        case "deadline" -> {
            EventChainType.setState(this, EventChainType.DEADLINE);
            return "enter the name of deadline task: ";
        }
        case "event" -> {
            EventChainType.setState(this, EventChainType.EVENT);
            return "Enter the name of the event task:";
        }
        case "exit" -> {
            EventChainType.setState(this, EventChainType.DEFAULT);
            return "return to main";
        }
        case "back" -> {
            EventChainType.setState(this, EventChainType.LIST);
            return "back to List state";
        }
        default -> {
            return "unknown type";
        }
        }
    }

    private String processTodoState(String userMessage) {
        switch (userMessage) {
        case "back" -> {
            EventChainType.setState(this, EventChainType.ADD);
            return "return to add";
        }
        case "exit" -> {
            EventChainType.setState(this, EventChainType.DEFAULT);
            return "back to main";
        }
        default -> {
            tempName = userMessage;
            EventChainType.setState(this, EventChainType.TODO_NAMED);
            return "enter description:";
        }
        }
    }

    private String processDeadlineState(String userMessage) {
        switch (userMessage) {
        case "back" -> {
            EventChainType.setState(this, EventChainType.ADD);
            return "return to add";
        }
        case "exit" -> {
            EventChainType.setState(this, EventChainType.DEFAULT);
            return "back to main";
        }
        default -> {
            tempName = userMessage;
            EventChainType.setState(this, EventChainType.DEADLINE_NAMED);
            return "enter description";
        }
        }
    }

    private String processTodoNamedState(String userMessage) {
        switch (userMessage) {
        case "back" -> {
            EventChainType.setState(this, EventChainType.ADD);
            return "return to add";
        }
        case "exit" -> {
            EventChainType.setState(this, EventChainType.DEFAULT);
            return "back to main";
        }
        default -> {
            taskList.put(tempName, Todo.createTodo(tempName, userMessage)); // push newly created task in map
            writer.writeMapToFile(taskList, filePath); // immediate rewrite storage upon update
            EventChainType.setState(this, EventChainType.ADD);
            return "todo added. You currently have " + taskList.size()
                    + " tasks: \n" + "enter type for the next task to be added: ";
        }
        }
    }

    private String processDeadlineNamedState(String userMessage) {
        switch (userMessage) {
        case "back" -> {
            EventChainType.setState(this, EventChainType.ADD);
            return "return to add";
        }
        case "exit" -> {
            EventChainType.setState(this, EventChainType.DEFAULT);
            return "back to main";
        }
        default -> {
            tempDescription = userMessage;
            EventChainType.setState(this, EventChainType.DEADLINE_DESCRIBED);
            return "enter deadline date (yyyy-mm-dd): ";
        }
        }
    }

    private String processDeadlineDescribedState(String userMessage) {
        switch (userMessage) {
        case "back" -> {
            EventChainType.setState(this, EventChainType.ADD);
            return "return to add";
        }
        case "exit" -> {
            EventChainType.setState(this, EventChainType.DEFAULT);
            return "back to main";
        }
        default -> {
            try {
                LocalDate byDate = LocalDate.parse(userMessage);
                taskList.put(tempName, Deadline.createDeadline(tempName, tempDescription, byDate));
                writer.writeMapToFile(taskList, filePath); // immediate rewrite storage upon update
                EventChainType.setState(this, EventChainType.ADD);
                return "deadline added. You currently have " + taskList.size()
                        + " tasks: \n" + "enter type for the next task to be added: ";
            } catch (DateTimeParseException e) {
                return "invalid format. please retry";
            }
        }
        }
    }

    private String processEventState(String userMessage) {
        switch (userMessage) {
        case "back" -> {
            EventChainType.setState(this, EventChainType.ADD);
            return "Returning to add menu.";
        }
        case "exit" -> {
            EventChainType.setState(this, EventChainType.DEFAULT);
            return "Returning to main menu.";
        }
        default -> {
            tempName = userMessage;
            EventChainType.setState(this, EventChainType.EVENT_NAMED);
            return "Enter description for the event:";
        }
        }
    }

    private String processEventNamedState(String userMessage) {
        switch (userMessage) {
        case "back" -> {
            EventChainType.setState(this, EventChainType.EVENT);
            return "Returning to event name entry.";
        }
        case "exit" -> {
            EventChainType.setState(this, EventChainType.DEFAULT);
            return "Returning to main menu.";
        }
        default -> {
            tempDescription = userMessage;
            EventChainType.setState(this, EventChainType.EVENT_DESCRIBED);
            return "Enter the start date of the event (yyyy-MM-dd):";
        }
        }
    }

    private String processEventDescribedState(String userMessage) {
        switch (userMessage) {
        case "back" -> {
            EventChainType.setState(this, EventChainType.EVENT_NAMED);
            return "Returning to event description entry.";
        }
        case "exit" -> {
            EventChainType.setState(this, EventChainType.DEFAULT);
            return "Returning to main menu.";
        }
        default -> {
            try {
                tempStartTime = LocalDate.parse(userMessage);
                EventChainType.setState(this, EventChainType.EVENT_START_SET);
                return "Enter the end date of the event (yyyy-MM-dd):";
            } catch (DateTimeParseException e) {
                return "Invalid date format. Please enter in yyyy-MM-dd format.";
            }
        }
        }
    }

    private String processEventStartSetState(String userMessage) {
        switch (userMessage) {
        case "back" -> {
            EventChainType.setState(this, EventChainType.EVENT_DESCRIBED);
            return "Returning to start date entry.";
        }
        case "exit" -> {
            EventChainType.setState(this, EventChainType.DEFAULT);
            return "Returning to main menu.";
        }
        default -> {
            try {
                tempEndTime = LocalDate.parse(userMessage);
                if (tempEndTime.isBefore(tempStartTime)) {
                    return "End date cannot be before start date. Please enter a valid end date.";
                }
                EventChainType.setState(this, EventChainType.EVENT_END_SET);
                return "Enter the location of the event:";
            } catch (DateTimeParseException e) {
                return "Invalid date format. Please enter in yyyy-MM-dd format.";
            }
        }
        }
    }

    private String processEventEndSetState(String userMessage) {
        switch (userMessage) {
        case "back" -> {
            EventChainType.setState(this, EventChainType.EVENT_START_SET);
            return "Returning to end date entry.";
        }
        case "exit" -> {
            EventChainType.setState(this, EventChainType.DEFAULT);
            return "Returning to main menu.";
        }
        default -> {
            tempLocation = userMessage;
            // Create and add the Event task
            Event event = Event.createEvent(tempName, tempDescription, tempStartTime, tempEndTime, tempLocation);
            taskList.put(tempName, event);
            writer.writeMapToFile(taskList, filePath); // immediate rewrite storage upon update
            // Reset temporary variables
            tempName = null;
            tempDescription = null;
            tempStartTime = null;
            tempEndTime = null;
            tempLocation = null;
            EventChainType.setState(this, EventChainType.ADD);
            return "event added. You currently have " + taskList.size()
                    + " tasks: \n" + "enter type for the next task to be added: ";
        }
        }
    }

    private String processViewState(String userMessage) {
        switch (userMessage) {
        case "find" -> {
            EventChainType.setState(this, EventChainType.FIND);
            return "enter keyword";
        }
        case "mark" -> {
            EventChainType.setState(this, EventChainType.MARK);
            return "enter name of the task to mark done";
        }
        case "unmark" -> {
            EventChainType.setState(this, EventChainType.UNMARK);
            return "enter name of the task to mark undone";
        }
        case "delete" -> {
            EventChainType.setState(this, EventChainType.DELETE);
            return "enter the name of task to delete";
        }
        case "back" -> {
            EventChainType.setState(this, EventChainType.LIST);
            return "return to list";
        }
        case "exit" -> {
            EventChainType.setState(this, EventChainType.DEFAULT);
            return "return to main";
        }
        case "sort" -> {
            return taskList.keySet().stream().sorted().reduce("", (x, y) -> x + taskList.get(y) + y + "\n");
        }
        default -> {
            return "unknown command";
        }
        }
    }

    private String processFindState(String userMessage) {
        switch (userMessage) {
        case "back" -> {
            EventChainType.setState(this, EventChainType.VIEW);
            return "back to view";
        }
        case "exit" -> {
            EventChainType.setState(this, EventChainType.DEFAULT);
            return "back to main";
        }
        default -> {
            for (Task task : taskList.values()) {
                if (task.getName().toLowerCase().contains(userMessage)) {
                    flaggedTask = task;
                    EventChainType.setState(this, EventChainType.FOUND);
                    return "Found task: " + task.toString();
                }
            }
            return userMessage + " not found";
        }
        }
    }

    private String processFoundState(String userMessage) {
        switch (userMessage) {
        case "back" -> {
            flaggedTask = null;
            EventChainType.setState(this, EventChainType.VIEW);
            return "back to view";
        }
        case "exit" -> {
            flaggedTask = null;
            EventChainType.setState(this, EventChainType.DEFAULT);
            return "back to main";
        }
        case "mark" -> {
            assert flaggedTask != null;
            if (flaggedTask.isDone()) {
                return flaggedTask.getName() + " is already marked done";
            } else {
                String taskName = flaggedTask.getName();
                flaggedTask.markDone();
                flaggedTask = null;
                EventChainType.setState(this, EventChainType.VIEW);
                return taskName + " marked done";
            }
        }
        case "unmark" -> {
            assert flaggedTask != null;
            if (!flaggedTask.isDone()) {
                return flaggedTask.getName() + " is already marked undone";
            } else {
                String taskName = flaggedTask.getName();
                flaggedTask.markUndone();
                flaggedTask = null;
                EventChainType.setState(this, EventChainType.VIEW);
                return taskName + " marked undone";
            }
        }
        case "delete" -> {
            String taskRemoved = flaggedTask.getName();
            taskList.remove(taskRemoved);
            flaggedTask = null;
            EventChainType.setState(this, EventChainType.VIEW);
            return taskRemoved + " has been removed.";
        }
        default -> {
            return "unknown command";
        }
        }
    }

    private String processMarkState(String userMessage) {
        switch (userMessage) {
        case "back" -> {
            EventChainType.setState(this, EventChainType.VIEW);
            return "back to view";
        }
        case "exit" -> {
            EventChainType.setState(this, EventChainType.DEFAULT);
            return "back to main";
        }
        default -> {
            for (Task task : taskList.values()) {
                if (task.getName().equalsIgnoreCase(userMessage)) {
                    task.markDone();
                    return "Mark task: " + task.toString() + ". Enter name for the next task to mark";
                }
            }
            return userMessage + " not found.";
        }
        }
    }

    private String processUnmarkState(String userMessage) {
        switch (userMessage) {
        case "back" -> {
            EventChainType.setState(this, EventChainType.VIEW);
            return "back to view";
        }
        case "exit" -> {
            EventChainType.setState(this, EventChainType.DEFAULT);
            return "back to main";
        }
        default -> {
            for (Task task : taskList.values()) {
                if (task.getName().equalsIgnoreCase(userMessage)) {
                    task.markUndone();
                    return "Unmark task: " + task.toString() + ". Enter name for the next task to unmark";
                }
            }
            return userMessage + " not found.";
        }
        }
    }

    private String processDeleteState(String userMessage) {
        switch (userMessage) {
        case "back" -> {
            EventChainType.setState(this, EventChainType.VIEW);
            return "back to view";
        }
        case "exit" -> {
            EventChainType.setState(this, EventChainType.DEFAULT);
            return "back to main";
        }
        default -> {
            for (Task task : taskList.values()) {
                if (task.getName().equalsIgnoreCase(userMessage)) {
                    taskList.remove(userMessage);
                    return "remove task: " + userMessage + ". Enter name for the next task to remove";
                }
            }
            return userMessage + " not found.";
        }
        }
    }

    // Method to set the event chain type for the bot
    public void setEventChainType(EventChainType eventChainType) {
        this.eventChainType = eventChainType;
    }
}
