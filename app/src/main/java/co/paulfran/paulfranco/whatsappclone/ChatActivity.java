package co.paulfran.paulfranco.whatsappclone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Intent intent = getIntent();

        String activeUser = intent.getStringExtra("username");

        setTitle("Chat with " + activeUser);

        Log.i("Infor", activeUser);
    }
}