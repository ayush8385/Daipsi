package com.digitalhain.daipsi.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.digitalhain.daipsi.R;

public class createAccountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
    }

    public void goto_login(View view) {
        Intent intent = new Intent(createAccountActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}