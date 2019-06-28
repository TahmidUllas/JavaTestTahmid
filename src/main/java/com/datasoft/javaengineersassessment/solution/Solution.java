package com.datasoft.javaengineersassessment.solution;

import com.datasoft.javaengineersassessment.utils.IO;
import org.json.simple.JSONArray; 
import org.json.simple.JSONObject; 
import org.json.simple.parser.*; 
import java.util.Iterator; 
import java.util.Map; 
import java.util.ArrayList;
import java.util.List;

public class Solution implements Runnable{
	
	/**
	 * Application entry to your solution
	 *
     * @return 
	 * @see Thread#run()
	 */
        public ArrayList <String> setKey(Iterator<Map.Entry> itr1,int i){
            ArrayList <String> key=new ArrayList<String>();
            key.add("id");
            int l=0;
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
                    JSONObject [] json=new JSONObject[nD];
                    int id=1;
                    for(int i=0;i<json.length;i++)
                    {
                        JSONParser parser = new JSONParser();
                        try{
                            json[i] = (JSONObject) parser.parse(io.readLine());
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
                            
                            if(i==0&&!(((String)pair.getKey()).equals(tables[k])))
                            {
                                key.add((String)pair.getKey());
                                l++;
                            }
                            else if(i>0&&!(((String)pair.getKey()).equals(tables[k]))){
                                if(!key.contains((String)pair.getKey()))
                                {
                                    key.add(l, (String)pair.getKey());
                                    l++;
                                }
                                else{
                                    l++;
                                }
                            }
                            else if(((String)pair.getKey()).equals(tables[k])){
                                l=0;
                                k++;
                                if(k>=tables.length)
                                {
                                    k--;
                                }
                            continue;
                        }
                        }
                        json[i].put("id", (i+1));
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
                                System.out.print(tables[1]);
                            }
                        }
                    System.out.println();

                    for (JSONObject json1 : json) //To print first table
                    {
                        for (int ind = 0; ind<keyArr.length; ind++) {
                            System.out.print(json1.getOrDefault(keyArr[ind], null) + " ");
                            if(ind==(keyArr.length-1)){
                            System.out.print(json1.get("id"));
                            }
                        }
                        
                        System.out.println();   
                    }
                    
                    System.out.println();
                    for(int tab=1;tab<tables.length;tab++){
                        
                        if(!(tables[tab].contains("(desc)")||tables[tab].contains("(asc)"))){
                            key=new ArrayList<String>();
                            Map newMap=((Map)json[0].get(tables[tab]));
                            for(int i=0;i<json.length;i++){
                                newMap= ((Map)json[i].get(tables[tab]));
                                Iterator<Map.Entry> itr1 = newMap.entrySet().iterator();
                                key=setKey(itr1,i);
                                newMap.put("id",(i+1));
                            }
                            keyArr=new String[key.size()];
                            int mInd=0;
                            for (String k : key) { 		      
                                    System.out.print(k+" ");
                                    keyArr[mInd]=k;
                                    mInd++;
                               }
                            System.out.println();
                            for (JSONObject json1 : json){
                                System.out.print(newMap.get("id"));
                                for (int ind = 0; ind<keyArr.length; ind++) {
                                System.out.print(newMap.getOrDefault(keyArr[ind], null) + " ");
                            }
                                System.out.println();
                            }
                            
                        
                            System.out.println();
                        }
                    }
                }
                    System.out.println("Goodbye :)");
	}
}
