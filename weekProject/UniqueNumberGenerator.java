package weekProject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class UniqueNumberGenerator {
    public static void main(String[] args) {
        String uniqueNumber = generateUniqueNumber();
        System.out.println("랜덤 숫자: " + uniqueNumber);
    }

    public static String generateUniqueNumber() {
        List<Integer> digits = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            digits.add(i);
        }
        Collections.shuffle(digits);

        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 16; i++) {
            sb.append(digits.get(random.nextInt(10)));
        }

        return sb.toString();
    }
}