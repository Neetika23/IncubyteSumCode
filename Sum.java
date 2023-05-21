import java.util.ArrayList;
import java.util.List;

public class StringCalculator {
    public static int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        String delimiter = ",|\n";
        String numbersWithoutDelimiter = numbers;

        if (numbers.startsWith("//")) {
            int delimiterIndex = numbers.indexOf("//") + 2;
            delimiter = numbers.substring(delimiterIndex, delimiterIndex + 1);
            numbersWithoutDelimiter = numbers.substring(numbers.indexOf("\n") + 1);
        }

        return sum(numbersWithoutDelimiter, delimiter);
    }

    private static int sum(String numbers, String delimiter) {
        String[] numberArray = numbers.split(delimiter);
        List<Integer> negativeNumbers = new ArrayList<>();
        int totalSum = 0;

        for (String number : numberArray) {
            int num = Integer.parseInt(number);

            if (num < 0) {
                negativeNumbers.add(num);
            }

            totalSum += num;
        }

        if (!negativeNumbers.isEmpty()) {
            throw new IllegalArgumentException("Negatives not allowed: " + negativeNumbers);
        }

        return totalSum;
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(add(""));// Output: 0
        System.out.println(add("1"));// Output: 1
        System.out.println(add("1,2"));// Output: 3
        System.out.println(add("1\n2,3"));// Output: 6
        System.out.println(add("//;\n1;2"));// Output: 3
        System.out.println(add("-1,2"));// Throws exception: Negatives not allowed: [-1]
    }
}
