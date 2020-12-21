package com.rajendra.foodapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rajendra.foodapp.adapter.AsiaFoodAdapter;
import com.rajendra.foodapp.adapter.PopularFoodAdapter;
import com.rajendra.foodapp.model.KenyanFood;
import com.rajendra.foodapp.model.PopularFood;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView popularRecycler, asiaRecycler;
    PopularFoodAdapter popularFoodAdapter;
    AsiaFoodAdapter asiaFoodAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // MY dummy data to out model class

        List<PopularFood> popularFoodList = new ArrayList<>();

        popularFoodList.add(new PopularFood("Kashata", "Ksh. 500", R.drawable.popularfood1));
        popularFoodList.add(new PopularFood("Nyama Choma", "Ksh. 450", R.drawable.popularfood2));
        popularFoodList.add(new PopularFood("Githeri", "Ksh. 200", R.drawable.popularfood3));
        popularFoodList.add(new PopularFood("Float Cake Vietnam", "$7.05", R.drawable.popularfood1));
        popularFoodList.add(new PopularFood("Chiken Drumstick", "$17.05", R.drawable.popularfood2));
        popularFoodList.add(new PopularFood("Fish Tikka Stick", "$25.05", R.drawable.popularfood3));

        setPopularRecycler(popularFoodList);


        List<KenyanFood> kenyanFoodList = new ArrayList<>();
        kenyanFoodList.add(new KenyanFood("Chapati", "Ksh. 20", R.drawable.chapo, "4.5", "Qwetu Inn"));
        kenyanFoodList.add(new KenyanFood("Kuku Kienyeji", "Ksh. 450", R.drawable.kuku, "2.2", "Asmara"));
        kenyanFoodList.add(new KenyanFood("Mandazi", "Ksh. 10 per pc", R.drawable.asiafood1, "4.5", "Brook Haven"));
        kenyanFoodList.add(new KenyanFood("Black Forest Cake", "Ksh. 5,000", R.drawable.asiafood2, "5", "Valentine cake house"));
        kenyanFoodList.add(new KenyanFood("Pilau", "Ksh. 400", R.drawable.pilau, "3.5", "Golden Tulip"));
        kenyanFoodList.add(new KenyanFood("Ugali Fish", "$25", R.drawable.ugali, "4.2", "Mama Oliech"));

        setAsiaRecycler(kenyanFoodList);

    }


    private void setPopularRecycler(List<PopularFood> popularFoodList) {

        popularRecycler = findViewById(R.id.popular_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        popularRecycler.setLayoutManager(layoutManager);
        popularFoodAdapter = new PopularFoodAdapter(this, popularFoodList);
        popularRecycler.setAdapter(popularFoodAdapter);

    }

    private void setAsiaRecycler(List<KenyanFood> kenyanFoodList) {

        asiaRecycler = findViewById(R.id.asia_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        asiaRecycler.setLayoutManager(layoutManager);
        asiaFoodAdapter = new AsiaFoodAdapter(this, kenyanFoodList);
        asiaRecycler.setAdapter(asiaFoodAdapter);

    }
}
