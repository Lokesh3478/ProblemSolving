class Solution {
    public List<String> commonChars(String[] words) {
        int start = 122;
        int end = 97;
        String smallWord = words[0];
        ArrayList<String>result = new ArrayList<>();
        for(int i =0;i<words.length;i++){
            smallWord = smallWord.length()<words[i].length()?smallWord : words[i];
        }
        for(char ch : smallWord.toCharArray()){
            start = Math.min(start,(int)ch);
            end = Math.max(end,(int)ch);
        }
        int map[][] = new int[words.length][end-start+1];
        int i =0;
        for(String word : words){
            for(char ch : word.toCharArray()){
                if((int)ch<=end&&(int)ch>=start){
                    map[i][ch-start]++;
                }
            }
            i++;
        }
        int resFreq[] = new int[end-start+1];
        for(char ch : smallWord.toCharArray()){
            int minFreq = map[0][ch-start];
            for(i =0;i<words.length&&minFreq!=0;i++){
                minFreq = Math.min(minFreq,map[i][ch-start]);
            }
            resFreq[ch-start] = minFreq;
        }
        for(char ch : smallWord.toCharArray()){
            while(resFreq[ch-start]>0){
                result.add(ch+"");
                resFreq[ch-start]--;
            }
        }
        return result;
    }
}
