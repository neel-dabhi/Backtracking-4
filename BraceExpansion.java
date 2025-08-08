// TC: O(k^n) n = number of groups, k = max group len
// SC: O(n + k^n) recursion stack + result list
class Solution {
    List<List<Character>> groups;
    List<String> result;
    public String[] expand(String s) {
        int n = s.length();
        int idx =0;
        result = new ArrayList<String>();
        groups = new ArrayList<List<Character>>();

        while(idx < n){
            char ch = s.charAt(idx);
            List<Character> group = new ArrayList<>();

            if(ch == '{'){
                idx++;
                while(s.charAt(idx) != '}'){
                    if(s.charAt(idx) != ','){
                        group.add(s.charAt(idx));
                    }
                    idx++;
                }
                idx++;
            }else{
                group.add(ch);
                idx++;
            }

            Collections.sort(group);
            groups.add(group);
        }

        helper(0, new StringBuilder());
        String[] arr = result.toArray(new String[result.size()]);
        return arr;
    }

    private void helper(int idx, StringBuilder path){
        // base
        if(idx == groups.size()){
            result.add(path.toString());
            return;
        }

        // logic
        List<Character> group = groups.get(idx);
        for(int i=0; i< group.size(); i++){
            char ch = group.get(i);
            int size = path.length();
            // action
            path.append(ch);
            // recurse
            helper(idx +1, path);
            // backtrack
            path.setLength(size);
        }

    }
}
