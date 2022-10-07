package com.example.mobile_task1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

public class ShowData extends AppCompatActivity implements View.OnClickListener{

    RadioButton btnSortFnmae;
    RadioButton btnSortLname;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_show_data);


    }

    TextView tvPersonId, tvFname, tvLname;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        tvPersonId = findViewById(R.id.tvPersonID);
        tvFname = findViewById(R.id.tvFname);
        tvLname = findViewById(R.id.tvLname);


        btnSortFnmae = findViewById(R.id.btnSortFname);
        btnSortFnmae.setOnClickListener(this);

        btnSortLname = findViewById(R.id.btnSortLname);
        btnSortLname.setOnClickListener(this);


        GetList("select * from Persons");


    }

    SimpleAdapter ad;



    public void GetList(String qu)
    {
        ListView lstv = (ListView) findViewById(R.id.listview1);
        List<Map<String, String>> MyDataList = null;
        ListItem MyData = new ListItem();
        MyDataList = MyData.getList(qu);

        String[] Fromw = {"id_person", "fname", "lname"};
        int[] Tow = {R.id.tvPersonID, R.id.tvFname, R.id.tvLname};

        ad = new SimpleAdapter(ShowData.this, MyDataList, R.layout.listlayouttemplate, Fromw, Tow);
        lstv.setAdapter(ad);
    }

    @Override
    public void onClick(View view) {


        switch (view.getId()) {

            case R.id.btnSortLname:

                GetList("select * from Persons order by lname");

                break;
            case R.id.btnSortFname:

                GetList("select * from Persons order by fname");

                break;
        }
    }





}