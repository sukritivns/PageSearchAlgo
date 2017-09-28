package com.company;

import java.util.*;

//This class has been defined for providing service

public class PageStrengthService {

    private Map<String,Integer>pageList= new HashMap<>();

    PageStrengthService()
    {}

    public void setPageList(Map<String,Integer> pageList)                     //List containing page weights for individual query object
    {
        this.pageList=pageList;
    }

    public Map<String,Integer> getPageList()
    {
        return  this.pageList;
    }


    public ArrayList<String> getPageRank()                                     //returns list of pages based on weight for each query object
    {
        Map<String,Integer> compList=this.getPageList();
        ArrayList<Integer> tempList=new ArrayList<>();
        ArrayList<String>  pageRankOrder=new ArrayList<>();
        for (Map.Entry<String,Integer> mapE : compList.entrySet())
        {
            tempList.add(mapE.getValue());
        }

        Collections.sort(tempList,Collections.reverseOrder());                 //Sorting pagelist in order of decreasing weights
        int in=0;
        String key;
        int value;
        while(in<compList.size())
        {
            for( Map.Entry<String,Integer> mapE1: compList.entrySet())
            {
                value=mapE1.getValue();
                key=mapE1.getKey();
                if(value==tempList.get(in))
                {
                    try {
                        if (!pageRankOrder.contains(key)) {
                            pageRankOrder.add(in, key);
                            break;
                        }
                    }
                    catch(Exception e)
                    {
                        return pageRankOrder;
                    }
                }

            }
            in++;
        }
        //Allowing max of 5 pages in final list
        if(pageRankOrder.size()>=5)
        {
            ArrayList<String> pageRankOrderSub=new ArrayList<>(pageRankOrder.subList(0,5));
            return pageRankOrderSub;
        }
        return  pageRankOrder;
    }
}
