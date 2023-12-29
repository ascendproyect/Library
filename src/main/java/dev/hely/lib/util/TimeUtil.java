package dev.hely.lib.util;

import java.util.concurrent.TimeUnit;

public class TimeUtil {

    public static long parse(String input) {
        if (input == null || input.isEmpty()) {
            return -1L;
        }

        long result = 0L;
        StringBuilder number = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            if (Character.isDigit(c)) {
                number.append(c);
            } else {
                String str;

                if (Character.isLetter(c) && !(str = number.toString()).isEmpty()) {
                    result += convert(Integer.parseInt(str), c);
                    number = new StringBuilder();
                }
            }
        }

        return result;
    }

    private static long convert(int value, char unit) {
        switch (unit) {
            case 'y':
                return value * TimeUnit.DAYS.toMillis(365L);
            case 'M':
                return value * TimeUnit.DAYS.toMillis(30L);
            case 'd':
                return value * TimeUnit.DAYS.toMillis(1L);
            case 'h':
                return value * TimeUnit.HOURS.toMillis(1L);
            case 'm':
                return value * TimeUnit.MINUTES.toMillis(1L);
            case 's':
                return value * TimeUnit.SECONDS.toMillis(1L);
        }

        return -1L;
    }
}
