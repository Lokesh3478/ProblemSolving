class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int sum = 0;
      //Lets take the sum of satisfication without grumpy period
        for(int i =0;i<customers.length;i++){
            if(grumpy[i]==0){
                sum+=customers[i];
            }
        }
      //Initialize extraSum  to get the extraSum possible when we can include grumpy seconds into sum in a continuous period
        int extraSum = 0;
        int left = 0; int right = 0;
      //Get the extra sum if acted non grumpy in first m minutes
        while(right<left+minutes){
           //Since non grumpy is specified by 0 and grumpy by 1 multiply it with corresponding grumpy value to ignore
          //non grumpy values and add grumpy alone for extrasum
            extraSum += customers[right]*grumpy[right++];
        }
        int res = extraSum;
      //Increase end side and add end side sum and decrease start side and decrease that sum to get next window extraSum
        while(right<customers.length){
          //Store the maximum sum in result
            extraSum += customers[right]*grumpy[right++];
            extraSum -= customers[left]*grumpy[left++];
            res = Math.max(res,extraSum);
        }
      //return the result
        return res+sum;
    }
}
