package com.meow;
public class Meowception extends Exception {
    String code;
    
    // public consturctor for Meowception
    public Meowception (String code) {
        this.code = code;
    }

    /*
     * Returns the error message based on the error code
     * @param void
     * @return String
     */
    @Override
    public String toString() {
        switch (code) {
            case "001":
                return "Meowception ERROR 001: meow meow enter a valid command neow.";
            case "007":
                return "Meowception ERROR 007: meow meow file does not exist!";
            case "100":
                return "Meowception ERROR 100: meow meow you need to enter a task and it cant be blank silly goose meow";
            case "200":
                return "Meowception ERROR 200: meow meow you need to enter a deadline you stupido meow";
            case "300":
                return "Meowception ERROR 300: meow meow you need to enter a correct timefrime such as yyyy-mm-dd HHmm";
            case "404":
                return "Meowception ERROR 404: meow meow task not found meow";
            default:
                return "Unknown Meowception Error.";
        }

    }
}
