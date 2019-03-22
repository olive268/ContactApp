package com.example.olive.contactapp;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView contactList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactList=findViewById(R.id.contact_list);
        contactList.setHasFixedSize(true);
        contactList.setLayoutManager(new LinearLayoutManager(this));
        setUpRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpRecyclerView();
    }

    public void setUpRecyclerView() {
        DbHelper helper=new DbHelper(this);
        List<Contact> list=helper.getAllData();
        ContactAdapter adapter=new ContactAdapter(list,this);
        contactList.setAdapter(adapter);
    }

    public void goToAdd(View view) {
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        View v=LayoutInflater.from(this).inflate(R.layout.dialog,null,false);
        alert.setTitle("Create New Contact");
        alert.setView(v);
        final AlertDialog dialog=alert.create();

        Button add=v.findViewById(R.id.add);

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EditText nameText=dialog.findViewById(R.id.input_name);
                    EditText numberText=dialog.findViewById(R.id.input_number);
                    String name=nameText.getText().toString().trim();
                    String number=numberText.getText().toString().trim();

                    if(name.isEmpty() || number.isEmpty())
                        Toast.makeText(MainActivity.this, "FILL IN ALL THE FIELDS", Toast.LENGTH_SHORT).show();

                    else {
                        Contact contact = new Contact(name, number);
                        DbHelper helper = new DbHelper(MainActivity.this);
                        helper.insert(contact);
                        dialog.cancel();
                        onResume();

                    }
                }
            });


        dialog.show();
    }
}
