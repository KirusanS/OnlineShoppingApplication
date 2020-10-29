package com.example.admininterface;

import androidx.appcompat.app.AppCompatActivity;

import com.b07.database.helper.DatabaseInsertHelper;
import com.b07.exceptions.BadInputException;
import com.b07.exceptions.DatabaseInsertException;
import com.example.login.*;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.database.Cursor;
import android.content.ContentValues;

import com.b07.*;

import java.sql.SQLException;

public class AccountDetails extends AppCompatActivity {

    String name;
    String address;
    int age;
    String password;
    private Context context;


    EditText nameInput;
    EditText ageInput;
    EditText addressInput;
    EditText passwordInput;

    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_details);
        final DatabaseInsertHelper dbInsert = new DatabaseInsertHelper(context);

        nameInput = (EditText) findViewById(R.id.nameInputAccount);
        ageInput = (EditText) findViewById(R.id.ageInputAccount);
        addressInput = (EditText) findViewById(R.id.addressInputAccount);
        passwordInput = (EditText) findViewById(R.id.passwordInputAccount);

        submitButton = (Button) findViewById(R.id.btnSubmitAccount);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameInput.getText().toString();
                address = addressInput.getText().toString();
                age = Integer.valueOf(ageInput.getText().toString());
                password = passwordInput.getText().toString();

                try {
                    dbInsert.insertNewUserHelper(name, age, address, password);
                } catch (Exception BadInputException) {

                }
            }
        });
    }
}
