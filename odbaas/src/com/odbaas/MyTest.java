/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.odbaas;

/**
 *
 * @author sanjay
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
class MyTest
{
	public static void main(String args[])
	{
		JSONArray array = new JSONArray();
		JSONObject obj1 = new JSONObject();
		obj1.put("name","Name1");
		obj1.put("age","Proper");
		JSONObject obj2 = new JSONObject();
		obj2.put("name","Name1");
		obj2.put("age","Proper");
                obj2.put("Valuezzz","Valuezzz");
                obj2.put("saluezzz","saluezzz");
                obj2.put("saluez111zz","saluez111zz");
                obj2.put("asedrfter","asedrfter");
                array.put(obj1);
                array.put(obj2);
                String res = array.toString();
		System.out.println(res);
                
                JSONArray nextarray = new JSONArray(res);
                for(int i=0;i<nextarray.length();++i)
                {
                    JSONObject obj = nextarray.getJSONObject(i);
                    Iterator<String> it=obj.keys();
                    List<String> keys = new ArrayList<String>();
                    while(it.hasNext())
                    {
                         keys.add((String)it.next());
                       
                    }
                    System.out.println(String.join(",",keys));
                }
                int aef=1;
                
	}
	
}