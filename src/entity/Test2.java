package entity;

/**
 * @author Kevin Song
 * Created on 2021/7/9.
 */
public class Test2 {
    public static void main(String[] agrs) {
        String[] sss = {"ssss","ttt","ssss"};
        System.out.println(getIndexStringLength(sss));
    }


    public static int getIndexStringLength(String[] strings) {
        if (null == strings) {
            return 0;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
            String s = strings[i] + "#";
            if (!sb.toString().contains(s)) {
                sb.append(s);
            }
        }

        return sb.toString().length();
    }
}
