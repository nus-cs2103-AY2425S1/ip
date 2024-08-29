class hmm {
    private String a = "H";

    public String get_a() {
        return this.a;
    }
}

class bye extends hmm {
    private String a = "meow";
}



public class Hi {



    public static void main(String[] args) {
        bye meow = new bye();
        System.out.println(meow.get_a());
    }
}




