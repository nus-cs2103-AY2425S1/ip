public class Storage {
    Task[] store = new Task[101];

    public String toString() {
        String thing = "";
        int j = 1;
        for(int i = 0; i<101; i++){
            if(store[i].isReal()){
                thing += j + "." + store[i].toString() + "\n";
                j++;
            }
        }
        return thing;
    }
}
