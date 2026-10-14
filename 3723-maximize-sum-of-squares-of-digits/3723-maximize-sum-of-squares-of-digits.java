class Solution {
    public String maxSumOfSquares(int num, int sum) {
        // If the sum is greater than the maximum possible sum for `num` digits (all 9s), return ""
        if (sum > 9L * num) {
            return "";
        }

        int nines = sum / 9;
        int rem = sum % 9;

        StringBuilder sb = new StringBuilder();
        
        // Append all the 9s
        for (int i = 0; i < nines; i++) {
            sb.append('9');
        }

        // Append the remainder digit if it's greater than 0
        if (rem > 0 || sb.length() == 0) {
            // Note: If nines == 0 and rem == 0, but num > 0, we handle it below via padding.
            if (rem > 0) {
                sb.append(rem);
            }
        }

        // If we haven't reached the required length `num`, pad with '0's
        while (sb.length() < num) {
            sb.append('0');
        }

        // If the length exceeds `num` (which can happen if rem == 0 and nines == num but we need a specific format, though handled by check), return empty if invalid
        if (sb.length() > num) {
            return "";
        }

        return sb.toString();
    }
}