package com.example.mobile_task1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ChangePerson extends AppCompatActivity implements View.OnClickListener {

    TextView tvPersonID;
    EditText etFname, etLname;
    Button btnChange, btnDelete;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_person);

        tvPersonID = findViewById(R.id.tvChangePersonID);
        etFname = findViewById(R.id.etChangeFname);
        etLname = findViewById(R.id.etChangeLname);

        btnChange = findViewById(R.id.btnChange);
        btnChange.setOnClickListener(this);

        btnDelete = findViewById(R.id.btnDelete);
        btnDelete.setOnClickListener(this);


        Bundle arguments = getIntent().getExtras();

        tvPersonID.setText(arguments.get("personID").toString());
        etFname.setText(arguments.get("fname").toString());
        etLname.setText(arguments.get("lname").toString());
    }

    @Override
    public void onClick(View view) {

        String idPerson;
        switch (view.getId()) {

            case R.id.btnChange:
                idPerson = tvPersonID.getText().toString();
                String fname = etFname.getText().toString();
                String lname = etLname.getText().toString();

                changePerson(view, fname, lname, idPerson);
                startActivity(new Intent(this, ShowData.class));
                break;
            case R.id.btnDelete:
                idPerson = tvPersonID.getText().toString();
                deletePerson(view, idPerson);
                startActivity(new Intent(this, ShowData.class));
                break;
        }
    }
        protected void changePerson (View v, String fname, String lname, String idPerson)
        {

            ConnectionHelper CH = new ConnectionHelper();
            Connection connection = CH.connectionClass();
            try {
                Log.w("Run: ", "Connection open!");

                Statement stmt = connection.createStatement();
                stmt.execute("UPDATE Persons SET fname = '"+fname+"', lname = '"+lname+"' WHERE id_person = " + idPerson);
                stmt.close();
                connection.close();
                Log.w("Run: ", "Connection close!");

                Toast.makeText(getBaseContext(),"Успешно изменено", Toast.LENGTH_SHORT).show();

            } catch (SQLException ex) {
                Log.w("SQLException error: ", ex.getMessage());
            } catch (Exception ex) {
                Log.w("Exception error: ", ex.getMessage());
            }
        }
    protected void deletePerson (View v, String idPerson)
    {

        ConnectionHelper CH = new ConnectionHelper();
        Connection connection = CH.connectionClass();
        try {
            Log.w("Run: ", "Connection open!");

            Statement stmt = connection.createStatement();
            stmt.execute("DELETE Persons WHERE id_person = " + idPerson);
            stmt.close();
            connection.close();
            Log.w("Run: ", "Connection close!");

            Toast.makeText(getBaseContext(),"Успешно удалено", Toast.LENGTH_SHORT).show();

        } catch (SQLException ex) {
            Log.w("SQLException error: ", ex.getMessage());
        } catch (Exception ex) {
            Log.w("Exception error: ", ex.getMessage());
        }
    }
    }
