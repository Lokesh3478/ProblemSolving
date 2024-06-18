class Solution {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        HashMap<Integer,Integer>map = new LinkedHashMap<>();
        //Store profit of each difficulty and store maximum profit of duplicate difficulties
        for(int i=0;i<profit.length;i++){
            map.put(difficulty[i],Math.max(map.getOrDefault(difficulty[i],0),profit[i]));
        }   
        /**Sort the difficulty array , to go from least difficulty 
        and storing the maximum profit so far encountered in 
        each difficulty , as the difficulty array is sorted ,
        it is possible to do earlier difficulty and to gain that 
        profit if it is maximum
         */
        Arrays.sort(difficulty);
        for(int i=0;i<difficulty.length-1;i++){
            map.put(difficulty[i+1],
                Math.max(
                    map.get(difficulty[i]),
                    map.get(difficulty[i+1])
                )
            );
        }
        int total = 0;
        //for each worker search his profit acquired
        for(int num : worker){
             int start = 0,end = difficulty.length-1,currentProfit = 0;
             /*Use binary search to search in area less than or equal to current worker capablity
             */
        while(start<=end){
            int mid = (start+end)/2;
            /**Move towards right if the current difficulty is less than or
            equal to worker ability to maximize the profit */
            if(difficulty[mid]<=num) {
                currentProfit = Math.max(currentProfit,map.get(difficulty[mid]));
                start = mid+1;
            }
            /**If current difficulty is freater than worker ability
            *Move to left side to get difficulty less than or equal to worker ability
             */
            else if(difficulty[mid]>num) {
                end = mid-1;
            }
        }
            total+=currentProfit;
        }
        return total;
    }
}
