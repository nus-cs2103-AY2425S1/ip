import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
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
	private Storage storage;
	private TaskList taskList;
	private Ui ui;

	public Wiggly(String filePath) {
		ui = new Ui();
		storage = new Storage(filePath);
		try {
			taskList = new TaskList(storage.load());
		} catch (WigglyException | FileNotFoundException e) {
			ui.printWrappedString("Cannot load storage from " + filePath);
			taskList = new TaskList();
		}
	}

	public void run() {

		ui.printGreeting();

		Scanner sc = new Scanner(System.in);
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
				ui.printTaskList(taskList);
				break;
			case BYE:
				ui.printExit();
				running = false;
				break;
			case MARK:
				try {
					int value = Integer.parseInt(parts[1]);
					if (value > taskList.getSize() || value <= 0) {
						ui.printWrappedString("There's no such task number!");
					} else {
						ui.printWrappedString(taskList.markDone(value));
						storage.save(taskList);
					}
				} catch (NumberFormatException e) {
					ui.printWrappedString("Oops, invalid number format detected");
				}
				break;
			case UNMARK:
				try {
					int value = Integer.parseInt(parts[1]);
					if (value > taskList.getSize() || value <= 0) {
						ui.printWrappedString("There's no such task number!");
					} else {
						ui.printWrappedString(taskList.markUndone(value));
						storage.save(taskList);
					}
				} catch (NumberFormatException e) {
					ui.printWrappedString("Oops, invalid number format detected");
				}
				break;
			case TODO:
				try {
					String taskDescription = parts[1];
					ui.printWrappedString(taskList.addTask(new ToDo(taskDescription)));
					storage.save(taskList);
				} catch (ArrayIndexOutOfBoundsException e) {
					ui.printWrappedString("Oops, missing todo description");
				}
				break;
			case DEADLINE:
				try {
					parts = parts[1].split(" /by ", 2);
					String taskDescription = parts[0];
					String by = parts[1];
					ui.printWrappedString(taskList.addTask(new Deadline(taskDescription,
							LocalDate.parse(by))));
					storage.save(taskList);
				} catch (ArrayIndexOutOfBoundsException e) {
					ui.printWrappedString("Oops, missing deadline description");
				}
				break;
			case EVENT:
				try {
					parts = parts[1].split(" /from | /to ", 3);
					String taskDescription = parts[0];
					String from = parts[1];
					String to = parts[2];
					ui.printWrappedString(taskList.addTask(new Event(taskDescription, LocalDate.parse(from),
							LocalDate.parse(to))));
					storage.save(taskList);
				} catch (ArrayIndexOutOfBoundsException e) {
					ui.printWrappedString("Oops, missing event description, from or to");
				}
				break;
			case DELETE:
				try {
					int value = Integer.parseInt(parts[1]);
					if (value > taskList.getSize() || value <= 0) {
						ui.printWrappedString("There's no such task number!");
					} else {
						ui.printWrappedString(taskList.deleteTask(value));
						storage.save(taskList);
					}
				} catch (NumberFormatException e) {
					ui.printWrappedString("Oops, invalid number format detected");
				}
				break;
			case UNKNOWN:
				ui.printWrappedString("Sorry!! I don't know this command");
				break;
			}
		}
	}

    public static void main(String[] args) {
		new Wiggly("./data/Wiggly.txt").run();
    }

}
