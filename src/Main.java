import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные (Фамилия Имя Отчество датарождения номертелефона пол):");

        try {
            String input = scanner.nextLine();
            String[] inputData = input.split(" ");

            // Проверяем количество введенных данных
            if (inputData.length != 6) {
                throw new IllegalArgumentException("Неверное количество данных. Введите все данные в требуемом формате.");
            }

            String lastName = inputData[0];
            String firstName = inputData[1];
            String middleName = inputData[2];
            String birthDate = inputData[3];
            long phoneNumber = Long.parseLong(inputData[4]);
            char gender = inputData[5].charAt(0);

            // Проверяем форматы данных
            if (!isValidBirthDate(birthDate) || (gender != 'f' && gender != 'm')) {
                throw new IllegalArgumentException("Неверный формат данных. Проверьте дату рождения и пол.");
            }

            // Создаем файл с именем фамилии
            File file = new File(lastName + ".txt");
            PrintWriter writer = new PrintWriter(file);

            // Записываем данные в файл
            writer.printf("%s %s %s %s %d %c%n", lastName, firstName, middleName, birthDate, phoneNumber, gender);

            writer.close();
            System.out.println("Данные успешно записаны в файл " + file.getName());
        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    // Метод для проверки формата даты рождения
    private static boolean isValidBirthDate(String date) {
        try {
            String[] parts = date.split("\\.");
            if (parts.length != 3) {
                return false;
            }
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);
            return day >= 1 && day <= 31 && month >= 1 && month <= 12 && year >= 1900;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}