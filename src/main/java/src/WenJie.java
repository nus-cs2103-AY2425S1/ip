package src;

import src.commands.Command;

import java.util.ArrayList;

public class WenJie {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public WenJie(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());

    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
    public static void main(String[] args) {
        String filePath = "./src/main/java/data/wenjie.txt";
        Storage storage = new Storage(filePath);

        try {

            boolean active = true;
            ArrayList<Task> taskList = storage.load();




            while(active) {
                String input = scanner.nextLine();

                String[] parts = input.split(" ");

                String firstWord = parts[0];

                switch(firstWord) {
//                    case "bye": {
//                        active = false;
//                        storage.writeTasks();
//                        break;
//                    }

//                    case "list": {
//                        String list =
//                                "____________________________________________________________\n" +
//                                        displayList(taskList) +
//                                        "____________________________________________________________\n";
//                        System.out.println(list);
//                        break;
//                    }

//                    case "mark": {
//
//                        if (parts.length <= 1) {
//                            throw new NoNumberInputtedException();
//                        }
//
//                        int taskNo = Integer.parseInt(parts[1]) - 1;
//
//                        if (taskNo + 1 > taskList.size()) {
//                            throw new OutOfBoundsException();
//                        }
//
//                        taskList.get(taskNo).setStatusIcon(true);
//                        String output =
//                                "____________________________________________________________\n" +
//                                        "Nice! I've marked this task as done:\n" +
//                                        taskList.get(taskNo) + "\n" +
//                                        "____________________________________________________________";
//                        System.out.println(output);
//                        break;
//                    }

//                    case "unmark": {
//
//                        if (parts.length <= 1) {
//                            throw new NoNumberInputtedException();
//                        }
//
//                        int taskNo = Integer.parseInt(parts[1]) - 1;
//
//                        if (taskNo + 1 > taskList.size()) {
//                            throw new OutOfBoundsException();
//                        }
//
//                        taskList.get(taskNo).setStatusIcon(false);
//                        String output =
//                                " ____________________________________________________________\n" +
//                                        " OK, I've marked this task as not done yet:\n " +
//                                        taskList.get(taskNo) + "\n" +
//                                        " ____________________________________________________________";
//                        System.out.println(output);
//                        break;
//                    }

                    case "todo": {

                        if (input.length() <= 5) {
                            throw new NoFollowUpException();
                        }

                        ToDo temp = new ToDo(input.substring(5));

                        String output =
                                "____________________________________________________________\n" +
                                        "Got it. I've added this task:\n" +
                                        temp + "\n" +
                                        "Now you have " + (taskList.size() + 1)+" tasks in the list.\n" +
                                        "____________________________________________________________\n";
                        System.out.println(output);
                        taskList.add(temp);
                        break;
                    }

                    case "deadline": {

                        if (input.length() <= 8) {
                            throw new NoFollowUpException();
                        }

                        String by = "";
                        for (int i = 0; i < parts.length; i++) {
                            if (parts[i].charAt(0) == '/') {
                                for (int j = i + 1; j < parts.length; j++){
                                    by += parts[j] + " ";
                                }
                                break;
                            }
                        }
                        int endIndex = 0;
                        for (int i = 0; i < input.length(); i++) {
                            if (input.charAt(i) == '/') {
                                endIndex = i;
                                break;
                            }
                        }

                        String desc = input.substring(9, endIndex);
                        Deadline temp = new Deadline(desc, by);
                        taskList.add(temp);
                        String output =
                                "____________________________________________________________\n" +
                                        "Got it. I've added this task:\n" +
                                        temp + "\n" +
                                        "Now you have " + taskList.size() +" tasks in the list.\n" +
                                        "____________________________________________________________\n";
                        System.out.println(output);
                        break;
                    }

                    case "event": {

                        if (input.length() <= 5) {
                            throw new NoFollowUpException();
                        }

                        String from = "", to = "";

                        for (int i = 0; i < parts.length; i++) {
                            if (parts[i].charAt(0) == '/') {
                                int j = i + 1;
                                while(parts[j].charAt(0) != '/') {
                                    from += parts[j] + " ";
                                    j++;
                                }
                                j++;
                                while(j < parts.length) {
                                    to += parts[j];
                                    j++;
                                }

                                break;
                            }
                        }
                        int endIndex = 0;
                        for (int i = 0; i < input.length(); i++) {
                            if (input.charAt(i) == '/') {
                                endIndex = i;
                                break;
                            }
                        }

                        if (endIndex == 0) {
                            throw new InvalidInputException();
                        }

                        String desc = input.substring(6, endIndex);
                        Event temp = new Event(desc, from, to);
                        taskList.add(temp);
                        String output =
                                "____________________________________________________________\n" +
                                        "Got it. I've added this task:\n" +
                                        temp + "\n" +
                                        "Now you have " + taskList.size() +" tasks in the list.\n" +
                                        "____________________________________________________________\n";
                        System.out.println(output);
                        break;
                    }

//                    case "delete": {
//
//                        if (parts.length <= 1) {
//                            throw new NoNumberInputtedException();
//                        }
//
//                        int taskNo = Integer.parseInt(parts[1]) - 1;
//
//                        if (taskNo + 1 > taskList.size()) {
//                            throw new OutOfBoundsException();
//                        }
//
//                        Task taskToRemove = taskList.get(taskNo);
//                        taskList.remove(taskNo);
//
//                        String output =
//                                "____________________________________________________________\n" +
//                                "Noted. I've removed this task:\n" +
//                                taskToRemove + "\n" +
//                                "Now you have " + taskList.size() + " tasks in the list.\n" +
//                                "____________________________________________________________";
//
//                        System.out.println(output);
//                        break;
//                    }


                    default :
                        throw new UnknownCommandException();
                }
            }


        } catch (DukeException de) {
            String output = "____________________________________________________________\n" +
                    de.toString() + "\n" +
                    "____________________________________________________________";
            System.out.println(output);
        }

    }




}
