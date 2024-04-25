import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Ass6a {
    public static void main(String[] args) {
        LibFacade facade = new LibFacade();
        facade.addBook(new Book("1", "Kemel Adam", "Kairat Zholdybaiuly"));
        facade.addBook(new Book("2", "Abai zholy", "Mukhtar Auezov"));

        System.out.println("Searching by author 'Mukhtar Auezov':");
        List<Book> booksByBloch = facade.searchByAuthor("Mukhtar Auezov");
        booksByBloch.forEach(book -> System.out.println(book.getTitle()));

        System.out.println("\nBorrowing 'Kemel Adam");
        boolean borrowResult = facade.borrow("1", "user1");
        System.out.println("Borrow Successful: " + borrowResult);
        System.out.println("Availability of 'Kemel Adam': " + facade.checkAvailability("1"));

    }

    static class LibFacade {
        private InvSys invSys = new InvSys();
        private UserSys userSys = new UserSys();

        public void addBook(Book book) {
            invSys.addBook(book);
        }

        public List<Book> searchByAuthor(String author) {
            return invSys.findBooksByAuthor(author);
        }

        public boolean borrow(String bookId, String userId) {
            if (userSys.isValidUser(userId) && invSys.isAvailable(bookId)) {
                return invSys.checkOut(bookId);
            }
            return false;
        }

        public boolean returnBook(String bookId, String userId) {
            if (userSys.isValidUser(userId)) {
                return invSys.returnBook(bookId);
            }
            return false;
        }

        public boolean checkAvailability(String bookId) {
            return invSys.isAvailable(bookId);
        }
    }

    static class InvSys {
        private Map<String, Book> books = new HashMap<>();

        public void addBook(Book book) {
            books.put(book.getId(), book);
        }

        public List<Book> findBooksByAuthor(String author) {
            return books.values().stream().filter(book -> book.getAuthor().equals(author)).collect(Collectors.toList());
        }

        public boolean checkOut(String bookId) {
            Book book = books.get(bookId);
            if (book != null && !book.isCheckedOut()) {
                book.setCheckedOut(true);
                return true;
            }
            return false;
        }

        public boolean returnBook(String bookId) {
            Book book = books.get(bookId);
            if (book != null && book.isCheckedOut()) {
                book.setCheckedOut(false);
                return true;
            }
            return false;
        }

        public boolean isAvailable(String bookId) {
            Book book = books.get(bookId);
            return book != null && !book.isCheckedOut();
        }
    }

    static class UserSys {
        private Map<String, Boolean> users = new HashMap<>();

        UserSys() {
            users.put("user1", true); // Valid user
            users.put("user2", false); // Invalid user
        }

        public boolean isValidUser(String userId) {
            return users.getOrDefault(userId, false);
        }
    }

    static class Book {
        private String id;
        private String title;
        private String author;
        private boolean isCheckedOut;

        Book(String id, String title, String author) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.isCheckedOut = false;
        }

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public boolean isCheckedOut() {
            return isCheckedOut;
        }

        public void setCheckedOut(boolean checkedOut) {
            isCheckedOut = checkedOut;
        }
    }
}
