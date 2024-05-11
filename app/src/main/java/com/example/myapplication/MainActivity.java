package com.example.myapplication;


import com.example.myapplication.model.UserData;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import com.example.myapplication.model.UserData;
import com.example.myapplication.view.UserAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    FloatingActionButton addButton;
    RecyclerView recyclerView;
    ArrayList<UserData> userList;
    UserAdapter userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        userList = new ArrayList<>();
        addButton = findViewById(R.id.add_button);
        recyclerView = findViewById(R.id.mRecycler);
        userAdapter = new UserAdapter(this, userList);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(userAdapter);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInfo();
            }
        });

    }

    private void addInfo() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View v = inflater.inflate(R.layout.activity_new_con, null);

        EditText description = v.findViewById(R.id.description);
        EditText hostname = v.findViewById(R.id.hostname);

        AlertDialog.Builder addDialog = new AlertDialog.Builder(this);
        addDialog.setView(v);
        addDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String descriptions = description.getText().toString();
                String hostnames = hostname.getText().toString();
                userList.add(new UserData( descriptions,  hostnames));
                userAdapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "Success",Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        addDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Toast.makeText(getApplicationContext(), "Cancel",Toast.LENGTH_SHORT).show();
            }
        });
        AlertDialog alertDialog = addDialog.create();
        alertDialog.show();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.options_menu, menu);
        return true;
    }


}