class Solution {
    public void sortColors(int[] nums) {
        int count[] = new int[3];
        for(int num : nums){
            count[num]++;
        }
        int i =0;
        int j =0;
        for(int num : count){
            while(num>0){
                nums[i++] = j;
                num--;
            }
            j++;
        }
    }
}
