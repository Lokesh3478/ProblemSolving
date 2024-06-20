class Solution {
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        //Initialize minimum distance as 1 , since the minimum distance possible between two consecutive blocks is 1
        //Initialize max to max - min of given points
        //Only within these range there lies a answer
        int min = 1; int max = position[position.length-1]--position[0];
        int ans = 0;
        //Use binary search to search the possible answer
        while(min<=max){
            int mid = min+(max-min)/2;
            //Check if current mid is possible for placing the balls with minimum distance as mid
            //If so move to right part after mid , since we are looking for max value
            //Since we are ignoring the right answers , there may be possiblity we end up in 
            //reaching wrong answer , so store the possible mid to ans
            if(isPossible(position,mid,m)){
                min = mid+1;
                ans = mid;
            }
            //If its not possible to place the balls, move to left side
            else{
                max = mid-1;
            }
        }
        return ans;
    }
    private boolean isPossible(int arr[],int diff,int m){
        int count =1;
        int lastPlaced = arr[0];
        //Place the first ball at minimum position
        for(int i =1;i<arr.length;i++){
           //Check for every position and change the last placed , if the difference is greater than or equal to mid value 
           //as the minimum distance should be mid value
            if(Math.abs(lastPlaced-arr[i])>=diff){
                lastPlaced = arr[i];
                count++;
                if(count>=m){
                    return true;
                }
            }
        }
        return false;
    }
}

