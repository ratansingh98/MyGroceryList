package com.example.ratan.mygrocerylist.UI;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ratan.mygrocerylist.Activites.DetailsActivity;
import com.example.ratan.mygrocerylist.Model.Grocery;
import com.example.ratan.mygrocerylist.R;

import java.util.List;

/**
 * Created by ratan on 8/1/18.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<Grocery> groceryItems;

    public RecyclerViewAdapter(Context context, List<Grocery> groceryItems) {
        this.context = context;
        this.groceryItems = groceryItems;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row,parent,false);

        return new ViewHolder(view,context);
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        Grocery grocery=groceryItems.get(position);
        holder.groceryItemName.setText(grocery.getName());
        holder.quantity.setText(grocery.getQuantity());
        holder.dateAdded.setText(grocery.getDateItemAdded());

    }

    @Override
    public int getItemCount() {
        return groceryItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView groceryItemName;
        public TextView quantity;
        public TextView dateAdded;
        public Button editButton;
        public Button deleteButton;
        public int id;

        public ViewHolder(View view,Context ctx) {
            super(view);
            context=ctx;

            groceryItemName=view.findViewById(R.id.name);
            quantity=view.findViewById(R.id.quantity);
            dateAdded=view.findViewById(R.id.dateAdded);

            editButton=view.findViewById(R.id.editButton);
            deleteButton=view.findViewById(R.id.deleteButton);

            editButton.setOnClickListener(this);
            deleteButton.setOnClickListener(this);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //go to next Screen/DetailsActivity
                    int postiton =getAdapterPosition();

                    Grocery grocery=groceryItems.get(postiton);
                    Intent intent=new Intent(context, DetailsActivity.class);
                    intent.putExtra("name",grocery.getName());
                    intent.putExtra("quantity",grocery.getQuantity());
                    intent.putExtra("id",grocery.getId());
                    intent.putExtra("date",grocery.getDateItemAdded());

                    context.startActivity(intent);

                }
            });
        }


        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.editButton:
                    break;
                case R.id.deleteButton:
                    break;


            }
        }
    }
}
