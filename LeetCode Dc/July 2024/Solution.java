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


    
    //2.7.24 LeetCode DC 350. Intersection of Two Arrays II
    
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



    
    //3.7.24 LeetCode DC 1509. Minimum Difference Between Largest and Smallest Value in Three Moves
    
    /*As per question description to minimize the range by replacing a num into any num, it is clear that we can
    remove any num from list by making it zero to minimize the range we can remove extremes that is max and min
    as we are given three moves, the three moves can be split in following ways 
                                  min          max        range
                                  1             2      arr[n-3] - arr[1]
                                  0             3      arr[n-4] - arr[0]
                                  2             1      arr[n-2]-arr[2]
                                  3             0      arr[n]-arr[3]
    */
    public int minDifference(int[] nums) {
        //if length <=3 change all elements to zero if 4 change all elements to minimum value of array
        //Both result in zero
        if(nums.length<=4){
            return 0;
        }
        //To find first 4 max and 4 min we can perform selection sort and after each selection we can shrink our sort space
        int n = nums.length;
        //Do it untill four selections or for half of array
        for(int i=0;i<Math.min(4,n/2);i++){
            moveMaxtoEnd(nums,i);
            moveMintoEnd(nums,i);
        }
        return Math.min(
            nums[n-1]-nums[3],Math.min(
                nums[n-4]-nums[0],Math.min(
                    nums[n-3]-nums[1],nums[n-2]-nums[2]
                )
            )
        );
    }
    public void moveMaxtoEnd(int nums[],int rank){
        //Consider current rank as current max ind
        int rangeMaxInd = rank;
        //Search within current rank to end-current rank
        //as already they are sorted by previous selections
        for(int i=rank;i<nums.length-rank;i++){
            if(nums[i]>nums[rangeMaxInd]){
                rangeMaxInd = i;
            }
        }
        //swap current rank element with the current max element
        int swap = nums[nums.length-1-rank];
        nums[nums.length-1-rank] = nums[rangeMaxInd];
        nums[rangeMaxInd] = swap;
    }
    //Same applier for min
    public void moveMintoEnd(int nums[],int rank){
        int rangeMinInd = rank;
        for(int i=rank;i<nums.length-rank;i++){
            if(nums[i]<nums[rangeMinInd]){
                rangeMinInd = i;
            }
        }
        int swap = nums[rank];
        nums[rank] = nums[rangeMinInd];
        nums[rangeMinInd]=swap;
    }
}

//4.7.24 LeetCode DC 2181. Merge Nodes in Between Zeros
/*The idea is to sum all elements between two zeroes and move the sum to later zero and link the nodes that
are initially had zeroes in it
*/
public ListNode mergeNodes(ListNode head) {
    //Initialize iterator to head
        ListNode iterator = head;
    //Initialize prev to head to link the nodes
        ListNode prev = iterator;
    //Traverse until the last node
        while(iterator!=null&&iterator.next!=null){
            //Initialize current sum to zero
            int sum = 0;
            //Move to next non zero node
            iterator = iterator.next;
            //Traverse unitl next zero appears
            while(iterator!=null&&iterator.val!=0){
                sum+=iterator.val;
                iterator = iterator.next;
            }
            //Change the value of zero valued node to sum
            iterator.val = sum;
            //Link the zero valued node as previous zero nodes next
            prev.next = iterator;
            //Set current zero node as previous node
            prev = prev.next;
        }   
        return head.next;

    }
