import java.util.*;
class Solution {
    public int minPatches(int[] nums, int n) {
        //Maximum range so far formed
        long maxRange = 0;
        int patches = 0; int size = nums.length;
        int i = 0;
        /*Do unitll we reache the maximum
        *range greater than or equal to n
        */
        while(maxRange<n){
            /* Check if the next maxRange is within the 
            array , if maxRange +1 is greater than or
            equal to current array element , we can add that 
            to maxSum
             */
            if(i<size&&maxRange+1>=nums[i]){
                maxRange +=nums[i];
                i++;
            }
            /*
            If the next maxRange is less than the current element , then patch the
            next maxRange to array , becuase adding the missing element as it its
            will increase the range greedily
             */
            else{
                patches++;
                maxRange+=maxRange+1;
            }
        }
        return patches;
    }
}
public class PatchingArray{
    public static void main(String args[]){
      //Driver Code
    }
}
