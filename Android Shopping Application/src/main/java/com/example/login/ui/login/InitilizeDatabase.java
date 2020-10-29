package com.example.login.ui.login;

import android.os.Bundle;

import com.b07.database.DatabaseDriverAndroid;
import com.b07.database.helper.DatabaseInsertHelper;
import com.example.login.R;
import com.example.login.new_account;
import com.example.login.ui.login.LoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.lang.String;
import android.content.Intent;
import com.b07.*;
import android.content.Context;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;

public class InitilizeDatabase extends AppCompatActivity {

    private Context context= this;
//    DatabaseDriverAndroid dbDriver = new DatabaseDriverAndroid(context);
////    DatabaseInsertHelper dbInsert = new DatabaseInsertHelper(context);
    private DatabaseInsertHelper dbInsert;
    String name;
    int age;
    String address;
    String password;

    Button newAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_int_db);
        dbInsert = new DatabaseInsertHelper(this);

        final EditText nameInput = (EditText) findViewById(R.id.name);
        final EditText ageInput = (EditText) findViewById(R.id.age);
        final EditText addressInput = (EditText) findViewById(R.id.address);
        final EditText passwordInput = (EditText) findViewById(R.id.username);

        newAccount = (Button) findViewById(R.id.login);

        newAccount.setEnabled(true);

        newAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameInput.getText().toString();
                age = Integer.parseInt(ageInput.getText().toString());
                address = addressInput.getText().toString();
                password = passwordInput.getText().toString();
                //TODO: create account for user. --> insertCustomer(name, age, address, password);
                try {
                    dbInsert.insertNewUserHelper(name, age, address, password);
                }
                catch(Exception BadInputException){

                }
                startActivity(new Intent(InitilizeDatabase.this, LoginActivity.class));
            }
        });

    }
}
