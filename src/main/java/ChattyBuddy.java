import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class ChattyBuddy {

    private static void loadDataFile(ArrayList<Task> inputList) throws FileNotFoundException {
        File dataSaved = new File("./data/chattybuddy.txt");
        Scanner s = new Scanner(dataSaved);

        while (s.hasNext()) {
            String[] dataArr = s.nextLine().split(" \\| ");
            if (dataArr[0].equals("T")) {
                for (int i = 0; i < dataArr.length; i++) {
                    System.out.println(dataArr[i]);
                }
                Todo newTodo = new Todo();
                inputList.add(newTodo);
                newTodo.convertSavedDataToTask(dataArr);
            } else if (dataArr[0].equals("D")) {
                Deadline newDeadline = new Deadline();
                inputList.add(newDeadline);
                newDeadline.convertSavedDataToTask(dataArr);
            } else if (dataArr[0].equals("E")) {
                Event newEvent = new Event();
                inputList.add(newEvent);
                newEvent.convertSavedDataToTask(dataArr);
            }
        }
    }

    private static void saveDataToFile(ArrayList<Task> inputList) throws IOException {
        FileWriter fw = new FileWriter("./data/chattybuddy.txt");
        String separation = " | ";
        for (Task task: inputList) {
            if (task instanceof Todo) {
                fw.write(task.type + separation + (task.isDone? "1" : "0") + separation + task.description + "\n");
            } else if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                fw.write(task.type + separation + (task.isDone? "1" : "0") + separation + task.description + separation + deadline.dueTime + "\n");
            } else if (task instanceof Event) {
                Event event = (Event) task;
                fw.write(task.type + separation + (task.isDone? "1" : "0") + separation + task.description + separation + event.startTime + separation + event.endTime + "\n");
            }
        }
        fw.close();
    }

    public static void main(String[] args) {
        ArrayList<Task> inputList = new ArrayList<>();
        try {
            loadDataFile(inputList);
        } catch (FileNotFoundException e) {
            System.out.println("error :" + e.getMessage());
        }

        String breakLine = "——————————————————————————";
        String logo = " ____        _           \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(breakLine);
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm ChattyBuddy");
        System.out.println("What can I do for you?");
        System.out.println(breakLine);
        Scanner userInput = new Scanner(System.in);

        // user inputs something
        String response = userInput.nextLine();
        String[] slicedStr = response.split(" ");

        // checking against all possible outcomes
        while (!response.equals("bye")) {
            try {
                // when user enters "list"
                if (response.equals("list")) {
                    System.out.println(breakLine);
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < inputList.size(); i++) {
                        System.out.printf("%d.[%s][%s] %s%n", i + 1,inputList.get(i).type,inputList.get(i).getStatusIcon(), inputList.get(i));
                    }
                    System.out.println(breakLine);

                    // when user enters "mark xxx"
                } else if (slicedStr.length == 2 && slicedStr[0].equals("mark")) {
                    try {
                        if (Integer.parseInt(slicedStr[1]) > inputList.size() || Integer.parseInt(slicedStr[1]) <= 0) {
                            throw new TaskIndexOutOfBound();
                        }
                        Task target = inputList.get(Integer.parseInt(slicedStr[1]) - 1);
                        target.setMarkStatus(true);
                        saveDataToFile(inputList);
                        System.out.println(breakLine);
                        System.out.println("OK, I've marked this task as done:");
                        System.out.printf("[%s][%s] %s%n", target.type, target.getStatusIcon(), target);
                        System.out.println(breakLine);
                    } catch (TaskIndexOutOfBound e) {
                        System.out.println(breakLine);
                        System.out.println(e.getMessage());
                        System.out.println(breakLine);
                    } catch (IOException e) {
                        System.out.println(breakLine);
                        System.out.println("error: " + e.getMessage());
                        System.out.println(breakLine);
                    }

                    // when user enters "unmark xxx"
                } else if (slicedStr.length == 2 && slicedStr[0].equals("unmark")) {
                    try {
                        if (Integer.parseInt(slicedStr[1]) > inputList.size() || Integer.parseInt(slicedStr[1]) <= 0) {
                            throw new TaskIndexOutOfBound();
                        }
                            Task target = inputList.get(Integer.parseInt(slicedStr[1]) - 1);
                            target.setMarkStatus(false);
                            saveDataToFile(inputList);
                            System.out.println(breakLine);
                            System.out.println("OK, I've marked this task as not done yet:");
                            System.out.printf("[%s][%s] %s%n", target.type, target.getStatusIcon(), target);
                            System.out.println(breakLine);
                    } catch (TaskIndexOutOfBound e) {
                        System.out.println(breakLine);
                        System.out.println(e.getMessage());
                        System.out.println(breakLine);
                    } catch (IOException e) {
                        System.out.println(breakLine);
                        System.out.println("error: " + e.getMessage());
                        System.out.println(breakLine);
                    }

                    // users enters "todo xxx"
                } else if (slicedStr[0].equals("todo")) {
                    try {
                        if (slicedStr.length == 1) {
                            throw new EmptyTaskException("todo");
                        }
                        Todo newTodo = new Todo();
                        newTodo.convertStringToTask(slicedStr);
                        inputList.add(newTodo);
                        saveDataToFile(inputList);
                        System.out.println(breakLine);
                        System.out.println("Got it. I've added this task:");
                        System.out.printf("  [%s][%s] %s%n", newTodo.type, newTodo.getStatusIcon(), newTodo);
                        System.out.printf("Now you have %d tasks in the list%n", inputList.size());
                        System.out.println(breakLine);
                    } catch (EmptyTaskException e) {
                        System.out.println(breakLine);
                        System.out.println(e.getMessage());
                        System.out.println(breakLine);
                    } catch (IOException e) {
                        System.out.println(breakLine);
                        System.out.println("error: " + e.getMessage());
                        System.out.println(breakLine);
                    }


                    // users enters "deadline xxx"
                } else if (slicedStr[0].equals("deadline")) {
                    try {
                        if (slicedStr.length == 1) {
                            throw new EmptyTaskException("deadline");
                        }
                        Deadline newDeadline = new Deadline();
                        newDeadline.convertStringToTask(slicedStr);
                        inputList.add(newDeadline);
                        saveDataToFile(inputList);
                        System.out.println(breakLine);
                        System.out.println("Got it. I've added this task:");
                        System.out.printf("  [%s][%s] %s%n", newDeadline.type, newDeadline.getStatusIcon(), newDeadline);
                        System.out.printf("Now you have %d tasks in the list%n", inputList.size());
                        System.out.println(breakLine);
                    } catch (EmptyTaskException e) {
                        System.out.println(breakLine);
                        System.out.println(e.getMessage());
                        System.out.println(breakLine);
                    } catch (IOException e) {
                        System.out.println(breakLine);
                        System.out.println("error: " + e.getMessage());
                        System.out.println(breakLine);
                    }


                    // user enters "event xxx"
                } else if (slicedStr[0].equals("event")) {
                    try {
                        if (slicedStr.length == 1) {
                            throw new EmptyTaskException("event");
                        }
                        Event newEvent = new Event();
                        newEvent.convertStringToTask(slicedStr);
                        inputList.add(newEvent);
                        saveDataToFile(inputList);
                        System.out.println(breakLine);
                        System.out.println("Got it. I've added this task:");
                        System.out.printf("  [%s][%s] %s%n", newEvent.type, newEvent.getStatusIcon(), newEvent);
                        System.out.printf("Now you have %d tasks in the list%n", inputList.size());
                        System.out.println(breakLine);
                    } catch (EmptyTaskException e) {
                        System.out.println(breakLine);
                        System.out.println(e.getMessage());
                        System.out.println(breakLine);
                    } catch (IOException e) {
                        System.out.println(breakLine);
                        System.out.println("error: " + e.getMessage());
                        System.out.println(breakLine);
                    }

                // user enters "delete xxx"
                } else if (slicedStr.length == 2 && slicedStr[0].equals("delete")){
                    try {
                        if (Integer.parseInt(slicedStr[1]) > inputList.size() || Integer.parseInt(slicedStr[1]) <= 0 ) {
                            throw new TaskIndexOutOfBound();
                        }
                        Task target = inputList.get(Integer.parseInt(slicedStr[1]) - 1);
                        inputList.remove(Integer.parseInt(slicedStr[1]) - 1);
                        saveDataToFile(inputList);
                        System.out.println(breakLine);
                        System.out.println("Noted. I've removed this task:");
                        System.out.printf("[%s][%s] %s%n", target.type, target.getStatusIcon(), target);
                        System.out.printf("Now you have %d tasks in the list%n", inputList.size());
                        System.out.println(breakLine);
                    } catch (TaskIndexOutOfBound e) {
                        System.out.println(breakLine);
                        System.out.println(e.getMessage());
                        System.out.println(breakLine);
                    } catch (IOException e) {
                        System.out.println(breakLine);
                        System.out.println("error: " + e.getMessage());
                        System.out.println(breakLine);
                    }

                // user enters otherwise
                } else {
                    throw new InvalidInputException();
                }
            } catch (InvalidInputException e) {
                System.out.println(breakLine);
                System.out.println(e.getMessage());
                System.out.println(breakLine);
            }
            response = userInput.nextLine();
            slicedStr = response.split(" ");
        }
        System.out.println(breakLine);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(breakLine);
    }
}
