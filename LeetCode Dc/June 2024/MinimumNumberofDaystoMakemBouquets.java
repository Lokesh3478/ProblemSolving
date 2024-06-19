class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        //To check if the total flowers are covering the range of required flowers
        if(m>bloomDay.length||k>bloomDay.length||(long)(m*k)>bloomDay.length) return -1;
        int min = bloomDay[0]; int max = bloomDay[0];
        //The day must lie somewhere between the minimum and maximum day required for a flower to blossom
        for(int num : bloomDay){
            min =Math.min(min,num);
            max =Math.max(max,num);
        }
        //using binary search to look minimum possible day to choose flowers
        while(min<max){
            //Take mid value
            int mid = min+(max-min)/2;
            //Check if it is possible to split the array as m groups each group having k flowers
            boolean possible = checkFlowers(bloomDay,mid,m,k);
            //If so then reduce search space into (min to mid) for a minimized day
            if(possible){
                max = mid;
            }
            //If not then reduce search space to (mid+1 to max) to look for better solution
            else{
                min = mid+1;
            }
        }
        return min+(max-min)/2;
    }
    private boolean checkFlowers(int arr[],int mid,int m,int k){
        int count = 0;
        int adj =0;
        for(int i=0;i<arr.length;i++){
            int num = arr[i];
            //Check if the flower can be selected
            if(num<=mid){
                //Increase the value of adjacent
                adj++;
            }
              //Check if its possible to group the flowers using remaining flowers
            else{
                if((arr.length-i)/k<m-count) return false;
                adj = 0;
            }
            //if adjacent reached , increase count and set adjacent to 0
            if(adj==k){
                count++;
                //if count == m return true
                if(count==m){
                    return true;
                }
                adj=0;
            }
        }
        return count>=m;
    }
}
