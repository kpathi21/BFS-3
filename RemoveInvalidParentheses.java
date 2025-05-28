import java.util.*;

public class RemoveInvalidParentheses {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        if (s.isEmpty())
            return res;

        Queue<String> q = new LinkedList<>();
        q.add(s);

        HashSet<String> visited = new HashSet<>();
        visited.add(s);

        boolean flag = false;

        while (!q.isEmpty() && !flag) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                String curr = q.poll();
                if (isValid(curr)) {
                    res.add(curr);
                    flag = true;
                } else {
                    for (int k = 0; k < curr.length(); k++) {
                        if (Character.isAlphabetic(k))
                            continue;

                        String baby = curr.substring(0, k) + curr.substring(k + 1);

                        if (!visited.contains(baby)) {
                            visited.add(baby);
                            q.add(baby);
                        }
                    }
                }
            }
        }

        return res;
    }

    private boolean isValid(String s) {
        int counter = 0;
        for (char c : s.toCharArray()) {
            if (Character.isAlphabetic(c))
                continue;
            if (c == '(')
                counter++;
            else if (c == ')')
                counter--;

            if (counter < 0)
                return false;
        }

        return counter == 0;
    }
}

//TC: O(2^n), SC: O(n) where n is the length of the String
