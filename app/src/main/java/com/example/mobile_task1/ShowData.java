package com.example.mobile_task1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
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

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShowData extends AppCompatActivity implements View.OnClickListener {

    RadioButton btnSortFnmae;
    RadioButton btnSortLname;
    Button btnSearch;

    EditText etSearch;

    private Adapter pAdapter;
    private List<Person> listProduct = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_show_data);

        ListView ivProducts = findViewById(R.id.listview1);//Находим лист в который будем класть наши объекты
        pAdapter = new Adapter(ShowData.this, listProduct); //Создаем объект нашего адаптера
        ivProducts.setAdapter(pAdapter); //Cвязывает подготовленный список с адаптером

        new GetProducts().execute(); //Подключение к нашей API в отдельном потоке
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnSortLname:
                //GetList("select * from Persons order by lname");
                break;
            case R.id.btnSortFname:
                // GetList("select * from Persons order by fname");
                break;
            case R.id.btnSearch:

                //String searchData = etSearch.getText().toString();
                // GetList("select * from Persons where fname like '%" + searchData + "%' or lname like '%" + searchData + "%'");
                break;
        }
    }

    private class GetProducts extends AsyncTask<Void, Void, String> implements com.example.mobile_task1.GetProducts {

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL("https://ngknn.ru:55797/NGKNN/КонстантиновАС/api/Persons");//Строка подключения к нашей API
                HttpURLConnection connection = (HttpURLConnection) url.openConnection(); //вызываем нашу API

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                /*
                BufferedReader успрощает чтение текста из потока символов
                InputStreamReader преводит поток байтов в поток символов
                connection.getInputStream() получает поток байтов
                */
                StringBuilder result = new StringBuilder();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    result.append(line);//кладет строковое значение в потоке
                }
                return result.toString();

            } catch (Exception exception) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONArray tempArray = new JSONArray(s);//преоброзование строки в json массив
                for (int i = 0; i < tempArray.length(); i++) {

                    JSONObject productJson = tempArray.getJSONObject(i);//Преобразование json объекта в нашу структуру
                    Person tempProduct = new Person(
                            productJson.getInt("ID"),
                            productJson.getString("fname"),
                            productJson.getString("lname")
                    );
                    listProduct.add(tempProduct);
                    pAdapter.notifyDataSetInvalidated();
                }
            } catch (Exception ignored) {


            }
        }

        TextView tvPersonId, tvFname, tvLname;


        SimpleAdapter ad;

    }
}