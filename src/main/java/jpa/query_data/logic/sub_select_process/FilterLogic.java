package jpa.query_data.logic.sub_select_process;

import jpa.query_data.model.Filter;

import java.util.ArrayList;
import java.util.List;import jpa.resource.model.CO;

/**
 * The {@link FilterLogic} class contains the methods processing {@link Filter} class
 */
public class FilterLogic {

    /**
     * The method filters between a given list and filter list (left join (get value of filter) or right join (get value of given list that is not in the filter list))
     * @param ids a given list
     * @param filter right list
     * @return list after joining
     */
    public int[] process(int[] ids, Filter filter){
        List<Integer> rs ;
        //ids = [A, B, C] , filter.include = [A] -> result = [A]
        //just get the values in filter.include list
        if(filter.getIncludes() != null && filter.getIncludes().length != 0){
            return filter.getIncludes();
        }else{
            //ids = [A , B, C], filter.ignore = [A] -> result = [B, C]
            //left join given list
            boolean dup;
            rs = new ArrayList<Integer>();
            for (int id: ids){
                dup = true;
                for(int ignore: filter.getIgnores()){
                    if(id == ignore){
                        dup = false;
                        break;
                    }
                }
                if(dup)rs.add(id);
            }
        }
        int[] rsTmp = new int[rs.size()];
        for(int i = 0; i < rs.size(); i++) rsTmp[i] = rs.get(i);
        return rsTmp;
    }
}
