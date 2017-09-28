package com.company;

import java.util.HashMap;
import java.util.Map;

public class Page {
    private final String codeWord="P";
    private String pageName;

    private String[] pageKeywords;
    private Map<String,Integer> mapPage;

    private static Map<String,Integer> pageWeights= new HashMap<>();

    public String getPageName()
    {
        return this.pageName;
    }
    Page(String[] pageKeywords,int pageCount )
    {
        int weight=8;
        //this.pageKeywords=pageKeywords;
        this.mapPage= new HashMap<>();
        for(int i=0;i<pageKeywords.length;i++)
        {
            this.mapPage.put(pageKeywords[i],weight);
            weight--;
        }
        this.pageName=codeWord+pageCount;
    }

    public int calculatePageWeight(String queryKeyword,int queryWeight)
    {
        int pageWeight=0;
        for(Map.Entry<String,Integer> pageE : this.mapPage.entrySet())
        {
            if(queryKeyword.equalsIgnoreCase(pageE.getKey()))

            {
                pageWeight =queryWeight * pageE.getValue();
                break;
            }

        }
        return pageWeight;
    }

}

