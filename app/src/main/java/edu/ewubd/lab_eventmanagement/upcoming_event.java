package edu.ewubd.lab_eventmanagement;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.NameValuePair;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.BasicNameValuePair;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class upcoming_event extends AppCompatActivity {
    Button create, history, exit;
    ListView lvEvents;
    ArrayList<Event> events;
    CustomEventAdapter adapter;

    @SuppressLint("MissingInflatedId")

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upcoming_event);

        create = findViewById(R.id.create);
        history = findViewById(R.id.history);

        create.setOnClickListener(v->createEvent());

        //initialize list-reference by ListView object defined in XML
        lvEvents = findViewById(R.id.lvEvents);
        //load events from database if there is any
      //  remoteServer();
        loadData();
    }

    private void createEvent() {
        Intent i = new Intent(upcoming_event.this,eventinfo.class);
        startActivity(i);
    }


    /*
    private void remoteServer() {
        String[] keys = {"action", "id", "semester"};
        String[] values = {"restore", "2020-1-60-247", "2022-3"};
        httpRequest(keys, values);
    }

     */

/*
    @SuppressLint("StaticFieldLeak")
    public void httpRequest(final String keys[], final String values[]){
        new AsyncTask<Void,Void,String>(){
            protected String doInBackground(Void...param){
                try{
                    List<NameValuePair> params = new ArrayList<NameValuePair>();
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

 */


    /*
    private void updateEventListByServerData(String data) throws JSONException {
        JSONObject jo = new JSONObject(data);
        if(jo.has("events")){
            JSONArray ja = jo.getJSONArray("events");
            for(int i=0; i<ja.length(); i++){
                JSONObject event = ja.getJSONObject(i);
                String eventKey = event.getString("key");
                String eventValue = event.getString("value");
                // split eventValue to show in event list

                String[] fieldValues = eventValue.split("___");

                events = new ArrayList<>();

                if (fieldValues.length==9){
                    String name = fieldValues[0];
                    String dateTime = fieldValues[1];
                    String place = fieldValues[2];
                    String eventType = fieldValues[3];
                    String capacity = fieldValues[4];
                    String budget = fieldValues[5];
                    String email = fieldValues[6];
                    String phone = fieldValues[7];
                    String description = fieldValues[8];


                    Event e = new Event(eventKey, name, dateTime, place, eventType, capacity,budget, email, phone, description);
                    events.add(e);
                } else {
                    System.out.printf("length="+fieldValues.length);
                }


                adapter = new CustomEventAdapter(this, events);
                lvEvents.setAdapter(adapter);

                lvEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                        // String item = (String) parent.getItemAtPosition(position);
                        System.out.println(position);
                        Intent i = new Intent(upcoming_event.this, eventinfo.class);
                        i.putExtra("EventKey", events.get(position).key);
                        startActivity(i);
                    }
                });
                // handle the long-click on an event-list item
                lvEvents.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        //String message = "Do you want to delete event - "+events[position].name +" ?";
                        String message = "Do you want to delete event - "+events.get(position).name +" ?";
                        showDialog(message, "Delete Event", events.get(position).key);
                        return true;
                    }
                });



            }} else {
                System.out.println(jo);
            }}

     */
/*
                private void showDialog(String message, String title, String key){
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage(message);
                    builder.setTitle(title);

                    builder.setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int id) {

                                    dialogInterface.cancel();
                                    remoteServer();
                                    adapter.notifyDataSetChanged();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int id) {
                                    dialogInterface.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }


    }

 */


   private void loadData() {
        events = new ArrayList<>();
        KeyValueDB db = new KeyValueDB(this);
        Cursor rows = db.execute("SELECT * FROM key_value_pairs");
        if (rows.getCount() == 0) {
            return;
        }


        //events = new ArrayList<>();
        while (rows.moveToNext()) {
            String key = rows.getString(0);
            String eventData = rows.getString(1);
            String[] fieldValues = eventData.split("___");

            if (fieldValues.length==9){


           /* String name = fieldValues[0];
            String place = fieldValues[1];
            String dateTime = fieldValues[7];
            String eventType = fieldValues[8];
            String capacity = fieldValues[2];
            String budget = fieldValues[3];
            String email = fieldValues[4];
            String phone = fieldValues[5];
            String description = fieldValues[6];

            */


                String name = fieldValues[0];
                String place = fieldValues[1];
                String capacity = fieldValues[2];
                String budget = fieldValues[3];
                String email = fieldValues[4];
                String phone = fieldValues[5];
                String description = fieldValues[6];
                String dateTime = fieldValues[7];
                String eventType = fieldValues[8];

            System.out.println("testR" + name);
            System.out.println("testR" + dateTime);
            System.out.println("testR" + place);
            System.out.println("testR" + eventType);
            System.out.println("testR" + capacity);
            System.out.println("testR" + budget);
            System.out.println("testR" + email);
            System.out.println("testR" + phone);
            System.out.println("testR" + description);


            // Event e = new Event(key, name, place, dateTime, eventType, capacity,budget, email, phone, description);

                Event e = new Event(key, name, place, capacity, budget, email, phone, description, dateTime, eventType);


            events.add(e);
         }
            else{
                System.out.println(fieldValues.length);
            }
        }
        db.close();
        adapter = new CustomEventAdapter(this, events);
        lvEvents.setAdapter(adapter);

        // handle the click on an event-list item
        lvEvents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                // String item = (String) parent.getItemAtPosition(position);
                System.out.println(position);
                Intent i = new Intent(upcoming_event.this, eventinfo.class);
                i.putExtra("EventKey", events.get(position).key);
                startActivity(i);
            }
        });
        // handle the long-click on an event-list item
        lvEvents.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //String message = "Do you want to delete event - "+events[position].name +" ?";
                String message = "Do you want to delete event - "+events.get(position).name +" ?";
                System.out.println(message);
                showDialog(message, "Delete Event", events.get(position).key);
                return true;
            }
        });
    }
    private void showDialog(String message, String title, String key){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setTitle(title);

        builder.setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {
                        KeyValueDB db = new KeyValueDB(getApplicationContext());
                        db.deleteDataByKey(key);
                        dialogInterface.cancel();
                        // remoteServer();
                        loadData();
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int id) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}