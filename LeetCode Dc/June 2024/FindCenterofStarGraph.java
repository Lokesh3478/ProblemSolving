class Solution {
    public int findCenter(int[][] edges) {
      //A star graph have a single node connected to all other nodes without cycle,
      //Therfore the centre node is the node that have all edges connected to it 
      //So return the node that appears common in first two edges
        return (edges[0][0] ==edges[1][0]||
                edges[0][0]==edges[1][1])?edges[0][0]   
                :edges[0][1];
    }
}
