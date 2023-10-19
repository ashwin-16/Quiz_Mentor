package com.example.quiz_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.quiz_app.Adapters.QuestionsAdapter;
import com.example.quiz_app.databinding.ActivityAddQuestionBinding;
import com.example.quiz_app.databinding.ActivityQuestionBinding;
import com.example.quiz_app.model.QuestionModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity {
   ActivityQuestionBinding binding;
   FirebaseDatabase database;
   ArrayList<QuestionModel>list;
   QuestionsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityQuestionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

//        getSupportActionBar().hide();
        database=FirebaseDatabase.getInstance();
        list=new ArrayList<>();

        int setNum=getIntent().getIntExtra("setNum",0);
        String categoryName=getIntent().getStringExtra("categoryName");

        LinearLayoutManager layoutManager =new LinearLayoutManager(this);
        binding.recyQuestions.setLayoutManager(layoutManager);

        adapter=new QuestionsAdapter(this,list);  //erorrrrr
       binding.recyQuestions.setAdapter(adapter);

       database.getReference().child("Sets").child(categoryName).child("questions")
                       .orderByChild("setNum").equalTo(setNum)
                       .addValueEventListener(new ValueEventListener() {
                           @Override
                           public void onDataChange(@NonNull DataSnapshot snapshot) {
                               if(snapshot.exists()){
                                   for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                                       QuestionModel model=dataSnapshot.getValue(QuestionModel.class);
                                       model.setKey(dataSnapshot.getKey());
                                       list.add(model);
                                   }
                                   adapter.notifyDataSetChanged();
                               }else{
                                   Toast.makeText(QuestionActivity.this,"questions not exist",Toast.LENGTH_LONG).show();

                               }
                           }

                           @Override
                           public void onCancelled(@NonNull DatabaseError error) {

                           }
                       });

        binding.addQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(QuestionActivity.this,AddQuestionActivity.class);
                intent.putExtra("category",categoryName);
                intent.putExtra("setNum",setNum);
                startActivity(intent);

            }
        });
    }
}