package com.example.spaarksassignment.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.spaarksassignment.Models.Users;
import com.example.spaarksassignment.R;
import com.example.spaarksassignment.databinding.SampleCardviewBinding;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<Users> userList;
    Context context;

    public UserAdapter(List<Users> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }
    public void setData(List<Users> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_cardview, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Users user = userList.get(position);

        holder.binding.nameCV.setText(user.getName());
        holder.binding.usernameCV.setText(user.getUsername());
        holder.binding.emailCV.setText(user.getEmail());

        // Address
        Users.Address address = user.getAddress();
        if (address != null) {
            holder.binding.streetCV.setText(address.getStreet());
            holder.binding.cityCV.setText(address.getCity());
            holder.binding.zipCodeCV.setText(address.getZipcode());
        }

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        SampleCardviewBinding binding;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = SampleCardviewBinding.bind(itemView);
        }
    }
}
