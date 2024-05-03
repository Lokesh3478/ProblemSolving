import java.util.*;
public class SteppingNumbers {
    public static List<Integer> getSteppingNumbers(int n) {
        List<Integer> result = new ArrayList<>();
        Queue<String> queue = new LinkedList<>();        
        // Add base level containing 1 to 9
        result.add(0);
        for (int i = 1; i <= 9; i++) {
            queue.offer(String.valueOf(i));
        } 
        // BFS to generate stepping numbers
        for (int level = 1; level <= n; level++) {
            Queue<String> nextLevel = new LinkedList<>();
            
            while (!queue.isEmpty()) {
                String numStr = queue.poll();
                int num = Integer.parseInt(numStr);
                result.add(num);
                
                // Generate next stepping numbers by concatenating with adjacent possible digits
                int lastDigit = num % 10;
                if (lastDigit - 1 >= 0) {
                    nextLevel.offer(numStr + (lastDigit - 1));
                }
                if (lastDigit + 1 <= 9) {
                    nextLevel.offer(numStr + (lastDigit + 1));
                }
            }
            
            queue.addAll(nextLevel);
        }
        
        return result;
    }
    
    public static void main(String[] args) {
       // Change this to generate stepping numbers up to a different level
			   int level = 5; // level denotes maximum number of digits it increase by powers of 10 , starts with 10^0
        List<Integer> steppingNumbers = getSteppingNumbers(levels);
    }
}
