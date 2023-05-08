package lab7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;

    public class InputOutput {
        ArrayList<Book> books;
        Logic logic;

        public InputOutput() {

            logic = new Logic();

        }
        public void printBook(List<Book> books) {
            for (Book book : books){
                System.out.println(book);
            }
        }

        public void printFilterNameSortYear(List<Book> books) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введіть автора");
            String au = scanner.nextLine();
            System.out.println(au);
            printBook(logic.filterNameSortYear(books, au));
        }

        public void printFilterEdition(List<Book> books) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введіть видавництво");
            String ed = scanner.nextLine();
            printBook(logic.filterEdition(books, ed));
        }
        public void printFilterYear(List<Book> books) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введіть рік");
            int year = scanner.nextInt();
            printBook(logic.filterYear(books, year));
        }

        public void printAuthorAlphabet(List<Book> books) {
            System.out.println(Arrays.toString(new List[]{logic.sortAuthor(books)}));
        }
    }

