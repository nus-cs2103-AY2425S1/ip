import java.util.ArrayList;

/**
 * This class implements a chatbot by the name of Bunbun.
 *
 * @author Eda Yeo
 * @version CS2103T AY24/25 Semester 1
 */
public class Bunbun {
    public static void main(String[] args) {
        UI.startScreen();
        TaskList list = new TaskList();

        while (true) {
            String msg = Parser.getMessage();
            if (msg.equals("bye")) {
                UI.endScreen();
                break;
            } else {
                System.out.println(msg);
                ArrayList<String> tokens = Parser.getTokens();
                if (tokens.get(0).equals("list")) {
                    list.displayList();
                } else if (tokens.get(0).equals("mark") && (tokens.size() == 2)) {
                    String val = tokens.get(1);
                    list.markDoneTask(Integer.parseInt(val));
                } else if (tokens.get(0).equals("todo")) {
                    String taskDescription = "";
                    int len = tokens.size();
                    for (int i = 1; i < len; i++) {
                        taskDescription += tokens.get(i) + " ";
                    }
                    ToDo todo = new ToDo(taskDescription);
                    list.addTask(todo);
                } else if (tokens.get(0).equals("deadline")) {
                    String taskDescription = "";
                    String deadline = "";
                    int len = tokens.size();
                    for (int i = 1; i < len; i++) {
                        if (tokens.get(i).equals("/by")) {
                            i += 1;
                            while (i < len) {
                                deadline += tokens.get(i) + " ";
                                i += 1;
                            }
                        } else {
                            taskDescription += tokens.get(i) + " ";
                        }
                    }
                    Deadline deadlineTask = new Deadline(taskDescription, deadline);
                    list.addTask(deadlineTask);
                } else if (tokens.get(0).equals("event")) {
                    String taskDescription = "";
                    String start = "";
                    String end = "";
                    int len = tokens.size();
                    int i = 1;
                    while (i < len && !(tokens.get(i).equals("/from"))) {
                        taskDescription += tokens.get(i) + " ";
                        i += 1;
                    }
                    i += 1;
                    while (i < len && !(tokens.get(i).equals("/to"))) {
                        start += tokens.get(i) + " ";
                        i += 1;
                    }
                    i += 1;
                    while (i < len) {
                        end += tokens.get(i) + " ";
                        i += 1;
                    }
                    Event event = new Event(taskDescription, start, end);
                    list.addTask(event);
                }
            }
        }
    }
}
