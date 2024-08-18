import java.util.ArrayList;
import java.util.Scanner;

public class Infinity <T extends Task> {
  private static final String BOTNAME = "Infinity";
  private static final String BREAKLINE = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
  private static final int MAXSIZE = 100;

  private ArrayList<Task> tasks = new ArrayList<>(MAXSIZE);
  private int nextTaskIndex = 0;

  private static String botReply(String input) {
    return String.format("%s: %s", BOTNAME, input);
  }

  private <T extends Task> void addTask(T task) throws InfinityException {
    if (nextTaskIndex >= MAXSIZE) {
      throw new InfinityException(botReply("I'm sorry, but I can't remember more tasks."));
    }
    tasks.add(task);
    nextTaskIndex++;
    System.out.println(botReply(String.format("I've added '%s'", task)));
  }

  private void deleteTask(String currentInput) throws InfinityException {
    int taskIndex = 0;
    try {
      taskIndex = Integer.parseInt(currentInput);
    } catch (NumberFormatException e) {
      System.out.println(botReply("Hey! That's not a number"));
      System.out.println(BREAKLINE);
    }
    taskIndex--;
    if (taskIndex >= nextTaskIndex || taskIndex < 0) {
      throw new InfinityException(botReply("Hmmm, you seem to have chose a task that doesn't exist. Nice try :)"));
    } else {
      try {
        Task removedTask = tasks.remove(taskIndex);
        System.out.println(botReply(String.format("I've removed task %d:", taskIndex + 1)));
        System.out.println(removedTask.toString());
      } catch (IndexOutOfBoundsException e) {
        throw new InfinityException(botReply("Hmmm, you seem to have chose a task that doesn't exist. Nice try :)"));
      }
    }
  }

  private void markTask(String currentInput) throws InfinityException {
    String[] words = currentInput.split(" ");
    int taskIndex = Integer.parseInt(words[1]) - 1;
    if (taskIndex >= nextTaskIndex || taskIndex < 0) {
      throw new InfinityException(botReply("Hmmm, I can't find that task. Please try again."));
    }
    tasks.get(taskIndex).markAsDone();
    System.out.println(botReply(String.format("I've marked task %d as done:", taskIndex + 1)));
    System.out.println(tasks.get(taskIndex).toString());
    System.out.println(BREAKLINE);
  }

  private void listTasks() {
    if (nextTaskIndex == 0) {
      System.out.println(botReply("Task list is empty :("));
    } else {
      System.out.println(botReply(""));
      int i = 1;
      for (Task task : tasks) {
        System.err.println(String.format("    %d. %s", i, task.toString()));
        i++;
      }
    }
  }

  private void echo(String input) throws InfinityException {
    throw new InfinityException("Wait a minute, that's not something I recognise...");
  }

  public Infinity() {
    Scanner userInputs = new Scanner(System.in);

    System.out.println(BREAKLINE);
    System.out.println(botReply(String.format("Hello, I'm a dummy bot called %s", BOTNAME)));
    System.out.println(botReply("What can I not do for you?"));
    System.out.println(BREAKLINE);

    while (true) {
      String currentInput = userInputs.nextLine();
      System.out.println(BREAKLINE);
      try {

        if (currentInput.equals("bye")) {

          System.out.println(botReply("Well, if you are leaving, then I must be infinitely too dumb :("));
          System.out.println(BREAKLINE);
          userInputs.close();
          System.exit(0);

        } else if (currentInput.equals("list")) {

          this.listTasks();
          System.out.println(BREAKLINE);

        } else if (currentInput.startsWith("mark") && currentInput.length() > 5) {

          this.markTask(currentInput);

        } else if (currentInput.startsWith("todo") && currentInput.length() > 5) {

          this.addTask(new ToDos(currentInput.substring(5)));

        } else if (currentInput.startsWith("deadline") && currentInput.length() > 9) {

          this.addTask(new Deadline(currentInput.substring(9)));

        } else if (currentInput.startsWith("event") && currentInput.length() > 6) {

          this.addTask(new Event(currentInput.substring(6)));

        } else if (currentInput.startsWith("delete") && currentInput.length() > 7) {

          this.deleteTask(currentInput.substring(7));

        } else {

          this.echo(currentInput);

        }

      } catch (InfinityException e) {

        System.out.println(botReply(e.getMessage()));
        System.out.println(BREAKLINE);

      }
    }
  }

  public static void main(String[] args) {
    Infinity infinityBot = new Infinity();
  }

}
