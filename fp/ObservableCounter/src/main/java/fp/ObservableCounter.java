package fp;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;
import java.util.function.Consumer;


/**
 * ObservableCounter is a counter that can be
 * incremented with the increment() method.
 * The mehtod value() returns the number of calls
 * to increment() since the creation of the counter.
 *
 * The counter can be observed with a functional programming
 * interface.
 *
 * Complete and modify the class if necessary
 */
public class ObservableCounter {

    private int counter;
    private List<Consumer<Integer>> observers;
    
    public ObservableCounter() {
        this.counter = 0;
        this.observers = new ArrayList<>();
    }

    /**
     * Return the current value of the counter
     *
     * @return the current value of the counter
     */
    public synchronized int value() {
         return counter;
    }

    /**
     * Increments the counter and notifies all the registered observers
     *
     * @return the new value of the counter
     */
    public synchronized int increment() {
        counter++;
        notifyObservers();

         return counter;
    }

    private void notifyObservers() {
        for (Consumer<Integer> observer : observers) {
            observer.accept(counter); // Notifier l'observateur avec la valeur actuelle
        }
    }

    /**
     * Adds an observer that will listen and be notified
     * with the new value when the counter value has changed.
     *
     * @param o the observer that is added to the list of observers
     */
    public synchronized void onChange(Consumer<Integer> o) {
        observers.add(o);
    }

    public static void main(String[] args) {

        // initialize a counter at 0
        ObservableCounter c = new ObservableCounter();

        // augment the counter value
        c.increment();

        // the counter is now at 1
        assert(c.value() == 1);

        // register a code that will prints the value of the counter each time it modified with increment()
        c.onChange(v -> System.out.println("observer:"+v));

        // increment the counter, since one observer listen to the change, he should be notified
        // as a consequence, the message "observer:2" should be printed
        c.increment();

    }


}
