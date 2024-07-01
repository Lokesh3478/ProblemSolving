/* 
To find the unwanted edges, we can use a union-find data structure to identify and union the nodes that have a common parent. 
We traverse through each edge and union them with their common parent. If an edge has two vertices that already have a common 
ancestor, then we eliminate that edge from the graph.
*/

/* 
Greedy Approach: To maximize the number of edges we can remove, we need to create a union set of common paths first, 
then handle the individual paths for Alice and Bob.
*/

class Union {
    int n = 0; 
    int par[];
    int rank[];
  
    // Constructor to initialize the union-find structure
    public Union(int n) {
        this.n = n;
        par = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            par[i] = i + 1;
        }
        Arrays.fill(rank, 1);
    }

    /* 
    The union method unites the sets containing the two vertices of an edge.
    If any edge contains vertices with the same parent, it means they are already connected and the edge is redundant.
    */
    public int union(int edge[]) {
        int p1 = par[edge[1] - 1]; 
        int p2 = par[edge[2] - 1];

        // Find the root of p1
        while (p1 != par[p1 - 1]) {
            par[p1 - 1] = par[par[p1 - 1] - 1];
            p1 = par[p1 - 1];
        }

        // Find the root of p2
        while (p2 != par[p2 - 1]) {
            par[p2 - 1] = par[par[p2 - 1] - 1];
            p2 = par[p2 - 1];
        }

        // If both vertices have the same root, the edge is redundant
        if (p1 == p2) {
            return 0;
        } else {
            // Union by rank: attach the tree with lower rank under the root of the tree with higher rank
            if (rank[p1 - 1] > rank[p2 - 1]) {
                par[p2 - 1] = par[p1 - 1];
                rank[p2 - 1] += rank[p1 - 1]; 
            } else {
                par[p1 - 1] = par[p2 - 1];
                rank[p1 - 1] += rank[p2 - 1]; 
            }
        }

        // Decrement the edge count after a successful union
        this.n -= 1;
        return 1;
    }

    // Check if all nodes are connected (i.e., there is one connected component)
    public boolean isConnected() {
        return this.n == 1;
    }
}

class Solution {
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        Union alice = new Union(n);
        Union bob = new Union(n);
        int count = 0;

        // Process type 3 edges (common to both Alice and Bob)
        for (int e[] : edges) {
            if (e[0] == 3) {
                count += alice.union(e) | bob.union(e);
            }
        }

        // Process type 1 edges (specific to Alice)
        for (int e[] : edges) {
            if (e[0] == 1) {
                count += alice.union(e);
            }
        }

        // Process type 2 edges (specific to Bob)
        for (int e[] : edges) {
            if (e[0] == 2) {
                count += bob.union(e);
            }
        }

        // If either Alice or Bob cannot fully traverse the graph, return -1
        if (!alice.isConnected() || !bob.isConnected()) {
            return -1;
        }

        // Return the number of edges that can be removed
        return edges.length - count;
    }
}

