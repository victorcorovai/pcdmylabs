import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PCDlab1 extends JFrame {
    private JTextField inputField;
    private JTextArea resultArea;

    public PCDlab1() {
        // Настройки главного окна
        setTitle("PCD Lab 1 - Обработка массива");
        setSize(550, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Центрирование окна на экране
        setLayout(new BorderLayout(10, 10));

        // Верхняя панель для ввода данных
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        topPanel.add(new JLabel("Массив (через запятую):"));

        inputField = new JTextField("2, 5, 8, 11, 4, 13, 6, 15", 20);
        topPanel.add(inputField);

        JButton calculateButton = new JButton("Рассчитать");
        topPanel.add(calculateButton);

        add(topPanel, BorderLayout.NORTH);

        // Центральная область для вывода результатов с прокруткой
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        // Обработка нажатия кнопки
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processArray();
            }
        });

        // Запуск расчета для дефолтного массива при старте
        processArray();
    }

    private void processArray() {
        try {
            // Читаем строку из поля ввода и разбиваем на числа
            String text = inputField.getText();
            String[] parts = text.split(",");
            int[] arr = new int[parts.length];
            for (int i = 0; i < parts.length; i++) {
                arr[i] = Integer.parseInt(parts[i].trim());
            }

            StringBuilder sb = new StringBuilder();
            sb.append("Исходный массив: ");
            for (int num : arr) {
                sb.append(num).append(" ");
            }
            sb.append("\n\n");

            // Список для хранения индексов нечетных чисел
            List<Integer> oddPositions = new ArrayList<>();

            // Поиск с последнего элемента (обратный порядок)
            for (int i = arr.length - 1; i >= 0; i--) {
                if (arr[i] % 2 != 0) {
                    oddPositions.add(i); // Сохраняем позицию (индекс)
                }
            }

            sb.append("Найденные позиции нечетных чисел (с конца): ").append(oddPositions).append("\n\n");
            sb.append("Суммы позиций два по два:\n");

            // Суммируем позиции по две
            for (int i = 0; i < oddPositions.size(); i += 2) {
                if (i + 1 < oddPositions.size()) {
                    int sum = oddPositions.get(i) + oddPositions.get(i + 1);
                    sb.append("Позиции ").append(oddPositions.get(i))
                            .append(" + ").append(oddPositions.get(i + 1))
                            .append(" = ").append(sum).append("\n");
                } else {
                    sb.append("Одиночная позиция без пары: ").append(oddPositions.get(i)).append("\n");
                }
            }

            // Выводим результат в текстовое поле интерфейса
            resultArea.setText(sb.toString());

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Ошибка ввода! Убедитесь, что числа разделены запятыми.",
                    "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Запуск интерфейса в потоке обработки событий Swing
        SwingUtilities.invokeLater(() -> {
            new PCDlab1().setVisible(true);
        });
    }
}
