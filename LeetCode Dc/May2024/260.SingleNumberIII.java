class Solution {
    public int[] singleNumber(int[] nums) {
     int xorSum = 0;
     for(int num : nums){
        xorSum^=num;
     }
     int bit =  0;
     for(bit = 0;bit<32;bit++){
        if((xorSum&(1<<bit))!=0){
            break;
        }
     }   
     int res[] = new int[2];
     for(int num :nums){
        if((num&(1<<bit))!=0){
            res[0]^=num;
        }
        else{
            res[1]^=num;
        }
     }
     System.gc();
     return res;
    }
}
