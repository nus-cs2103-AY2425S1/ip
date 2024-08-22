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
                    try {
                        if (sizeOfTodo == 1) {
                            throw new Exception("Enter a description for the Todo Task.\n");
                        }
                        String name = "";
                        for (int i = 1; i < sizeOfTodo - 1; i++) {
                            name += (parts[i] + " ");
                        }
                        name += parts[sizeOfTodo - 1];
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
                    } catch (Exception e) {
                        speak(e.getMessage());
                    }
                    break;
                case "deadline":
                    try {
                        if (parts.length == 1) {
                            throw new Exception("Enter a description for the Deadline Task.\n");
                        }
                        String deadlineName = "";
                        int d = 1;
                        int sizeOfCmd = parts.length;

                        while (!parts[d].equals("/by")) {
                            deadlineName += (parts[d] + " ");
                            d++;
                            if (d == sizeOfCmd) {
                                throw new Exception("Enter `/by` followed by a deadline.\n");
                            }
                        }

                        d++;
                        if (d == sizeOfCmd) {
                            throw new Exception("Enter a date for the Deadline Task.\n");
                        }
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
                    } catch (Exception e) {
                        speak(e.getMessage());
                    }
                    break;
                case "event":
                    try {
                        if (parts.length == 1) {
                            throw new Exception("Enter a description for the Event Task.\n");
                        }
                        String eventName = "";
                        int sizeOfEventCmd = parts.length;
                        int e = 1;
                        while (!parts[e].equals("/from")) {
                            eventName += (parts[e] + " ");
                            e++;
                            if (e == sizeOfEventCmd) {
                                throw new Exception("Enter `/from` followed by a start date/time.\n");
                            }
                        }
                        e++;
                        if (e == sizeOfEventCmd) {
                            throw new Exception("Enter a start date/time.\n");
                        }
                        String startTime = "";
                        while (!parts[e].equals("/to")) {
                            startTime += (parts[e] + " ");
                            e++;
                            if (e == sizeOfEventCmd) {
                                throw new Exception("Enter `/to` followed by an end time.\n");
                            }
                        }
                        e++;
                        if (e == sizeOfEventCmd) {
                            throw new Exception("Enter an end time.\n");
                        }
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
                    } catch (Exception e) {
                        speak(e.getMessage());
                    }
                    break;
                default:
                    speak("Invalid command.\n");
            }
        }
    }

    public static void speak(String cmd) {
        System.out.println("________________________\n"
                + cmd
                + "________________________\n");
    }
}