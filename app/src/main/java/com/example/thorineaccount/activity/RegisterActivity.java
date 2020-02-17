package com.example.thorineaccount.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thorineaccount.R;


public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button buttonRegister = (Button)findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(new View.OnClickListener() {//注册按钮的点击事件
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    private void register(){
        EditText editTextName = (EditText)findViewById(R.id.editTextName);
        EditText editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        EditText editTextPassword2 = (EditText)findViewById(R.id.editTextPassword2);
        EditText editTextPrompt = (EditText) findViewById(R.id.editTextPrompt);

        String name = editTextName.getText().toString();
        if(name.length()<1) {
            Toast.makeText(this,"昵称不能为空",Toast.LENGTH_SHORT).show();
            return;
        }
        String pwd = editTextPassword.getText().toString();
        String pwd2 = editTextPassword2.getText().toString();
        if (pwd.length() < 1){
            Toast.makeText(this,"密码不能为空.",Toast.LENGTH_LONG).show();
            return;
        }
        if (!pwd.equals(pwd2)){
            Toast.makeText(this,"两次密码不相同.",Toast.LENGTH_LONG).show();
            return;
        }
        String prompt = editTextPrompt.getText().toString();
        //用SharedPreferences方式存储数据
        SharedPreferences sp = this.getSharedPreferences("tinyaccount", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("name",name);
        editor.putString("password",pwd);
        editor.putString("prompt",prompt);

        editor.apply();
        finish();
    }

}
