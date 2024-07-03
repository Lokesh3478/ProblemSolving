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

    //2.7.24 LeetCode DC 350
     public int[] intersect(int[] nums1, int[] nums2) {
        int n1 = nums1.length; int n2 = nums2.length;
        int min = 1000; int max = 0;
         //By greedy to create a hashmap of smaller array, we find max and min of smaller array
        for(int i=0;i<Math.min(n1,n2);i++){
            if(n1<n2){
                min = Math.min(nums1[i],min);
                max = Math.max(nums1[i],max);
            }
            else{
                min = Math.min(nums2[i],min);
                max = Math.max(nums2[i],max);
            }
        }
         //Create a map for value range in smaller array i.e max-min+1
        int map[] = new int[max-min+1];
         //Update the count of each element at its position in the range
         // if range of values in small array is 2 to 7 , then an array size 6 created to store
         // 2,3,4,5,6,7 frequencies all elements frequency strores in num-min index
        for(int i=0;i<Math.min(n1,n2);i++){
            if(n1<n2){
                map[nums1[i]-min]++;
            }
            else{
                map[nums2[i]-min]++;
            }
        }
         //Create a array list to dynamically increase size of array and 
         //add elements that are common in larger and smaller subarray
        ArrayList<Integer>res = new ArrayList<>();
        for(int i=0;i<Math.max(n1,n2);i++){
            if(n1<n2){
                if(nums2[i]>=min&&nums2[i]<=max&&map[nums2[i]-min]!=0){
                    res.add(nums2[i]);
                    map[nums2[i]-min]--;
                }
            }
            else{
                if(nums1[i]>=min&&nums1[i]<=max&&map[nums1[i]-min]!=0){
                    res.add(nums1[i]);
                    map[nums1[i]-min]--;
                }
            }
        }
         //Add elements from list to result array
        int resArr[] = new int[res.size()];
        for(int i=0;i<res.size();i++){
            resArr[i] = res.get(i);
        }
        return resArr;
    }
}
