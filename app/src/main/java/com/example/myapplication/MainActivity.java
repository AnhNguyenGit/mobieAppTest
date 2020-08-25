package com.example.myapplication;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.Settings;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Button btn_login = (Button) findViewById(R.id.btn_login);
        final EditText txt_username = (EditText) findViewById(R.id.txt_username);
        final EditText txt_password = (EditText) findViewById(R.id.txt_password);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String token = Store.readData(getApplicationContext());
//                Gson jon = new Gson();
//                JsonObject obj =  jon.fromJson(token, JsonObject.class);
//                String registration_token = obj.get("registration_id").getAsString();
//                String DeviceId;
//                DeviceId = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
//                String data = String.format("UserName: %s, PassWord: %s and %s",txt_username.getText(), txt_password.getText(), "Kotlin");
//                Toast.makeText(getApplicationContext(),data,Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), token, Toast.LENGTH_SHORT).show();
//   ApiCall apiCall = new ApiCall();

                processLogin(txt_username.getText().toString(), txt_password.getText().toString());

            }
        });
    }

    private  void processLogin(String username, String password){
        if(username.isEmpty() || username == null || password.isEmpty() || password ==  null ){
            Toast.makeText(getApplicationContext(), "UserName and passoword is require", Toast.LENGTH_SHORT).show();
            return;
        }
        ApiCall api = new ApiCall(getApplicationContext());
        api.Login(username, password, getApplicationContext());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
