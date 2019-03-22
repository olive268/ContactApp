package com.example.olive.contactapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class ContactViewHolder extends RecyclerView.ViewHolder {
    static TextView nameText,numberText;
    public ContactViewHolder(@NonNull View itemView) {
        super(itemView);
        nameText=itemView.findViewById(R.id.person_name);
        numberText=itemView.findViewById(R.id.person_phone);
    }
    public void setName(String name){
        nameText.setText(name);
    }
    public void setNumber(String number){
        numberText.setText(number);
    }

}
