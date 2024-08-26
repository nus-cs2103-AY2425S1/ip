package count.action;

import count.TaskList;

public class ListReply extends Action {
    TaskList ls;
    public ListReply(TaskList ls) {
        this.ls = ls;
    }
    @Override
    public String run() {
        String ans = "Here are the tasks in your list:\n";
        for (int i = 0 ; i < ls.getList().size() ; i++) {
            if (i != ls.getList().size() - 1) {
                ans += (i + 1) + "." + ls.getList().get(i).toString() + "\n";
            } else {
                ans += (i + 1) + "." + ls.getList().get(i).toString();
            }
        }
        return ans;
    }
}
