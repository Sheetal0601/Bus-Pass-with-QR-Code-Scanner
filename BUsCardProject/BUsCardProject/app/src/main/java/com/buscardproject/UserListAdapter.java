package com.buscardproject;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.viewHolder> {

    Context context;
    private List<UserList> actorsLists;
    int quantity,_quantity;
    DBhelp help;
    SQLiteDatabase db;

    public UserListAdapter(Context context, List<UserList> actorsLists) {
        this.context = context;
        this.actorsLists = actorsLists;
    }

    @NonNull
    @Override
    public UserListAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.useradpter, parent, false);
        return new UserListAdapter.viewHolder(view);
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(@NonNull UserListAdapter.viewHolder holder, int position) {

        holder.txtViewtitle.setText(actorsLists.get(position).getName());
        holder.txtViewtitle1.setText(actorsLists.get(position).getEmail());
        holder.txtViewtitle2.setText(actorsLists.get(position).getMobileno());
        holder.txtViewtitle3.setText(actorsLists.get(position).getAddress()+"\n"+actorsLists.get(position).getCity()
                +" "+actorsLists.get(position).getState()+"\nPincode: "+actorsLists.get(position).getPincode());


    }
    public void dataDelete(String product_id){
        SQLiteDatabase readableDatabase = new DBhelp(context).getReadableDatabase();
        readableDatabase.execSQL("DELETE FROM tbl_productlist  WHERE product_id = '" + product_id + "'");
        readableDatabase.close();
    }



    @Override
    public int getItemCount() {
        return actorsLists.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView txtViewtitle,txtViewtitle1,txtViewtitle2,txtViewtitle3,txtViewtitle5;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            txtViewtitle = itemView.findViewById(R.id.txtViewtitle);
            txtViewtitle1 = itemView.findViewById(R.id.txtViewtitle1);
            txtViewtitle2 = itemView.findViewById(R.id.txtViewtitle2);
            txtViewtitle3 = itemView.findViewById(R.id.txtViewtitle3);

        }
    }

}