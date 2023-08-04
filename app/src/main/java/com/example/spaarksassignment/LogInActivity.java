    package com.example.spaarksassignment;

    import androidx.appcompat.app.AppCompatActivity;

    import android.content.Intent;
    import android.content.SharedPreferences;
    import android.os.Bundle;
    import android.view.View;

    import com.example.spaarksassignment.databinding.ActivityLogInBinding;

    public class LogInActivity extends AppCompatActivity {
        ActivityLogInBinding binding;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            binding = ActivityLogInBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            binding.LogInPageFinalLogIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String uname = binding.LogInPageUserName.getText().toString();
                    String pass = binding.LogInPagePassword.getText().toString();
                    if(!uname.equals("123")){
                        binding.LogInPageUserName.setError("Wrong Usename!");
                        return;
                    }
                    if(!pass.equals("123")){
                        binding.LogInPagePassword.setError("Wrong Password!");
                        return;
                    }

                    Intent intent = new Intent(LogInActivity.this,MainActivity.class);

                    SharedPreferences preferences = getSharedPreferences("UserData", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("isLoggedIn", true);
                    editor.apply();
//                    intent.putExtra("userName",uname);
//                    intent.putExtra("pass",pass);

                    startActivity(intent);
                    finish();
                }
            });
        }
    }