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
	public boolean check(String [] tbl,String str)
        {
            boolean flag=false;
            for(int i=0;i<tbl.length;i++)
            {
                if(str==tbl[i]){
                    flag=true;
                }
            }
            return flag;
        }
	ArrayList <String> key=new ArrayList<String>();
	/**
	 * Application entry to your solution
	 *
	 * @see Thread#run()
	 */
	@Override
	public void run() {
                IO io=new IO();    //making reader object
                System.out.println("All set ...");
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
                    key.add("id");
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
                        while (itr1.hasNext()) {
                            Map.Entry pair = itr1.next();
                            if(i==0&&((String)pair.getKey())!=tables[0])
                            {
                                key.add((String)pair.getKey());
                                l++;
                            }
                            else if(i>0&&((String)pair.getKey())!=tables[0]){
                                if(!key.contains((String)pair.getKey()))
                                {
                                    key.add(l, (String)pair.getKey());
                                    json[i-1].put((String)pair.getKey(), null);
                                    l++;
                                }
                                else{
                                    l++;
                                }
                            }
                            else if((String)pair.getKey()==tables[0]){
                                l=0;
                            break;
                        }
                        }
                    }
                    System.out.println("test# "+t);
                    for(int i=0;i<json.length;i++)
                    {
                    for (String k : key) { 		      
                            System.out.print(k+" "); 		
                       }
                    System.out.println();
                        Iterator<Map.Entry> itr1 = json[i].entrySet().iterator();
                        while (itr1.hasNext()) {
                            Map.Entry pair = itr1.next();
                            System.out.print(pair.getValue()+" ");
                        }
                        System.out.println();
                    }

                }
                    System.out.println("Goodbye :)");
	}
}
