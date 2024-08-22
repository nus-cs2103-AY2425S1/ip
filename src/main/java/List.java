public class List {
    public String[] str;
    public int n = 0;
    public List(){
        str = new String[100];
    }

    public String add(String adding){
        str[n] = adding;
        n++;
        return adding;
    }

    public String get() {
        String temp = "";
        for (int i = 0; i < n; i++) {
            temp = String.format("%s %d. ",temp, i + 1) + str[i];
            if (i < n - 1) {
                temp = temp + "\n";
            }
        }
        return temp;
    }
}
