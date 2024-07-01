import java.util.*;
//To get a maximum sum of edge products, we need to assign maximu value to the node with higher degree as it may appear in 
//multiple edges and get invloved in many products for the sum of products
class Solution {
    public long maximumImportance(int n, int[][] roads) {
      //Create a array to store the degrees of each nodes
        int[] degree = new int[n];
        for (int[] road : roads) {
            degree[road[0]]++;
            degree[road[1]]++;
        }
      //Sort the degrees
        Arrays.sort(degree);
      //Start from 1
        long val = 1;
        long totalImportance = 0;
      //Contribute lower value to higher value to lower degree node to higher degree node
        for (int d : degree) {
            totalImportance += val*d;
            val++;
        }
        
        return totalImportance;
    }
}
