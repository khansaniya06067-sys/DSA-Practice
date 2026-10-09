import java.util.Stack;

class Solution {
    public int minInsertions(String s) {
        Stack<Character> st = new Stack<>();
        int n = s.length();
        int i = 0;
        int count = 0;

        while (i < n) {
            if (s.charAt(i) == '(') {
                st.push(s.charAt(i));
                i++;
            } else { // s.charAt(i) == ')'
                // Check if we have a consecutive double closing parenthesis '))'
                if (i + 1 < n && s.charAt(i + 1) == ')') {
                    if (st.size() > 0) {
                        st.pop(); // Matches existing '(' with '))'
                    } else {
                        count++;  // Need an extra '(' before '))'
                    }
                    i += 2; // Processed both ')'
                } else {
                    // Single ')' found -> Needs an extra ')' to make '))'
                    count++; 

                    if (st.size() > 0) {
                        st.pop(); // Matches existing '(' with the completed '))'
                    } else {
                        count++;  // Need an extra '(' as well
                    }
                    i++; // Processed single ')'
                }
            }
        }

        // Every remaining '(' in the stack needs two ')'
        if (st.size() > 0) {
            count += st.size() * 2;
        }

        return count;
    }
}