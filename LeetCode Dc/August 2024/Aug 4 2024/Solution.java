class Solution {
    public int rangeSum(int[] nums, int n, int left, int right) {
        int mod = (int)(Math.pow(10,9))+7;
        int subArray[] = new int[n*(n+1)/2];
        int index = 0;
        subArray[0] = nums[0];
        for(index =1;index<n;index++){
            subArray[index] = ((subArray[index-1]%mod)+nums[index])%mod;
        }
        int i =1;
        while(i<n){
            int j  = index-(n-i);
            int end = j+(n-i);
            while(j<end){
                subArray[index++] = ((subArray[j])%mod-nums[i-1])%mod;
                j++;
            }
            i++;
        }
        Arrays.sort(subArray);
        long sum = 0;
        for(i = left-1;i<right;i++){
            sum=(sum+subArray[i])%mod;
        }
        return (int)sum;
    }
}
