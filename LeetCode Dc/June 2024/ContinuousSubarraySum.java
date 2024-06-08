class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        HashMap<Integer,Integer>map = new LinkedHashMap<>();
        int sum = 0; int i =0;
        for(int num : nums){
            sum+=num;
            int rem = sum%k;
            if(rem==0&&i!=0) return true;
            if(!map.containsKey(rem)) map.put(rem,i);
            else if(i-map.get(rem)>1) return true;
            i++;
        }
        return false;
    }
}
