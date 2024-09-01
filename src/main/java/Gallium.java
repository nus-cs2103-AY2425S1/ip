import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

public class Gallium {
    public static void main(String[] args) {
        Ui ui = new Ui();
        ArrayList<Task> taskList = new ArrayList<Task>();
        TaskList tasklist = new TaskList(taskList);
        String Message = ui.readNextLine();
        String bye = "bye";
        String list = "list";
        String mark = "mark";
        String unmark = "unmark";

        try {
            File dir = new File("./data");
            dir.mkdirs();
            File f = new File(dir, "gallium.txt");
            f.createNewFile();
            Scanner scanner = new Scanner(f);
            while (scanner.hasNextLine()) {
                String taskDesc = scanner.nextLine();
                if (taskDesc.startsWith("[T]")) {
                    Todo todo = new Todo(taskDesc);
                    taskList.add(todo);
                } else if (taskDesc.startsWith("[D]")) {
                    Deadline deadline = new Deadline(taskDesc);
                    taskList.add(deadline);
                } else if (taskDesc.startsWith("[E]")) {
                    Event event = new Event(taskDesc);
                    taskList.add(event);
                }
                Task.count = taskList.size() + 1;
            }
            scanner.close();
        } catch (IOException e) {
            ui.showCreateFileError(e);
        }

        while (!Message.equals(bye)) {
            try {
                if (Message.equals(list)) {
                    ui.printList(tasklist);
                } else if (Message.matches(mark + " \\d+") || Message.matches(unmark + " \\d+")) {
                    boolean isMark = Message.startsWith(mark);
                    Pattern pattern = Pattern.compile((isMark ? mark : unmark) + " (\\d+)");
                    Matcher matcher = pattern.matcher(Message);
                    if (matcher.matches()) {
                        int index = Integer.parseInt(matcher.group(1));
                        Task task = taskList.get(index - 1);
                        task.setIsDone(isMark);
                        ui.printMarkMessage(isMark, task);
                    }
                } else if (Message.equals("todo") || Message.equals("todo ") || Message.equals("deadline")
                        || Message.equals("deadline ") || Message.equals("event") || Message.equals("event ")) {
                    throw new GalliumException("OOPS!!! The description of a " + Message + " cannot be empty.");
                } else if (Message.startsWith("todo ")) {
                    Todo todo = new Todo(Message);
                    ui.printAddTodo(todo);
                    taskList.add(todo);
                    Task.count++;
                } else if (Message.startsWith("deadline ")) {
                    Deadline deadline = new Deadline(Message);
                    ui.printAddDeadline(deadline);
                    taskList.add(deadline);
                    Task.count++;
                } else if (Message.startsWith("event ")) {
                    Event event = new Event(Message);
                    ui.printAddEvent(event);
                    taskList.add(event);
                    Task.count++;
                } else if (Message.startsWith("delete ")) {
                    Pattern pattern = Pattern.compile(("delete") + " (\\d+)");
                    Matcher matcher = pattern.matcher(Message);
                    if (matcher.matches()) {
                        int index = Integer.parseInt(matcher.group(1));
                        Task task = taskList.get(index - 1);
                        Task.count--;
                        ui.printDelete(task);
                        taskList.remove(index - 1);
                    }
                } else if (Message.startsWith("date ")) {
                    LocalDate date = LocalDate.parse(Message.split("date ")[1]);
                    StringBuilder tasksStringBuilder = new StringBuilder();
                    for (int i = 1; i < Task.count; i++) {
                        Task task = taskList.get(i - 1);
                        if (task.description.startsWith("[D]") || task.description.startsWith("deadline ")) {
                            Deadline deadline = (Deadline) task;
                            if (date.format(DateTimeFormatter.ofPattern("MMM d yyyy")).equals(deadline.date)) {
                                tasksStringBuilder.append("\n    " + deadline.toString());
                            }
                        } else if (task.description.startsWith("[E]") || task.description.startsWith("event ")) {
                            Event event = (Event) task;
                            if (date.format(DateTimeFormatter.ofPattern("MMM d yyyy")).equals(event.toDate)) {
                                tasksStringBuilder.append("\n    " + event.toString());
                            }
                        }
                    }
                    String tasks = tasksStringBuilder.toString();
                    ui.printMatchingDate(tasks);

                } else {
                    throw new GalliumException("OOPS!!! I'm sorry, but I don't know what that means :(");
                }
                // Message = userInput.nextLine();
            } catch (GalliumException e) {
                ui.showGalliumException(e);
                // Message = userInput.nextLine();
            } catch (ArrayIndexOutOfBoundsException e) {
                if (Message.startsWith("deadline ")) {
                    ui.showIncompleteDeadline();
                } else if (Message.startsWith("event ")) {
                    ui.showIncompleteEvent();
                }
            } catch (IndexOutOfBoundsException e) {
                if (Message.startsWith(mark) || Message.startsWith(unmark) || Message.startsWith("delete")) {
                    ui.showWrongIndex();
                }
            }
            Message = ui.readNextLine();
        }

        StringBuilder listStringBuilder = new StringBuilder();
        for (int i = 1; i < Task.count; i++) {
            Task task = taskList.get(i - 1);
            listStringBuilder.append(task.toString()).append("\n");
        }
        String listString = listStringBuilder.toString();
        try {
            FileWriter fw = new FileWriter("./data/gallium.txt");
            fw.write(listString);
            fw.close();
        } catch (IOException e) {
            ui.showIOException(e);
        }

        ui.printByeMessage();
        ui.closeScanner();
    }
}
