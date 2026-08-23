class Solution {
    public boolean sumGame(String num) {
        int n = num.length();

        int leftSum = 0;
        int rightSum = 0;

        int leftQuestion = 0;
        int rightQuestion = 0;

        for (int i = 0; i < n / 2; i++) {
            char c = num.charAt(i);

            if (c == '?') {
                leftQuestion++;
            } else {
                leftSum += c - '0';
            }
        }

        for (int i = n / 2; i < n; i++) {
            char c = num.charAt(i);

            if (c == '?') {
                rightQuestion++;
            } else {
                rightSum += c - '0';
            }
        }

        int diff = leftSum - rightSum;
        int questionDiff = leftQuestion - rightQuestion;

        // Same number of '?' on both sides
        if (questionDiff == 0) {
            return diff != 0;
        }

        // Alice can force a win when the difference
        // cannot be balanced by Bob's moves.
        if (Math.abs(questionDiff) % 2 == 1) {
            return true;
        }

        int remaining = Math.abs(questionDiff) / 2;

        if (questionDiff > 0) {
            return diff + remaining * 9 != 0;
        } else {
            return diff - remaining * 9 != 0;
        }
    }
}