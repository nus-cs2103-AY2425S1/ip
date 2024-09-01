import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    private static final String BYE = "bye";
    private static final String LIST = "list";
    private static final String MARK = "mark";
    private static final String UNMARK = "unmark";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String DELETE = "delete";
    private static final String DATE = "date";

    private Ui ui;
    private TaskList taskList;

    public Parser(Ui ui, TaskList taskList) {
        this.ui = ui;
        this.taskList = taskList;
    }

    public void run(String Message) {
        while (!Message.equals(BYE)) {
            try {
                if (Message.equals(LIST)) {
                    runList();
                } else if (Message.matches(MARK + " \\d+") || Message.matches(UNMARK + " \\d+")) {
                    runMark(Message);
                } else if (Message.equals("todo") || Message.equals("todo ") || Message.equals("deadline")
                        || Message.equals("deadline ") || Message.equals("event") || Message.equals("event ")) {
                    throw new GalliumException("OOPS!!! The description of a " + Message + " cannot be empty.");
                } else if (Message.startsWith(TODO) || Message.startsWith(DEADLINE)
                        || Message.startsWith(EVENT)) {
                    runAdd(Message);
                } else if (Message.startsWith(DELETE)) {
                    runDelete(Message);
                } else if (Message.startsWith(DATE)) {
                    runDate(Message);
                } else {
                    throw new GalliumException("OOPS!!! I'm sorry, but I don't know what that means :(");
                }
            } catch (GalliumException e) {
                ui.showGalliumException(e);
                // Message = userInput.nextLine();
            } catch (ArrayIndexOutOfBoundsException e) {
                if (Message.startsWith(DEADLINE)) {
                    ui.showIncompleteDeadline();
                } else if (Message.startsWith(EVENT)) {
                    ui.showIncompleteEvent();
                }
            } catch (IndexOutOfBoundsException e) {
                if (Message.startsWith(MARK) || Message.startsWith(UNMARK) || Message.startsWith(DELETE)) {
                    ui.showWrongIndex();
                }
            }
            Message = ui.readNextLine();
        }
    }

    public void runList() {
        this.ui.printList(this.taskList);
    }

    public void runMark(String Message) {
        boolean isMark = Message.startsWith(MARK);
        Pattern pattern = Pattern.compile((isMark ? MARK : UNMARK) + " (\\d+)");
        Matcher matcher = pattern.matcher(Message);
        if (matcher.matches()) {
            int index = Integer.parseInt(matcher.group(1));
            Task task = taskList.getTask(index - 1);
            task.setIsDone(isMark);
            ui.printMarkMessage(isMark, task);
        }
    }

    public void runAdd(String Message) {
        if (Message.startsWith("todo ")) {
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
        }
    }

    public void runDelete(String Message) {
        Pattern pattern = Pattern.compile(("delete") + " (\\d+)");
        Matcher matcher = pattern.matcher(Message);
        if (matcher.matches()) {
            int index = Integer.parseInt(matcher.group(1));
            Task task = taskList.get(index - 1);
            Task.count--;
            ui.printDelete(task);
            taskList.remove(index - 1);
        }
    }

    public void runDate(String Message) {
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
    }

}
