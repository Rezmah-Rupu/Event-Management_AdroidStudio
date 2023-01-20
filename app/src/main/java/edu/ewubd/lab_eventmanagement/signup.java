package edu.ewubd.lab_eventmanagement;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class signup extends AppCompatActivity {

    EditText name, email, phone, userid,password,reenter;
    Button exit, go;
    CheckBox checkbox1, checkbox2;
    TableRow row1, row2, row3,row6;
    TextView login;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SharedPreferences sp = this.getSharedPreferences("logineventinfo", MODE_PRIVATE);
        SharedPreferences.Editor e = sp.edit();

        String V1 = sp.getString("Rem_user","No");
        String V2 = sp.getString("Rem_log","No");


        if(V2.equals("Yes")){
            Intent i = new Intent(signup.this,upcoming_event.class);
            startActivity(i);
            return;
        }

        setContentView(R.layout.signup);

        name = findViewById(R.id.name_id);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        userid = findViewById(R.id.userid);
        password = findViewById(R.id.pass);
        reenter = findViewById(R.id.repass);

        checkbox1 = findViewById(R.id.chechbox1);
        checkbox2 = findViewById(R.id.checkbox2);

        row1 = findViewById(R.id.row1);
        row2 = findViewById(R.id.row2);
        row3 = findViewById(R.id.row3);
        row6 = findViewById(R.id.row6);

        String u = sp.getString("user_id","");
        if(u.length() > 0){
            if(V1.equals("Yes")){
                userid.setText(u);
                checkbox1.setChecked(true);
            }
            HideRow(null);
        }

        go = findViewById(R.id.btngo);
        go.setOnClickListener(view-> funcGo());

        login = findViewById(R.id.login);
        login.setOnClickListener(view-> HideRow(view));

        exit = findViewById(R.id.btnexit);
    }

    private void HideRow(View view){
        row1.setVisibility(view.GONE);
        row2.setVisibility(view.GONE);
        row3.setVisibility(view.GONE);
        row6.setVisibility(view.GONE);
    }

    public void funcGo() {
        System.out.println(name.getText().toString().trim());
        System.out.println(email.getText().toString().trim());
        System.out.println(phone.getText().toString().trim());
        System.out.println(userid.getText().toString().trim());
        System.out.println(password.getText().toString().trim());
        System.out.println(reenter.getText().toString().trim());

        if (checkbox1.isChecked()){
            System.out.println("Remember UserID checked");

        } if(checkbox2.isChecked()){
            System.out.println("Remember Password is checked");
        }

        SharedPreferences sp = this.getSharedPreferences("logineventinfo", MODE_PRIVATE);
        SharedPreferences.Editor e = sp.edit();


        e.putString("user_id",userid.getText().toString());
        e.putString("user_name",name.getText().toString());
        e.putString("password",password.getText().toString());
        e.putString("phone",phone.getText().toString());
        e.putString("email",email.getText().toString());

        if (checkbox1.isChecked()){
            e.putString("Rem-user","Yes");
        } else {
            e.putString("Rem_user","No");
        }

        if(checkbox2.isChecked()){
            e.putString("Rem_log","Yes");
        }
        else {
            e.putString("Rem_log","No");
        }
        e.apply();

            Intent i = new Intent(signup.this,upcoming_event.class);
            startActivity(i);
            return;


    }
}

