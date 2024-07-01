import java.util.*;

class Solution {
    // Method to get the list of ancestors for each node in a directed acyclic graph (DAG)
    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        List<List<Integer>> list = new ArrayList<>(n); // Adjacency list to store graph
        List<List<Integer>> ancestors = new ArrayList<>(n); // List to store ancestors of each node
        
        // Initialize the adjacency list and ancestors list
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>()); // Initialize empty list for each node
            ancestors.add(new ArrayList<>()); // Initialize empty list for ancestors of each node
        }

        // Populate the adjacency list with edges
        for (int[] edge : edges) {
            list.get(edge[0]).add(edge[1]); // Add edge from edge[0] to edge[1]
        }

        // Perform DFS for each node to find all ancestors
        for (int i = 0; i < n; i++) {
            dfs(i, i, list, ancestors, new boolean[n]);
        }

        // Sort the ancestors list for each node
        for (List<Integer> l : ancestors) {
            Collections.sort(l); // Sort ancestors in ascending order
        }

        return ancestors; // Return the list of ancestors for each node
    }

    // Helper method to perform DFS and find ancestors
    private void dfs(int start, int node, List<List<Integer>> list, List<List<Integer>> ancestors, boolean[] visited) {
        for (int neighbor : list.get(node)) {
            if (!ancestors.get(neighbor).contains(start)) { // Check if the ancestor is already added
                ancestors.get(neighbor).add(start); // Add the start node as an ancestor
                dfs(start, neighbor, list, ancestors, visited); // Recursively call DFS on the neighbor
            }
        }
    }
}
