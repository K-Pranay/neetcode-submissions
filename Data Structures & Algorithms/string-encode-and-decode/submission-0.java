class Solution {

    public String encode(List<String> strs) {
        StringBuilder encode = new StringBuilder();
        for(String str : strs){
            encode.append(str.length()).append("#").append(str);
        }
        return encode.toString();
    }

    public List<String> decode(String str) {
        List<String> strs = new ArrayList<>();
        int i =0;
        int j =0;
        int len = str.length();
        int size = 0;
        while(i<len){
            j = i;
            while(str.charAt(j) != '#'){
                j++;
            }
            size = Integer.parseInt(str.substring(i,j));
            j++;
            strs.add(str.substring(j,j+size));
            i=j+size;
        }
        return strs;
    }
}
