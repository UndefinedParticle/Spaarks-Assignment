package com.example.spaarksassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.spaarksassignment.Adapters.UserAdapter;
import com.example.spaarksassignment.Adapters.UserViewPagerAdapter;
import com.example.spaarksassignment.Models.Users;
import com.example.spaarksassignment.databinding.ActivityMainBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {
    ActivityMainBinding binding;
    private GoogleMap googleMap;
    ArrayList<Users> list = new ArrayList<>();
    UserAdapter adapter;
    String latitudeStr = "17.3850";
    String longitudeStr = "78.4867";
    private Marker currentMarker;


    ViewPager2 viewPager2;
    UserViewPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapView);
        mapFragment.getMapAsync(this);

        viewPager2 = findViewById(R.id.viewPager2);
        pagerAdapter = new UserViewPagerAdapter(list, this);
        viewPager2.setAdapter(pagerAdapter);

        // Set a ViewPager callback to update the marker position when the user swipes
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if (list != null && !list.isEmpty()) {
                    Users user = list.get(position);
                    updateMarkerPosition(user);
                }
            }
        });

        // Get the UserApiService instance
        UserApiService userApiService = ApiService.getUserApiService();

        // Call the API to fetch users data
        Call<ArrayList<Users>> call = userApiService.getUsers();
        call.enqueue(new Callback<ArrayList<Users>>() {
            @Override
            public void onResponse(Call<ArrayList<Users>> call, Response<ArrayList<Users>> response) {
                if (response.isSuccessful()) {
                    list = response.body();
                    setUserList(list);
                    // Process the data here (e.g., show markers on the map)
                    showMarkersOnMap();
                } else {
                    // Handle error case
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Users>> call, Throwable t) {
                // Handle network call failure
            }
        });


    }

    private void updateMarkerPosition(Users user) {
        if (currentMarker != null) {
            currentMarker.remove();
        }
        googleMap.clear();

        LatLng latLng = new LatLng(Double.parseDouble(user.getAddress().getGeo().getLat()), Double.parseDouble(user.getAddress().getGeo().getLng()));
        MarkerOptions markerOptions = new MarkerOptions()
                .position(latLng)
                .title(user.getName())
                .snippet(user.getAddress().getCity());

        currentMarker = googleMap.addMarker(markerOptions);
        currentMarker.setTag(user);

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 3));
    }




    private void setUserList(ArrayList<Users> list) {
        if (pagerAdapter != null) {
            pagerAdapter.setData(list); // Set the data to the pagerAdapter and notify the change
        }
    }


    @Override
    public void onMapReady(GoogleMap map) {

        googleMap = map;
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID); // Set the map type to hybrid (satellite view with place names)
        googleMap.setBuildingsEnabled(true); // Enable 3D buildings
        googleMap.getUiSettings().setZoomControlsEnabled(true); // Show zoom controls on the map
        fetchUserDataAndShowMarkers();
    }

    private void fetchUserDataAndShowMarkers() {
        // Get the UserApiService instance
        UserApiService userApiService = ApiService.getUserApiService();

        // Call the API to fetch users data
        Call<ArrayList<Users>> call = userApiService.getUsers();
        call.enqueue(new Callback<ArrayList<Users>>() {
            @Override
            public void onResponse(Call<ArrayList<Users>> call, Response<ArrayList<Users>> response) {
                if (response.isSuccessful()) {
                    list = response.body();
                    if (list != null && !list.isEmpty()) {
                        showMarkersOnMap();

                    }
                } else {
                    // Handle error case
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Users>> call, Throwable t) {
                // Handle network call failure
            }
        });
    }

    private void showMarkersOnMap() {
        // Check if the list is not empty
        if (!list.isEmpty()) {
            // Get the first user from the list
            Users firstUser = list.get(0);
            LatLng firstLatLng = new LatLng(Double.parseDouble(firstUser.getAddress().getGeo().getLat()), Double.parseDouble(firstUser.getAddress().getGeo().getLng()));

            // Add a marker for the first user
            MarkerOptions markerOptions = new MarkerOptions()
                    .position(firstLatLng)
                    .title(firstUser.getName())
                    .snippet(firstUser.getAddress().getCity());
            currentMarker = googleMap.addMarker(markerOptions);
            currentMarker.setTag(firstUser);

            // Move camera to the first marker
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(firstLatLng));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(firstLatLng, 3));
        }
    }

    @Override
    public void onBackPressed() {
        finishAffinity(); // This will exit the app from LoginActivity
    }

}