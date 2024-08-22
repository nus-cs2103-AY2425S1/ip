import java.util.ArrayList;
import java.util.Scanner;

public class BeeBot {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Boolean exit_status = false;
        String greet = "Hello! I'm BeeBot\n"
                + "What can I do for you?\n";
        String exit = "Bye. Hope to see you again!\n";
        ArrayList<Task> taskList = new ArrayList<>();

        speak(greet);

        while (!exit_status) {
            String input = scanner.nextLine();
            String[] parts = input.split(" ");
            String cmd = parts[0];

            switch (cmd) {
                case "bye":
                    speak(exit);
                    exit_status = true;
                    break;
                case "list":
                    int size = taskList.size();
                    String listStr = "";
                    for (int i = 0; i < size; i++) {
                        int num = i + 1;
                        listStr += (num + "." + taskList.get(i).stringify());
                    }
                    speak(listStr);
                    break;
                case "mark":
                    int taskNum = Integer.parseInt(parts[1]);
                    Task currTask = taskList.get(taskNum - 1);
                    currTask.markAsDone();
                    speak("Nice! I've marked this task as done:\n" + currTask.stringify());
                    break;
                case "unmark":
                    int taskNum2 = Integer.parseInt(parts[1]);
                    Task curr = taskList.get(taskNum2 - 1);
                    curr.markAsUndone();
                    speak("Nice! I've marked this task as not done yet:\n" + curr.stringify());
                    break;
                case "todo":
                    int sizeOfTodo = parts.length;
                    String name = "";
                    for (int i = 1; i < sizeOfTodo; i++) {
                        name += (parts[i] + " ");
                    }

                    ToDo newToDo = new ToDo(name);
                    taskList.add(newToDo);
                    int currSize = taskList.size();

                    if (currSize == 1) {
                        speak("Got it. I've added this task:\n"
                                + newToDo.stringify()
                                + "Now you have "
                                + currSize + " task in the list.\n");
                    } else {
                        speak("Got it. I've added this task:\n"
                                + newToDo.stringify()
                                + "Now you have "
                                + currSize + " tasks in the list.\n");
                    }
                    break;
                case "deadline":
                    String deadlineName = "";
                    int d = 1;

                    while (!parts[d].equals("/by")) {
                        deadlineName += (parts[d] + " ");
                        d++;
                    }
                    d++;
                    String deadlineDate = "";
                    for (; d < parts.length - 1; d++) {
                        deadlineDate += (parts[d] + " ");
                    }
                    deadlineDate += parts[d];
                    Deadline newDeadline = new Deadline(deadlineName, deadlineDate);
                    taskList.add(newDeadline);
                    int currSize2 = taskList.size();
                    if (currSize2 == 1) {
                        speak("Got it. I've added this task:\n"
                                + newDeadline.stringify()
                                + "Now you have "
                                + currSize2 + " task in the list.\n");
                    } else {
                        speak("Got it. I've added this task:\n"
                                + newDeadline.stringify()
                                + "Now you have "
                                + currSize2 + " tasks in the list.\n");
                    }
                    break;
                case "event":
                    String eventName = "";
                    int e = 1;
                    while (!parts[e].equals("/from")) {
                        eventName += (parts[e] + " ");
                        e++;
                    }
                    e++;
                    String startTime = "";
                    while (!parts[e].equals("/to")) {
                        startTime += (parts[e] + " ");
                        e++;
                    }
                    e++;
                    String endTime = "";
                    for (; e < parts.length - 1; e++) {
                        endTime += (parts[e] + " ");
                    }
                    endTime += parts[e];
                    Event newEvent = new Event(eventName, startTime, endTime);
                    taskList.add(newEvent);
                    int currSize3 = taskList.size();

                    if (currSize3 == 1) {
                        speak("Got it. I've added this task:\n"
                                + newEvent.stringify()
                                + "Now you have "
                                + currSize3 + " task in the list.\n");
                    } else {
                        speak("Got it. I've added this task:\n"
                                + newEvent.stringify()
                                + "Now you have "
                                + currSize3 + " tasks in the list.\n");
                    }
                    break;
            }
        }
    }

    public static void speak(String cmd) {
        System.out.println("________________________\n"
                + cmd
                + "________________________\n");
    }
}