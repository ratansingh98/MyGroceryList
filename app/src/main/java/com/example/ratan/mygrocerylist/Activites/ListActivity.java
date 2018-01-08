package com.example.ratan.mygrocerylist.Activites;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.ratan.mygrocerylist.Data.DatabaseHandler;
import com.example.ratan.mygrocerylist.Model.Grocery;
import com.example.ratan.mygrocerylist.R;
import com.example.ratan.mygrocerylist.UI.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private List<Grocery> groceryList;
    private List<Grocery> listItems;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        db= new DatabaseHandler(this);
        recyclerView=findViewById(R.id.recyclerViewID);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        groceryList=new ArrayList<>();
        listItems=new ArrayList<>();

        //Get items from database
        groceryList=db.getAllGroceries();

        for(Grocery c:groceryList){
            Grocery grocery=new Grocery();
            grocery.setName(c.getName());
            grocery.setQuantity("Qty: "+c.getQuantity());
            grocery.setId(c.getId());
            grocery.setDateItemAdded("Added on: "+c.getDateItemAdded());

            Log.d("Name:",c.getName());
            listItems.add(grocery);
        }

        recyclerViewAdapter=new RecyclerViewAdapter(this,listItems);
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();
    }


}
