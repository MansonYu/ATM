package com.manson.atm;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
        String userid = ed_userid.getText().toString();
        final String passwd = ed_passwd.getText().toString();

        //Firebase 內容
//        atm666-f5202
//          users
//              manson
//                  passwd:"123456"
//              moon
//                  passwd:"9999"
//              rex
//                  passwd:"8888"


        FirebaseDatabase.getInstance().getReference("users").child(userid).child("passwd")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String cloud_pw = (String) dataSnapshot.getValue();
                        if(cloud_pw.equals(passwd)){
                            setResult(RESULT_OK);
                            finish();
                        }
                        else{
                            new AlertDialog.Builder(LoginActivity.this)
                                    .setTitle("錯誤")
                                    .setMessage("輸入密碼錯誤")
                                    .setPositiveButton("OK", null)
                                    .show();

                            ed_userid.setText("");
                            ed_passwd.setText("");
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

//        簡易式判斷登入
//        if(userid.equals("manson") && passwd.equals("123456")){
//            setResult(RESULT_OK);
//            finish();
//        }
//        else{
//            new AlertDialog.Builder(this)
//                    .setTitle("錯誤")
//                    .setMessage("輸入密碼錯誤")
//                    .setPositiveButton("OK", null)
//                    .show();
//
//            ed_userid.setText("");
//            ed_passwd.setText("");
//        }

    }

    public void quit(View view){

//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("提示")
//                .setMessage("您確定要離開?")
//                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        finish();
//                    }
//                })
//                .setNegativeButton("取消", null)
//                .show();


        new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("確定要離開?")
                .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("取消", null)
                .show();


    }
}
