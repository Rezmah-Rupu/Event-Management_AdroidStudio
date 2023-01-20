package edu.ewubd.lab_eventmanagement;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.NameValuePair;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.BasicNameValuePair;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class eventinfo extends AppCompatActivity {
    EditText name, datetime, place, description, capacity, budget, phone, email;
    Button save, eventlist, share;
    RadioButton indoor, outdoor, online;
    private String key = "";




    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.eventinfo);



        name = findViewById(R.id.name_id);
        datetime = findViewById(R.id.td);
        place = findViewById(R.id.place);
        description = findViewById(R.id.des);
        capacity = findViewById(R.id.cap);
        budget = findViewById(R.id.bugdet);
        phone = findViewById(R.id.phone);
        email = findViewById(R.id.email);
        indoor = findViewById(R.id.rbindoor);
        outdoor = findViewById(R.id.rboutdoor);
        online = findViewById(R.id.rbonline);

        save = findViewById(R.id.btnsave);
        save.setOnClickListener(v-> funcSave());

        share = findViewById(R.id.btnshare);
        share.setOnClickListener(v-> finish());

        eventlist = findViewById(R.id.btnlist);
        eventlist.setOnClickListener(v-> cancel());

        //loadData("rup1669399450669");  //


        Intent i = getIntent();
        if(i.hasExtra("EventKey")){
            key = i.getStringExtra("EventKey");
        }

    }

    private void cancel() {
        Intent i = new Intent(eventinfo.this,upcoming_event.class);
        startActivity(i);
    }

    public void funcSave() {
        /*System.out.println(name.getText().toString().trim());
        System.out.println(place.getText().toString().trim());

        if(indoor.isChecked()){
            System.out.println("Indoor Clicked");
        }
        else if(outdoor.isChecked()){
            System.out.println("Outdoor Clicked");
        }
        else if (indoor.isChecked()){
            System.out.println("Online Clicked");
        }

        System.out.println(datetime.getText().toString().trim());
        System.out.println(capacity.getText().toString().trim());
        System.out.println(budget.getText().toString().trim());
        System.out.println(email.getText().toString().trim());
        System.out.println(phone.getText().toString().trim());
        System.out.println(description.getText().toString().trim()); */

        String name_ = name.getText().toString();
        String time = datetime.getText().toString();
        String place_ = place.getText().toString();
        String caps = capacity.getText().toString();
        String bud = budget.getText().toString();
        String email_ = email.getText().toString();
        String phone_ = phone.getText().toString();
        String des = description.getText().toString();

        String type = "";
        if (indoor.isChecked()){
            type = "indoor";
        }
        else if (outdoor.isChecked()){
            type = "outdoor";
        }
        else if (online.isChecked()){
            type = "online";
        }

        //if (key.length() == 0 ){
            key = name_ + System.currentTimeMillis();
     //   }

        System.out.println("Perfectly worked" + key);

        //  String value = name_ + "___"  + place_+"___" + time+"___" + caps + "___" +bud+"___"+email_+"___" + phone_+"___"+des+"___"+type+"___";

        String value = name_ + "___"+ place_+"___" + caps + "___"+ bud +"___"+email_+"___"+phone_+"___"+des+"___" + time + "___" + type+"___";

        KeyValueDB kvdb = new KeyValueDB(this);
        kvdb.insertKeyValue(key, value);

        String[] keys = {"action", "id", "semester", "key", " event"};
        String[] values = {"backup", "2020-1-60-247", "2022-3", key, value};
        httpRequest(keys, values);
    }

    @SuppressLint("StaticFieldLeak")
    public void httpRequest(final String[] keys, final String [] values){
        new AsyncTask<Void,Void,String>(){
            protected String doInBackground(Void...param){
                try{
                    List<NameValuePair>params = new ArrayList<NameValuePair>();
                    for (int i=0; i< keys.length; i++){
                        params.add(new BasicNameValuePair(keys[i],values[i]));
                    }
                    String data = JSONParser.getInstance().makeHttpRequest("https://muthosoft.com/univ/cse489/index.php","POST",params);
                    return data;
                } catch (Exception ex){
                    ex.printStackTrace();
                }
                return null;
            }

            protected void onPostExecute(String data){
                if (data != null){
                    try {
                        updateEventListByServerData(data);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }.execute();
    }

    private void updateEventListByServerData(String data) throws JSONException {
        JSONObject jo = new JSONObject(data);
        if(jo.has("events")){
            JSONArray ja = jo.getJSONArray("events");
            for(int i=0; i<ja.length(); i++){
                JSONObject event = ja.getJSONObject(i);
                String eventKey = event.getString("key");
                String eventValue = event.getString("value");
                // split eventValue to show in event list


            }
            } else {
            System.out.println(jo);
        }
        }

    public void loadData(String key){
        KeyValueDB kvdb = new KeyValueDB(this);
        String v = kvdb.getValueByKey(key);
        String values[] = v.split("___");
        for (int i = 0; i<values.length; i++ ){
            System.out.println(values[i]);
        }

        //rup1669399450669

    }

}