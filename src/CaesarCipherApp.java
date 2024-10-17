import java.util.Scanner;
import java.io.*;
public class CaesarCipherApp {
    private CaesarCipher cipher;
    private FileHandler fileHandler;
    private Validator validator;

    public CaesarCipherApp() {
        cipher = new CaesarCipher();
        fileHandler = new FileHandler();
        validator = new Validator();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Выберите режим:");
            System.out.println("1. Шифрование");
            System.out.println("2. Расшифровка");
            System.out.println("3. Выход");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    encryptMode(scanner);
                    break;
                case "2":
                    decryptMode(scanner);
                    break;
                case "3":
                    System.out.println("Выход.");
                    return;
                default:
                    System.out.println("Некорректный выбор. Попробуйте снова.");
            }
        }
    }

    private void encryptMode(Scanner scanner) {
        System.out.println("Введите путь к файлу для шифрования:");
        String filePath = scanner.nextLine();

        if (!validator.fileExists(filePath)) {
            System.out.println("Файл не найден.");
            return;
        }

        System.out.println("Введите ключ:");
        String keyInput = scanner.nextLine();
        if (!validator.isValidKey(keyInput)) {
            System.out.println("Некорректный ключ.");
            return;
        }

        int key = Integer.parseInt(keyInput);

        try {
            String content = fileHandler.readFile(filePath);
            String encrypted = cipher.encrypt(content, key);

            System.out.println("Введите путь для сохранения зашифрованного файла:");
            String outputFilePath = scanner.nextLine();
            fileHandler.writeFile(outputFilePath, encrypted);
            System.out.println("Файл успешно зашифрован и сохранён.");
        } catch (IOException e) {
            System.out.println("Ошибка при работе с файлами: " + e.getMessage());
        }
    }

    private void decryptMode(Scanner scanner) {
        System.out.println("Введите путь к файлу для расшифровки:");
        String filePath = scanner.nextLine();

        if (!validator.fileExists(filePath)) {
            System.out.println("Файл не найден.");
            return;
        }

        System.out.println("Введите ключ:");
        String keyInput = scanner.nextLine();
        if (!validator.isValidKey(keyInput)) {
            System.out.println("Некорректный ключ.");
            return;
        }

        int key = Integer.parseInt(keyInput);

        try {
            String content = fileHandler.readFile(filePath);
            String decrypted = cipher.decrypt(content, key);

            System.out.println("Введите путь для сохранения расшифрованного файла:");
            String outputFilePath = scanner.nextLine();
            fileHandler.writeFile(outputFilePath, decrypted);
            System.out.println("Файл успешно расшифрован и сохранён.");
        } catch (IOException e) {
            System.out.println("Ошибка при работе с файлами: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        CaesarCipherApp app = new CaesarCipherApp();
        app.start();
    }
}
