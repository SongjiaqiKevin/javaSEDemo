package entity;

import java.util.*;


/**
 * @author Kevin Song
 * Created on 2021/7/28.
 */
public class Test3 {

    public String longestCommonPrefix(String[] strs) {
        if(null==strs||strs.length==0){
            return "";
        }
        if(strs.length==1){
            return strs[0];
        }
        List<String> listString = new ArrayList<>();
        listString.addAll(Arrays.asList(strs));
        Collections.sort(listString);
        String first = listString.get(0);
        String last = listString.get(listString.size()-1);
        char[] firstChar = first.toCharArray();
        char[] lastChar = last.toCharArray();
        int length = Math.min(firstChar.length,lastChar.length);
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<length;i++){
            if(firstChar[i]==lastChar[i]){
                sb.append(firstChar[i]);
            }else {
                break;
            }
        }
        return sb.toString();
    }


}
