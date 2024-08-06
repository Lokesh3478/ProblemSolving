class Solution {
    HashMap<Integer,String>keyMap = new LinkedHashMap<>();
    public int minimumPushes(String word) {
        int map[][] = new int[26][2];
        for(int i =0;i<25;i++){
            map[i][0] = 'a'+i;
        }
        for(int i =0;i<word.length();i++){
            map[word.charAt(i)-'a'][1]++;
        }
        Arrays.sort(map,(a,b)->{
            return b[1]-a[1];
        });
        int keyUsed = 1;
        int res = 0;
        for(int i = 0;i<25;i++){
            System.out.println((char)(map[i][0])+" "+map[i][1]);
            if(map[i][1]==0){
                break;
            }
            res+=map[i][1]*((keyUsed-1)/8+1);
            keyMap.put(keyUsed,keyMap.getOrDefault(keyUsed,"")+(char)(map[i][0]));
            keyUsed++;
        }
        System.out.println(map);
        return res;
    }
}
