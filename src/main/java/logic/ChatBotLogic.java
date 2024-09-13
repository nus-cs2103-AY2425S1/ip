package logic;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.stream.Collectors;

import task.Deadline;
import task.Task;
import task.Todo;
import util.ListMapWriter;
import util.ListReader;

/**
 * The {@code ChatBotLogic} class is responsible for processing user input,
 * managing the state of the chatbot, and handling task-related operations such as
 * adding, viewing, marking, unmarking, deleting, and finding tasks.
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
	 * A reference to a task for operations like marking, unmarking, or deleting.
	 */
	private Task flaggedTask = null;

	/**
	 * The file path where tasks are stored.
	 */
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
		case ADD -> {
			return processAddState(userMessage);
		}
		case TODO -> {
			return processTodoState(userMessage);
		}
		case DEADLINE -> {
			return processDeadlineState(userMessage);
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
		}
		writer.writeMapToFile(taskList, filePath);
		return "debugging";
	}

	// State processing methods
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
			return "Sure. What do you want to do with the list? (add, view, find, mark, unmark, delete)";
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
			return taskList.toString();
		}
		case "add" -> {
			EventChainType.setState(this, EventChainType.ADD);
			return "Enter the type of task to add: (todo, deadline)";
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
			return "unknown command";
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
			taskList.put(tempName, Todo.createTodo(tempName, userMessage));
			EventChainType.setState(this, EventChainType.ADD);
			return "todo added. enter type for the next task to be added: ";
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
				EventChainType.setState(this, EventChainType.ADD);
				return "deadline added. enter type for the next task to be added: ";
			} catch (DateTimeParseException e) {
				return "invalid format. please retry";
			}
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
		}
		return "";
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
