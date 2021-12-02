package com.sqlite.firebaseapp;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DAOuser {
    private DatabaseReference databaseReference;

    public DAOuser() {
        FirebaseDatabase db=FirebaseDatabase.getInstance();
        databaseReference=db.getReference(User.class.getSimpleName());
    }

    public Task<Void> add(User user){
        return  databaseReference.push().setValue(user);
    }
    public Task<Void> update(String key, HashMap<String,Object> hashMap){
        return  databaseReference.child(key).updateChildren(hashMap);
    }

    public Task<Void> remove(String key){
        return  databaseReference.child(key).removeValue();
    }

}
