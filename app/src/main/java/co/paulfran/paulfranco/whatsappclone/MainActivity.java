package co.paulfran.paulfranco.whatsappclone;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity {

    public void signupLogin(View view) {
        EditText usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        EditText passwordEditText = (EditText) findViewById(R.id.passwordEditText);
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
                    // if errors, display errors to the user using a Toast
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();

                }
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ParseAnalytics.trackAppOpenedInBackground(getIntent());
    }
}
