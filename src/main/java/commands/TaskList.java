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

    public enum status {
        MARKED,
        UNMARKED
    }

    public status current_status;
    public static Ui r = new Ui();
    public String tag;
    public static ArrayList<TaskList> task_List_list = new ArrayList<>();
    static Storage fs;
    static DateTimeSystem ds = new DateTimeSystem();

    static {
        try {
            fs = new Storage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public TaskList(String name, String tag) throws IOException {
        this.name = name;
        current_status = status.UNMARKED;
        this.tag = tag;
    }

    public static void init_list() throws IOException {
        StringBuilder sb = fs.read();
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
                    t.setCurrent_status(status.MARKED);
                } else {
                    t.setCurrent_status(status.UNMARKED);
                }
                task_List_list.add(t);
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
//                System.out.println("FULL DATE: " + full_date.toString());
                String[] full_date_token = full_date.toString().split(" ");
                String[] date_token = full_date_token[0].split("-");
                String[] time_token = full_date_token[1].split(":");

                LocalDateTime ldt = ds.createDate(date_token[0],date_token[1],date_token[2],time_token[0],time_token[1]);

                TaskList d = new Deadlines(name.toString(), ldt);
                if (s.contains("[X]")) {
                    d.setCurrent_status(status.MARKED);
                } else {
                    d.setCurrent_status(status.UNMARKED);
                }
                task_List_list.add(d);
//                System.out.println("===INIT=== " + d.getName());

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

                LocalDateTime ldt_start = ds.createDate(date_token_start[0],date_token_start[1],date_token_start[2],time_token_start[0],time_token_start[1]);

                String[] full_date_token_end = end.toString().split(" ");
                String[] date_token_end = full_date_token_end[0].split("-");
                String[] time_token_end = full_date_token_end[1].split(":");

                LocalDateTime ldt_end = ds.createDate(date_token_end[0],date_token_end[1],date_token_end[2],time_token_end[0],time_token_end[1]);

                TaskList e = new Events(name.toString(), ldt_start, ldt_end);
                if (s.contains("[X]")) {
                    e.setCurrent_status(status.MARKED);
                } else {
                    e.setCurrent_status(status.UNMARKED);
                }
                task_List_list.add(e);
//                System.out.println("===INIT=== " + e.getName());

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
        if (task_List_list.isEmpty()) {
            r.emptyList();
        } else {
            if (task_List_list.size() > index) {
                TaskList temp = task_List_list.get(index - 1);
                task_List_list.remove(temp);
                fs.delete(index);
                r.delete_message(temp);
            } else {
                r.doesNotExist();
            }
        }
    }

    public static void findTask(String input) throws FileNotFoundException {
        StringBuilder sb = fs.read();
        String temp = sb.toString();
        String[] lineTokens = temp.split("\n");
        StringBuilder finalSb = new StringBuilder();
        int count = 1;
        for (int i = 0; i < lineTokens.length; i++) {
            String name = "";
            if (lineTokens[i].contains("\\(")) {
                System.out.println("==DEBUG==" + lineTokens[i]);
                String[] charTokens = lineTokens[i].split("\\(");
                name = charTokens[0].substring(10);
            } else {
                name = lineTokens[i].substring(10);
            }

            if (name.contains(input)) {
                //Get the entire line and print it out using stringbuilder
                finalSb.append(count).append(".").append(lineTokens[i].substring(3)).append("\n");
                count++;
            }
        }

        r.findTaskMessage(finalSb);
    }

    public static void list_task() throws FileNotFoundException {
        StringBuilder temp = fs.read();
        r.list_task_message(String.valueOf(temp));
    }

    public static void mark_task(int index) throws IOException {
        if (task_List_list.isEmpty()) {
            r.emptyList();
        } else {
            if (task_List_list.size() >= index) {
                TaskList temp = task_List_list.get(index - 1);
                System.out.println("CURRENT STATUS IS : " + temp.getCurrent_status());
                if (temp.getCurrent_status()==status.MARKED) {
                    r.alreadyMarked();
                } else {
                    temp.setCurrent_status(status.MARKED);
                    fs.mark(index);
                    r.mark_message(temp.getName());
                }
            } else {
                r.doesNotExist();
            }
        }
    }

    public static void unmark_task(int index) throws IOException {
        if (task_List_list.isEmpty()) {
            r.emptyList();
        } else {
            if (task_List_list.size() > index) {
                TaskList temp = task_List_list.get(index - 1);
                if (temp.getCurrent_status() == status.UNMARKED) {
                    r.alreadyUnmarked();
                } else {
                    temp.setCurrent_status(status.UNMARKED);
                    fs.unmark(index);
                    r.unmark_message(temp.getName());
                }
            } else {
                r.doesNotExist();
            }
        }
    }

    public String getName() {
        return name;
    }

    public status getCurrent_status() {
        return current_status;
    }

    public void setCurrent_status(status current_status) {
        this.current_status = current_status;
    }

    public String getTag() {
        return tag;
    }

    public int get_list_size() {
        return task_List_list.size();
    }

    public abstract LocalDateTime getDate();

    public abstract LocalDateTime getStart();

    public abstract LocalDateTime getEnd();

}
