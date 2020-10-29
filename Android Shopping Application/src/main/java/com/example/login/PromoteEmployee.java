package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import com.b07.users.Admin;
import com.example.login.*;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PromoteEmployee extends AppCompatActivity {

    int userId;
    EditText inputUserId;
    Button submitButton;
    boolean cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promote_employee);

        inputUserId = (EditText) findViewById(R.id.editUserId);
        submitButton = (Button) findViewById(R.id.btnSubmitPromote);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userId = Integer.valueOf(inputUserId.getText().toString());
                try {
                    cont = Admin.promoteEmployee(userId);
                    if (!cont) {
                        showToast("Invalid ID");
                    }
                } catch (Exception BadInputException) {
                        showToast("Invalid Input");
                }
            }
        });

    }
    private void showToast(String text) {
        Toast.makeText(PromoteEmployee.this, text, Toast.LENGTH_SHORT).show();
    }
}
