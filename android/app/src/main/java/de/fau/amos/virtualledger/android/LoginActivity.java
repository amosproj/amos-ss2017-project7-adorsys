package de.fau.amos.virtualledger.android;

import android.app.Application;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import javax.inject.Inject;

import de.fau.amos.virtualledger.R;
import de.fau.amos.virtualledger.android.auth.login.LoginProvider;
import de.fau.amos.virtualledger.android.dagger.module.AppModule;

/**
 * Created by sebastian on 14.05.17.
 */

public class LoginActivity extends AppCompatActivity {

    @Inject
    LoginProvider loginProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        init();

        setContentView(R.layout.login);


        Button button_register = (Button) findViewById(R.id.loginButton);

        final TextView textview = (TextView) findViewById(R.id.textViewFailLogin);

        button_register.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        final String userID = ((EditText) findViewById(R.id.userIDField)).getText().toString();
                        final String password = ((EditText) findViewById(R.id.SecretField)).getText().toString();

                        loginProvider.login(userID, password);
                        if(loginProvider.isLoggedIn()){
                            executeNextActivityMenu();
                        }else{
                            textview.setTextColor(Color.RED);
                            textview.setText("Login failed!");
                        }
                    }
                }
        );
    }
    private void init() {
        ((App) getApplication()).getNetComponent().inject(this);
    }

    private void executeNextActivityMenu() {
        Intent intent = new Intent(this, MainActivity_Menu.class);
        startActivity(intent);
    }
}
