package com.example.diabetescare;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
public class FindDoctor extends AppCompatActivity {
    SearchView searchView;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String > adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);
        listView = (ListView) findViewById(R.id.lv1);
        list = new ArrayList<>();
        list.add("Clovis");
        list.add("Sandrah");
        list.add("Pascal");
        list.add("Allan");
        list.add("Namara");
        list.add("Joan");
        list.add("Muhindo");
        list.add("Hazel");
        list.add("Arinda");
        list.add("Ivan");
        list.add("Bwambale");
        list.add("Ronald");
        list.add("Keith");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position ==  0) {
                    Intent meetdoctor=new Intent(getApplicationContext(),MeetDoctor.class);
                    startActivity(meetdoctor);
                    Toast.makeText(FindDoctor.this, "Doctor Clovis", Toast.LENGTH_SHORT).show();
                }
                if (position ==  1) {
                    Toast.makeText(FindDoctor.this, "Dr Pascal", Toast.LENGTH_SHORT).show();
                }
                if (position ==  6) {
                    Toast.makeText(FindDoctor.this, "Dr Ivan", Toast.LENGTH_SHORT).show();
                }
                if (position ==  3) {
                    Toast.makeText(FindDoctor.this, "Doctor Clovis", Toast.LENGTH_SHORT).show();
                }

                if (position ==  4) {
                    Toast.makeText(FindDoctor.this, "Doctor Clovis", Toast.LENGTH_SHORT).show();

                }

            }});
        searchView = (SearchView) findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if(list.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(FindDoctor.this, "No Match found",Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //    adapter.getFilter().filter(newText);
                return false;
            }
        });
    }
 }