package co.paulfran.paulfranco.whatsappclone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity {

    Boolean loginModeActive = false;

    public void toggleLoginMode(View view) {
        //Log.i("Info", "Mode Toggled");
        Button loginSignupButton = (Button) findViewById(R.id.loginSignupButton);
        TextView toggleLoginModeTextView = (TextView) findViewById(R.id.toggleLoginModeTextView);

        if (loginModeActive) {
            loginModeActive = false;
            loginSignupButton.setText("Sign Up");
            toggleLoginModeTextView.setText("Or Log In");

        } else {
            loginModeActive = true;
            loginSignupButton.setText("Login");
            toggleLoginModeTextView.setText("Or Sign Up");

        }
    }


    public void signupLogin(View view) {
        EditText usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        EditText passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        if (loginModeActive){
            // Log User In
            ParseUser.logInInBackground(usernameEditText.getText().toString(), passwordEditText.getText().toString(), new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    // Check if there are arrors
                    if (e == null) {
                        // if no errors
                        Log.i("Info", "User is logged in");

                    } else {

                        String message = e.getMessage();

                        if (message.toLowerCase().contains("java")){
                            message = e.getMessage().substring(e.getMessage().indexOf(" "));
                        }

                        // if there are errors show user error message
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                    }
                }
            });

        } else {
            // Sign User Up

            ParseUser user = new ParseUser();
            // Get user name and set it to a string
            user.setUsername(usernameEditText.getText().toString());
            // Get password and set it to a string
            user.setPassword(usernameEditText.getText().toString());
            // Sign up the user
            user.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {
                    // Checks for an error
                    if (e == null) {
                        // if no error
                        Log.i("Info", "User signed up");
                    } else {

                        String message = e.getMessage();

                        if (message.toLowerCase().contains("java")){
                            message = e.getMessage().substring(e.getMessage().indexOf(" "));
                        }

                        // if errors, display errors to the user using a Toast
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();

                    }
                }
            });
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Whatsapp Login");

        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }
}
