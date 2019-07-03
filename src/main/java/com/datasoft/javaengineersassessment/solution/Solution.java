package com.datasoft.javaengineersassessment.solution;

import com.datasoft.javaengineersassessment.utils.IO;
import org.apache.wink.json4j.*;
import java.util.*;
import java.lang.*;
import java.io.*;
public class Solution implements Runnable{
	
	
         /**
         * @param itr1 Iterator
         * @param i Integer
         * @return ArrayList
         */
    public ArrayList <String> setKey(Iterator<Map.Entry> itr1,int i){
        ArrayList <String> key=new ArrayList<>();
        key.add("id");
        int l=1;
        while (itr1.hasNext()) {
            Map.Entry pair = itr1.next();
            if(!key.contains((String)pair.getKey()))
            {
                key.add(l, (String)pair.getKey());
                l++;
            }
            else {
                key.add((String)pair.getKey());
                l++;
            }
        }
        return key;
    }
        /**
         * @param item String Array
         * @param str String to check
         * @return true if first table
         * to Check whether in first table or not
         */
    public boolean isFirst(String [] item,String str){
        boolean flag=true;
        for(String temp :item){
            if(temp.contains(str))
            {
                flag=false;
            }
        }
        return flag;
    }
         /**
         * @param aList ArrayList
         * @param k String key to sort
         * @return ArrayList of JSONObject
         * 
         * to sort Array in descending order
         */
    public ArrayList <JSONObject> sortDesc(ArrayList <JSONObject> aList,String k){
        try{
            String first="",second="";
            JSONObject joTemp=new JSONObject();
            for(int i=0;i<aList.size();i++){
                for(int j=1;j<aList.size()-i;j++){
                    first=(String)(aList.get(j)).get(k);
                    second=(String)(aList.get(j-1)).get(k);
                    if(first.compareToIgnoreCase(second)>0){
                        joTemp=aList.get(j-1);
                        aList.set(j-1, aList.get(j));
                        aList.set(j, joTemp);
                    }
                }
            }
        return aList;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
         /**
         * @param aList ArrayList
         * @param k String key to sort
         * @return ArrayList of JSONObject
         * 
         * to sort Array in descending order
         */
    public ArrayList <JSONObject> sortAsc(ArrayList <JSONObject> aList,String str){
        try{
            String first="",second="";
            JSONObject joTemp=new JSONObject();
            for(int i=0;i<aList.size();i++){
                for(int j=1;j<aList.size()-i;j++){
                    first=(String)(aList.get(j)).get(str);
                    second=(String)(aList.get(j-1)).get(str);
                    if(first.compareToIgnoreCase(second)<0){
                        joTemp=aList.get(j-1);
                        aList.set(j-1, aList.get(j));
                        aList.set(j, joTemp);
                    }
                }
            }
        return aList;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Application entry to your solution
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        //IO io=new IO(); 
        try
        {//Buffer reader
        StringBuilder sb = new StringBuilder();
        String line;
        BufferedReader io = new BufferedReader(new InputStreamReader(System.in));
        
        
        System.out.println("All set ...");
        int n=Integer.parseInt(io.readLine());    //input for test case number
        for(int t=1;t<=n;t++){
            ArrayList <String> key=new ArrayList<String>();
            key.add("id");
            String [] nTnD=(io.readLine()).split(" ");
            int nT=Integer.parseInt(nTnD[0]),nD=Integer.parseInt(nTnD[1]);;
            String [] tables=(io.readLine()).split(" ");
            OrderedJSONObject [] json=new OrderedJSONObject[nD];
            Stack <String> paren=new  Stack<>();
            int ob=0;
            line= io.readLine();
            while (!line.equals("")){
                while(true){
                    
                    if(line.equals("{")||line.equals("[")){
                        sb.append(line);
                        paren.push(line);
                        System.out.println(paren.toString());
                        line=io.readLine();
                    }
                    else if(line.equals("}")||line.equals("]")){
                        sb.append(line);
                        paren.pop();
                        line=io.readLine();
                        if(paren.isEmpty()){
                            json[ob]=new OrderedJSONObject(sb.toString());
                            System.out.println(sb.toString());
                            sb=new StringBuilder();
                            ob++;
                            break;
                        }
                    }
                    else{
                        sb.append(line);
                        line=io.readLine();
                    }
                }
            }
            for(int i=0;i<json.length;i++)
            {
                   
                    //json[i] =new OrderedJSONObject(sb.toString());  //takes input as JSON DATA 
                Iterator<Map.Entry> itr1 = json[i].entrySet().iterator();
                int l=0;
                int k=0;
                if(!(tables.length==1)){
                    k++;
                }
                while (itr1.hasNext()) {
                    Map.Entry pair = itr1.next();
                    System.out.println((String)pair.getKey());
                        if(i==0&&(isFirst(tables,(String)pair.getKey())))
                        {
                            key.add((String)pair.getKey());
                            l++;

                        }
                    else if(i>0&&(isFirst(tables,(String)pair.getKey()))){
                        if(!key.contains((String)pair.getKey()))
                        {
                            key.add(l,(String)pair.getKey());
                            l++;
                        }
                        else{
                            l++;
                        }
                    }
                    else if(!(isFirst(tables,(String)pair.getKey()))){
                        l++;
                        k++;
                        if(k>=tables.length)
                        {
                            k--;
                        }
                    continue;
                }
            }
                try{
                    json[i].put("id", (i+1));
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
            System.out.println();
            System.out.println("test# "+t);    //printing test cases
            System.out.println(tables[0]);    //printing first table
            for (String k : key) { 		      
                    System.out.print(k+" ");
               }
            if(tables.length>1)     
            {
                if(!(tables[1].contains("(desc)")||tables[1].contains("(asc)")))
                {
                    System.out.println(tables[1]);
                }
                else{
                    String str = tables[1]; 
                String[] arrOfStr = str.split("[()]");
                System.out.println(arrOfStr[0]);
                }
            }
            else{
                System.out.println();
            }


            for (OrderedJSONObject json1 : json) //To print first table
            {
                for (int ind = 0; ind<key.size(); ind++) {
                    System.out.print(json1.getOrDefault(key.get(ind), null) + " ");
                    if(ind==(key.size()-1)&&tables.length>1){
                        try{
                            System.out.print(json1.get("id"));
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                }
                System.out.println();   
            }
            System.out.println();
            for(int tab=1;tab<tables.length;tab++){  //to  handle multiple tables
                if(!(tables[tab].contains("(desc)")||tables[tab].contains("(asc)"))) //checking whether JSONObject or JSONArray
                {
                    System.out.println(tables[tab]);
                    key=new ArrayList<String>();
                    Map [] newMap=new Map[nD];
                    for(int i=0;i<json.length;i++){
                        try{
                            newMap[i]= ((Map)json[i].get(tables[tab]));
                            Iterator<Map.Entry> itr1 = newMap[i].entrySet().iterator();
                            key=setKey(itr1,i);
                            newMap[i].put("id",(i+1));
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                    }
                    
                    for (String k : key) { 		      
                            System.out.print(k+" ");
                       }
                    
                    System.out.println();
                    
                    for (Map nMap : newMap){
                        for (int ind = 0; ind<key.size(); ind++){
                            System.out.print(nMap.getOrDefault(key.get(ind), null) + " ");
                        }
                        System.out.println();
                    }
                    System.out.println();
                }
                else{      //enters if JSONArray
                    String[] arrOfStr = tables[tab].split("[()]");
                    tables[tab]=arrOfStr[0];
                    System.out.println(tables[tab]);
                    key=new ArrayList<String>();
                    key.add("id");
                    key.add(tables[0]);
                    key.add(arrOfStr[0]);
                    ArrayList <JSONObject> arrList=new ArrayList<JSONObject>();
                    JSONObject jotemp=new JSONObject();
                    for(int i=0,idVal=1;i<nD;i++){
                        try{
                            JSONArray jA=(JSONArray)json[i].get(tables[tab]);
                            for(int a=0;a<jA.size();a++){
                                jotemp=new JSONObject();
                                jotemp.put(tables[0],(i+1));
                                jotemp.put("id",idVal);
                                jotemp.put(tables[tab],jA.get(a));
                                arrList.add(jotemp);
                                idVal++;
                            }
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }   
                    }
                    
                    for (String k : key) { 		      
                        System.out.print(k+" ");
                    }
                    System.out.println();
                    if(arrOfStr[1].contains("desc")){
                        arrList=sortDesc(arrList,key.get(key.size()-1));
                    }
                    else{
                        arrList=sortAsc(arrList,key.get(key.size()-1));
                    }

                    for (Iterator<JSONObject> it = arrList.iterator(); it.hasNext();) {
                        try{
                        JSONObject item = it.next();
                        for (String k : key) {
                            System.out.print(item.get(k)+" ");
                            }
                        }
                        catch(Exception e){
                            e.printStackTrace();
                        }
                        System.out.println();
                    }
                }
            }
            //sb=new StringBuilder(); //to skip the empty line input after each test case
            //line="";
        }
        io.close();
        }
        catch(Exception e)
        {
        }
       
    }
}
