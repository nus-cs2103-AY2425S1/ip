package commands;
import storage.Storage;
import system.Ui;
import system.DateTimeSystem;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
public abstract class TaskList {
    public String name;

    public enum Status {
        MARKED,
        UNMARKED
    }

    public Status currentStatus;
    public static Ui ui = new Ui();
    public String tag;
    public static ArrayList<TaskList> taskLists = new ArrayList<>();
    static Storage storage;
    static DateTimeSystem dateTimeSystem = new DateTimeSystem();

    static {
        try {
            storage = new Storage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public TaskList(String name, String tag) throws IOException {
        this.name = name;
        currentStatus = Status.UNMARKED;
        this.tag = tag;
    }

    public static void init_list() throws IOException {
        StringBuilder sb = storage.read();
        String[] lines = sb.toString().split("\n");
        for (String s : lines) {
            if (s.contains("[T]")) {
                String[] tokens = s.split(" ");
                StringBuilder name = new StringBuilder();
                for (int i = 2; i < tokens.length; i++) {
                    name.append(tokens[i]);
                }


                TaskList t = new ToDos(name.toString());
                if (s.contains("[X]")) {
                    t.setCurrentStatus(Status.MARKED);
                } else {
                    t.setCurrentStatus(Status.UNMARKED);
                }
                taskLists.add(t);
//                System.out.println("===INIT=== " + t.getName());

            } else if (s.contains("[D]")) {
                String[] tokens = s.split(" ");
                StringBuilder name = new StringBuilder();
                StringBuilder full_date = new StringBuilder();
                boolean isName = true;
                for (int i = 2; i < tokens.length; i++) {
                    if (isName) {
                        if (tokens[i].equals("(by:")) {
                            isName = false;
                        } else {
                            name.append(tokens[i]);
                        }
                    } else {
                        full_date.append(tokens[i]).append(" ");
                    }
                }
                String[] full_date_token = full_date.toString().split(" ");
                String[] date_token = full_date_token[0].split("-");
                String[] time_token = full_date_token[1].split(":");

                LocalDateTime ldt = dateTimeSystem.createDate(date_token[0],date_token[1],date_token[2],time_token[0],time_token[1]);

                TaskList d = new Deadlines(name.toString(), ldt);
                if (s.contains("[X]")) {
                    d.setCurrentStatus(Status.MARKED);
                } else {
                    d.setCurrentStatus(Status.UNMARKED);
                }
                taskLists.add(d);

            } else if (s.contains("[E]")){
                String[] tokens = s.split(" ");
                StringBuilder name = new StringBuilder();
                StringBuilder start = new StringBuilder();
                StringBuilder end = new StringBuilder();
                boolean isName = true;
                boolean isEnd = false;
                for (int i = 2; i < tokens.length; i++) {
                    if (isEnd) {
                        end.append(tokens[i]).append(" ");
                    }
                    if (isName) {

                        if (tokens[i].equals("(from:")) {
                            isName = false;
                        } else {
                            name.append(tokens[i]);
                        }
                    } else {

                        if (tokens[i].equals("to:")) {
                            isEnd = true;
                        } else {
                            start.append(tokens[i]).append(" ");
                        }
                    }


                }

                String[] full_date_token_start = start.toString().split(" ");
                String[] date_token_start = full_date_token_start[0].split("-");
                String[] time_token_start = full_date_token_start[1].split(":");

                LocalDateTime ldt_start = dateTimeSystem.createDate(date_token_start[0],date_token_start[1],date_token_start[2],time_token_start[0],time_token_start[1]);

                String[] full_date_token_end = end.toString().split(" ");
                String[] date_token_end = full_date_token_end[0].split("-");
                String[] time_token_end = full_date_token_end[1].split(":");

                LocalDateTime ldt_end = dateTimeSystem.createDate(date_token_end[0],date_token_end[1],date_token_end[2],time_token_end[0],time_token_end[1]);

                TaskList e = new Events(name.toString(), ldt_start, ldt_end);
                if (s.contains("[X]")) {
                    e.setCurrentStatus(Status.MARKED);
                } else {
                    e.setCurrentStatus(Status.UNMARKED);
                }
                taskLists.add(e);
            }
        }
    }

//    public void update_saved_tasklist() throws IOException {
//        String marked = "[X]";
//        String unmarked = "[ ]";
//        int current_size = task_list.size();
//        if (current_size >= 1) {
//            int counter = 1;
//            for (Task t : task_list) {
//                StringBuilder information;
//                if (t.getCurrent_status()==status.MARKED) {
//                    information = new StringBuilder(counter + ". [" + t.getTag() + "]" + marked + " " + t.getName());
//                } else {
//                    information = new StringBuilder(counter + ". [" + t.getTag() + "]" + unmarked + " " + t.getName());
//
//                }
//
//                if (t.getTag().equals("D")){
//                    information.append(" (by: ").append(t.getDay()).append(")");
//                } else if (t.getTag().equals("E")) {
//                    information.append(" (from: ").append(t.getStart()).append(" to: ").append(t.getEnd()).append(")");
//                }
//                System.out.println("===DEBUG=== "+information);
//                fs.write(String.valueOf(information));
//                counter++;
//            }
//        } else {
//            //List was empty
//            System.out.println(" ==DEBUG== Nothing inside!");
//        }
//    }
    public static void delete_task(int index) throws IOException {
        if (taskLists.isEmpty()) {
            ui.emptyList();
        } else {
            if (taskLists.size() > index) {
                TaskList temp = taskLists.get(index - 1);
                taskLists.remove(temp);
                storage.delete(index);
                ui.delete_message(temp);
            } else {
                ui.doesNotExist();
            }
        }
    }

    public static void list_task() throws FileNotFoundException {
        StringBuilder temp = storage.read();
        ui.list_task_message(String.valueOf(temp));
    }

    public static void mark_task(int index) throws IOException {
        if (taskLists.isEmpty()) {
            ui.emptyList();
        } else {
            if (taskLists.size() >= index) {
                TaskList temp = taskLists.get(index - 1);
                System.out.println("CURRENT STATUS IS : " + temp.getCurrentStatus());
                if (temp.getCurrentStatus()== Status.MARKED) {
                    ui.alreadyMarked();
                } else {
                    temp.setCurrentStatus(Status.MARKED);
                    storage.mark(index);
                    ui.mark_message(temp.getName());
                }
            } else {
                ui.doesNotExist();
            }
        }
    }

    public static void unmark_task(int index) throws IOException {
        if (taskLists.isEmpty()) {
            ui.emptyList();
        } else {
            if (taskLists.size() > index) {
                TaskList temp = taskLists.get(index - 1);
                if (temp.getCurrentStatus() == Status.UNMARKED) {
                    ui.alreadyUnmarked();
                } else {
                    temp.setCurrentStatus(Status.UNMARKED);
                    storage.unmark(index);
                    ui.unmark_message(temp.getName());
                }
            } else {
                ui.doesNotExist();
            }
        }
    }

    public String getName() {
        return name;
    }

    public Status getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(Status currentStatus) {
        this.currentStatus = currentStatus;
    }

    public String getTag() {
        return tag;
    }

    public int get_list_size() {
        return taskLists.size();
    }

    public abstract LocalDateTime getDate();

    public abstract LocalDateTime getStart();

    public abstract LocalDateTime getEnd();

}
