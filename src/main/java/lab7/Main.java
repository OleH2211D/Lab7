package lab7;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    List<Book> books;
    String file;
    Logic logic = new Logic();
    InputOutput printAll = new InputOutput();
    Json json = new Json();

    public static void main(String[] args) {
        Main prog = new Main();
        prog.run();
    }

    private void run() {
        while (true) {
            System.out.println("1: Додати запис до файлу");
            System.out.println("2: Вивести зміст файлу на екран");
            System.out.println("3: Вивести список книг заданого автора в порядку зростання року видання");
            System.out.println("4: Вивести список книг, що видані заданим видавництвом");
            System.out.println("5: Вивести список книг, що випущені після заданого року");
            System.out.println("6: Вивести список авторів в алфавітному порядку");
            System.out.println("7: Додати запис до файлу JSON");
            System.out.println("8: Вивести зміст файлу JSON на екран");
            System.out.println("9: Видалити дані з файлу");
            System.out.println("0: Вийти з програми");
            Scanner sc = new Scanner(System.in);
            int k = sc.nextInt();
            int q;
            File file1 = null;
            switch (k) {
                case 0:
                    System.out.println("Роботу завершенно");
                    System.exit(0);
                    break;
                case 1:
                    System.out.println("Введіть кількість книг");
                    q = sc.nextInt();
                    List<Book> temp = new ArrayList<>();

                    for (int i = 0; i < q; i++) {
                        temp.add(logic.scannerBook(sc));
                    }
                    System.out.println("Введіть назву файлу");
                    String fileName = sc.next();
                    Path path = Paths.get(fileName);
                    if (!Files.exists(path)) {
                        logic.writeToFile(path, temp);
                    } else {
                        logic.addToFile(path, temp);
                    }
                    break;
                case 2:
                    System.out.println("Введіть назву файлу");
                    file = sc.next();
                    System.out.println("Зміст файлу");
                    books = logic.readFromFile(file);
                    printAll.printBook(books);
                    break;
                case 3:
                    System.out.println("Введіть назву файлу");
                    file = sc.next();
                    books = logic.readFromFile(file);
                    printAll.printFilterNameSortYear(books);
                    break;
                case 4:
                    System.out.println("Введіть назву файлу");
                    file = sc.next();
                    books = logic.readFromFile(file);
                    printAll.printFilterEdition(books);
                    break;
                case 5:
                    System.out.println("Введіть назву файлу");
                    file = sc.next();
                    books = logic.readFromFile(file);
                    printAll.printFilterYear(books);
                    break;
                case 6:
                    System.out.println("Введіть назву файлу");
                    file = sc.next();
                    books = logic.readFromFile(file);
                    printAll.printAuthorAlphabet(books);
                    break;
                case 7:
                    System.out.println("Введіть кількість книг");
                    q = sc.nextInt();
                    temp = logic.addToList(q,sc);
                    System.out.println("Введіть назву файлу");
                    fileName = sc.next();
                    file1 = new File(fileName);
                    if (!Files.exists(file1.toPath())) {
                        json.writeToFileJson(file1, temp);
                    }
                    break;
                case 8:
                    System.out.println("Введіть назву файлу");
                    file = sc.next();
                    System.out.println("Зміст файлу");
                    books = json.readFromFileJson(new File(file));
                    printAll.printBook(books);
                    break;
                case 9:
                    System.out.println("Введіть назву файлу:");
                    String file = sc.next();
                    books = logic.readFromFile(file);
                    System.out.println("Введіть ім’я автора, книги якого ви хочете видалити:");
                    String authorName = sc.next();
                    books.removeIf(book -> book.getAuthor().equals(authorName));
                    logic.writeToFile(Paths.get(file), books);
                    System.out.println("Книги автора " + authorName + " було видалено з файлу.");
                    break;

            }
        }
    }
}