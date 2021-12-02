package com.sqlite.firebaseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<User> usersList=new ArrayList<>();
    RecyclerView recyclerView;
    UsersAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    private DatabaseReference databaseReference;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth=FirebaseAuth.getInstance();

databaseReference= FirebaseDatabase.getInstance().getReference("User");
databaseReference.addValueEventListener(valueEventListener);



        recyclerView=findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setLayoutManager(layoutManager);

        DAOuser daOuser=new DAOuser();
       final EditText fname=findViewById(R.id.fname);
       final EditText lname=findViewById(R.id.lname);
       final Button registration=findViewById(R.id.registration);
        Button btn=findViewById(R.id.btn);
        Button btnlogin=findViewById(R.id.btnlogin);
        Button btnlogout=findViewById(R.id.btnlogout);

        btnlogout.setOnClickListener(v->{
            auth.signOut();
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
        });
        btnlogin.setOnClickListener(v->{
            Intent intent=new Intent(this,LoginActivity.class);
            startActivity(intent);
        });

        registration.setOnClickListener(v->{
            Intent intent=new Intent(this,RegistrationActivity.class);
            startActivity(intent);
        });

        btn.setOnClickListener(v->{
            User user=new User(fname.getText().toString(),lname.getText().toString());
            daOuser.add(user).addOnSuccessListener(suc->{
                Toast.makeText(this, "Saved Successfully", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(err->{
                Toast.makeText(this, err.getMessage(), Toast.LENGTH_SHORT).show();
            });

//            HashMap<String,Object> hashMap=new HashMap<>();
//            hashMap.put("fname",fname.getText().toString());
//            hashMap.put("lname",lname.getText().toString());
//
//            daOuser.update("-MppVUDrS0VzzVs6UUlx",hashMap).addOnSuccessListener(suc->{
//                Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show();
//            }).addOnFailureListener(err->{
//                Toast.makeText(this, err.getMessage(), Toast.LENGTH_SHORT).show();
//            });


//            daOuser.remove("-MppVWCK_D6F6qL1b-iZ").addOnSuccessListener(suc->{
//                Toast.makeText(this, "Removed Successfully", Toast.LENGTH_SHORT).show();
//            }).addOnFailureListener(err->{
//                Toast.makeText(this, err.getMessage(), Toast.LENGTH_SHORT).show();
//            });


        });
        adapter=new UsersAdapter(this,usersList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser==null){
            Intent i=new Intent(this,LoginActivity.class);
            startActivity(i);
        }
            }

    public void findAll(){
//        for(User user:usersList){
//            Toast.makeText(this, user.getFname(), Toast.LENGTH_SHORT).show();
//        }

    }
    ValueEventListener valueEventListener =new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            usersList.clear();
            if(snapshot.exists()){
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    User user=dataSnapshot.getValue(User.class);
                    usersList.add(user);
                }
                adapter=new UsersAdapter(getApplicationContext(),usersList);
                recyclerView.setAdapter(adapter);

            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
}