class Solution {
    public String replaceWords(List<String> dictionary, String sentence) {
        StringBuilder result = new StringBuilder("");
        Trie trieDs = new Trie();
        for(String  word : dictionary){
            trieDs.insert(word);
        }
        for(String word : sentence.split(" ")){
            String prefix = trieDs.havePrefix(word);
            if (!prefix.equals("")) {
                result.append(prefix).append(" ");
            } 
            else {
                result.append(word).append(" ");
            }
        }
        return result.toString().trim();
    }
}
class Node{
    Node next[] = new Node[26];
    boolean completed = false;
    public Node(){}
    public boolean containsKey(char ch){ return next[ch-'a']!=null; }
    public void putKey(char ch,Node node){ next[ch-'a']=node; }
    public Node getKey(char ch){ return next[ch-'a']; }
    public boolean isComplete(){ return completed; }
    public void setComplete(){ completed = true; }
}
class Trie{
    Node root;
    public Trie(){ root = new Node(); }
    public void insert(String word){
        insertInvoke(word,0,root);
    }
    private void insertInvoke(String word,int i,Node node){
        if(i>=word.length()) return;
        char ch = word.charAt(i);
        if(!node.containsKey(word.charAt(i))) node.putKey(ch,new Node());
        if(i==word.length()-1) node.setComplete();
        insertInvoke(word,i+1,node.getKey(ch));
    }
    public String havePrefix(String prefix){
        return havePrefixInvoke(prefix,0,root,"");
    }
    public String havePrefixInvoke(String prefix, int i, Node node, String res) {
        if(i>=prefix.length()){
            if(!node.isComplete()) return "";
            return res;
        }
        char ch = prefix.charAt(i);
        if(!node.containsKey(ch)){
            if(!node.isComplete()) return "";
            return res;
        }
        res+=ch;
        if (node.isComplete()) return res;
        node = node.getKey(ch);
        return havePrefixInvoke(prefix, i + 1, node, res);
    }
}
