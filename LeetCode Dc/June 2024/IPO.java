import java.util.*;

class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        // Min-heap to store projects with preference given to least capital
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        // Max-heap to store all possible projects with current capital as investment and choose 
        // maximum profit project
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // Insert all projects into the min-heap
        for (int i = 0; i < profits.length; i++) {
            minHeap.offer(new int[]{capital[i], profits[i]});
        }

        // Perform up to k investments
        for (int i = 0; i < k; i++) {
            // Move all affordable projects with current capital as investment from min-heap to max-heap
            while (!minHeap.isEmpty() && minHeap.peek()[0] <= w) {
                maxHeap.offer(minHeap.poll()[1]);
            }

            // If no projects can be undertaken with current capital, break
            if (maxHeap.isEmpty()) {
                break;
            }

            // Take the project with the highest profit
            w += maxHeap.poll();
        }

        return w;
    }
}
public class IPO{
  //Driver class
  public static void main(String args[]){
     //Main method
  }
}
