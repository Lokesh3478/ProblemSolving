class Solution {
    public int minKBitFlips(int[] nums, int k) {
       //The best way to minimize flips , flipping all the zeroes rightward ,
       //by going from left to right , we can minimize the number of flips
        int left  =0;
        int count = 0;
        int n = nums.length;
       //Use queue to store the flip index
        Queue<Integer>q = new LinkedList<>();
        while(left<n){
//pop the flip index that are out of bound for current index
           while(!q.isEmpty()&&q.element()+k-1<left){
            q.remove();
           }
//Check if the current number of flips on this element would produce 1 , by moding with two , everytime you increase 0 or 1 
//it gets flipped , if current element is 0 after all the flips , then add new flip index to queue and increase count
           if((nums[left]+q.size())%2==0){
              if(n-left<k){
                return -1;
              }
              count+=1;
              q.add(left);
           }
           left++;
        }
        return count;
    }
}
