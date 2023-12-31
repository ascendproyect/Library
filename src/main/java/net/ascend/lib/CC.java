package net.ascend.lib;

import net.ascend.lib.color.IridiumColorAPI;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Leandro Figueroa (LeandroSSJ)
 * viernes, abril 02, 2021
 */

@UtilityClass
public class CC {

    public static final char BOX;
    public static final char CHAR;

    public static final char STICK;
    public static final char HEART;

    public static final char ARROW_LEFT;
    public static final char ARROW_RIGHT;

    static {
        BOX = '\u2588';
        CHAR = '\u00A7';

        STICK = '\u2503';
        HEART = '\u2764';

        ARROW_LEFT = '\u00AB';
        ARROW_RIGHT = '\u00BB';
    }

    public static String translate(String input) {
        return IridiumColorAPI.process(input);
    }

    public static List<String> translate(List<String> input) {
        return input.stream().map(CC::translate).collect(Collectors.toList());
    }

    public static String capitalize(String string) {
        return String.valueOf(string.charAt(0)).toUpperCase() + string.substring(1);
    }
}
