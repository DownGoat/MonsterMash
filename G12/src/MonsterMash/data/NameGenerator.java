package data;

import java.util.*;

public class NameGenerator {

    private static List vocals = new ArrayList();
    private static List startConsonants = new ArrayList();
    private static List endConsonants = new ArrayList();
    private static List nameInstructions = new ArrayList();

    public static String getName() {
        String demoVocals[] = { "a", "e", "i", "o", "u", "ei", "ai", "ou", "j",
        "ji", "y", "oi", "au", "oo" };
        String demoStartConsonants[] = { "b", "c", "d", "f", "g", "h", "k",
        "l", "m", "n", "p", "q", "r", "s", "t", "v", "w", "x", "z",
        "ch", "bl", "br", "fl", "gl", "gr", "kl", "pr", "st", "sh",
        "th" };
        String demoEndConsonants[] = { "b", "d", "f", "g", "h", "k", "l", "m",
        "n", "p", "r", "s", "t", "v", "w", "z", "ch", "gh", "nn", "st",
        "sh", "th", "tt", "ss", "pf", "nt" };
        String demoNameInstructions[] = { "vd", "cvdvd", "cvd", "vdvd" };
        vocals.addAll(Arrays.asList(demoVocals));
        startConsonants.addAll(Arrays.asList(demoStartConsonants));
        endConsonants.addAll(Arrays.asList(demoEndConsonants));
        nameInstructions.addAll(Arrays.asList(demoNameInstructions));
        return firstCharUppercase(getNameByInstructions(getRandomElementFrom(nameInstructions)));
    }

    private static int randomInt(int min, int max) {
        return (int) (min + (Math.random() * (max + 1 - min)));
    }

    private static String getNameByInstructions(String nameInstructions) {
        String name = "";
        int l = nameInstructions.length();
        for (int i = 0; i < l; i++) {
            char x = nameInstructions.charAt(0);
            switch (x) {
                case 'v':
                    name += getRandomElementFrom(vocals);
                    break;
                case 'c':
                    name += getRandomElementFrom(startConsonants);
                    break;
                case 'd':
                    name += getRandomElementFrom(endConsonants);
                    break;
            }
            nameInstructions = nameInstructions.substring(1);
        }
        return name;
    }
    
    private static String firstCharUppercase(String name){
        return Character.toString(name.charAt(0)).toUpperCase() + name.substring(1);
    }
    
    private static String getRandomElementFrom(List<String> v) {
        return v.get(randomInt(0, v.size() - 1));
    }
}