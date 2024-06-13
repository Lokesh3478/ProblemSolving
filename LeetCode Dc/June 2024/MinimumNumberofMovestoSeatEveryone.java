class Solution {
    public int[] minMax(int arr[]){
        int min = arr[0]; int max = arr[0];
        for(int i =1;i<arr.length;i++){
            min = Math.min(min,arr[i]);
            max = Math.max(max,arr[i]);
        }
        return new int[]{min,max};
    }
    public void countSort(int arr[],int count[],int min,int max){
        int ind = 0;
        for(int i=min;i<=max;i++){
            while(count[i-min]>0){
                arr[ind++] = i;
                count[i-min]--;
            }
        }
    }
    public int minMovesToSeat(int[] seats, int[] students) {
        int limits[] = minMax(seats);
        int count[] = new int[limits[1]-limits[0]+1];
        for(int num : seats){
            count[num-limits[0]]++;
        }
        countSort(seats,count,limits[0],limits[1]);
        limits = minMax(students);
        count = new int[limits[1]-limits[0]+1];
        for(int s : students){
            count[s-limits[0]]++;
        }
        countSort(students,count,limits[0],limits[1]);
        int moves = 0;
        for(int i =0;i<seats.length;i++){
            //System.out.println(seats[i]+" "+students[i]);
            moves+=Math.abs(seats[i]-students[i]);
        }
        return moves;
    }
}
