package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
   private static int pageCount=0,queryCount=0,objectCount=12;
   private static  Page[] pageInOb=new Page[objectCount];
   private static  Query[] queryInOb=new Query[objectCount];
   private  static PageStrengthService pageStrengthService =new PageStrengthService();

    public static void main(String[] args) {
        String input;
        Scanner scan = new Scanner(System.in);
        int noOfCase = objectCount;
        //Taking Test Case Input
        while (noOfCase > 0)
        {
            input = scan.nextLine();
            parseInput(input);
            noOfCase--;
        }

        Map<String,Integer> queryMap;
        Map<String,Integer> pageList;
        ArrayList<String> pageOrderList;


        for(int index=0;index<queryCount;index++) {
            queryMap = queryInOb[index].getKeyword();
            pageList = calculateStrength(queryMap);       // method call for calculating page strength
                                                          //Map pageList is returned with Page Number(key)& Weights(value)
            pageStrengthService.setPageList(pageList);
            pageOrderList=pageStrengthService.getPageRank();
            pageOrderList.add(0,"Q"+(index+1)+":");
            for( String pageOrder: pageOrderList)
            {
                System.out.print(pageOrder+" ");
            }
            System.out.println();
        }
    }

    //End of main()
    private static Map<String,Integer> calculateStrength(Map<String,Integer> queryMap)
    {
        String keyword;
        int weight,index=0;
        Map<String,Integer> listPageWeights=new HashMap<>();
        while(index<pageCount)
        {
            int pageWeight=0;
            for (Map.Entry<String,Integer> mapEntry : queryMap.entrySet()) {
                keyword= mapEntry.getKey();
                weight=mapEntry.getValue();
                pageWeight=pageWeight+pageInOb[index].calculatePageWeight(keyword,weight);

            }
            if(pageWeight!=0)
            {
                listPageWeights.put(pageInOb[index].getPageName(),pageWeight);
            }
            index++;
        }
    return listPageWeights;
    }

    private static void parseInput(String inputTest)
    {
        char c=inputTest.charAt(0);                                                          //Storing input for match
        String match= Character.toString(c);

        inputTest=inputTest.substring(2,inputTest.length());                                 //Splitting the input string to string array
        String[] keywords = inputTest.split(" ");

        if (match.equalsIgnoreCase("P"))
        {
            for(int index=0;index<keywords.length;index++) {
                pageInOb[pageCount] = new Page(keywords, pageCount + 1);
            }
            pageCount++;
        }
        else if (match.equalsIgnoreCase("Q"))
        {
            for(int index=0;index<keywords.length;index++) {
                queryInOb[queryCount] = new Query(keywords, queryCount + 1);
            }
            queryCount++;
        }
        else
            System.exit(0);

    }

}



