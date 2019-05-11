package util;

import java.util.ArrayList;
import java.util.HashMap;

public class SortUtil {

    public ArrayList<Integer> sortList(ArrayList<Integer> list){
        ArrayList<Integer> indexes = new ArrayList<>();
        ArrayList<Integer> tempList = new ArrayList<Integer>(){{addAll(list);}};
        for(int i=0; i<list.size(); i++){
            indexes.add(i);
        }

        for(int i=0; i<tempList.size()-1; i++){
            int max = tempList.get(i);
            for(int j=i+1; j<tempList.size(); j++){
                if(tempList.get(j) > max){
                    int temp = tempList.get(j);
                    tempList.set(i, temp);
                    tempList.set(j, max);

                    max = tempList.get(i);

                    temp = indexes.get(i);
                    indexes.set(i, indexes.get(j));
                    indexes.set(j, temp);
                }
            }
        }

        return indexes;
    }

}
