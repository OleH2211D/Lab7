package lab7;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.*;

public class Logic {
    public Logic(){

    }
    public void writeToFile(Path file, List<Book> books) {
        try (BufferedWriter bfr = Files.newBufferedWriter(file)) {
            for (Book book : books) {
                bfr.write(book.createString());
            }
        } catch (IOException ignored) {
        }
    }

    public Book scannerBook(Scanner s) {
        System.out.println("Введіть id");
        int id = s.nextInt();
        s = new Scanner(System.in);
        System.out.println("Введіть назву");
        String name = s.nextLine();
        System.out.println("Введіть автора");
        String author = s.nextLine();
        System.out.println("Введіть видавництво");
        String edition = s.nextLine();
        System.out.println("Введіть дату видання");
        LocalDate date = LocalDate.parse(s.next());
        System.out.println("Введіть кількість сторінок");
        int pages = s.nextInt();
        System.out.println("Введіть ціну");
        double price = s.nextDouble();

        return new Book(id, name, author, edition, date, pages, price);
    }

    public void addToFile(Path path, List<Book> books) {
        try (BufferedWriter bfr = Files.newBufferedWriter(path, StandardOpenOption.APPEND)) {
            for (Book book : books) {
                bfr.write(book.createString());
            }
        } catch (IOException ignored) {
        }

    }
    public List<Book> addToList(int q,Scanner s) {
        List<Book> books = new ArrayList<>();

        for (int i = 0; i < q; i++) {
            books.add(scannerBook(s));
        }
        return books;
    }
    public List<Book> readFromFile(String file) {
        List<Book> books = new ArrayList<>();
        int i = 0;
        String str = "";
        Path path = Paths.get(file);
        try {
            Scanner scanner = new Scanner(path);
            while (scanner.hasNext()) {
                str = scanner.nextLine();
                String[] strs = str.trim().split("\\|");
                int id = Integer.parseInt(strs[0]);
                String name = strs[1];
                String author = strs[2];
                String edition = strs[3];
                LocalDate date = LocalDate.parse(strs[4]);
                int pages = Integer.parseInt(strs[5]);
                double price = Double.parseDouble(strs[6]);
                books.add( new Book(id, name, author, edition, date, pages, price));
                i++;
            }

        } catch (IOException ignored) {
        }

        return books;
    }
    public List<Book> filterNameSortYear(List<Book> books, String author) {//список книг заданого автора в порядку зростання року видання;
        List<Book> temp = new ArrayList<>();

        for (Book book : books) {
            if (Objects.equals(book.getAuthor(), author)) {
                temp.add(book);
            }
        }

        Collections.sort(temp, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return Integer.compare(o1.getDate().getYear(), o2.getDate().getYear());
            }
        });

        return temp;
    }

    public List<Book> filterEdition(List<Book> books, String edition) {//список книг, що видані заданим видавництвом;
        List<Book> temp = new ArrayList<>();

        for (Book book : books) {
            if (Objects.equals(book.getEdition(), edition)) {
                temp.add(book);
            }
        }

        return temp;
    }

    public List<Book> filterYear(List<Book> books, int year) {//список книг, що випущені після заданого року;
        List<Book> temp = new ArrayList<>();

        for (Book book : books) {
            if (book.getDate().isAfter(LocalDate.of(year, 12, 31))) {
                temp.add(book);
            }
        }

        return temp;
    }


    public List<String> sortAuthor(List<Book> books) {//список авторів в алфавітному порядку
        List<String> temp = new ArrayList<>();

        for (Book book : books) {
            String author = book.getAuthor();

            if (!temp.contains(author)) {
                temp.add(author);
            }
        }

        Collections.sort(temp);

        return temp;
    }

}






