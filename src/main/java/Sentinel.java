import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
public class Sentinel {

    enum Command{todo, deadline, event, list, mark, unmark, delete, help, bye }

    private static SentinelList list;
    private Ui ui;

    public Sentinel(){
        ui = new Ui();
        try {
            list = new FileLoader().loadTasks();
        } catch (Exception e) {
            ui.showError(e);
            list = new SentinelList();
        }
    }

    public void run(){
        ui.showWelcome();
        Scanner sc = new Scanner(System.in);
        Command input = null;
        do {
            ui.showLine();
            try {
                input = Command.valueOf(sc.next().toLowerCase());
            } catch (IllegalArgumentException e) {
                ui.showUnrecognisedCommand();
                continue;
            }
            ui.showLine();
            new FileWriter(list).saveTasks();
            switch (input) {
                case list -> {
                    ui.showList(list);
                }
                case mark, unmark, delete -> {
                    int num = sc.nextInt();
                    if (num > list.size()) {
                        ui.showNoItemExists();
                        break;
                    } else if (input.equals(Command.delete)) {
                        ui.showRemovedandRemaining(list, list.remove(num-1));
                        break;
                    } else if (input.equals(Command.mark) && list.taskIsDone(num-1)) {
                        ui.showAlreadyMarkedDone(list.get(num-1));
                        break;
                    } else if (input.equals(Command.unmark) && !list.taskIsDone(num-1)) {
                        ui.showAlreadyMarkedUndone(list.get(num-1));
                        break;
                    }
                    list.toggleMark(num-1);
                    ui.showTaskMark(list.get(num-1));

                }
                case todo, deadline, event -> {
                    Task newTask = null;
                    String input2 = sc.nextLine().trim();
                    if (input2.isEmpty()) {System.out.println(input.toString().substring(0, 1).toUpperCase() + input.toString().substring(1) + " name cannot be empty"); continue;}
                    switch (input){
                        case todo -> newTask = new ToDo(input2);


                        case deadline, event -> {
                            String[] stringArr = input2.split(" ");
                            String taskName = "", from = "", to = "";
                            boolean task = true, fr = false;
                            for (String word: stringArr){
                                if (word.equalsIgnoreCase("/by") || word.equalsIgnoreCase("/to")) {task = false; fr = false;}
                                else if (word.equalsIgnoreCase("/from")) {task = false; fr = true;}

                                else if (task && !fr) taskName = taskName.concat(word + " ");
                                else if (!task && fr) from = from.concat(word + " ");
                                else to = to.concat(word + " ");
                            }
                            taskName = taskName.trim(); from = from.trim(); to = to.trim();
                            LocalDateTime _from = GeminiAPI.formatDateTime(from), _to = GeminiAPI.formatDateTime(to);
                            switch (input){
                                case deadline -> {
                                    if (_to == null) {
                                        ui.showDeadlineCommandGuidelines();
                                        continue;
                                    }
                                    newTask = new Deadline(taskName, _to);
                                }
                                case event -> {
                                    if (_from == null || _to == null) {
                                        ui.showEventCommandGuidelines();
                                        continue;
                                    }
                                    newTask = new Event(taskName, _from, _to);
                                }
                            }
                        }
                    }
                    if (newTask == null) ui.showError(new Exception("Something went wrong creating new task!"));
                    list.add(newTask);
                    ui.showAddedTask(newTask);
                }
                case help -> {
                    ui.showHelp();
                }
            }
        } while (input == null || !input.equals(Command.bye));
        ui.showGoodbye();
    }

    public static void main(String[] args) {
        new Sentinel().run();
    }
}
