package com.datasoft.javaengineersassessment.solution;

import com.datasoft.javaengineersassessment.utils.IO;
//import org.json.simple.JSONArray; 
//import org.json.simple.JSONObject; 
import org.apache.wink.json4j.OrderedJSONObject;
import org.apache.wink.json4j.*;
import java.util.Iterator; 
import java.util.Map; 
import java.util.ArrayList;

public class Solution implements Runnable{
	
	/**
	 * Application entry to your solution
	 *
     * @param itr1
     * @param i
     * @return 
	 * @see Thread#run()
	 */
        public ArrayList <String> setKey(Iterator<Map.Entry> itr1,int i){
            ArrayList <String> key=new ArrayList<String>();
            
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
        public ArrayList <JSONObject> sortDesc(ArrayList <JSONObject> aList,String str){
            try{
                String first="",second="";
                JSONObject joTemp=new JSONObject();
                for(int i=0;i<aList.size();i++){
                    for(int j=1;j<aList.size()-i;j++){
                        first=(String)(aList.get(j)).get(str);
                        second=(String)(aList.get(j-1)).get(str);
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
	@Override
	public void run() {
                IO io=new IO();//making reader object
                System.out.println("All set ...");
                ArrayList <String> key=new ArrayList<String>();
                key.add("id");
                String temp="";
                int n=Integer.parseInt(io.readLine());    //input for test case number
                for(int t=1;t<=n;t++){
                    
                    String [] nTnD=(io.readLine()).split(" ");
                    int nT=Integer.parseInt(nTnD[0]),nD=Integer.parseInt(nTnD[1]);;
                    String [] tables=(io.readLine()).split(" ");
                    System.out.println("number of objects: "+n+"\nnumber of tables: "+nT+"\nnumber of json array: "+nD);
                    for(int i=0;i<tables.length;i++)
                    {
                        System.out.println(tables[i]);
                    }
                    OrderedJSONObject [] json=new OrderedJSONObject[nD];
                    int id=1;
                    for(int i=0;i<json.length;i++)
                    {
                        try{
                            json[i] =new OrderedJSONObject(io.readLine());
                            System.out.println(json[i]);
                            
                        }
                        catch(Exception e){
                        e.printStackTrace();
                        }
                        Iterator<Map.Entry> itr1 = json[i].entrySet().iterator();
                        int l=0;
                        int k=0;
                        if(!(tables.length==1)){
                            k++;
                        }
                        while (itr1.hasNext()) {
                            Map.Entry pair = itr1.next();
                            
                            if(i==0&&(isFirst(tables,(String)pair.getKey())))
                            {
                                key.add((String)pair.getKey());
                                l++;
                            }
                            else if(i>0&&(isFirst(tables,(String)pair.getKey()))){
                                if(!key.contains((String)pair.getKey()))
                                {
                                    key.add(l, (String)pair.getKey());
                                    l++;
                                }
                                else{
                                    l++;
                                }
                            }
                            else if(!(isFirst(tables,(String)pair.getKey()))){
                                l=0;
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
                            
                        }
                    }
                    System.out.println("test# "+t);
                    String [] keyArr=new String[key.size()];
                    int kInd=0;
                    for (String k : key) { 		      
                            System.out.print(k+" ");
                            keyArr[kInd]=k;
                            kInd++;
                       }
                    if(tables.length>1){
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
                    

                    for (OrderedJSONObject json1 : json) //To print first table
                    {
                        for (int ind = 0; ind<keyArr.length; ind++) {
                            System.out.print(json1.getOrDefault(keyArr[ind], null) + " ");
                            if(ind==(keyArr.length-1)){
                                try{
                                    System.out.print(json1.get("id"));
                                }
                                catch(Exception e){
                                    
                                }
                            }
                        }
                        
                        System.out.println();   
                    }
                    
                    System.out.println();
                    for(int tab=1;tab<tables.length;tab++){  //to  handle multiple tables
                        
                        if(!(tables[tab].contains("(desc)")||tables[tab].contains("(asc)"))){
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
                                catch(Exception e)
                                {
                                    
                                }
                            }
                            keyArr=new String[key.size()];
                            int mInd=0;
                            for (String k : key) { 		      
                                    System.out.print(k+" ");
                                    keyArr[mInd]=k;
                                    mInd++;
                                    
                               }
                            System.out.println();
                            for (Map nMap : newMap){
                                
                                for (int ind = 0; ind<keyArr.length; ind++) {
                                System.out.print(nMap.getOrDefault(keyArr[ind], null) + " ");
                            }
                                System.out.println();
                            }
                            
                        
                            System.out.println();
                        }
                        else{
                            String str = tables[tab]; 
                            String[] arrOfStr = str.split("[()]");
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
                                        System.out.println();
                                        idVal++;
                                    }
                                }
                                catch(Exception e){  
                                    
                                }   
                            }
                            keyArr=new String[key.size()];
                            int mInd=0;
                            for (String k : key) { 		      
                                System.out.print(k+" ");
                                keyArr[mInd]=k;
                                    mInd++;
                            }
                            System.out.println();
                            if(arrOfStr[1].contains("desc")){
                                arrList=sortDesc(arrList,keyArr[keyArr.length-1]);
                            }
                            else{
                                arrList=sortAsc(arrList,keyArr[keyArr.length-1]);
                            }
                            //arrList=sort(arrList,keyArr[keyArr.length-1]);
                            for (Iterator<JSONObject> it = arrList.iterator(); it.hasNext();) {
                                try{
                                JSONObject item = it.next();
                                // to print Json Array
                                for (String k : keyArr) {
                                    
                                    System.out.print(item.get(k)+" ");
                                    }
                                    
                                }
                                catch(Exception e){}
                                System.out.println();
                            }
                        }
                    }
                }
                    System.out.println("Goodbye :)");
	}
}
