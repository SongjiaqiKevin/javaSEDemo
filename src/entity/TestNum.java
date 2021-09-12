package entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Kevin Song
 * Created on 2021/7/23.
 */
public class TestNum {
    public static void main(String[] args){

    }

    public static Integer getSum(int target, List<Integer> nums){
        if(null==nums||nums.size()==0){
            return null;
        }
        Integer sum = 0;
        if(nums.size()<=3){
            for (Integer num : nums) {
                sum += num;
            }
            return sum;
        }

        List<Integer> sums = new ArrayList<>();

        for(int i=0;i<nums.size();i++){
            for(int v=i+1;v< nums.size();v++){
                for(int p=v+1;p<nums.size();p++){
                    sums.add(nums.get(i)+nums.get(v)+nums.get(p));
                }
            }
        }
        Collections.sort(sums);
        int min=0;
        boolean zhengshu = true;
        for(Integer sumNum:sums){
            if(sumNum==target){
                return target;
            }
            if(sumNum<target){
                int cha= target-sumNum;
                if(min==0){
                    min=cha;
                }else {
                    min = Math.min(cha,min);
                }
                zhengshu=false;
            }
            if(sumNum>target){
                int cha = sumNum-target;
                if(min==0){
                    min=cha;
                }else {
                    min = Math.min(cha,min);
                }
                zhengshu=true;
            }
        }
        return target+min*(zhengshu?1:-1);
    }
}
