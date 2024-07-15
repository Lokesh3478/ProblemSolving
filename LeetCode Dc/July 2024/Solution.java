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


    //5.7.24 LeetCode DC 2058. Find the Minimum and Maximum Number of Nodes Between Critical Points

    /*The idea is to use three pointers to keep track of critical points, If any critical point found perform the following operations
    Initialize first and last pointer as -1 and -1
    if first pointer is -1 upon a critical point detection then change it to current index
    after that upon each crirtcal point update min by min(min,index-last)
    and update last to index
    As min going to be one of those adjacent element distance
    and max going to be last and firsr critical point distance
    */
    public int[] nodesBetweenCriticalPoints(ListNode head) {
    int min = Integer.MAX_VALUE; // Initialize min to maximum possible value
    int first = -1, last = -1; // Initialize first and last critical points to -1 (not found)
    int prevInd = 0; // Initialize previous critical point index

    // Check if the list is too short to have any critical points
    if (head == null || head.next == null) {
        return new int[]{-1, -1}; // Return {-1, -1} if the list has less than 2 nodes
    }

    ListNode prev = head; // Start with the first node as previous node
    ListNode curr = head.next; // Start with the second node as current node
    int index = 1; // Index of the current node

    // Traverse the list until the current node and its next node are not null
    while (curr != null && curr.next != null) {
        // Check if current node is a critical point (local minima or maxima)
        if ((prev.val < curr.val && curr.val > curr.next.val) ||
            (prev.val > curr.val && curr.val < curr.next.val)) {
            
            // If first critical point is not set, set it
            if (first == -1) {
                first = index;
                prevInd = index; // Set previous critical point index
            } else {
                // Update last critical point
                last = index;
                // Calculate the minimum distance between consecutive critical points
                min = Math.min(min, last - prevInd);
                prevInd = index; // Update previous critical point index
            }
        }

        // Move to the next node
        index++;
        prev = curr;
        curr = curr.next;
    }

    // If less than two critical points found, return {-1, -1}
    if (first == -1 || last == -1) {
        return new int[]{-1, -1};
    }

    // Return the minimum distance and the distance between the first and last critical points
    return new int[]{min, last - first};
}


//6.7.24 Leetcode DC 2582. Pass the Pillow
    /*On each complete pass the pillow reaches the other and the remaining passes can be counted from end if the complete passes is odd
    or from beginning if the complete passes are even
    */
    public int passThePillow(int n, int time) {
        int completePasses = time/(n-1);
        if(completePasses%2==0){
            return 1+ (time-(completePasses*(n-1)));
        }
        return n-(time-(completePasses*(n-1)));
    }

//7.7.24 1518. Water Bottles
public int numWaterBottles(int numBottles, int numExchange) {
    int count = 0; // Initialize the count of consumed bottles
    
    // Continue the process as long as we have enough bottles to exchange for a new one
    while (numBottles >= numExchange) {
        int k = numBottles / numExchange; // Calculate how many new bottles we can get
        int currentConsume = k * numExchange; // Calculate the number of bottles consumed in this iteration
        count += currentConsume; // Add the consumed bottles to the total count
        numBottles = numBottles - currentConsume + k; // Update the number of bottles: subtract consumed, add new full bottles
    }
    
    // Add any remaining bottles that cannot be exchanged to the total count
    return count + numBottles;
}

//8.7.24 1823. Find the Winner of the Circular Game
public int findTheWinner(int n, int k) {
        int q[] = new int[n];
        for(int i =1;i<=n;i++){
            q[i-1] = i;
        }
        int front =0,rear = n,pointer=0;
        while(rear!=1){
            pointer = (pointer+k-1)%rear;
            q[pointer%rear] = -1;
            for(int j=pointer%rear;j<rear-1;j++){
                q[j] = q[j+1];
            }
            rear--;
        }   
        return q[front];
    }

//LeetCode DC 9.7.24 1701. Average Waiting Time
public double averageWaitingTime(int[][] customers) {
    double sum = 0;  // Initialize sum to store the total waiting time
    int current = 0;  // Initialize current to keep track of the current time

    // Loop through each customer
    for(int[] o : customers){
        // If the current time is less than the customer's arrival time, update current time to the customer's arrival time
        if(current < o[0]){
            current = o[0];
        }

        // Add the service time to the current time
        current += o[1];

        // Calculate the waiting time for this customer and add it to sum
        // Use modulo to prevent overflow (although it may not be necessary here)
        sum += (current - o[0]) % Math.pow(2, 31);
    }

    // Calculate and return the average waiting time
    return sum / customers.length;
}

//10.7.24 LeetCode DC 1598. Crawler Log Folder
public int minOperations(String[] logs) {
        int stkSize = 0;
        for (String log: logs) {
            stkSize += add(log,stkSize);
        }
        return stkSize;
    }
    private int add(String log, int res) {
        return log.charAt(1) == '.' ? res == 0 ? 0 : -1 : log.charAt(0) == '.' ? 0 : 1;
    }

//11.7.24 LeetCode DC 1190. Reverse Substrings Between Each Pair of Parentheses
public String reverseParentheses(String s) {
        Stack<Character> stk = new Stack<>();
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == ')') {
                // When encountering a closing parenthesis, start to pop and reverse
                StringBuilder temp = new StringBuilder();
                while (!stk.isEmpty() && stk.peek() != '(') {
                    temp.append(stk.pop());
                }
                if (!stk.isEmpty() && stk.peek() == '(') {
                    stk.pop(); // remove the matching '('
                }
                // Push reversed characters back onto the stack
                for (int j = 0; j < temp.length(); j++) {
                    stk.push(temp.charAt(j));
                }
            } else {
                // Push current character to the stack
                stk.push(c);
            }
        }
        StringBuilder res = new StringBuilder();
        while (!stk.isEmpty()) {
            res.insert(0, stk.pop());
        }
        return res.toString();
    }

    //12.7.24 LeetCode DC 1717. Maximum Score From Removing Substrings

    public int maximumGain(String s, int x, int y) {
        Stack<Character>stk = new Stack<>();
        String pref1 = x>y?"ab":"ba";
        String pref2 = pref1=="ab"?"ba":"ab";
        int count = 0;
        for(char ch : s.toCharArray()){
            if(ch==pref1.charAt(1)){
                if(!stk.isEmpty()&&stk.peek()==pref1.charAt(0)){
                    count+=Math.max(x,y);
                    stk.pop();
                }
                else{
                    stk.push(ch);
                }
            }
            else{
                stk.push(ch);
            }
        }
        s = "";
        for(char ch : stk){
            s+=ch;
        }
        stk.clear();
        for(char ch : s.toCharArray()){
            if(ch==pref2.charAt(1)){
                if(!stk.isEmpty()&&stk.peek()==pref2.charAt(0)){
                    count+=Math.min(x,y);
                    stk.pop();
                }
                else {
                   stk.push(ch);
                }
            }
            else{
                stk.push(ch);
            }
        }
        return count;
    }

//13.7.24 LeetCode DC 2751. Robot Collisions
public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        HashMap<Integer,Integer>indices = new LinkedHashMap<>();
        for(int i=0;i<positions.length;i++){
            indices.put(positions[i],i);
        }
        ArrayList<Integer>survivors = new ArrayList<>();
        Stack<Integer>stack = new Stack<>();
        int sorted[] =(Arrays.copyOf(positions,positions.length));
        Arrays.sort(sorted);
        for(int num : sorted){
            if(directions.charAt(indices.get(num))=='R'||stack.isEmpty()){
                stack.push(num);
            }
            else{
                boolean added = true;;
                int top = stack.peek();
                while(!stack.isEmpty()&&directions.charAt(indices.get(top))=='R'){
                    if(healths[indices.get(top)]==healths[indices.get(num)]){
                        healths[indices.get(top)]=0;
                        healths[indices.get(num)]=0;
                        stack.pop();
                        added = false;
                        break;
                    }
                    else if(healths[indices.get(top)]>healths[indices.get(num)]){
                        healths[indices.get(top)]--;
                        healths[indices.get(num)]=0;
                        added= false;
                        break;
                    }
                    else{
                        healths[indices.get(num)]--;
                        healths[indices.get(top)] = 0;
                        stack.pop();
                    }
                    if(!stack.isEmpty()){
                        top = stack.peek();
                    }
                    else{
                        break;
                    }
                }
                if(added){
                    stack.push(num);
                }
            }
        }
        for(int num : healths){
            if(num!=0){
                survivors.add(num);
            }
        }
        return survivors;
    }
//14-7-24 LeetCode DC726. Number of Atoms
/*Given a string formula representing a chemical formula, return the count of each atom.
The atomic element always starts with an uppercase character, then zero or more lowercase letters, representing the name.
One or more digits representing that element's count may follow if the count is greater than 1. If the count is 1, no digits will follow.
Two formulas are concatenated together to produce another formula.
A formula placed in parentheses, and a count (optionally added) is also a formula.*/
/*Solutuon Approach
use a hashmap to keep track of each element
Since there may be nested elements like (()), use a stack of hashmap to assign nested values to 
correct set of elements.
Create a stack of hashmap,
if'(' add a new map to stack and add the element present in it to the map
if an upper case if found , form the element till lowecase element are present after uppercase
Now look for digits after the element, if no digits formed map it with 0,
add these to top map of stack
if ')' is found look for number after ')' bracket if no number found make it 1 
multiply it with top map of stack 
merge top two maps.
The number of maps is equal to number of openBrackets + one non nested
the order is however reduced to one as each hashmap opened due to'(' is closed at ')'
as the string is always valid
*/
    public boolean isLower(char ch) {
            return (ch >= 'a' && ch <= 'z');
    }

    public boolean isNumber(char ch) {
        return (ch >= '0' && ch <= '9');
    }

    public String countOfAtoms(String formula) {
        Stack<HashMap<String, Integer>> stack = new Stack<>();
        stack.push(new LinkedHashMap<>());
        int ptr = 0;

        while (ptr < formula.length()) {
            if (formula.charAt(ptr) == '(') {
                stack.push(new LinkedHashMap<>());
                ptr++;
                continue;
            } else if (formula.charAt(ptr) == ')') {
                ptr++;
                int count = 0;
                while (ptr < formula.length() && isNumber(formula.charAt(ptr))) {
                    count = count * 10 + (formula.charAt(ptr) - '0');
                    ptr++;
                }
                count = Math.max(count, 1);
                HashMap<String, Integer> top = stack.pop();
                HashMap<String, Integer> prevMap = stack.peek();
                for (String ele : top.keySet()) {
                    prevMap.put(ele, prevMap.getOrDefault(ele, 0) + top.get(ele) * count);
                }
                continue;
            } else {
                StringBuilder element = new StringBuilder();
                element.append(formula.charAt(ptr));
                ptr++;
                while (ptr < formula.length() && isLower(formula.charAt(ptr))) {
                    element.append(formula.charAt(ptr));
                    ptr++;
                }
                int count = 0;
                while (ptr < formula.length() && isNumber(formula.charAt(ptr))) {
                    count = count * 10 + (formula.charAt(ptr) - '0');
                    ptr++;
                }
                count = Math.max(count, 1);
                stack.peek().put(element.toString(), stack.peek().getOrDefault(element.toString(), 0) + count);
            }
        }

        HashMap<String, Integer> finalMap = stack.pop();
        TreeMap<String, Integer> sortedMap = new TreeMap<>(finalMap);

        StringBuilder res = new StringBuilder();
        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            res.append(entry.getKey());
            if (entry.getValue() > 1) {
                res.append(entry.getValue());
            }
        }
        return res.toString();
    }

    //15-7-24 LeetCode DC 2196. Create Binary Tree From Descriptions
    public TreeNode createBinaryTree(int[][] descriptions) {
    // Initialize variables to track the maximum and minimum node values
    int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
    
    // Determine the range of node values
    for(int[] d : descriptions) {
        max = Math.max(max, Math.max(d[0], d[1]));
        min = Math.min(min, Math.min(d[0], d[1]));
    }
    
    // Create an array to map node values to TreeNode objects and a boolean array to track non-root nodes
    TreeNode[] map = new TreeNode[max - min + 1];
    boolean[] notRoot = new boolean[max - min + 1];
    
    // Build the tree from the description array
    for(int[] d : descriptions) {
        // Initialize node and child variables
        TreeNode node = null;
        TreeNode child = null;
        
        // Check if the child node already exists in the map
        if(map[d[1] - min] != null) {
            child = map[d[1] - min];
        } else {
            // Create a new child node if it does not exist
            child = new TreeNode(d[1]);
        }
        
        // Set the left or right child based on the description
        if(d[2] == 1) {
            // If d[2] is 1, set child as the left child
            if(map[d[0] - min] != null) {
                map[d[0] - min].left = child;
                map[d[1] - min] = map[d[0] - min].left;
            }
        } else {
            // If d[2] is 0, set child as the right child
            if(map[d[0] - min] != null) {
                map[d[0] - min].right = child;
                map[d[1] - min] = map[d[0] - min].right;
            }
        }
        
        // Mark the child node as not a root
        notRoot[d[1] - min] = true;
    }
    
    // Identify and return the root node (a node which is not a child of any other node)
    for(int[] d : descriptions) {
        if(!notRoot[d[0] - min]) {
            return map[d[0] - min];
        }
    }
    
    // Return null if no root node is found (should not happen if input is valid)
    return null;
}

}
