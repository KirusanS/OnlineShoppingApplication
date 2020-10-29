package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import com.b07.store.EmployeeInterface;
import com.example.login.*;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class AddCustomer extends AppCompatActivity {
    String name;
    String address;
    int age;
    String password;
    int id;

    EditText nameInput;
    EditText ageInput;
    EditText addressInput;
    EditText passwordInput;

    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);

        nameInput = (EditText) findViewById(R.id.nameInputCustomer);
        ageInput = (EditText) findViewById(R.id.ageInputCustomer);
        addressInput = (EditText) findViewById(R.id.addressInputCustomer);
        passwordInput = (EditText) findViewById(R.id.passwordInputCustomer);

        submitButton = (Button) findViewById(R.id.btnSubmitCustomer);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameInput.getText().toString();
                address = addressInput.getText().toString();
                age = Integer.valueOf(ageInput.getText().toString());
                password = passwordInput.getText().toString();
                try {
                    id = EmployeeInterface.createNewCustomer(name, age, address, password);
                } catch (Exception BadInputException) {
                    showToast("Invalid input. Please try again.");
                }
            }
        });
    }



    private void showToast(String text) {
        Toast.makeText(AddCustomer.this, text, Toast.LENGTH_SHORT).show();
    }
}
