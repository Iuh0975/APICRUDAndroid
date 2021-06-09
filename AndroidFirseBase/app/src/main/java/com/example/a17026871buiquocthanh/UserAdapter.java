package com.example.thandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a17026871buiquocthanh.ISenderUser;
import com.example.a17026871buiquocthanh.R;
import com.example.a17026871buiquocthanh.User;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {


    private Context context;
    private LayoutInflater inflater;
    private ArrayList<User> users;
    ISenderUser sendData;

    public UserAdapter(Context context, ArrayList<User> users) {
        inflater=LayoutInflater.from(context);
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View itemView=inflater.inflate(R.layout.item_user,parent,false );
        return new UserViewHolder(itemView,this );
    }

    @Override
    public void onBindViewHolder(@NonNull  UserViewHolder holder, int position) {
        User user=users.get(position);

        holder.tvID.setText(user.getId());
        holder.tvName.setText(user.getName());
        holder.tvDiaChi.setText(user.getDiaChi());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData = (ISenderUser)context;
                sendData.sendUser(users.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvDiaChi,tvID;
        UserAdapter userAdapter;
        public UserViewHolder(@NonNull  View itemView, UserAdapter userAdapter) {
            super(itemView);
            tvName=itemView.findViewById(R.id.tvName);
            tvDiaChi=itemView.findViewById(R.id.tvDiachi);
            tvID=itemView.findViewById(R.id.tvID);
            this.userAdapter=userAdapter;
        }
    }
}