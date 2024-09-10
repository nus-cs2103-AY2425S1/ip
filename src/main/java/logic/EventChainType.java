package logic;

/**
 * The {@code EventChainType} enum defines the different states that the chatbot can be in during an event chain.
 * These states determine how the chatbot processes user input and the flow of operations, particularly in
 * managing tasks such as adding, viewing, marking, and deleting tasks.
 */
public enum EventChainType {

	/** The default state of the chatbot, waiting for an initial command. */
	DEFAULT,

	/** The state when the user has entered the list command, to list tasks or manage task operations. */
	LIST,

	/** The state where the user is adding a new task. */
	ADD,

	/** The state for adding a new "todo" task, waiting for the task name. */
	TODO,

	/** The state where the "todo" task name has been provided, waiting for a description. */
	TODO_NAMED,

	/** The state for adding a new "deadline" task, waiting for the task name. */
	DEADLINE,

	/** The state where the "deadline" task name has been provided, waiting for a description. */
	DEADLINE_NAMED,

	/** The state where the "deadline" task description has been provided, waiting for the deadline date. */
	DEADLINE_DESCRIBED,

	/** The state for viewing the current list of tasks. */
	VIEW,

	/** The state for finding a task by keyword. */
	FIND,

	/** The state where a task has been found, allowing further operations like marking or deleting. */
	FOUND,

	/** The state for marking a task as done. */
	MARK,

	/** The state for unmarking a task (setting it to not done). */
	UNMARK,

	/** The state for deleting a task. */
	DELETE,
	/** The state before terminating the program*/
	TERMINATE;
}
