package com.example.spaarksassignment.Adapters;

// UserDetailsFragment.java
// ...

// UserDetailsFragment.java
// ...

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.spaarksassignment.Models.Users;
import com.example.spaarksassignment.databinding.SampleCardviewBinding;

public class UserDetailsFragment extends Fragment {
    private static final String ARG_USER = "user";

    private SampleCardviewBinding binding;

    public UserDetailsFragment() {
    }

    public static UserDetailsFragment newInstance(Users user) {
        UserDetailsFragment fragment = new UserDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_USER, user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = SampleCardviewBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Users user = getArguments().getParcelable(ARG_USER);

        // Set user details to the views in the fragment
        if (user != null) {
            binding.nameCV.setText(user.getName());
            binding.emailCV.setText(user.getEmail());
            binding.emailCV.setText(user.getEmail());

            Users.Address address = user.getAddress();
            if (address != null) {
                binding.streetCV.setText(address.getStreet());
                binding.cityCV.setText(address.getCity());
                binding.zipCodeCV.setText(address.getZipcode());
            }
            // ... Add other user details as needed
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
