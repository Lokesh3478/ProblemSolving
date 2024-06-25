
class Solution {
    public TreeNode bstToGst(TreeNode root) {
        TreeNode temp = root;
        int sum = 0;
      //Initialize a stack to keep track of nodes
        Stack<TreeNode>stk = new Stack<>();
      //Traverse untill stack became empty or current node became null
        while(!stk.isEmpty()||temp!=null){
          //Stack till right end of current node reaches
            while(temp!=null){
                stk.push(temp);
                temp = temp.right;
            }
          //pop the last inserted node
            temp = stk.pop();
          //add its sum to global sum
            sum+=temp.val;
          //Change value to sum
            temp.val = sum;
          //move to left after right and root
            temp = temp.left;
        }
        return root;
    }
}
