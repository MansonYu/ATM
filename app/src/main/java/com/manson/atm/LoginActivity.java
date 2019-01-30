package com.manson.atm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText ed_userid;
    private EditText ed_passwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ed_userid = findViewById(R.id.ed_userid);
        ed_passwd = findViewById(R.id.ed_passwd);

    }

    public void login(View view){
        String id = ed_userid.getText().toString();
        String pw = ed_passwd.getText().toString();

        if(id.equals("manson") && pw.equals("123456")){
            setResult(RESULT_OK);
            finish();
        }
    }

    public void quit(View view){
        finish();
    }
}
