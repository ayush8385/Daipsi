package com.digitalhain.daipsi.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.digitalhain.daipsi.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void goto_create_account(View view) {
        Intent intent = new Intent(LoginActivity.this, createAccountActivity.class);
        startActivity(intent);
        finish();
    }
}