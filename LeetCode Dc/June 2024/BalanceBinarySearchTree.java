/*
The problem requires to balance a binary tree , a balance binary tree would have a difference between 
left sub tree depth and right subtree depth as atmost 1. 
Approach: If we have a sorted value of bst , we can choose middle as root and left part as left subtree
and right part as right subtree. Recursively we can choose the left and right subtrees by making the 
corresponding sub problem mid element as corresponding level root 

Example : 1,2,3,4,5,6
                                          level 0 :0,5 mid = 2                    
                              level 1:                       
                              left subtree: 0,1 mid = 0  right subtree :3,5 mid = 4
level 2                                                                                         
left subtree: 0,-1 null   right subtree: 1,1 mid =1    left subtree: 3,3 mid =3  right subtree: 5,5 mid = 6
                                                      
                                                    left subtree:3,2 null
*/
class Solution {
//Funtion to balance the binary search tree
    public TreeNode balanceBST(TreeNode root) {
        ArrayList<Integer>values = new ArrayList<>();
        inorder(root,values);
        int size = values.size();
        TreeNode res = insert(values,0,size-1);
        return res;
    }
    public TreeNode insert(List<Integer>arr,int start,int end){
        if(start>end){
            return null;
        }        
      //Finding root element of current level
        int mid = start+((end-start)/2);
      //building left subtree
        TreeNode leftSubtree = insert(arr,start,mid-1);
      //building right subtree
        TreeNode rightSubtree = insert(arr,mid+1,end);
        TreeNode node = new TreeNode(arr.get(mid),leftSubtree,rightSubtree);
      //returning current level root
        return node;
    }
    public void inorder(TreeNode root,List<Integer>arr){
        if(root==null){
            return;
        }
      //recursive call to traverse left nodes
        inorder(root.left,arr);
      //print root node 
        arr.add(root.val);
      //recursice call to go right
        inorder(root.right,arr);
    }
}
