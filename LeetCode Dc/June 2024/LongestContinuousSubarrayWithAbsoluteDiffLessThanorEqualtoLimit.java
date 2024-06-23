class Solution {
    public int longestSubarray(int[] arr, int limit) {
        // Initialize pointers and variables
        int left = 0, right = 0, n = arr.length, res = 0;
        
        // Min and max values in the current window
        int min = arr[left], max = arr[left];
        
        // Priority queues to maintain the min and max values with their indices
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);

        while (right < n) {
            // Add the current element with its index to both heaps
            minHeap.add(new int[]{arr[right], right});
            maxHeap.add(new int[]{arr[right], right});

            // Ensure the difference between max and min values in the current window is within the limit
            while (!maxHeap.isEmpty() && !minHeap.isEmpty() && maxHeap.peek()[0] - minHeap.peek()[0] > limit) {
                // Move the left pointer to shrink the window , select min value to pick long range , shrink smaller
                left = Math.min(maxHeap.peek()[1], minHeap.peek()[1]) + 1;

                // Remove elements from heaps that are outside the current window
                while (!maxHeap.isEmpty() && maxHeap.peek()[1] < left) {
                    maxHeap.poll();
                }
                while (!minHeap.isEmpty() && minHeap.peek()[1] < left) {
                    minHeap.poll();
                }
            }

            // Update the result with the maximum length of the window
            res = Math.max(right - left + 1, res);
            
            // Move the right pointer to expand the window
            right++;
        }

        return res;
    }
}
