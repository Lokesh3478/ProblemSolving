import java.util.*;
public class Solution{
  //1.8.24 LeetCode 2678 - selection based problem
  public int countSeniors(String[] details) {
        int count = 0;
        for(String d : details){
            int tens = d.charAt(11)-'0';
            int ones = d.charAt(12)-'0';
            int age = tens*10+ones;
            if(age>60){
                count++;
            }
        }
        return count;
    }
  
}
