import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class main {
    private static final Map<Character, Integer> romanNumerals = new HashMap<>() {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};
    public static String calc(String input) throws Exception {
        String[] tokens = input.trim().split("\\s+");
        if (tokens.length != 3) {
            throw new Exception("throws Exception");
        }

        String num1Str = tokens[0];
        String num2Str = tokens[2];
        char op = tokens[1].charAt(0);

        int num1, num2;
        boolean isRomanNumeral = false;

        try {
            num1 = Integer.parseInt(num1Str);
            num2 = Integer.parseInt(num2Str);
        } catch (NumberFormatException e) {
            if (isRomanNumeral(num1Str) && isRomanNumeral(num2Str)) {
                num1 = romanToArabic(num1Str);
                num2 = romanToArabic(num2Str);
                isRomanNumeral = true;
            } else {
                throw new Exception("throws Exception");
            }
        }

        if (num1 < 1 || num1 > 10 || num2 < 1 || num2 > 10) {
            throw new Exception("throws Exception");
        }

        int result;
        switch (op) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num1 / num2;
                break;
            default:
                throw new Exception("throws Exception");
        }

        if (isRomanNumeral) {
            if (result < 1) {
                throw new Exception("throws Exception");
            }
            return arabicToRoman(result);
        } else {
            return String.valueOf(result);
        }
    }

    private static boolean isRomanNumeral(String str) {
        for (char c : str.toCharArray()) {
            if (!romanNumerals.containsKey(c)) {
                return false;
            }
        }
        return true;
    }

    private static int romanToArabic(String roman) {
        int result = 0;
        int prev = 0;
        for (int i = roman.length() - 1; i >= 0; i--) {
            int value = romanNumerals.get(roman.charAt(i));
            if (value < prev) {
                result -= value;
            } else {
                result += value;
            }
            prev = value;
        }
        return result;
    }

    private static String arabicToRoman(int arabic) {
        if (arabic < 1 || arabic > 3999) {
            throw new IllegalArgumentException("throws Exception");
        }

        StringBuilder result = new StringBuilder();
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] numerals = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        for (int i = 0; i < values.length; i++) {
            while (arabic >= values[i]) {
                result.append(numerals[i]);
                arabic -= values[i];
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter: ");
            String input = scanner.nextLine();
            if (input.equals("exit")) {
                break;
            }
            try {
                String result = calc(input);
                System.out.println(result);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
