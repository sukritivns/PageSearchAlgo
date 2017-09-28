package com.company;

import java.util.HashMap;
import java.util.Map;

public class Query {
    private String queryName;
    private int weight=8;
    private Map<String,Integer> mapQuery;
    private final String codeWord="Q";
    private int[] keywordWeight;

    Query(String[] queryKeywords, int queryCount)
    {
        this.mapQuery=new HashMap();
        for(int i=0;i<queryKeywords.length;i++)
        {
            this.mapQuery.put(queryKeywords[i],weight);
            weight--;
        }
        this.queryName=codeWord+queryCount;
    }


    public  Map<String,Integer> getKeyword()
    {
        return this.mapQuery;
    }

}

