import java.util.*;

class Solution {
    public List<String> braceExpansionII(String expression) {
        Set<String> set = parseExpression(expression);
        List<String> result = new ArrayList<>(set);
        Collections.sort(result);
        return result;
    }

    private Set<String> parseExpression(String expr) {
        Set<String> resultSet = new HashSet<>();
        List<Set<String>> currentGroups = new ArrayList<>();
        
        int i = 0;
        int n = expr.length();

        while (i < n) {
            char c = expr.charAt(i);

            if (c == '{') {
                int braceCount = 1;
                int j = i + 1;
                while (j < n && braceCount > 0) {
                    if (expr.charAt(j) == '{') braceCount++;
                    else if (expr.charAt(j) == '}') braceCount--;
                    j++;
                }
                
                // Parse the inner expression recursively
                Set<String> innerSet = parseExpression(expr.substring(i + 1, j - 1));
                currentGroups.add(innerSet);
                i = j;
            } else if (Character.isLetter(c)) {
                // Collect contiguous letters as a single string option
                StringBuilder sb = new StringBuilder();
                while (i < n && Character.isLetter(expr.charAt(i))) {
                    sb.append(expr.charAt(i));
                    i++;
                }
                Set<String> letterSet = new HashSet<>();
                letterSet.add(sb.toString());
                currentGroups.add(letterSet);
            } else if (c == ',') {
                // Evaluate concatenated groups up to ',' and add to result set
                resultSet.addAll(combineGroups(currentGroups));
                currentGroups.clear();
                i++;
            }
        }

        // Add remaining concatenated groups after the loop finishes
        resultSet.addAll(combineGroups(currentGroups));
        return resultSet;
    }

    private Set<String> combineGroups(List<Set<String>> groups) {
        Set<String> combined = new HashSet<>();
        if (groups.isEmpty()) return combined;

        combined.add("");
        for (Set<String> group : groups) {
            Set<String> nextCombined = new HashSet<>();
            for (String prefix : combined) {
                for (String suffix : group) {
                    nextCombined.add(prefix + suffix);
                }
            }
            combined = nextCombined;
        }

        return combined;
    }
}