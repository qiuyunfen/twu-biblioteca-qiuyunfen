package com.twu.biblioteca;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.LibraryThing;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.model.User;

import java.util.ArrayList;
import java.util.HashMap;

public class BibliotecaLib {
    public HashMap<String, String> HINT_INFO = new HashMap<String, String>();
    public HashMap<String, Integer> STATUS = new HashMap<String, Integer>();
    public HashMap<String, String> COMMAND = new HashMap<String, String>();
    public static int status ;
    public User user;
    public ArrayList<Book> books;
    public ArrayList<Movie> movies;
    public String flag = "";

    public void init() {
        initHintInfo();
        initStatus();
        initCommand();
        books = initBooksList();
        movies = initMoviesList();
    }

    public void initHintInfo() {
        HINT_INFO.put("WELCOME_MESSAGE", ",Welcome to Biblioteca Library");
        HINT_INFO.put("MAIN_COMMAND", "1.List Books\n2.List Movies\n3.exit");
        HINT_INFO.put("LIST_COMMAND","1.check out\n2.return");
        HINT_INFO.put("CHECK_OUT_ID", "please input the id you want to check out:");
        HINT_INFO.put("RETURN_ID", "please input the id you want to return:");
        HINT_INFO.put("CHECK_OUT_SUCCESS", "Thank you! Enjoy!");
        HINT_INFO.put("CHECK_OUT_FAIL", "That id is not available.");
        HINT_INFO.put("RETURN_SUCCESS", "Thank you for returning.");
        HINT_INFO.put("RETURN_FAIL", "That is not a valid id to return.");
        HINT_INFO.put("EXIT","quit");
    }


    public void initStatus() {
        STATUS.put("MAIN_DEFAULT", -1);
        STATUS.put("SIGN_IN", 0);
        STATUS.put("LIST_THING", 1);
        STATUS.put("CHECK_OUT", 2);
        STATUS.put("RETURN", 3);
        status = STATUS.get("MAIN_DEFAULT");
    }

    public void initCommand() {
        COMMAND.put("LIST_BOOKS", "1");
        COMMAND.put("LIST_MOVIES", "2");
        COMMAND.put("EXIT", "3");
        COMMAND.put("CHECK_OUT", "1");
        COMMAND.put("RETURM", "2");
    }

    public String handleInput(String input) {
        if(status == STATUS.get("MAIN_DEFAULT")) {
            status = STATUS.get("SIGN_IN");
            return handleUserSignIn(input)  + HINT_INFO.get("MAIN_COMMAND") + "\n";
        } else if(status == STATUS.get("SIGN_IN")) {
            return processSignInStatus(input);
        } else if(status == STATUS.get("LIST_THING")) {
            return  processStatus(input);
        } else if(status == STATUS.get("CHECK_OUT")) {
            status = STATUS.get("SIGN_IN");
            return processCheckout(input);
        } else if(status == STATUS.get("RETURN")) {
            status = STATUS.get("SIGN_IN");
            return returnBook(Integer.parseInt(input)) +  HINT_INFO.get("MAIN_COMMAND") + "\n";
        }
        return "";
    }

    private String processCheckout(String input) {
        if(flag.equals(COMMAND.get("LIST_BOOKS"))) {
            return checkoutBook(Integer.parseInt(input)) +  HINT_INFO.get("MAIN_COMMAND") + "\n";
        } else {
            return checkoutMovie(Integer.parseInt(input)) +  HINT_INFO.get("MAIN_COMMAND") + "\n";
        }
    }

    private String processStatus(String input) {
        if(input.equals(COMMAND.get("CHECK_OUT"))) {
            status = STATUS.get("CHECK_OUT");
            return HINT_INFO.get("CHECK_OUT_ID");
        } else if(input.equals(COMMAND.get("RETURM"))) {
            status = STATUS.get("RETURN");
            return HINT_INFO.get("RETURN_ID");
        }
        return null;
    }

    private String processSignInStatus(String input) {
        if(input.equals(COMMAND.get("LIST_BOOKS"))) {
            flag = COMMAND.get("LIST_BOOKS");
            status = STATUS.get("LIST_THING");
            return getUnCheckOutBooksList() + getCheckoutBooksList() + HINT_INFO.get("LIST_COMMAND") + "\n";
        } else if(input.equals(COMMAND.get("LIST_MOVIES"))) {
            flag = COMMAND.get("LIST_MOVIES");
            status = STATUS.get("LIST_THING");
            return getlistMovies() + getCheckoutMoviesList() + HINT_INFO.get("LIST_COMMAND") + "\n";
        } else if(input.equals(COMMAND.get("EXIT"))) {
            return HINT_INFO.get("EXIT");
        }
        return "";
    }

    public ArrayList<Book> initBooksList() {
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(new Book(1,"Test-driven Development: By Example", "Kent Beck", 2003));
        books.add(new Book(2,"Head First Java", "Sierra k", 2007));
        ArrayList<String> checkOutUsers = books.get(0).getCheckOutUser();
        checkOutUsers.add("Lucy");
        return books;
    }

    public String getUnCheckOutBooksList() {
        String msg = "List Books:\n";
        for(Book book : books) {
            if(book.getCheckOutUser().size() == 0) {
                msg += book.getId() + ":bookName:" + book.getName()  + ",author:" + book.getAuthor() + ",year:" + book.getYear() + "\n";
            }
        }
        return msg;
    }

    public String getCheckoutBooksList() {
        String msg = "The books you have checked out:\n";
        for(Book book : books) {
            if(book.getCheckOutUser().size() > 0 && book.getCheckOutUser().get(0).equals(user.getName())) {
                msg += book.getId() + ":bookName:" + book.getName()  + ",author:" + book.getAuthor() + ",year:" + book.getYear() + "\n";
            }
        }
        return msg;
    }

    public String getCheckoutMoviesList() {
        String msg = "You have checked out:\n";
        for(Movie movie : movies) {
            if(movie.getCheckOutUser().size() > 0 && movie.getCheckOutUser().contains(user.getName())) {
                msg += movie.getId() + ":movieName:" + movie.getName()  + ",director:" + movie.getDirector() + ",year:" + movie.getYear() + ",rating:" +
                                movie.getRating() + "\n";
            }
        }
        return msg;
    }

    public String returnBook(int bookId) {
        for(Book book: books) {
            if(book.getId() == bookId && book.getCheckOutUser().size() > 0 && book.getCheckOutUser().get(0).equals(user.getName())) {
                book.returnBack(user.getName());
                return HINT_INFO.get("RETURN_SUCCESS") + "\n";
            }
        }
        return HINT_INFO.get("RETURN_FAIL") + "\n";
    }

    public String returnMovie(int movieId) {
        for(Movie movie: movies) {
            if(movie.getId() == movieId && movie.getCheckOutUser().size() > 0 && movie.getCheckOutUser().get(0).equals(user.getName())) {
                movie.returnBack(user.getName());
                return HINT_INFO.get("RETURN_SUCCESS") + "\n";
            }
        }
        return HINT_INFO.get("RETURN_FAIL") + "\n";
    }

    public ArrayList<Movie> initMoviesList() {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        movies.add(new Movie(1,"Wonder Woman", 2017, "Patty Jenkins", 7.3));
        return movies;
    }

    public String getlistMovies() {
        String msg = "List Movies:\n";
        for(Movie movie : movies) {
            msg += movie.getId() + ":movieName:" + movie.getName()  + ",director:" + movie.getDirector() + ",year:" + movie.getYear() + ",rating:" +
                    movie.getRating() + "\n";
        }
        return msg;
    }

    public String checkoutBook(int bookId) {
        for(Book book: books) {
            if(bookId == book.getId() && book.getCheckOutUser().size() == 0) {
                book.checkOut(user.getName());
                return HINT_INFO.get("CHECK_OUT_SUCCESS") + "\n";
            }
        }
        return HINT_INFO.get("CHECK_OUT_FAIL") + "\n";
    }

    public String checkoutMovie(int movieId) {
        for(Movie movie: movies) {
            if(movieId == movie.getId()) {
                addUsertoCheckList(movie);
                return HINT_INFO.get("CHECK_OUT_SUCCESS") + "\n";
            }
        }
        return HINT_INFO.get("CHECK_OUT_FAIL") + "\n";
    }

    private void addUsertoCheckList(LibraryThing movie) {
        ArrayList<String> checkOutUsers = movie.getCheckOutUser();
        checkOutUsers.add(user.getName());
        movie.setCheckOutUser(checkOutUsers);
    }

    public String printWelcomeMsg() {
        return user.getName() + HINT_INFO.get("WELCOME_MESSAGE") + "\n";
    }

    public User userSignIn(String name) {
       user = new User(name);
       return user;
    }

    public String handleUserSignIn(String input) {
        user = userSignIn(input);
        return printWelcomeMsg();
    }
}
