package com.example.spaarksassignment.Adapters;

// UserViewPagerAdapter.java
// ...

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.spaarksassignment.Models.Users;
import com.example.spaarksassignment.R;
import com.example.spaarksassignment.databinding.SampleCardviewBinding;

import java.util.ArrayList;

public class UserViewPagerAdapter extends RecyclerView.Adapter<UserViewPagerAdapter.UserViewHolder> {
    private ArrayList<Users> userList;
    private Context context;

    public UserViewPagerAdapter(ArrayList<Users> userList, Context context) {
        this.userList = userList;
        this.context = context;
    }

    public void setData(ArrayList<Users> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate your item layout here (e.g., R.layout.item_user)
        View view = LayoutInflater.from(context).inflate(R.layout.sample_cardview, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Users user = userList.get(position);
        // Bind your user data to the views here
        holder.bindUserData(user);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    static class UserViewHolder extends RecyclerView.ViewHolder {
        // Declare your views here (e.g., TextView name, TextView address, etc.)

        SampleCardviewBinding binding;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialize your views here (e.g., name = itemView.findViewById(R.id.name))
            binding = SampleCardviewBinding.bind(itemView);
        }

        public void bindUserData(Users user) {
            // Bind the user data to the views here (e.g., name.setText(user.getName()))


            binding.nameCV.setText(user.getName());
            binding.usernameCV.setText(user.getUsername());
            binding.emailCV.setText(user.getEmail());

            // Address
            Users.Address address = user.getAddress();
            if (address != null) {
                binding.streetCV.setText(address.getStreet());
                binding.cityCV.setText(address.getCity());
                binding.zipCodeCV.setText(address.getZipcode());
            }
        }
    }
}
