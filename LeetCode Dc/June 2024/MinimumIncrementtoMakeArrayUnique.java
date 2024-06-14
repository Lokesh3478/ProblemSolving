class Solution {
    public int minIncrementForUnique(int[] nums) {
        int min = nums[0];  int max = nums[0];
        for(int num : nums){
            min = Math.min(min,num);
            max = Math.max(max,num);
        }
        int count[] = new int[max-min+1];
        int extraSize = 0;
        for(int num : nums){
            if(count[num-min]>=1) extraSize++;
            count[num-min]++;
        }
        int extra[] = new int[extraSize+1];
        int moves = 0;
        for(int i=0;i<count.length-1;i++){
            if(count[i]>1){
                int extraCount = count[i]-1;
                count[i+1]+=extraCount;
                moves+=extraCount;
            }
        }
        moves += count[max-min]-1;
        extra[0] += count[max-min]-1;
        for(int i =0;i<extra.length-1;i++){
            if(extra[i]>1){
                int extraCount = extra[i]-1;
                extra[i+1] +=extraCount;
                moves+=extraCount;
            }
        }
        return moves;
    }
}
