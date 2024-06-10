class Solution {
    public int heightChecker(int[] heights) {
        int n = heights.length;
        int max = heights[0]; int min = heights[0];
        for(int i=1;i<n;i++){
            max = Math.max(heights[i],max);
            min = Math.min(heights[i],min);
        }
        int count[] = new int[max-min+1];
        for(int i=0;i<heights.length;i++){
            count[heights[i]-min]++;
        }
        int ind =  min;int res = 0;
        for(int i=0;i<heights.length;i++){
            while(ind<=max-min+1&&count[ind-min]<=0){
                ind++;
            }
            if(ind!=heights[i]){
                res++;
            }
            count[ind-min]--;
        }
        return res;
    }
}
