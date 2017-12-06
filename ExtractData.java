package com.example.cindy.tess;

import android.util.Log;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cindy on 3/12/2017.
 */

public class ExtractData {
    private String input;
//    private HashMap<String,String> essentialData=new HashMap<String,String>();
    private JSONObject essentialData=new JSONObject();
    private String output=new String();

    ExtractData(String input){
        this.input=input;
    }
    public JSONObject ExtractionOfData()
    {

        List<String> myList = new ArrayList<String>(Arrays.asList(this.input.split(" ")));
        int i=0;
        StringBuilder output=new StringBuilder("");
        Pattern p = Pattern.compile("\\d{2}/\\d{2}/\\d{2}");

        while(myList.get(i).equals("SUBTOTAL")==false)
        {
            if(myList.get(i).equals("Mains")==false)
            {
                // match
                Matcher m = p.matcher(myList.get(i));
                if(m.matches())
                {
                    try{
                        essentialData.put("Address",output.toString());
                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }
//                    essentialData.put("Address",output.toString());
                    i=i+8;
                }
                else
                {
                    output.append(myList.get(i)+" ");
                }
            }
            else if(myList.get(i).equals("Mains"))
            {
                i++;
                while(myList.get(i).equals("SUBTOTAL")==false)
                {
                    StringBuilder outputForFood=new StringBuilder("");
                    while(myList.get(i).contains("$")==false)
                    {
                        outputForFood.append(myList.get(i)+" ");
                        i++;
                    }
//                    essentialData.put(outputForFood.toString(),myList.get(i));
                    try{
                        essentialData.put(outputForFood.toString(),myList.get(i));

                    }
                    catch (Exception ex)
                    {
                        ex.printStackTrace();
                    }

                    i++;
                }
//                Log.i("MAPSIZE",Integer.toString(essentialData.size()));
//                try{
//                    Log.i("ADDRESS",essentialData.get("Address"));
//                }
//                catch (Exception ex)
//                {
//                    ex.printStackTrace();
//                }

//                output=essentialData.toString();
                return essentialData;
            }
            i++;
        }

        return essentialData;
    }
}
