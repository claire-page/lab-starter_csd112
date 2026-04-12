package part1.logging;

import java.time.Instant;
import java.util.Random;


public class InDenialLogger extends ConsoleLogger implements Logger{
    /** Prints a random in-denial message to the console. *
     * @param message is ignored
     * @param level is also ignored
     */
    @Override
    public void log(String message, LogLevel level) {
        //ignores the level I'm assuming.
        Random rand = new Random();
        int randomint = rand.nextInt(0, 10);
        String[] denialMsgs = {" *Crashing noise* Don't worry about that, haha. it's supposed to do that, I think...",
                            " Excuse me, programmer? Sorry to bother you, it's just that...uh...I think  maybe...ahhhh, never mind.",
                            " PAH! My grandpa never caught exceptions and he did just fine...",
                            " I know you probably think an error happened. But it didn't. And if it did, it wasn't my fault.",
                            " *Explosion noise* yeah no I'm definitely keeping an eye out for errors...I'd tell you if something went wrong, *more explosion noises* so dooooon't even worry about it.",
                            " Something happened, but, uh, it might be above my pay grade... I'll let you figure it out. Since you're so capable.",
                            " Clean up on file...somewhere.",
                            " OOPS! I mean...it's not like anyone is actually going to use this software.",
                            " ...just tell them it's a feature.",
                            " ...huuh? what? I was asleep, did something happen?",
                            "welp."
        };

        System.out.println(Instant.now().toString() + denialMsgs[randomint]);
    }
}
