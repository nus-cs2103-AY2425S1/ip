import java.util.Scanner;

public class TaskListUI {
  private Scanner scanner = new Scanner(System.in);
  private TaskList taskList = new TaskList();
  private TasksStorage storage = TasksStorage.getInstance();

  public TaskListUI(Scanner scanner) {
    this.scanner = scanner;
  }

  // Simple helper method to determine if the task count is 1 or more
  private String taskWord() {
    return this.taskList.size() == 1 ? "task" : "tasks";
  }

  // Add a task to the list
  private void addTask(Task task) {
    taskList.add(task);

    System.out.println("Got it. I've added this task:");
    System.out.println("  " + task.toString());
    System.out.println("Now you have " + this.taskList.size() + " " + this.taskWord() + " in the list.");
  }

  // Delete a task from the list at the specified index
  private void deleteTask(int taskNumber) throws IndexOutOfBoundsException {
    taskNumber--; // Adjust task number to match array index

    if (taskNumber >= this.taskList.size() || taskNumber < 0) {
      throw new IndexOutOfBoundsException(
          "Invalid task number. There are " + this.taskList.size() + " " + this.taskWord() + " in your list.");
    }

    Task task = this.taskList.get(taskNumber);
    System.out.println("Noted. I've removed this task:");
    System.out.println("  " + task.toString());
    System.out.println("Now you have " + (this.taskList.size() - 1) + " " + this.taskWord() + " in the list.");

    this.taskList.remove(taskNumber);
  }

  // Display all tasks in the list
  private void displayTasks() throws IllegalCommandException {
    if (this.taskList.size() == 0) {
      throw new IllegalCommandException("You have no tasks in your list.");
    }

    for (int i = 0; i < this.taskList.size(); i++) {
      Task task = this.taskList.get(i);
      System.out.println("  " + (i + 1) + ". " + task.toString());
    }
  }

  // Mark a task as done or not done
  private void markTask(int taskNumber, boolean done)
      throws IllegalCommandException {
    taskNumber--; // Adjust task number to match array index

    try {
      boolean success = this.taskList.markTask(taskNumber, done);
      if (!success) {
        throw new IllegalCommandException(this.taskList.get(taskNumber), done);
      }
      if (done) {
        System.out.println("Nice! I've marked this task as done:");
      } else {
        System.out.println("OK, I've marked this task as not done yet:");
      }
      System.out.println("  " + this.taskList.get(taskNumber).toString());
    } catch (IndexOutOfBoundsException e) {
      throw new IllegalCommandException(
          "Invalid task number. There are " + this.taskList.size() + " " + this.taskWord() + " in your list.");
    }
  }

  public void run() {
    this.taskList = storage.readTasks();

    System.out.println("____________________________________________________________");
    System.out.println("Hello! I'm Vuewee\nWhat can I do for you?");
    System.out.println("____________________________________________________________");

    // Echo input from user until user types "bye"
    while (true) {
      String input = scanner.nextLine();
      try {
        System.out.println("____________________________________________________________");

        CommandParser parser = new CommandParser(input);

        // Exit the loop if user types "bye"
        if (parser.getCommand() == CommandType.BYE) {
          break;
        }

        switch (parser.getCommand()) {
          // List all tasks with done status if user types "list"
          case LIST: {
            this.displayTasks();
            break;
          }
          // Mark task as done if user types "mark <task number>"
          case MARK: {
            parser.parse(true, true);
            markTask(parser.getIntParam(), true);
            break;
          }
          // Unmark task as done if user types "unmark <task number>"
          case UNMARK: {
            parser.parse(true, true);
            markTask(parser.getIntParam(), false);
            break;
          }
          // Add todo task to task list
          // (Usage: todo <description>)
          case TODO: {
            parser.parse(true);
            addTask(new TodoTask(parser.getDescription()));
            break;
          }
          // Add deadline task to task list
          // (Usage: deadline <description> /by <date>)
          case DEADLINE: {
            parser.parse(true, false, new CommandOption[] { new CommandOption("by", "date") });
            addTask(new DeadlineTask(parser.getDescription(), parser.getOption("by")));
            break;
          }
          // Add event task to task list
          // (Usage: event <description> /from <fromDate> /to <toDate>)
          case EVENT: {
            parser.parse(true, false,
                new CommandOption[] { new CommandOption("from", "fromDate"), new CommandOption("to", "toDate") });
            addTask(new EventTask(parser.getDescription(), parser.getOption("from"), parser.getOption("to")));
            break;
          }
          // Delete task from task list
          // (Usage: delete <taskNumber>)
          case DELETE: {
            parser.parse(true, true);
            deleteTask(parser.getIntParam());
            break;
          }
          default: {
            System.out.println("Unhandled unknown command: " + parser.getCommand());
            break;
          }
        }
      } catch (IndexOutOfBoundsException | IllegalCommandException e) {
        System.out.println(e.getMessage());
      }
      System.out.println("____________________________________________________________");
      storage.storeTasks(this.taskList);
    }

    System.out.println("Bye. Hope to see you again soon!");
    System.out.println("____________________________________________________________");
  }
}
