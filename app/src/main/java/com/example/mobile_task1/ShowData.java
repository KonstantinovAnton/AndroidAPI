package com.example.mobile_task1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

public class ShowData extends AppCompatActivity implements View.OnClickListener{

    RadioButton btnSortFnmae;
    RadioButton btnSortLname;
    Button btnSearch;

    EditText etSearch;
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

        tvPersonId = findViewById(R.id.tvChangePersonID);
        tvFname = findViewById(R.id.tvFname);
        tvLname = findViewById(R.id.tvLname);

        btnSortFnmae = findViewById(R.id.btnSortFname);
        btnSortFnmae.setOnClickListener(this);

        btnSortLname = findViewById(R.id.btnSortLname);
        btnSortLname.setOnClickListener(this);

        btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(this);

        etSearch = findViewById(R.id.etSearch);


        ListView listView = findViewById(R.id.listview1);
        List<String> list = GetList("select * from Persons");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                tvFname = view.findViewById(R.id.tvFname);
                String fname = tvFname.getText().toString();

                tvLname = view.findViewById(R.id.tvLname);
                String lname = tvLname.getText().toString();

                tvPersonId = view.findViewById(R.id.tvChangePersonID);
                String personID = tvPersonId.getText().toString();

                Intent intent = new Intent(ShowData.this, ChangePerson.class);

                intent.putExtra("fname", fname);
                intent.putExtra("lname", lname);
                intent.putExtra("personID", personID);

                startActivity(intent);



               // Toast.makeText(getBaseContext(),"Test Toast Short " + tvFname.getText().toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    SimpleAdapter ad;

    public List GetList(String qu)
    {
        ListView lstv = (ListView) findViewById(R.id.listview1);
        List<Map<String, String>> MyDataList = null;
        ListItem MyData = new ListItem();
        MyDataList = MyData.getList(qu);

        String[] Fromw = {"id_person", "fname", "lname"};
        int[] Tow = {R.id.tvChangePersonID, R.id.tvFname, R.id.tvLname};

        ad = new SimpleAdapter(ShowData.this, MyDataList, R.layout.listlayouttemplate, Fromw, Tow);
        lstv.setAdapter(ad);

        return MyDataList;
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
            case R.id.btnSearch:

                String searchData = etSearch.getText().toString();
                GetList("select * from Persons where fname like '%" + searchData + "%' or lname like '%" + searchData + "%'");
                break;
        }
    }
}