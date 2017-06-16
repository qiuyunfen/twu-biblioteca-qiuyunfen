package com.twu.biblioteca;

import com.twu.biblioteca.model.Book;
import com.twu.biblioteca.model.LibraryThing;
import com.twu.biblioteca.model.Movie;
import com.twu.biblioteca.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BibliotecaLib {
    public HashMap<String, String> HINT_INFO = new HashMap<String, String>();
    public HashMap<String, Integer> STATUS = new HashMap<String, Integer>();
    public HashMap<String, String> COMMAND = new HashMap<String, String>();
    public static int status ;
    public User curUser;
    public ArrayList<Book> books;
    public ArrayList<Movie> movies;
    public ArrayList<User> users;
    public String flag = "";

    public void init() {
        initHintInfo();
        initStatus();
        initCommand();
        books = initBooksList();
        movies = initMoviesList();
        users = initUsersList();
    }

    public void initHintInfo() {
        HINT_INFO.put("WELCOME_MESSAGE", ",Welcome to Biblioteca Library");
        HINT_INFO.put("LOGIN_FAIL", "please enter current account");
        HINT_INFO.put("MAIN_COMMAND", "1.List Books\n2.List Movies\n3.display information\n4.exit");
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
        COMMAND.put("DISPLAY_INFORMATION", "3");
        COMMAND.put("EXIT", "4");
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
            return processReturn(input);
        }
        return "";
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
            return getUnCheckOutBooksList() + getCheckoutBooksList("You have checked out:\n") + HINT_INFO.get("LIST_COMMAND") + "\n";
        } else if(input.equals(COMMAND.get("LIST_MOVIES"))) {
            flag = COMMAND.get("LIST_MOVIES");
            status = STATUS.get("LIST_THING");
            return getlistMovies() + getCheckoutMoviesList() + HINT_INFO.get("LIST_COMMAND") + "\n";
        } else if(input.equals(COMMAND.get("DISPLAY_INFORMATION"))) {
            status = STATUS.get("SIGN_IN");
            return displayUserInfo() +  HINT_INFO.get("MAIN_COMMAND") + "\n";
        } else if(input.equals(COMMAND.get("EXIT"))) {
            return HINT_INFO.get("EXIT");
        }
        return "";
    }

    private String processReturn(String input) {
        if(flag.equals(COMMAND.get("LIST_BOOKS"))) {
            return returnBack(Integer.parseInt(input), books) +  HINT_INFO.get("MAIN_COMMAND") + "\n";
        } else {
            return returnBack(Integer.parseInt(input), movies) +  HINT_INFO.get("MAIN_COMMAND") + "\n";
        }
    }

    private String processCheckout(String input) {
        if(flag.equals(COMMAND.get("LIST_BOOKS"))) {
            return checkOut(Integer.parseInt(input), books) + HINT_INFO.get("MAIN_COMMAND") + "\n";
        } else {
            return checkOut(Integer.parseInt(input), movies) + HINT_INFO.get("MAIN_COMMAND") + "\n";
        }
    }

    public ArrayList<Book> initBooksList() {
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(new Book(1,"Test-driven Development: By Example", "Kent Beck", 2003));
        books.add(new Book(2,"Head First Java", "Sierra k", 2007));
        ArrayList<String> checkOutUsers = books.get(0).getCheckOutUser();
        checkOutUsers.add("Lucy");
        return books;
    }

    public ArrayList<User> initUsersList() {
        ArrayList<User> users = new ArrayList<User>();
        users.add(new User("Lucy", "531802979@qq.com", "swust", "18980131432","123-4567","111"));
        users.add(new User("Tom", "qwiud@swust.edu.cn", "swust", "12345678910","234-5678","111"));
        return users;
    }

    public ArrayList<Movie> initMoviesList() {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        movies.add(new Movie(1,"Wonder Woman", 2017, "Patty Jenkins", 7.3));
        return movies;
    }

    public String getCheckoutBooksList(String msg) {
        ArrayList<Book> checkoutBooks = getCheckoutList(books);
        return generateBookStr(checkoutBooks, msg);
    }

    public String generateBookStr(ArrayList<Book> checkoutBooks, String message) {
        String msg = message;
        for(Book book : checkoutBooks) {
            msg += book.getId() + ":bookName:" + book.getName()  + ",author:" + book.getAuthor() + ",year:" + book.getYear() + "\n";
        }
        return msg;
    }

    public <T extends LibraryThing> ArrayList<T> getCheckoutList(ArrayList<T> things) {
        ArrayList<T> lists = new ArrayList<T>();
        for(T thing: things) {
            if(thing.getCheckOutUser().size() > 0 && thing.getCheckOutUser().contains(curUser.getName())) {
                lists.add(thing);
            }
        }
        return lists;
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

    public String getlistMovies() {
        String msg = "List Movies:\n";
        for(Movie movie : movies) {
            msg += movie.getId() + ":movieName:" + movie.getName()  + ",director:" + movie.getDirector() + ",year:" + movie.getYear() + ",rating:" +
                    movie.getRating() + "\n";
        }
        return msg;
    }

    public String getCheckoutMoviesList() {
        String msg = "You have checked out:\n";
        for(Movie movie : movies) {
            if(movie.getCheckOutUser().size() > 0 && movie.getCheckOutUser().contains(curUser.getName())) {
                msg += movie.getId() + ":movieName:" + movie.getName()  + ",director:" + movie.getDirector() + ",year:" + movie.getYear() + ",rating:" +
                                movie.getRating() + "\n";
            }
        }
        return msg;
    }


    public <T extends LibraryThing> String returnBack(int id, ArrayList<T> list) {
        for(T thing: list) {
            if(thing.getId() == id && thing.getCheckOutUser().size() > 0 && thing.getCheckOutUser().get(0).equals(curUser.getName())) {
                thing.returnBack(curUser.getName());
                return HINT_INFO.get("RETURN_SUCCESS") + "\n";
            }
        }
        return HINT_INFO.get("RETURN_FAIL") + "\n";
    }

    public <T extends LibraryThing> String checkOut(int id, ArrayList<T> list) {
        for(T thing: list) {
            if(id == thing.getId() && thing.getCheckOutUser().size() == 0) {
                thing.checkOut(curUser.getName());
                return HINT_INFO.get("CHECK_OUT_SUCCESS") + "\n";
            }
        }
        return HINT_INFO.get("CHECK_OUT_FAIL") + "\n";
    }

    public String printWelcomeMsg() {
        return curUser.getName() + HINT_INFO.get("WELCOME_MESSAGE") + "\n";
    }

    public User userSignIn(String account, String password) {
       for(User cuser: users) {
           if(cuser.getAccount().equals(account) && cuser.getPassword().equals(password)) {
               return cuser;
           }
       }
       return null;
    }

    public String handleUserSignIn(String input) {
        String[] infos = input.split(",");
        curUser = userSignIn(infos[0], infos[1]);
        if(curUser != null) {
            return printWelcomeMsg();
        } else {
            return HINT_INFO.get("LOGIN_FAIL");
        }
    }

    public String displayUserInfo() {
        return curUser.getName()+","+curUser.getEmail()+","+curUser.getAddress()+","+curUser.getPhoneNumber() + "\n";
    }
}
