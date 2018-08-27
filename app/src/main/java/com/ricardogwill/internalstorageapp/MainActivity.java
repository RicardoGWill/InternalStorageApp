package com.ricardogwill.internalstorageapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        writeEditText = findViewById(R.id.write_editText);
        retreivedTextView = findViewById(R.id.retrieved_textView);
    }

    EditText writeEditText;
    TextView retreivedTextView;

    // Note: Try/catch portion can be automatically created via "Alt + Enter".
    public void saveButtonOnClick(View view) {
        String myTextMessage = writeEditText.getText().toString();
        try {
            FileOutputStream fileOutputStream = openFileOutput("InternalStorageAppSavedText.txt", MODE_PRIVATE);
            fileOutputStream.write(myTextMessage.getBytes());
            fileOutputStream.close();
            Toast.makeText(getApplicationContext(), "Text saved.", Toast.LENGTH_SHORT).show();
            writeEditText.setText("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // Note: Try/catch portion can be automatically created via "Alt + Enter".
    public void retrieveButtonOnClick(View view) {
        try {
            FileInputStream fileInputStream = openFileInput ("InternalStorageAppSavedText.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            String lines;
            while ((lines = bufferedReader.readLine()) != null) {
                stringBuffer.append(lines + "\n");
            }
            retreivedTextView.setText(stringBuffer.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
