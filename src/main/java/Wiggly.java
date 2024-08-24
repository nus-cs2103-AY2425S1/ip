import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Wiggly {

    private enum Command {
        LIST,
        BYE,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        UNKNOWN
    }

    public static void main(String[] args) throws IOException {

	    System.out.println(
			    """
					    ____________________________________
					    Hello! I'm Wiggly
					    What can I do for you?
					    ____________________________________
					    """);

	    Scanner sc = new Scanner(System.in);
	    TaskList taskList = new TaskList("./data/Wiggly.txt");

		FileEditor saveFileEditor = new FileEditor("./data/Wiggly.txt");

		String in;
	    String[] parts;
	    Command command;

	    boolean running = true;
	    while (running) {

		    in = sc.nextLine();
		    parts = in.split(" ", 2);
		    try {
			    command = Command.valueOf(parts[0].toUpperCase());
		    } catch (IllegalArgumentException e) {
			    command = Command.UNKNOWN;
		    }

		    switch (command) {
		    case LIST:
			    String listString = "____________________________________\n" + "Here are the tasks in your list:\n" +
					    taskList +
					    "_____________________________________" + "\n";
				System.out.println(listString);
			    break;
		    case BYE:
			    System.out.println(
					    """
							    ____________________________________
							    Bye. Hope to see you again soon!
							    ____________________________________
							    """
			    );
			    running = false;
			    break;
		    case MARK:
			    try {
				    int value = Integer.parseInt(parts[1]);
				    if (value > taskList.getSize() || value <= 0) {
					    System.out.println(
							    """
									    ____________________________________
									    There's no such task number!
									    ____________________________________
									    """
					    );
				    } else {
					    System.out.println(taskList.markDone(value));
					    saveFileEditor.save(taskList);
				    }
			    } catch (NumberFormatException e) {
				    System.out.println(
						    """
								    ____________________________________
								    Oops, invalid number format detected
								    ____________________________________
								    """
				    );
			    }
			    break;
		    case UNMARK:
			    try {
				    int value = Integer.parseInt(parts[1]);
				    if (value > taskList.getSize() || value <= 0) {
					    System.out.println(
							    """
									    ____________________________________
									    There's no such task number!
									    ____________________________________
									    """
					    );
				    } else {
					    System.out.println(taskList.markUndone(value));
					    saveFileEditor.save(taskList);
				    }
			    } catch (NumberFormatException e) {
				    System.out.println(
						    """
								    ____________________________________
								    Oops, invalid number format detected
								    ____________________________________
								    """
				    );
			    }
			    break;
		    case TODO:
			    try {
				    String taskDescription = parts[1];
				    System.out.println(taskList.addTask(new ToDo(taskDescription)));
				    saveFileEditor.save(taskList);
			    } catch (ArrayIndexOutOfBoundsException e) {
				    System.out.println(
						    """
								    ____________________________________
								    Oops, missing todo description
								    ____________________________________
								    """
				    );
			    }
			    break;
		    case DEADLINE:
			    try {
				    parts = parts[1].split(" /by ", 2);
				    String taskDescription = parts[0];
				    String by = parts[1];
				    System.out.println(taskList.addTask(new Deadline(taskDescription, by)));
				    saveFileEditor.save(taskList);
			    } catch (ArrayIndexOutOfBoundsException e) {
				    System.out.println(
						    """
								    ____________________________________
								    Oops, missing deadline description or by
								    ____________________________________
								    """
				    );
			    }
			    break;
		    case EVENT:
			    try {
				    parts = parts[1].split(" /from | /to ", 3);
				    String taskDescription = parts[0];
				    String from = parts[1];
				    String to = parts[2];
				    System.out.println(taskList.addTask(new Event(taskDescription, from, to)));
				    saveFileEditor.save(taskList);
			    } catch (ArrayIndexOutOfBoundsException e) {
				    System.out.println(
						    """
								    ____________________________________
								    Oops, missing event description, from or to
								    ____________________________________
								    """
				    );
			    }
			    break;
		    case DELETE:
			    try {
				    int value = Integer.parseInt(parts[1]);
				    if (value > taskList.getSize() || value <= 0) {
					    System.out.println(
							    """
									    ____________________________________
									    There's no such task number!
									    ____________________________________
									    """
					    );
				    } else {
					    System.out.println(taskList.deleteTask(value));
					    saveFileEditor.save(taskList);
				    }
			    } catch (NumberFormatException e) {
				    System.out.println(
						    """
								    ____________________________________
								    Oops, invalid number format detected
								    ____________________________________
								    """
				    );
			    }
			    break;
		    case UNKNOWN:
			    System.out.println(
					    """
							    ____________________________________
							    Sorry!! I don't know this command :(
							    ____________________________________
							    """
			    );
			    break;
		    }
	    }

    }

}
