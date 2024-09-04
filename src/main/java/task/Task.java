package task;
import storage.Storage;
import system.Ui;
import system.DateTimeSystem;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
public abstract class Task {
    public String name;

    public enum Status {
        MARKED,
        UNMARKED
    }

    public Status currentStatus;
    public static Ui ui = new Ui();
    public String tag;
//    public static ArrayList<Task> tasks = new ArrayList<>();
    static Storage storage;
    static DateTimeSystem dateTimeSystem = new DateTimeSystem();

    static {
        try {
            storage = new Storage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Task(String name, String tag){
        this.name = name;
        currentStatus = Status.UNMARKED;
        this.tag = tag;
    }

    public static void init_list() throws IOException {
        TaskList.tasks.clear();
        StringBuilder sb = storage.read();
        String[] lines = sb.toString().split("\n");
        for (String s : lines) {
            if (s.contains("[T]")) {
                String[] tokens = s.split(" ");
                StringBuilder name = new StringBuilder();
                for (int i = 2; i < tokens.length; i++) {
                    name.append(tokens[i]);
                }


                Task t = new ToDos(name.toString());
                if (s.contains("[X]")) {
                    t.setCurrentStatus(Status.MARKED);
                } else {
                    t.setCurrentStatus(Status.UNMARKED);
                }
                TaskList.addTasks(t);


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

                Task d = new Deadlines(name.toString(), ldt);
                if (s.contains("[X]")) {
                    d.setCurrentStatus(Status.MARKED);
                } else {
                    d.setCurrentStatus(Status.UNMARKED);
                }
//                tasks.add(d);
                TaskList.addTasks(d);

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

                LocalDateTime ldtStart = dateTimeSystem.createDate(date_token_start[0],date_token_start[1],date_token_start[2],time_token_start[0],time_token_start[1]);

                String[] full_date_token_end = end.toString().split(" ");
                String[] date_token_end = full_date_token_end[0].split("-");
                String[] time_token_end = full_date_token_end[1].split(":");

                LocalDateTime ldtEnd = dateTimeSystem.createDate(date_token_end[0],date_token_end[1],date_token_end[2],time_token_end[0],time_token_end[1]);

                Task e = new Events(name.toString(), ldtStart, ldtEnd);
                if (s.contains("[X]")) {
                    e.setCurrentStatus(Status.MARKED);
                } else {
                    e.setCurrentStatus(Status.UNMARKED);
                }
                TaskList.addTasks(e);

            }
        }
    }

    public static String delete_task(int index) throws IOException {
        String response = "";
        ArrayList<Task> temporaryTaskList = TaskList.tasks;
        if (temporaryTaskList.isEmpty()) {
            response = ui.emptyList();
        } else {
            if (temporaryTaskList.size() >= index) {
                Task temp = temporaryTaskList.get(index - 1);
                temporaryTaskList.remove(temp);
                storage.delete(index);
                response = ui.delete_message(temp);
            } else {
                response = ui.doesNotExist();
            }
        }

        return response;
    }

    public static String findTask(String input) throws FileNotFoundException {
        StringBuilder sb = storage.read();
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
                finalSb.append(count).append(".").append(lineTokens[i].substring(3)).append("\n");
                count++;
            }
        }

        return ui.findTaskMessage(finalSb);
    }

    public static String list_task() throws FileNotFoundException {
        StringBuilder temp = storage.read();
        return ui.list_task_message(String.valueOf(temp));
    }

    public static String mark_task(int index) throws IOException {
        String response = "";
        ArrayList<Task> temporaryTaskList = TaskList.tasks;
        if (temporaryTaskList.isEmpty()) {
            response = ui.emptyList();
        } else {
            if (temporaryTaskList.size() >= index) {
                Task temp = temporaryTaskList.get(index - 1);
                System.out.println("CURRENT STATUS IS : " + temp.getCurrentStatus());
                if (temp.getCurrentStatus()== Status.MARKED) {
                    response = ui.alreadyMarked();
                } else {
                    temp.setCurrentStatus(Status.MARKED);
                    storage.mark(index);
                    response = ui.mark_message(temp.getName());
                }
            } else {
                response = ui.doesNotExist();
            }
        }

        return response;
    }

    public static String unmark_task(int index) throws IOException {
        String response = "";
        ArrayList<Task> temporaryTaskList = TaskList.tasks;
        if (temporaryTaskList.isEmpty()) {
            response = ui.emptyList();
        } else {
            if (temporaryTaskList.size() > index) {
                Task temp = temporaryTaskList.get(index - 1);
                if (temp.getCurrentStatus() == Status.UNMARKED) {
                    response = ui.alreadyUnmarked();
                } else {
                    temp.setCurrentStatus(Status.UNMARKED);
                    storage.unmark(index);
                    response = ui.unmark_message(temp.getName());
                }
            } else {
                response = ui.doesNotExist();
            }
        }

        return response;
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
        return TaskList.tasks.size();
    }

    public abstract LocalDateTime getDate();

    public abstract LocalDateTime getStart();

    public abstract LocalDateTime getEnd();

}
