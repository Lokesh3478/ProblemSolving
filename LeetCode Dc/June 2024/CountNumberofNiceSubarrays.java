class Solution {
    public int numberOfSubarrays(int[] nums, int k) {
        int count = 0,n = nums.length,left = 0,right = 0,oddCount = 0;
      //Initialize left a start of window and right as end of window
        while(right<n){
          //Increment odd count at each odd values
            oddCount+=nums[right]%2;
          //If oddcount reaches greater than k , shrink window from left side , unitl it becames equal
            while(left<=right&&oddCount>k){
                oddCount-=nums[left]%2;
                left++;
            }
          //If odd count equals k , check for left and right inclusion with current perfect array
            if(oddCount==k){
                int temp = left;
              //shift from left , and increase count as long as we are equal to k
                while(left<=right&&oddCount==k){
                    oddCount-=nums[left]%2;
                    left++;
                    count++;
                }
              //Reinitialize left to previous left for right inclusion
                oddCount++;
                left = temp;
            }
            
            right++;
        }
        return count;
    }
}
