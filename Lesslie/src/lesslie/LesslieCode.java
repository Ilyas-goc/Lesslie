package lesslie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

public class LesslieCode {

    //Assignment 1
    public static int closestDistance(List<Integer> integers) {
    // Return the distance between the two closest integers in the input list.
    // distance(n, n-1) = | integers[n] - integers[n-1] |
        int closest = Integer.MAX_VALUE;
        int compare;
        Collections.sort(integers);

        for(int n = 1; n< integers.size(); n++){
            compare = Math.abs(integers.get(n) - integers.get(n - 1));
            if(compare < closest){
                closest = compare;
            }
        }
        return closest;
    }

    //Assignment 2
    
    private static int[] storedFib = new int[1000];
    public static int fibonacci(int n) {
        // Return the n:th fibonacci number using a recursive algorithm.
        if(n<2) return n;

        if(storedFib[n] != -1) return storedFib[n];
        storedFib[n] =  fibonacci(n-1) + fibonacci(n-2);
        return storedFib[n];
    }

    //Assignment 3
    public static class Memoize<T> {
        private T call;
        private final Callable<T> callable;

        public Memoize(Callable<T> callable) throws Exception {
            this.callable = callable;
        }

        public T get() throws Exception {
            if (call == null) {
                call = callable.call();
            }
            return call;
        }
    }
    
    public static class callable implements Callable {
        private int n;
        public callable(int n) {
            this.n = n;

        }
        @Override
        public Object call() throws Exception {
            return fib(n);
        }
        public Integer fib(int n) {
            if (n <= 2)
                return 1;
            return fib(n - 1) + fib(n - 2);
        }
    }

    public static void main(String[] args) throws Exception {

        //Closest distance, assignment 1
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i<100; i++){
            list.add((int)(Math.random() * 100000000));
        }
        System.out.println("Closest Distance: " + closestDistance(list));

        //Fibonacci, assignment 2
        for(int i = 0; i<storedFib.length; i++){
            storedFib[i] = -1;
        }
        System.out.println("Fibonacci: " + fibonacci(400));

        //Memoize, assignment 3
        callable call = new callable(42);
        Memoize mem = new Memoize(call);
        for(int i = 0; i<5; i++) {
        	System.out.println(mem.get());
        }
    }

}
