import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Task {
    private String name;

    //    private Boolean status;
    enum status {
        MARKED,
        UNMARKED
    }

    status current_status;
    public static Response r = new Response();
    ;
    public String tag;
    public static ArrayList<Task> task_list = new ArrayList<>();
    static FileSystem fs;

    static {
        try {
            fs = new FileSystem();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Task(String name, String tag) throws IOException {
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


                Task t = new ToDos(name.toString());
                if (s.contains("[X]")) {
                    t.setCurrent_status(status.MARKED);
                } else {
                    t.setCurrent_status(status.UNMARKED);
                }
                task_list.add(t);
                System.out.println("===INIT=== " + t.getName());

            } else if (s.contains("[D]")) {
                String[] tokens = s.split(" ");
                StringBuilder name = new StringBuilder();
                StringBuilder day = new StringBuilder();
                boolean isName = true;
                for (int i = 2; i < tokens.length; i++) {
                    if (isName) {
                        if (tokens[i].equals("(by:")) {
                            isName = false;
                        } else {
                            name.append(tokens[i]);
                        }
                    } else {
                        day.append(tokens[i]);
                    }
                }

                Task d = new Deadlines(name.toString(), day.toString());
                if (s.contains("[X]")) {
                    d.setCurrent_status(status.MARKED);
                } else {
                    d.setCurrent_status(status.UNMARKED);
                }
                task_list.add(d);
                System.out.println("===INIT=== " + d.getName());

            } else if (s.contains("[E]")){
                String[] tokens = s.split(" ");
                StringBuilder name = new StringBuilder();
                StringBuilder start = new StringBuilder();
                StringBuilder end = new StringBuilder();
                boolean isName = true;
                boolean isEnd = false;
                for (int i = 2; i < tokens.length; i++) {
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
                            start.append(tokens[i]);
                        }
                    }

                    if (isEnd) {
                        end.append(tokens[i]);
                    }
                }

                Task e = new Events(name.toString(), start.toString(), end.toString());
                if (s.contains("[X]")) {
                    e.setCurrent_status(status.MARKED);
                } else {
                    e.setCurrent_status(status.UNMARKED);
                }
                task_list.add(e);
                System.out.println("===INIT=== " + e.getName());

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
        if (task_list.isEmpty()) {
            r.emptyList();
        } else {
            if (task_list.size() > index) {
                Task temp = task_list.get(index - 1);
                task_list.remove(temp);
                fs.delete(index);
                r.delete_message(temp);
            } else {
                r.doesNotExist();
            }
        }
    }

    public static void list_task() throws FileNotFoundException {
        StringBuilder temp = fs.read();
        r.list_task_message(String.valueOf(temp));
//        System.out.println("===DEBUG=== list size: " + task_list.size());
    }

    public static void mark_task(int index) throws IOException {
        if (task_list.isEmpty()) {
            r.emptyList();
        } else {
            if (task_list.size() > index) {
                Task temp = task_list.get(index - 1);
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
        if (task_list.isEmpty()) {
            r.emptyList();
        } else {
            if (task_list.size() > index) {
                Task temp = task_list.get(index - 1);
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
        return task_list.size();
    }

    public abstract String getDay();

    public abstract String getStart();

    public abstract String getEnd();

}
