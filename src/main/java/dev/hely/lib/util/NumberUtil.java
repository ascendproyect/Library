package dev.hely.lib.util;

public class NumberUtil {

    public static Integer getInt(String string) {
        try {
            return Integer.parseInt(string);
        }
        catch (IllegalArgumentException ex) {
            return null;
        }
    }

    public static boolean isInt(String sInt) {
        try {
            Integer.parseInt(sInt);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

}
