class Solution {
    public int appendCharacters(String s, String t) {
        if(s.equals(t)){
            return 0;
        }
        for(;i<m&&k<n;i++){
            if(s.charAt(i)==t.charAt(k)){
                k++;
            }
        }
        return n-k;
    }
}
