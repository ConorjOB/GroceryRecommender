package com.example.mynam.groceryrecommender;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class compare extends AppCompatActivity {

    private static Button milk, cheese, cereal, bread, yogurt, ham, calories, carbs, price, compare;
    public String param1="";
    public String param2="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);

        milk = (Button) findViewById(R.id.buttonMilk);
        cheese = (Button) findViewById(R.id.buttonCheese);
        cereal = (Button) findViewById(R.id.buttonCereal);
        bread = (Button) findViewById(R.id.buttonBread);
        yogurt = (Button) findViewById(R.id.buttonYogurt);
        ham = (Button) findViewById(R.id.buttonHam);
        calories = (Button) findViewById(R.id.buttonCal);
        carbs = (Button) findViewById(R.id.buttonCarbs);
        price = (Button) findViewById(R.id.buttonPrice);
        compare = (Button) findViewById(R.id.buttonCompare);

        milk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                param1 = "Milk";
            }
        });
        cheese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                param1 = "Cheese";
            }
        });
        cereal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                param1 = "Cereal";
            }
        });
        bread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                param1 = "Bread";
            }
        });
        yogurt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                param1 = "Yogurt";
            }
        });
        ham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                param1="Ham";
            }
        });
        calories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                param2="Calories";
            }
        });
        carbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                param2="Carbs";
            }
        });
        price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                param2="Price";
            }
        });
        compare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toDisplay();
            }
        });
    }

    public void toDisplay()
    {
        if(param1.equals("") || param2.equals(""))
        {
            Toast.makeText(getApplicationContext(), "Product or Favorite is blank!", Toast.LENGTH_LONG).show();
        }
        else{
            Intent intent = new Intent(this, listItems.class);
            Bundle bundle = new Bundle();
            bundle.putString("product name", param1);
            bundle.putString("favorite", param2);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}
