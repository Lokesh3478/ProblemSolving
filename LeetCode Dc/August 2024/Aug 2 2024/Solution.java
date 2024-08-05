class Solution {
    public int minSwaps(int[] nums) {
        int res = Integer.MAX_VALUE;
        int onesCount = 0;
        for(int i =0;i<nums.length;i++){
            onesCount+=nums[i];
        }
        int n = nums.length;
        int left = 0,right = 0,zeroCount = onesCount;
        if(onesCount ==0){
            return 0;
        }
        while(right<n+onesCount-1){
            zeroCount-=nums[(right%n)];
            if(right-left+1==onesCount){
                res = Math.min(res,zeroCount);
                zeroCount += nums[left];
                left++;
            }
            right++;
        }
        return res;
    }
}
