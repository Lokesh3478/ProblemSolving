class Solution {
    public boolean canBeEqual(int[] target, int[] arr) {
        int min =1,max = 1000;
        int map[] = new int[max-min+1];
        int n = arr.length;
        for(int i =0;i<n;i++){
            map[arr[i]-min]++;
            map[target[i]-min]--;
        }
        for(int i =0;i<n;i++){
            if(map[arr[i]-min]!=0){
                return false;
            }
        }
    }
}
