package com.example.lab2_android;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> users = new ArrayList<String>();
    ArrayList<Integer> selectedUser = new ArrayList<Integer>();
    //ArrayList<SelectUsers> selectUsers = new ArrayList<SelectUsers>(); //new
    int sUser;
    ArrayAdapter<String> adapter;
    ListView listView;
    EditText editText;
    Button addUserButton;
    Button removeUserButton;
    boolean itemChoice = false;
    public int selectedItemId, oldPosition = 0, flag, index;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addUserButton = findViewById(R.id.button1);
        removeUserButton = findViewById(R.id.button2);
        editText = findViewById(R.id.name);
        listView = findViewById(R.id.listUsers);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, users);
        listView.setAdapter(adapter);

        removeUserButton.setEnabled(false);

        addUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!editText.getText().toString().equals("")) {
                    users.add(editText.getText().toString());
                    editText.setText("");
                    adapter.notifyDataSetChanged();
                }
            }
        });

        removeUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //users.remove(selectedItemId);
                //adapter.notifyDataSetChanged();
                //removeUserButton.setEnabled(false);
                //listView.getChildAt(selectedItemId).setBackgroundColor(getResources().getColor(R.color.white));

                Collections.sort(selectedUser);

                for (int i=selectedUser.size()-1; i>=0; i--) {
                    sUser = selectedUser.get(i);
                    users.remove(sUser);
                    listView.getChildAt(selectedUser.get(i)).setBackgroundColor(getResources().getColor(R.color.white));
                }
                adapter.notifyDataSetChanged();
                removeUserButton.setEnabled(false);
                selectedUser.clear();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //listView.getChildAt(oldPosition).setBackgroundColor(getResources().getColor(R.color.white));
                //selectedItemId = position;
                //listView.getChildAt(selectedItemId).setBackgroundColor(getResources().getColor(R.color.purple));
                //oldPosition = position;

                flag = 0;

                if(selectedUser.isEmpty()) {
                    listView.getChildAt(position).setBackgroundColor(getResources().getColor(R.color.purple));
                    selectedUser.add(position);
                    removeUserButton.setEnabled(true);
                }
                else {
                    for (int j=0; j < selectedUser.size(); j++) {
                        if(selectedUser.get(j) == position) {
                            flag = 1;
                            index = j;
                            break;
                        }
                    }

                    if(flag == 1) {
                        selectedUser.remove(index);
                        listView.getChildAt(position).setBackgroundColor(getResources().getColor(R.color.white));
                        if(selectedUser.size() == 0)
                            removeUserButton.setEnabled(false);
                    }
                    else {
                        selectedUser.add(position);
                        listView.getChildAt(position).setBackgroundColor(getResources().getColor(R.color.purple));
                        removeUserButton.setEnabled(true);
                    }
                }
            }
        });

    }
}