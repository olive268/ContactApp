package com.example.olive.contactapp;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder> {
    List<Contact> list;
    MainActivity c;

    public ContactAdapter(List<Contact> list, MainActivity c) {
        this.list = list;
        this.c = c;
    }


    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup,final int i) {
        final View v= LayoutInflater.from(c).inflate(R.layout.contact_item,viewGroup,false);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent();
                i.setAction(Intent.ACTION_CALL);
                TextView tv=v.findViewById(R.id.person_phone);
                i.setData(Uri.parse("tel:"+tv.getText().toString()));
                c.startActivity(i);
            }
        });
        v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                TextView tvname=v.findViewById(R.id.person_name);
                DbHelper helper=new DbHelper(c);
                helper.delete(tvname.getText().toString());
                c.setUpRecyclerView();
                Toast.makeText(c, tvname.getText().toString()+" is deleted", Toast.LENGTH_SHORT).show();

                return true;
            }
        });
        return new ContactViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder contactViewHolder, int i) {
        contactViewHolder.setName(list.get(i).getName());
        contactViewHolder.setNumber(list.get(i).getNumber());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
