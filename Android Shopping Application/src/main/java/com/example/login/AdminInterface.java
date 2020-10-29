package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;

import com.example.login.ui.login.LoginActivity;

import android.view.View;
import com.example.login.*;
import com.example.admininterface.AccountDetails;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class AdminInterface extends AppCompatActivity {

    private Button btnMove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.

        btnMove = findViewById(R.id.BtnSetInventory);
        btnMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToSetInventory();
            }
        });
        btnMove = findViewById(R.id.BtnAddEmployee);
        btnMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToAddEmployee();
            }
        });
        btnMove = findViewById(R.id.BtnAddCustomer);
        btnMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToAddCustomer();
            }
        });
        btnMove = findViewById(R.id.BtnPromoteEmployee);
        btnMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToPromoteEmployee();
            }
        });
        btnMove = findViewById(R.id.btnAccountDetails);
        btnMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveToAccountDetails();
            }
        });
    }

    private void moveToSetInventory() {
        Intent intent = new Intent(AdminInterface.this, SetInventory.class);
        startActivity(intent);
    }
    private void moveToAddEmployee() {
        Intent intent = new Intent(AdminInterface.this, AddEmployee.class);
        startActivity(intent);
    }
    private void moveToAddCustomer() {
        Intent intent = new Intent(AdminInterface.this, AddCustomer.class);
        startActivity(intent);
    }
    private void moveToPromoteEmployee() {
        Intent intent = new Intent(AdminInterface.this, PromoteEmployee.class);
        startActivity(intent);
    }
    private void moveToAccountDetails() {
        Intent intent = new Intent(AdminInterface.this, AccountDetails.class);
        startActivity(intent);
    }


}
