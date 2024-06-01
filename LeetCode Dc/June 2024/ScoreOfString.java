class Solution {
    public int scoreOfString(String s) {
        return helper(0,s);
    }
    public int helper(int i,String s){
        if(i==s.length()-1){
            return 0;
        }
        return Math.abs(s.charAt(i)-s.charAt(i+1))+helper(i+1,s);
    }
}
