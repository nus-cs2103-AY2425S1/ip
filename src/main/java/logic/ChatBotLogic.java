package logic;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import logic.EventChainType;
import task.Deadline;
import task.Task;
import task.Todo;
import util.ListMapWriter;
import util.ListReader;

/**
 * The {@code ChatBotLogic} class is responsible for processing user input,
 * managing the state of the chatbot, and handling task-related operations such as
 * adding, viewing, marking, unmarking, deleting, and finding tasks.
 *
 * It uses an event chain model where the chatbot transitions through different states
 * based on user commands and input.
 */
public class ChatBotLogic {

	/** The current state of the chatbot's event chain. */
	private EventChainType eventChainType;

	/** A map that holds tasks, with task names as keys and {@link Task} objects as values. */
	private Map<String, Task> taskList;

	/** A utility for reading tasks from a file. */
	private ListReader reader = new ListReader();

	/** A utility for writing tasks to a file. */
	private ListMapWriter writer = new ListMapWriter();

	/** Temporary storage for task names during task creation. */
	private String tempName;

	/** Temporary storage for task descriptions during task creation. */
	private String tempDescription;

	/** A reference to a task for operations like marking, unmarking, or deleting. */
	private Task flaggedTask = null;

	/** The file path where tasks are stored. */
	private String filePath = System.getProperty("user.home") + "/tasks.txt";
	/**
	 * Constructs a new {@code ChatBotLogic} instance, initializes the event chain type,
	 * and loads the storage file containing tasks.
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
	 * Processes the user's message and returns the bot's response. The response
	 * depends on the current state of the chatbot, which is managed through an event chain.
	 *
	 * @param userMessage The message from the user.
	 * @return The bot's response.
	 */
	public String readInput(String userMessage) {
		if (eventChainType == EventChainType.TERMINATE) {
			switch (userMessage) {
			case "yes" -> {
				writer.writeMapToFile(taskList, filePath);
				System.exit(0);
			}
			default -> {
				eventChainType = EventChainType.DEFAULT;
				return "We are so back.";
			}
			}
		}
		if (eventChainType == EventChainType.DEFAULT) { // state DEFAULT
			switch (userMessage) {
			case "exit" -> {
				eventChainType = EventChainType.TERMINATE;
				return "It's jover?";
			}
			case "list" -> {
				eventChainType = EventChainType.LIST;
				return "Sure. What do you want to do with the list? (add, view, find, mark, unmark, delete)";
			}
			default -> {
				eventChainType = EventChainType.DEFAULT;
				return "At your service my queen.";
			}
			}
		}
		if (eventChainType == EventChainType.LIST) {    // state DEFAULT -> LIST
			switch (userMessage) {
				case "view" -> {
					eventChainType = EventChainType.VIEW;
					return taskList.toString();
				}
				case "add" -> {
					eventChainType = EventChainType.ADD;
					return "Enter the type of task to add: (todo, deadline)";
				}
				case "mark" -> {    // bypassing VIEW state to direct operate on list
					eventChainType = EventChainType.MARK;
					return "enter name of the task to mark done";
				}
				case "unmark" -> {  // bypassing VIEW state to direct operate on list
					eventChainType = EventChainType.UNMARK;
					return "enter name of the task to mark undone";
				}
				case "delete" -> {  // bypassing VIEW state to direct operate on list
					eventChainType = EventChainType.DELETE;
					return "enter the name of task to delete";
				}
				case "find" -> {    // bypassing VIEW state to direct operate on list
					eventChainType = EventChainType.FIND;
					return "enter keyword: ";
				}
				case "exit" -> {
					eventChainType = EventChainType.DEFAULT;
					return "return to main";
				}
				default -> {
					return "unknown command";
				}
			}
		}
		if (eventChainType == EventChainType.ADD) {     // state DEFAULT -> LIST -> ADD
			switch (userMessage) {
				case "todo" -> {
					eventChainType = EventChainType.TODO;
					return "enter the name of todo task: ";
				}
				case "deadline" -> {
					eventChainType = EventChainType.DEADLINE;
					return "enter the name of deadline task: ";
				}
				case "exit" -> {
					eventChainType = EventChainType.DEFAULT;
					return "return to main";
				}
				case "back" -> {
					eventChainType = EventChainType.LIST;
					return "back to List state";
				}
				default -> {
					eventChainType = EventChainType.DEFAULT;
					return "unknown type, return to main";
				}
			}
		}
		if (eventChainType == EventChainType.TODO) {    // state DEFAULT -> LIST -> ADD -> TODO
			switch (userMessage) {
				case "back" -> {
					eventChainType = EventChainType.ADD;
					return "return to add";
				}
				case "exit" -> {
					eventChainType = EventChainType.DEFAULT;
					return "back to main";
				}
				default -> {
					tempName = userMessage;
					eventChainType = EventChainType.TODO_NAMED;
					return "enter description:";
				}
			}
		}
		if (eventChainType == EventChainType.DEADLINE) {    // state DEFAULT -> LIST -> ADD -> DEADLINE
			switch (userMessage) {
			case "back" -> {
				eventChainType = EventChainType.ADD;
				return "return to add";
			}
			case "exit" -> {
				eventChainType = EventChainType.DEFAULT;
				return "back to main";
			}
			default -> {
				tempName = userMessage;
				eventChainType = EventChainType.DEADLINE_NAMED;
				return "enter description";
			}
			}
		}
		if (eventChainType == EventChainType.TODO_NAMED) {      // state DEFAULT -> LIST -> ADD -> TODO -> TODO_NAMED
			switch (userMessage) {
			case "back" -> {
				eventChainType = EventChainType.ADD;
				return "return to add";
			}
			case "exit" -> {
				eventChainType = EventChainType.DEFAULT;
				return "back to main";
			}
			default -> {
				taskList.put(tempName, Todo.createTodo(tempName, userMessage));
				eventChainType = EventChainType.ADD;
				return "todo added. enter type for the next task to be added: ";
			}
			}
		}
		if (eventChainType == EventChainType.DEADLINE_NAMED) {  // state DEFAULT -> LIST -> ADD -> DEADLINE -> DEADLINE_NAMED
			switch (userMessage) {
			case "back" -> {
				eventChainType = EventChainType.ADD;
				return "return to add";
			}
			case "exit" -> {
				eventChainType = EventChainType.DEFAULT;
				return "back to main";
			}
			default -> {
				tempDescription = userMessage;
				eventChainType = EventChainType.DEADLINE_DESCRIBED;
				return "enter deadline date (yyyy-mm-dd): ";
			}
			}
		}
		// state DEFAULT -> LIST -> ADD -> DEADLINE -> DEADLINE_NAMED -> DEADLINE_DESCRIBED
		if (eventChainType == EventChainType.DEADLINE_DESCRIBED) {
			switch (userMessage) {
			case "back" -> {
				eventChainType = EventChainType.ADD;
				return "return to add";
			}
			case "exit" -> {
				eventChainType = EventChainType.DEFAULT;
				return "back to main";
			}
			default -> {
				try {
					LocalDate byDate = LocalDate.parse(userMessage);
					taskList.put(tempName, Deadline.createDeadline(tempName, tempDescription, byDate));
					eventChainType = EventChainType.ADD;
					return "deadline added. enter type for the next task to be added: ";
				} catch (DateTimeParseException e) {
					// stay in DEADLINE_DESCRIBED state
					return "invalid format. please retry";
				}
			}
			}
		}
		// state DEFAULT -> LIST -> VIEW
		if (eventChainType == EventChainType.VIEW) {
			switch (userMessage) {
			case "find" -> {
				eventChainType = EventChainType.FIND;
				return "enter keyword";
			}
			case "mark" -> {
				eventChainType = EventChainType.MARK;
				return "enter name of the task to mark done";
			}
			case "unmark" -> {
				eventChainType = EventChainType.UNMARK;
				return "enter name of the task to mark undone";
			}
			case "delete" -> {
				eventChainType = EventChainType.DELETE;
				return "enter the name of task to delete";
			}
			case "back" -> {
				eventChainType = EventChainType.LIST;
				return "return to list";
			}
			case "exit" -> {
				eventChainType = EventChainType.DEFAULT;
				return "return to main";
			}
			default -> {
				return "unknown command";
			}
			}
		}
		// state DEFAULT -> LIST -> VIEW -> FIND
		if (eventChainType == EventChainType.FIND) {
			switch (userMessage) {
				case "back" -> {
					eventChainType = EventChainType.VIEW;
					return "back to view";
				}
				case "exit" -> {
					eventChainType = EventChainType.DEFAULT;
					return "back to main";
				}
			default -> {
				for (Task task : taskList.values()) {
					if (task.getName().toLowerCase().contains(userMessage)) {
						flaggedTask = task;
						eventChainType = EventChainType.FOUND;
						return "Found task: " + task.toString();
					}
				}
				return userMessage + " not found";
			}
			}
		}
		// state DEFAULT -> LIST -> VIEW -> FIND -> FOUND
		if (eventChainType == EventChainType.FOUND) {
			switch (userMessage) {
			case "back" -> {
				flaggedTask = null;
				eventChainType = EventChainType.VIEW;
				return "back to view";
			}
			case "exit" -> {
				flaggedTask = null;
				eventChainType = EventChainType.DEFAULT;
				return "back to main";
			}
			case "mark" -> {
					if (flaggedTask.isDone()) {
						return flaggedTask.getName() + " is already marked done";
					} else {
						String taskName = flaggedTask.getName();
						flaggedTask.markDone();
						flaggedTask = null;
						eventChainType = EventChainType.VIEW;
						return taskName + " marked done";
					}
				}
				case "unmark" -> {
					if (!flaggedTask.isDone()) {
						return flaggedTask.getName() + " is already marked undone";
					} else {
						String taskName = flaggedTask.getName();
						flaggedTask.markUndone();
						flaggedTask = null;
						eventChainType = EventChainType.VIEW;
						return taskName + " marked undone";
					}
				}
				case "delete" -> {
					String taskRemoved = flaggedTask.getName();
					taskList.remove(taskRemoved);
					flaggedTask = null;
					eventChainType = EventChainType.VIEW;
					return taskRemoved + " has been removed.";
				}
			}
		}
		// state DEFAULT -> LIST -> VIEW -> MARK
		if (eventChainType == EventChainType.MARK) {
			switch (userMessage) {
			case "back" -> {
				eventChainType = EventChainType.VIEW;
				return "back to view";
			}
			case "exit" -> {
				eventChainType = EventChainType.DEFAULT;
				return "back to main";
			}
			default -> {
				for (Task task : taskList.values()) {
					if (task.getName().equalsIgnoreCase(userMessage)) {
						task.markDone();
						return "Mark task: " + task.toString() + ". Enter name for the next task to mark";
					}
				}
				return userMessage + "not found.";
			}
			}
		}
		// state DEFAULT -> LIST -> VIEW -> UNMARK
		if (eventChainType == EventChainType.UNMARK) {
			switch (userMessage) {
			case "back" -> {
				eventChainType = EventChainType.VIEW;
				return "back to view";
			}
			case "exit" -> {
				eventChainType = EventChainType.DEFAULT;
				return "back to main";
			}
			default -> {
				for (Task task : taskList.values()) {
					if (task.getName().equalsIgnoreCase(userMessage)) {
						task.markUndone();
						return "Unmark task: " + task.toString() + ". Enter name for the next task to unmark";
					}
				}
				return userMessage + "not found.";
			}
			}
		}
		// state DEFAULT -> LIST -> VIEW -> DELETE
		if (eventChainType == EventChainType.DELETE) {
			switch (userMessage) {
			case "back" -> {
				eventChainType = EventChainType.VIEW;
				return "back to view";
			}
			case "exit" -> {
				eventChainType = EventChainType.DEFAULT;
				return "back to main";
			}
			default -> {
				for (Task task : taskList.values()) {
					if (task.getName().equalsIgnoreCase(userMessage)) {
						taskList.remove(userMessage);
						return "remove task: " + userMessage + ". Enter name for the next task to remove";
					}
				}
				return userMessage + "not found.";
			}
			}
		}
		writer.writeMapToFile(taskList, filePath);
		return "debugging";
	}
}
