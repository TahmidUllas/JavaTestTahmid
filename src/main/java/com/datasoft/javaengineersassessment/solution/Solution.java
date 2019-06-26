package com.datasoft.javaengineersassessment.solution;

import com.datasoft.javaengineersassessment.utils.IO;
public class Solution implements Runnable{
	
	
	/**
	 * Application entry to your solution
	 *
	 * @see Thread#run()
	 */
	@Override
	public void run() {
                IO io=new IO();
                int n=Integer.parseInt(io.readLine());
                String temp=io.readLine();
                int nT=Integer.parseInt(""+temp.charAt(0));
                int nD=Integer.parseInt(""+temp.charAt(2));
                //String [] temp=io.readLine.split(" ");
		System.out.println("All set ...");
		System.out.println("Goodbye :)");
                System.out.println("number of objects: "+n+"\nnumber of tables: "+nT+"\nnumber of json array: "+nD);
	
	}
}
