package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import com.b07.store.EmployeeInterface;
import com.example.login.*;

public class AddEmployee extends AppCompatActivity {
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
        setContentView(R.layout.activity_add_employee);

        nameInput = (EditText) findViewById(R.id.nameInput);
        ageInput = (EditText) findViewById(R.id.ageInput);
        addressInput = (EditText) findViewById(R.id.addressInput);
        passwordInput = (EditText) findViewById(R.id.passwordInput);

        submitButton = (Button) findViewById(R.id.BtnSubmit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameInput.getText().toString();
                address = addressInput.getText().toString();
                age = Integer.valueOf(ageInput.getText().toString());
                password = passwordInput.getText().toString();
                try {
                    id = EmployeeInterface.createNewEmp(name, age, address, password);
                } catch (Exception BadInputException) {
                    showToast("Invalid input. Please try again.");
                }
            }
        });
    }

    private void showToast(String text) {
        Toast.makeText(AddEmployee.this, text, Toast.LENGTH_SHORT).show();
    }
}
