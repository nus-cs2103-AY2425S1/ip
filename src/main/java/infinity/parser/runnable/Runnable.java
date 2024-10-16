package infinity.parser.runnable;

import infinity.infinityexception.InfinityException;

/**
 * Functional Interface to run lambda functions in Parser for multi inputs
 */
@FunctionalInterface
public interface Runnable {
    String run(String input) throws InfinityException;
}
