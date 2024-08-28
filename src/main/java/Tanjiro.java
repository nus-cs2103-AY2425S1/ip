import java.io.IOException;

public class Tanjiro {
    public static void main(String[] args) throws IOException {
        Ui ui = new Ui();
        InputProcessor i = new InputProcessor();
        Storage fs = new Storage();
        fs.createFile();
        TaskList.init_list();
        Parser p = new Parser();

        ui.greet();

        String user_input = i.read().toLowerCase();
        while (!p.containBye(user_input)) {
            if (p.containList(user_input)) {
                TaskList.list_task();
            } else if (p.containMark(user_input)) {
//                int list_no = Character.getNumericValue(user_input.charAt(user_input.length() - 1));
//                if (user_input.contains("unmark")) {
//                    TaskList.unmark_task(list_no);
//                } else {
//                    TaskList.mark_task(list_no);
//                }
                p.performMark(user_input);
            } else if (p.containToDo(user_input)) {
//                try {
//                    String name = user_input.substring(5);
//                    ToDos temp_todo = new ToDos(name);
//                    temp_todo.add_task(temp_todo);
//                } catch (StringIndexOutOfBoundsException e) {
//                    ui.empty_todo();
//                }
                p.performToDo(user_input);
            } else if (p.containDeadline(user_input)) {
//                try {
//                    String[] deadline_arr = user_input.split("/");
//                    String name = deadline_arr[0].substring(9);
//                    String date = deadline_arr[1].substring(3);
//
//                    String[] tokens = date.split(" ");
//                    String[] dateTokens = tokens[0].split("-");
//                    String time = tokens[1];
//                    String year = dateTokens[0];
//                    String month = dateTokens[1];
//                    String day = dateTokens[2];
//                    LocalDateTime ldt = ds.createDate(year,month,day,time.substring(0,2),time.substring(2));
//
//                    Deadlines temp_deadline = new Deadlines(name, ldt);
//                    temp_deadline.add_task(temp_deadline);
//                } catch (StringIndexOutOfBoundsException e) {
//                    ui.empty_deadline();
//                }
                p.performDeadline(user_input);
            } else if (p.containEvent(user_input)) {
//                try {
//                    String[] deadline_arr = user_input.split("/");
//                    String name = deadline_arr[0].substring(6);
//                    String start = deadline_arr[1].substring(5);
//                    String end = deadline_arr[2].substring(3);
//
//                    String[] full_date_token_start = start.split(" ");
//                    String[] date_token_start = full_date_token_start[0].split("-");
//
//                    System.out.println("Start Date: " + date_token_start[0] + " / " + date_token_start[1] + " / " + date_token_start[2]);
//
//                    LocalDateTime ldt_start = ds.createDate(date_token_start[0],date_token_start[1],date_token_start[2],full_date_token_start[1].substring(0,2),full_date_token_start[1].substring(2));
//
//                    String[] full_date_token_end = end.split(" ");
//                    String[] date_token_end = full_date_token_end[0].split("-");
//
//                    LocalDateTime ldt_end = ds.createDate(date_token_end[0],date_token_end[1],date_token_end[2],full_date_token_end[1].substring(0,2),full_date_token_end[1].substring(2));
//
//                    Events temp_event = new Events(name, ldt_start, ldt_end);
//                    temp_event.add_task(temp_event);
//                } catch (StringIndexOutOfBoundsException e) {
//                    ui.empty_event();
//                }
                p.performEvent(user_input);
            } else if (p.containDelete(user_input)) {
//                int list_no = Character.getNumericValue(user_input.charAt(user_input.length() - 1));
//                TaskList.delete_task(list_no);
                p.performDelete(user_input);
            } else {
                ui.invalid_input();
            }
            user_input = i.read().toLowerCase();
        }

        ui.goodbye();

    }

}
