package project;

import java.util.concurrent.atomic.AtomicBoolean;

public class BookState {
    private static BookState instance;
    private AtomicBoolean isBook1Added;
    private AtomicBoolean isBook2Added;
    private AtomicBoolean isBook3Added;
    private AtomicBoolean isBook4Added;
    private AtomicBoolean isBook5Added;
    private AtomicBoolean isBook6Added;
    private AtomicBoolean isBook7Added;
    private AtomicBoolean isBook8Added;
    private AtomicBoolean isBook9Added;

    private BookState() {
        isBook1Added = new AtomicBoolean(false);
        isBook2Added = new AtomicBoolean(false);
        isBook3Added = new AtomicBoolean(false);
        isBook4Added = new AtomicBoolean(false);
        isBook5Added = new AtomicBoolean(false);
        isBook6Added = new AtomicBoolean(false);
        isBook7Added = new AtomicBoolean(false);
        isBook8Added = new AtomicBoolean(false);
        isBook9Added = new AtomicBoolean(false);
    }

    public static synchronized BookState getInstance() {
        if (instance == null) {
            instance = new BookState();
        }
        return instance;
    }

    public boolean isBook1Added() {
        return isBook1Added.get();
    }
    public boolean isBook2Added() {
        return isBook2Added.get();
    }
    public boolean isBook3Added() {
        return isBook3Added.get();
    }
    public boolean isBook4Added() {
        return isBook4Added.get();
    }
    public boolean isBook5Added() {
        return isBook5Added.get();
    }
    public boolean isBook6Added() {
        return isBook6Added.get();
    }
    public boolean isBook7Added() {
        return isBook7Added.get();
    }
    public boolean isBook8Added() {
        return isBook8Added.get();
    }
    public boolean isBook9Added() {
        return isBook9Added.get();
    }

    public boolean addBook1() {
        return isBook1Added.compareAndSet(false, true);
    }
    public boolean addBook2() {
        return isBook2Added.compareAndSet(false, true);
    }
    public boolean addBook3() {
        return isBook3Added.compareAndSet(false, true);
    }
    public boolean addBook4() {
        return isBook4Added.compareAndSet(false, true);
    }
    public boolean addBook5() {
        return isBook5Added.compareAndSet(false, true);
    }
    public boolean addBook6() {
        return isBook6Added.compareAndSet(false, true);
    }
    public boolean addBook7() {
        return isBook7Added.compareAndSet(false, true);
    }
    public boolean addBook8() {
        return isBook8Added.compareAndSet(false, true);
    }
    public boolean addBook9() {
        return isBook9Added.compareAndSet(false, true);
    }
}