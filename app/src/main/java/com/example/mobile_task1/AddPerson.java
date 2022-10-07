package com.example.mobile_task1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class AddPerson extends AppCompatActivity implements View.OnClickListener {

    EditText edTextAddFname, edTextAddLname;
    Button btnAdd, btnGoToMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);

        edTextAddFname = findViewById(R.id.editTextAddFname);
        edTextAddLname = findViewById(R.id.editTextAddLname);

        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnGoToMain = findViewById(R.id.btnGoToMain);
        btnGoToMain.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnAdd:

                String fname = edTextAddFname.getText().toString();
                String lname = edTextAddLname.getText().toString();

                addPerson(view, fname, lname);
                break;
            case R.id.btnGoToMain:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }


    protected void addPerson(View v, String fname, String lname )
    {

        ConnectionHelper CH = new ConnectionHelper();
        Connection connection = CH.connectionClass();
        try {
            Log.w("Run: ", "Connection open!");

            Statement stmt = connection.createStatement();
            stmt.execute("INSERT INTO Persons(fname, lname) VALUES ('"+fname+"', '"+lname+"')");
            stmt.close();
            connection.close();
            Log.w("Run: ", "Connection close!");
        }
        catch (SQLException ex)
        {
            Log.w("SQLException error: ", ex.getMessage());
        }
        catch (Exception ex)
        {
            Log.w("Exception error: ", ex.getMessage());
        }
    }
}