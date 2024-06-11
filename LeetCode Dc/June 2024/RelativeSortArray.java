class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int min =arr1[0];
        int max = arr1[0];
        for(int num : arr1){
            min = Math.min(min,num);
            max = Math.max(max,num);
        }
        int count[] =new int[max-min+1];
        int ans[] = new int[arr1.length];
        for(int num : arr1){
            count[num-min]++;
        }
        int ind =0;
        for(int num : arr2){
            while(count[num-min]>0){
                ans[ind++] = num;
                count[num-min]--;
            }
        }
        for(int i = min;i<=max;i++){
            while(count[i-min]>0){
                ans[ind++] = i;
                count[i-min]--;
            }
        }
        return ans;
    }
}
