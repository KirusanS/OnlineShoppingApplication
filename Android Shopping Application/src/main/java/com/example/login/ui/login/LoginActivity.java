package com.example.login.ui.login;

import android.app.Activity;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.b07.database.DatabaseDriverAndroid;
import com.b07.database.DatabaseDriverAndroid;
import com.b07.database.helper.DatabaseInsertHelper;
import com.b07.database.helper.DatabaseSelectHelper;
import com.example.login.customershop.MainActivity;
import com.example.login.new_account;
import com.example.login.R;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private new_account new_account;
    DatabaseSelectHelper dbSelect = new DatabaseSelectHelper(this);
    DatabaseDriverAndroid dbDriver = new DatabaseDriverAndroid(this);
    DatabaseInsertHelper dbInsert = new DatabaseInsertHelper(this);
    DatabaseDriverAndroid dbDr = new DatabaseDriverAndroid(this);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO: check if the DB currently exists, otherwise make a new DB and get user to login

        super.onCreate(savedInstanceState);
        boolean dbExists;
        long userid;
        try {
            userid = dbInsert.insertNewUserHelper("1", 1, "1", "1");
        }
        catch(Exception BadInputException){
            
        }
        try{
            int numUsers = dbSelect.getUsersDetailsHelper().size();
            if (numUsers == 0) {
                dbExists = false;
            }
            dbExists = true;
        }
        catch (Exception SQLException){
            dbExists = false;
        }
        if(!dbExists){
            startActivity(new Intent(LoginActivity.this, InitilizeDatabase.class));
        }
        else {
            setContentView(R.layout.activity_login);
            loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                    .get(LoginViewModel.class);

            final EditText usernameEditText = findViewById(R.id.username);
            final EditText passwordEditText = findViewById(R.id.password);
            final Button loginButton = findViewById(R.id.login);
            final Button new_account = findViewById(R.id.new_account);

            loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
                @Override
                public void onChanged(@Nullable LoginFormState loginFormState) {
                    if (loginFormState == null) {
                        return;
                    }
                    loginButton.setEnabled(loginFormState.isDataValid());
                    if (loginFormState.getUsernameError() != null) {
                        usernameEditText.setError(getString(loginFormState.getUsernameError()));
                    }
                    if (loginFormState.getPasswordError() != null) {
                        passwordEditText.setError(getString(loginFormState.getPasswordError()));
                    }
                }
            });

            loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
                @Override
                public void onChanged(@Nullable LoginResult loginResult) {
                    if (loginResult == null) {
                        return;
                    }
                    if (loginResult.getError() != null) {
                        showLoginFailed(loginResult.getError());
                    }
                    if (loginResult.getSuccess() != null) {
                        updateUiWithUser(loginResult.getSuccess());
                    }
                    setResult(Activity.RESULT_OK);

                    //Complete and destroy login activity once successful
                    finish();
                }
            });

            TextWatcher afterTextChangedListener = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    // ignore
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    // ignore
                }

                @Override
                public void afterTextChanged(Editable s) {
                    loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
            };
            usernameEditText.addTextChangedListener(afterTextChangedListener);
            passwordEditText.addTextChangedListener(afterTextChangedListener);
            passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        loginViewModel.login(usernameEditText.getText().toString(),
                                passwordEditText.getText().toString());
                    }
                    return false;
                }
            });

            new_account.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(LoginActivity.this, new_account.class));
                    //new_account;
                }
            });
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int typeOfAccount = loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                    if (typeOfAccount == 0) {
                        //TODO: go to EMPLOYEE ACCOUNT
                    } else if (typeOfAccount == 1) {
                        startActivity(new Intent(LoginActivity.this, com.example.login.AdminInterface.class));
                    } else if (typeOfAccount == 2) {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));

                    } else {
                        startActivity(new Intent(LoginActivity.this, LoginActivity.class));
                    }
                    //startActivity(new Intent(LoginActivity.this, MainActivity.class));

                }
            });
        }
    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}
