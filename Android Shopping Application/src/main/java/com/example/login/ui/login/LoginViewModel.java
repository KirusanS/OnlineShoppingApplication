package com.example.login.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.util.Patterns;

import com.b07.database.helper.DatabaseSelectHelper;
import com.b07.users.UserFactory;
import com.b07.users.*;
import com.b07.database.helper.*;
import com.example.login.customershop.MainActivity;
import com.example.login.data.LoginRepository;
import com.example.login.data.Result;
import com.example.login.data.model.LoggedInUser;
import com.example.login.R;
import android.content.Context;
import android.content.Intent;

public class LoginViewModel extends ViewModel {
    Context c;

    DatabaseSelectHelper dbSelect = new DatabaseSelectHelper(c);
    UserFactory userFactory = new UserFactory(c);
    private MutableLiveData<LoginFormState> loginFormState = new MutableLiveData<>();
    private MutableLiveData<LoginResult> loginResult = new MutableLiveData<>();
    private LoginRepository loginRepository;

    LoginViewModel(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    LiveData<LoginFormState> getLoginFormState() {
        return loginFormState;
    }

    LiveData<LoginResult> getLoginResult() {
        return loginResult;
    }

    public int login(String username, String password) {
        // can be launched in a separate asynchronous job

        boolean result;
        try{
            result = dbSelect.validateCustPassword(Integer.parseInt(username),password) || dbSelect.validatePassword(Integer.parseInt(username),password) || dbSelect.validateEmpPassword(Integer.parseInt(username),password);
        }
        catch(Exception SQLExcpetion) {
            result = false;
            loginResult.setValue(new LoginResult(R.string.login_failed));
            return 1;
        }
        if (result) {
            int loggedInId = Integer.parseInt(username);
            try {
                if (dbSelect.getRoleName(dbSelect.getUserRoleId(loggedInId)).equals("EMPLOYEE")){
                    return 0;

                }
                if (dbSelect.getRoleName(dbSelect.getUserRoleId(loggedInId)).equals("ADMIN")){
                    return 1;

                }
                if (dbSelect.getRoleName(dbSelect.getUserRoleId(loggedInId)).equals("CUSTOMER")){
                    return 2;
                }
            }
            catch (Exception SQLException){
                loginResult.setValue(new LoginResult(R.string.login_failed));
            }
        } else {
            loginResult.setValue(new LoginResult(R.string.login_failed));
        }
        return -1;
    }

    public void loginDataChanged(String username, String password) {
        if (!isUserNameValid(username)) {
            loginFormState.setValue(new LoginFormState(R.string.invalid_username, null));
        } else if (!isPasswordValid(password)) {
            loginFormState.setValue(new LoginFormState(null, R.string.invalid_password));
        } else {
            loginFormState.setValue(new LoginFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        return true;
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return true;
    }
}
