import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PCDlab1 {
    public static void main(String[] args) {
        // Пример массива (вы можете изменить его или настроить ввод с клавиатуры)
        int[] arr = {2, 5, 8, 11, 4, 13, 6, 15};

        System.out.print("Исходный массив: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();

        // Список для хранения индексов нечетных чисел
        List<Integer> oddPositions = new ArrayList<>();

        // Начинаем поиск с последнего элемента (обратный порядок)
        for (int i = arr.length - 1; i >= 0; i--) {
            // Проверяем, является ли число нечетным
            if (arr[i] % 2 != 0) {
                oddPositions.add(i); // Сохраняем позицию (индекс)
            }
        }

        System.out.println("Найденные позиции нечетных чисел (с конца): " + oddPositions);

        // Суммируем позиции по две
        System.out.println("Суммы позиций два по два:");
        for (int i = 0; i < oddPositions.size(); i += 2) {
            if (i + 1 < oddPositions.size()) {
                int sum = oddPositions.get(i) + oddPositions.get(i + 1);
                System.out.println("Позиции " + oddPositions.get(i) + " + " + oddPositions.get(i + 1) + " = " + sum);
            } else {
                // Если осталась одна позиция без пары
                System.out.println("Одиночная позиция без пары: " + oddPositions.get(i));
            }
        }
    }
}