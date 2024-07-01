class Solution {
    //1.7.24 LeetCode DC 1550. Three Consecutive Odds
    public boolean threeConsecutiveOdds(int[] arr) {
        int res = 0; 
        for (int i = 0; i < arr.length; i++) {
            // Check if the current element is odd using bitwise AND operation
            if ((arr[i] & 1) == 1) {
                res++; // Increment the counter if the element is odd
                // If the counter reaches 3, return true
                if (res == 3) {
                    return true;
                }
            } else {
                // Reset the counter if the element is even
                res = 0;
            }
        }
        // Return false if no three consecutive odd numbers are found
        return false;
    }
}
