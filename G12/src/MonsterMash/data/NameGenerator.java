package data;

import java.util.*;

/**
 *
 * @author Llionv
 */
public class NameGenerator {

    private static List startWords = new ArrayList();
    private static List endWords = new ArrayList();

    public static String getName() {
        String thestartWords[] = {"Blessed", "Cursed", "Devious", "Forgotten", "Greater",
            "Hidden", "Hood", "Lost", "Vile", "Blood", "Creeping",
            "Sacred", "Whip", "Pixie", "Toad", "Dreadshredder", "Minature",
            "Terrifying", "Primordial", "Freezing", "Mighty", "Rainbow",
            "Slithering", "Babbling", "Withered"};
        String theendWords[] = {"Snake", "Hawk", "Pudding", "Kraken", "Shark", "Hedgehog",
            "Hacker", "Lurker", "Knight", "Slayer", "Demon", "Grotesque",
            "Serpant", "Spectre", "Billy", "Lizard", "Swarm", "Strangler", "Mummy", ""};

        startWords.addAll(Arrays.asList(thestartWords));
        endWords.addAll(Arrays.asList(theendWords));

        String name = startWords.get(randomInt(0, 24)) + " " + endWords.get(randomInt(0, 18));

        return name;
    }

    private static int randomInt(int min, int max) {
        return (int) (min + (Math.random() * (max + 1 - min)));
    }

    private static String getRandomElementFrom(List<String> v) {
        return v.get(randomInt(0, v.size() - 1));
    }
}