class Solution {
    public int longestPalindrome(String s) {
        int length = 0;
        int map[] = new int[58];
        for(char ch : s.toCharArray()){
            map[ch-'A']++;
        }
        boolean hasOdd = false;
        for(int val : map){
            if(val%2!=0){
                length+=val-1; 
                hasOdd = true;
            }
            else{
                length+=val;
            }
        }
        return length+(hasOdd?1:0);
    }
}
