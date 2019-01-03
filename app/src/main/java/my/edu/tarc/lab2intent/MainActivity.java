package my.edu.tarc.lab2intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    public static final String MESSAGE_TAG = "my.edu.tarc.lab2intent.MESSAGE";
    private static final int REPLY_REQUEST_CODE = 1;

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Main","onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Main","onDestroy");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Main","onPause");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Main", "onCreate");
    }

    //Event handler for the SEND button
    public void sendMessage(View view){
        EditText editTextMessage;
        editTextMessage = findViewById(R.id.editTextMessage);
        if(TextUtils.isEmpty(editTextMessage.getText())){
           editTextMessage.setError(getString(R.string.error_message));
           return;
        }
        String stringMesg = editTextMessage.getText().toString();
        //Use an Intent and pass data to the SecondActivity
        Intent intent = new Intent(this,SecondActivity.class);
        intent.putExtra(MESSAGE_TAG,stringMesg);
        startActivityForResult(intent, REPLY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REPLY_REQUEST_CODE && resultCode == RESULT_OK){
            TextView textViewReply;
            textViewReply = findViewById(R.id.textViewReply);
            //TODO: Obtain a reply from the Intent data
            if(data.hasExtra(SecondActivity.REPLY_TAG)){
                String stringReply = data.getStringExtra(SecondActivity.REPLY_TAG);
                textViewReply.setText(stringReply);
            }
        }
    }
}
