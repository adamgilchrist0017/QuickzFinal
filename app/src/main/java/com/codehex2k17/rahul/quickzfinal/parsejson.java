package com.codehex2k17.rahul.quickzfinal;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by vidhant on 12/3/17.
 */

public class parsejson {
    public static String[] barcodeids;
    private static String[] nameitems;
    public static String[] finalprices;
    public static String[] Finalprices1;

    public static double Finalprice3;

    public static JSONArray cart=null;
    private String json;
    double price1;
    double price;
    double sum=0;
    public parsejson(String json){
        this.json=json;
    }

    public static String[] getNames() {
        return nameitems;
    }

    public static void setNames(String[] names) {
        nameitems = names;
    }

    protected void parsejson(){
        JSONObject jsonObject=null;
        try{
            jsonObject=new JSONObject(json);
            cart=jsonObject.getJSONArray(Config.JSON_ARRAYCART);
            barcodeids= new String[cart.length()];
            setNames(new String[cart.length()]);
            finalprices=new String[cart.length()];
            Finalprices1=new String[cart.length()];
            for (int j=0;j<cart.length();j++) {
                JSONObject jo1 = cart.getJSONObject(j);

                Finalprices1[j] = jo1.getString(Config.KEY_PRICE2);
                price1 = Double.parseDouble(parsejson.Finalprices1[j]);

                sum = sum + price1;
            }
            Finalprice3=sum;

            for (int i=0 ; i<cart.length() ; i++) {
                JSONObject jo = cart.getJSONObject(i);
                barcodeids[i] = jo.getString(Config.KEY_ID);
                getNames()[i] = jo.getString(Config.KEY_NAME2);
                finalprices[i] = jo.getString(Config.KEY_PRICE2);



            }



        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
