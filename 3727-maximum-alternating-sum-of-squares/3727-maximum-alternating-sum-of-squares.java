import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] squares = new long[n];
        
        for (int i = 0; i < n; i++) {
            long val = nums[i];
            squares[i] = val * val;
        }
        
        // Sort the squared values in ascending order
        Arrays.sort(squares);
        
        long totalSum = 0;
        int mid = n / 2;
        
        // Subtract the smaller half and add the larger half
        for (int i = 0; i < n; i++) {
            if (i < mid) {
                totalSum -= squares[i];
            } else {
                totalSum += squares[i];
            }
        }
        
        return totalSum;
    }
}