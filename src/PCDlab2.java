public class PCDlab2 {
    public static void main(String[] args) {
        // Главная группа потоков (Main)
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();

        // Создаем подгруппу G7 внутри Main
        ThreadGroup groupG7 = new ThreadGroup(mainGroup, "G7");

        // Создаем подгруппу G3 внутри G7
        ThreadGroup groupG3 = new ThreadGroup(groupG7, "G3");

        // Создаем подгруппу G2 внутри Main
        ThreadGroup groupG2 = new ThreadGroup(mainGroup, "G2");

        // --- Создание потоков в группе G3 ---
        Thread tha = new Thread(groupG3, "Tha");
        tha.setPriority(6);

        Thread thb = new Thread(groupG3, "Thb");
        thb.setPriority(3);

        Thread thc = new Thread(groupG3, "Thc");
        thc.setPriority(6);

        Thread thd = new Thread(groupG3, "Thd");
        thd.setPriority(3);

        // --- Создание потоков в группе G7 ---
        Thread thA = new Thread(groupG7, "ThA");
        thA.setPriority(7);

        Thread thB = new Thread(groupG7, "ThB");
        thB.setPriority(6);

        // --- Создание потока Th2 в группе Main ---
        Thread th2Main = new Thread(mainGroup, "Th2");
        th2Main.setPriority(3);

        // --- Создание потоков в группе G2 ---
        Thread th1G2 = new Thread(groupG2, "Th1");
        th1G2.setPriority(7);

        Thread th2G2 = new Thread(groupG2, "Th2");
        th2G2.setPriority(3);

        Thread th3G2 = new Thread(groupG2, "Th3");
        th3G2.setPriority(3);

        // Рекурсивный вывод информации о всех потоках и группах, начиная с главной
        printGroupInfo(mainGroup, 0);
    }

    // Рекурсивный метод для обхода и вывода информации о группах и потоках
    private static void printGroupInfo(ThreadGroup group, int indent) {
        String indentation = "  ".repeat(indent);
        System.out.println(indentation + "Группа потоков: " + group.getName());

        // Получаем все потоки в текущей группе
        Thread[] threads = new Thread[group.activeCount()];
        int count = group.enumerate(threads, false);

        for (int i = 0; i < count; i++) {
            Thread t = threads[i];
            if (t != null) {
                System.out.println(indentation + "  -> Поток: [Имя: " + t.getName() +
                        ", Группа: " + t.getThreadGroup().getName() +
                        ", Приоритет: " + t.getPriority() + "]");
            }
        }

        // Получаем все подгруппы
        ThreadGroup[] subGroups = new ThreadGroup[group.activeGroupCount()];
        int groupCount = group.enumerate(subGroups, false);

        for (int i = 0; i < groupCount; i++) {
            if (subGroups[i] != null) {
                printGroupInfo(subGroups[i], indent + 1);
            }
        }
    }
}