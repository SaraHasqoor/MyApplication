package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.api.APIClient;
import com.example.myapplication.model.UserModelItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity2 extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textView = findViewById(R.id.textview);
        Call<List<UserModelItem>> call = APIClient.getInstance().getApi().getUserData();
        call.enqueue(new Callback<List<UserModelItem>>() {
            @Override
            public void onResponse(Call<List<UserModelItem>> call, Response<List<UserModelItem>> response) {

                if (response.isSuccessful()){
                    Toast.makeText(MainActivity2.this, "Successfully get data", Toast.LENGTH_SHORT).show();
                    List<UserModelItem> userModel = response.body();
                    for (int i=0 ; i<userModel.size() ; i++){
                        textView.append(""+userModel.get(i).getId()+"\n");
                    }

                }

            }

            @Override
            public void onFailure(Call<List<UserModelItem>> call, Throwable t) {


            }
        });




    }
}