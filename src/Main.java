import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[] words = {"АЗБУКА", "КОМПЬЮТЕР", "ПРОГРАММИРОВАНИЕ", "АВТОМОБИЛЬ", "КОФЕМАШИНА","ХЛЕБ" ,"КУРИЦА","МОЛОКО","КРЫСА","МОЛОТОК","ПРЕДОХРАНИТЕЛЬ",};
        Random randomWord = new Random();
        int randomIndex = randomWord.nextInt(words.length);
        String word = words[randomIndex];

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        StringBuilder guessedLetters = new StringBuilder();
        int errorCount = 0;

        System.out.print("Слово состоит из " + word.length() + " букв: ");
        for (int i = 0; i < word.length(); i++) {
            System.out.print("* ");
        }
        System.out.println();

        while (errorCount < 3) {
            try {
                System.out.print("Введите букву: ");
                String input = scanner.nextLine().toUpperCase();

                if (input.length() != 1) {
                    System.out.println("Введите только одну букву!");
                    continue;
                }

                char letter = input.charAt(0);

                if (guessedLetters.toString().contains(String.valueOf(letter))) {
                    System.out.println("Вы уже вводили эту букву!");
                    continue;
                }

                boolean correctGuess = false;
                StringBuilder displayWord = new StringBuilder();

                for (int i = 0; i < word.length(); i++) {
                    if (word.charAt(i) == letter) {
                        displayWord.append(letter).append(" ");
                        guessedLetters.append(letter);
                        correctGuess = true;
                    } else if (guessedLetters.toString().contains(String.valueOf(word.charAt(i)))) {
                        displayWord.append(word.charAt(i)).append(" ");
                    } else {
                        displayWord.append("* ");
                    }
                }

                if (!correctGuess) {
                    if (random.nextInt(1, 4) == 3) {
                        char randomLetter;
                        do {
                            randomLetter = word.charAt(random.nextInt(0, word.length()));
                        } while (guessedLetters.toString().contains(String.valueOf(randomLetter)));

                        System.out.println("Радуйтесь, вам повезло! Я даю вам подсказку, и это буква " + randomLetter);
                    } else {
                        errorCount++;
                        System.out.println("Неправильная буква! У вас осталось " + (3 - errorCount) + " попытки");
                    }
                }

                if (!displayWord.toString().contains("*")) {
                    System.out.println(displayWord);
                    System.out.println("Вы выиграли!");
                    return;
                } else {
                    System.out.println(displayWord);
                }
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Значение не должно быть пустым!");
            }
        }
        System.out.println("Вы проиграли!");
    }
}
