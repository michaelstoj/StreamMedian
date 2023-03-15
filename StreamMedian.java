import java.util.Comparator;
import java.util.PriorityQueue;
/**
 * Stream median accepts an input stream and creates
 * a min and max heap using priority queue and calculates the median
 * by getting the root value of smaller if there are odd numbers or calculating median
 * by averaging the two values of both roots if there are an even number of inputs
 * @author: Connor Norris & Mike Stoj
 * @edu.uwp.cs.340.course.CSCI 340 - Data Structures
 * @edu.uwp.cs.340.assignment.Section 01
 * @bugs : None
 */

public class StreamMedian {
    /**
     * smaller is a max heap with the smaller half of the numbers and uses
     * a comparator to make the biggest value at the root of the heap
     */
    PriorityQueue<Integer> smaller = new PriorityQueue<>( new Comparator<Integer>() {
        public int compare(Integer i1, Integer i2) {
            return i2.compareTo(i1);
        }
    });
    /**
     * bigger is a standard PriorityQueue with the larger half of the data and has
     * the min value at its root
     */
    PriorityQueue<Integer> bigger = new PriorityQueue<>();

    /**
     * insert adds the next value into the queues
     * @param i the value to be added
     */
    public void insert(Integer i){
        /**
         * if i is the first value or bigger or equal to smaller's root add it to smaller
         * or add it to bigger
         */
        if (smaller.isEmpty() || smaller.peek() >= i)
            smaller.add(i);
        else
            bigger.add(i);

/**
 * balance the heaps after each insert
 */
        balanceHeaps();
    }

    /**
     * compare sizes of the bigger and smaller queues, the smaller should be equal or have one element
     * more than bigger and depending on where the insert added the next value
     */
    public void balanceHeaps(){
        if (smaller.size() > bigger.size() + 1)
            bigger.add(smaller.poll());
        else if (smaller.size() < bigger.size())
            smaller.add(bigger.poll());
    }

    /**
     * calculates the median value using the size of the queues, if they are the same size then
     * get the root value at smaller and bigger and average them to get the median or
     * if smaller has one more value then just return its root value
     * @return the median value
     */
    public double getMedian() {
        // check if the sizes are equal then returns average or two numbers or return the smaller root element
        return smaller.size() == bigger.size() ? ((double)smaller.peek() + bigger.peek()) / 2 : smaller.peek();
    }

}
