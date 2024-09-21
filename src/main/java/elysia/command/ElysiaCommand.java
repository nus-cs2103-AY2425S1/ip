package elysia.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import elysia.exception.EmptyDescriptionException;
import elysia.task.Task;

/**
 * Represents a command that the name of Elysia being called.
 */
public class ElysiaCommand extends Command {
    private Random random = new Random();
    private List<String> voiceLines = List.of(
            "2nd of the Flame-Chasers, Elysia.\nAs you can see, I'm a girl beautiful like a flower.",
            "Miss Pink Elf?\nMy~ Since you insist to call me that, I'll gladly accept it.",
            "Did you miss me~?",
            "Hmm? You were staring at me just now, right?",
            "Leaving a girl alone like that, are you trying to play hard to get? How mean.",
            "Tragedy isn't the end. It's the beginning of hope. You'll have faith, right?",
            "I still have so much to tell you. Can we be like this forever?",
            "Don't move. I'm using your eyes as a mirror... Isn't this nostalgic?",
            "My eyes are beautiful?\nThey are not cosmetic contact lenses.\nThey are the magic of a beautiful girl.",
            "I can use telepathy like Aponia too.\nFor example... you're thinking about me, aren't you?",
            "Let's share about our weaknesses, I'll start.\nThe answer is... none!",
            "Sweet desserts are a perfect match for sweet girls. I think so too.",
            "Hurry to bed.\nPromise me that we'll meet in our dreams, alright?"
    );

    /**
     * Responds to user if Elysia is called.
     */
    @Override
    public String execute(ArrayList<Task> tasks) throws EmptyDescriptionException, IOException {
        // Randomly select a voice line
        int index = random.nextInt(voiceLines.size());
        return voiceLines.get(index);
    }
}
