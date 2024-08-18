import java.util.Scanner;

public class Infinity <T extends Task> {
  private static final String BOTNAME = "Infinity";
  private static final String BREAKLINE = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
  private Task[] tasks = new Task[100];
  private int nextTastIndex = 0;

  private static String botReply(String input) {
    return String.format("%s: %s", BOTNAME, input);
  }

  private <T extends Task> void addTask(T task) {
    if (nextTastIndex >= tasks.length) {
      System.out.println(botReply("I'm sorry, but I can't remember more tasks."));
      return;
    }
    tasks[nextTastIndex] = (Task) task;
    nextTastIndex++;
    System.out.println(botReply(String.format("I've added '%s'", task)));
  }

  private void listTasks() {
    if (nextTastIndex == 0) {
      System.out.println(botReply("Task list is empty :("));
    } else {
      System.out.println(botReply(""));
      for (int i = 0; i < nextTastIndex; i++) {
        System.out.println(String.format("    %d. %s", i + 1, tasks[i].toString()));
      }
    }
  }

  public void echo(String input) {
    System.out.println(botReply(input));
    System.out.println(BREAKLINE);
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
      if (currentInput.equals("bye")) {
        System.out.println(botReply("Well, if you are leaving, then I must be infinitely too dumb :("));
        System.out.println(BREAKLINE);
        userInputs.close();
        System.exit(0);
      } else if (currentInput.equals("list")) {
        this.listTasks();
        System.out.println(BREAKLINE);
      } else if (currentInput.startsWith("mark")) {
        try {
          String[] words = currentInput.split(" ");
          int taskIndex = Integer.parseInt(words[1]) - 1;
          if (taskIndex >= nextTastIndex || taskIndex < 0) {
            throw new IndexOutOfBoundsException();
          }
          tasks[taskIndex].markAsDone();
          System.out.println(botReply(String.format("I've marked task %d as done:", taskIndex + 1)));
          System.out.println(tasks[taskIndex].toString());
          System.out.println(BREAKLINE);
        } catch (IndexOutOfBoundsException e) {
          System.out.println(botReply("Hmmm, I can't find that task. Please try again."));
          System.out.println(BREAKLINE);
        }
      } else if (currentInput.startsWith("todo")) {
        try {
          this.addTask(new ToDos(currentInput.substring(5)));
        } catch (ArrayIndexOutOfBoundsException e) {}
      } else if (currentInput.startsWith("deadline")) {
        try {
          this.addTask(new Deadline(currentInput.substring(9)));
        } catch (ArrayIndexOutOfBoundsException e) {}
      } else if (currentInput.startsWith("event")) {
        try {
          this.addTask(new Event(currentInput.substring(6)));
        } catch (ArrayIndexOutOfBoundsException e) {}
      } else {
        this.echo(currentInput);
      }
    }
  }

  public static void main(String[] args) {
    Infinity infinityBot = new Infinity();
  }

}
