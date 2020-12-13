package com.example.mynam.groceryrecommender;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class listItems extends AppCompatActivity {
    //declaring all variables
    public static BarChart barChart;

    private Button showPrice;
    private FirebaseDatabase fDb;
    private DatabaseReference ref;
    private DatabaseReference mRef;
    private Button showCal;
    private Button showCarbs;
    private Button showRec;
    public static TextView data;
    public static String productName;
    public static String favorite;
    public static String baseUrl="Products/";
    public static String[] product = new String[3];
    public String[] display = new String[2];
    public int check = 0;
    public static String best = "";
    final Float[] nuval = new Float[4];
    public static boolean aldiComplete, tescoComplete, superValuComplete, lidlComplete;
    //fibre, carbs, calcium, vitamins, protein saturates sugars salt calories price energy
    public static Float[] sArray = new Float [12];
    public static Float[] aArray = new Float [12];
    public static Float[] tArray = new Float [12];
    public static Float[] lArray = new Float [12];
    final Float[] caloriesArray = new Float[4];
    final Float[] carbsArray = new Float[4];
    final Float[] priceArray = new Float[4];
    final Float[] energyArray = new Float[4];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = this.getIntent().getExtras();
        aldiComplete = tescoComplete = superValuComplete = lidlComplete = false;
        productName = "";
        favorite = "";
        if (b != null) {
            productName = b.getString("product name");
            favorite = b.getString("favorite");
        }
        String dbUrl = baseUrl+productName;
        fDb = FirebaseDatabase.getInstance();

        setContentView(R.layout.activity_list_items);
        showPrice = (Button) findViewById(R.id.showPrice);
        showCal = (Button) findViewById(R.id.showCal);
        showCarbs = (Button) findViewById(R.id.showEnergy);
        showRec = (Button) findViewById(R.id.recommend) ;

        barChart = (BarChart) findViewById(R.id.barChart);
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.setMaxVisibleValueCount(400);
        barChart.setPinchZoom(false);
        barChart.setDrawGridBackground(true);
        barChart.setFitBars(true);

        ref = fDb.getReference(dbUrl);
        String aldi = "/Aldi";
        String newurl =  baseUrl+productName+aldi;
        mRef = fDb.getReference(newurl);
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                float fibre, carbs, energy, calcium, vitamins, price, protein, saturates, sugars, salt, calories, numerator, denominator;
                Products info = snapshot.getValue(Products.class);
                System.out.println(info.getCalories());
                fibre = Float.valueOf(info.getFibre());
                carbs = Float.valueOf(info.getCarbs());
                energy = Float.valueOf(info.getEnergy());
                if(info.getCalcium()!="0") //making sure the value isnt empty as some products don't have calcium.
                {
                    calcium = Float.valueOf(info.getCalcium());
                }
                else{
                    calcium = 0;
                }
                if(info.getVitamins()!="0") //making sure the value isnt empty as some products don't have any vitamins
                {
                    vitamins = Float.valueOf(info.getVitamins());
                }
                else
                {
                    vitamins = 0;
                }
                if(info.getProtein()!="0") //making sure the value isnt empty as some products don't have any protein
                {
                    protein = Float.valueOf(info.getProtein());
                }
                else{
                    protein = 0;
                }
                saturates = Float.valueOf(info.getSaturates());
                sugars = Float.valueOf(info.getSugars());
                salt = Float.valueOf(info.getSalt());
                calories = Float.valueOf(info.getCalories());
                price = Float.valueOf(info.getPrice());
                aArray[0]=fibre;
                aArray[1]=carbs;
                aArray[2]=calcium;
                aArray[3]=vitamins;
                aArray[4]=protein;
                aArray[5]=saturates;
                aArray[6]=sugars;
                aArray[7]=salt;
                aArray[8]=calories;
                aArray[9]=price;
                aArray[10]=energy;

                numerator = fibre+calcium+vitamins+protein;
                denominator = carbs+saturates+sugars+salt+calories;
                nuval[0] = numerator/denominator;
                priceArray[0]=price;
                caloriesArray[0]=calories;
                carbsArray[0]=carbs;
                energyArray[0]=energy;

                aldiComplete = true;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());

            }
        });
        String superValue = "/SuperValu";
        newurl =  baseUrl+productName+superValue;
        mRef = fDb.getReference(newurl);
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                float fibre, carbs, energy, calcium, vitamins, price, protein, saturates, sugars, salt, calories, numerator, denominator;
                Products info = snapshot.getValue(Products.class);
                System.out.println(info.getCalories());
                fibre = Float.valueOf(info.getFibre());
                carbs = Float.valueOf(info.getCarbs());
                energy = Float.valueOf(info.getEnergy());
                if(info.getCalcium()!="0") //making sure the value isnt empty as some products don't have calcium.
                {
                    calcium = Float.valueOf(info.getCalcium());
                }
                else{
                    calcium = 0;
                }
                if(info.getVitamins()!="0") //making sure the value isnt empty as some products don't have any vitamins
                {
                    vitamins = Float.valueOf(info.getVitamins());
                }
                else
                {
                    vitamins = 0;
                }
                if(info.getProtein()!="0") //making sure the value isnt empty as some products don't have any protein
                {
                    protein = Float.valueOf(info.getProtein());
                }
                else{
                    protein = 0;
                }
                saturates = Float.valueOf(info.getSaturates());
                sugars = Float.valueOf(info.getSugars());
                salt = Float.valueOf(info.getSalt());
                calories = Float.valueOf(info.getCalories());
                price = Float.valueOf(info.getPrice());
                sArray[0]=fibre;
                sArray[1]=carbs;
                sArray[2]=calcium;
                sArray[3]=vitamins;
                sArray[4]=protein;
                sArray[5]=saturates;
                sArray[6]=sugars;
                sArray[7]=salt;
                sArray[8]=calories;
                sArray[9]=price;
                sArray[10]=energy;

                numerator = fibre+calcium+vitamins+protein;
                denominator = carbs+saturates+sugars+salt+calories;
                nuval[1] = numerator/denominator;
                priceArray[1]=price;
                caloriesArray[1]=calories;
                carbsArray[1]=carbs;
                energyArray[1]=energy;

                superValuComplete = true;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());

            }
        });
        String tesco = "/Tesco";
        newurl =  baseUrl+productName+tesco;
        mRef = fDb.getReference(newurl);
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //Variables needed for calculations
                float fibre, carbs, energy, calcium, vitamins, price, protein, saturates, sugars, salt, calories, numerator, denominator;
                //Using the products object to get values from the snapshot.
                Products info = snapshot.getValue(Products.class);
                //Assigning variables with their values which are turned into floats.
                fibre = Float.valueOf(info.getFibre());
                carbs = Float.valueOf(info.getCarbs());
                energy = Float.valueOf(info.getEnergy());
                if(info.getCalcium()!="0") //making sure the value isnt empty as some products don't have calcium.
                {
                    calcium = Float.valueOf(info.getCalcium());
                }
                else{
                    calcium = 0;
                }
                if(info.getVitamins()!="0") //making sure the value isnt empty as some products don't have any vitamins
                {
                    vitamins = Float.valueOf(info.getVitamins());
                }
                else
                {
                    vitamins = 0;
                }
                if(info.getProtein()!="0") //making sure the value isnt empty as some products don't have any protein
                {
                    protein = Float.valueOf(info.getProtein());
                }
                else{
                    protein = 0;
                }
                saturates = Float.valueOf(info.getSaturates());
                sugars = Float.valueOf(info.getSugars());
                salt = Float.valueOf(info.getSalt());
                calories = Float.valueOf(info.getCalories());
                price = Float.valueOf(info.getPrice());
                tArray[0]=fibre;
                tArray[1]=carbs;
                tArray[2]=calcium;
                tArray[3]=vitamins;
                tArray[4]=protein;
                tArray[5]=saturates;
                tArray[6]=sugars;
                tArray[7]=salt;
                tArray[8]=calories;
                tArray[9]=price;
                tArray[10]=energy;

                numerator = fibre+calcium+vitamins+protein;
                denominator = carbs+saturates+sugars+salt+calories;
                nuval[2] = numerator/denominator;
                priceArray[2]=price;
                caloriesArray[2]=calories;
                carbsArray[2]=carbs;
                energyArray[2]=energy;

                tescoComplete = true;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());

            }
        });

        String lidl = "/Lidl";
        newurl =  baseUrl+productName+lidl;
        mRef = fDb.getReference(newurl);
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                float fibre, carbs, energy, calcium, vitamins, price, protein, saturates, sugars, salt, calories, numerator, denominator;
                Products info = snapshot.getValue(Products.class);
                System.out.println(info.getCalories());
                fibre = Float.valueOf(info.getFibre());
                carbs = Float.valueOf(info.getCarbs());
                energy = Float.valueOf(info.getEnergy());
                if(info.getCalcium()!="0") //making sure the value isnt empty as some products don't have calcium.
                {
                    calcium = Float.valueOf(info.getCalcium());
                }
                else{
                    calcium = 0;
                }
                if(info.getVitamins()!="0") //making sure the value isnt empty as some products don't have any vitamins
                {
                    vitamins = Float.valueOf(info.getVitamins());
                }
                else
                {
                    vitamins = 0;
                }
                if(info.getProtein()!="0") //making sure the value isnt empty as some products don't have any protein
                {
                    protein = Float.valueOf(info.getProtein());
                }
                else{
                    protein = 0;
                }
                saturates = Float.valueOf(info.getSaturates());
                sugars = Float.valueOf(info.getSugars());
                salt = Float.valueOf(info.getSalt());
                calories = Float.valueOf(info.getCalories());
                price = Float.valueOf(info.getPrice());
                lArray[0]=fibre;
                lArray[1]=carbs;
                lArray[2]=calcium;
                lArray[3]=vitamins;
                lArray[4]=protein;
                lArray[5]=saturates;
                lArray[6]=sugars;
                lArray[7]=salt;
                lArray[8]=calories;
                lArray[9]=price;
                lArray[10]=energy;

                numerator = fibre+calcium+vitamins+protein;
                denominator = carbs+saturates+sugars+salt+calories;
                nuval[3] = numerator/denominator;
                priceArray[3]=price;
                caloriesArray[3]=calories;
                carbsArray[3]=carbs;
                energyArray[3]=energy;

                lidlComplete = true;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());

            }
        });

        showPrice.setOnClickListener(new View.OnClickListener() { //Three buttons which display the pie charts depending on which is clicked
            @Override
            public void onClick(View view) {
                isComplete(0);
            }
        });

        showCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isComplete(1);
            }
        });

        showCarbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isComplete(2);
            }
        });

        showRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isComplete(3);
            }
        });


    }

    public void isComplete(int index){
        if(aldiComplete == true && tescoComplete==true && superValuComplete==true&&lidlComplete==true)
        {
            if(index==0)
            {
                getBest(ref, favorite);
                createChart("price");
            }
            else if (index == 1)
            {
                getBest(ref, favorite);
                createChart("calories");
            }
            else if (index == 2)
            {
                getBest(ref, favorite);
                createChart("carbs");
            }
            else{
                getBest(ref, favorite);
                defaultDisplay();
        }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Database not loaded yet!", Toast.LENGTH_LONG).show();
        }
    }
    public void getBest(DatabaseReference ref, String favorite){
        String aldi = "Aldi";
        String tesco = "Tesco";
        String supervalu = "SuperValu";
        String lidl = "Lidl";
        int ascore, sscore, tscore, lscore;
        ascore = sscore = tscore = lscore = 0;
        int highest = 0;
        for(int i=0;i<4;i++)
        {
            if(nuval[i] > nuval[highest])
            {
                highest =i;
            }
        }

        if(highest == 0)
        {
            ascore++;
        }
        else if (highest == 1)
        {
            sscore++;
        }

        else if (highest == 2)
        {
            tscore++;
        }
        else {
            lscore++;
        }

        if(favorite == "carbs")
        {
            highest = 0;
            for(int i=0;i<4;i++)
            {
                if(carbsArray[i] < carbsArray[highest])
                {
                    highest =i;
                }
            }

        }
        else if (favorite == "calories")
        {
            highest = 0;
            for(int i=0;i<4;i++)
            {
                if(caloriesArray[i] < caloriesArray[highest])
                {
                    highest =i;
                }
            }

        }
        else
        {
            highest = 0;
            for(int i=0;i<4;i++)
            {
                if(priceArray[i] < priceArray[highest])
                {
                    highest =i;
                }
            }

        }
        if(highest == 0)
        {
            ascore++;
        }
        else if (highest == 1)
        {
            sscore++;
        }

        else if (highest == 2)
        {
            tscore++;
        }
        else {
            lscore++;
        }


        if(ascore > sscore && ascore > lscore && ascore > tscore)
        {
            best = aldi;
        }
        else if (sscore > ascore && sscore > lscore && sscore > tscore)
        {
            best = supervalu;
        }

        else if (tscore > ascore && tscore > lscore && tscore > sscore)
        {
            best = tesco;
        }
        else if (lscore > ascore && lscore > sscore && lscore > tscore) {
            best = lidl;
        }
        else{

            //if no "best" product yet, compare with energy, highest of the two left wins.
            highest = 0 ;
            for(int i=0;i<4;i++)
            {
                if(i==0)
                {
                    if(ascore!=1)
                    {
                        if(nuval[i] > nuval[highest])
                        {
                            highest =i;
                        }

                    }

                }
                else
                if(i==1)
                {
                    if(sscore!=1)
                    {
                        if(nuval[i] > nuval[highest])
                        {
                            highest =i;
                        }

                    }

                }

                else if(i==2)
                {
                    if(tscore!=1)
                    {
                        if(nuval[i] > nuval[highest])
                        {
                            highest =i;
                        }

                    }

                }
                else
                if(i==3)
                {
                    if(lscore!=1)
                    {
                        if(nuval[i] > nuval[highest])
                        {
                            highest =i;
                        }

                    }

                }
            }

            if(highest == 0)
            {
                best = aldi;
            }
            else if (highest == 1)
            {
                best=supervalu;
            }

            else if (highest == 2)
            {
                best=tesco;
            }
            else {
                best=lidl;
            }
        }
    }

    public void defaultDisplay()
    {
        final ArrayList<BarEntry> barEntries = new ArrayList<>();
        if(best == "Aldi")
        {
            barEntries.add(new BarEntry(1, aArray[0]));
            barEntries.add(new BarEntry(2, aArray[1]));
            barEntries.add(new BarEntry(3, aArray[2]));
            barEntries.add(new BarEntry(4, aArray[3]));
            barEntries.add(new BarEntry(5, aArray[4]));
            barEntries.add(new BarEntry(6, aArray[5]));
            barEntries.add(new BarEntry(7, aArray[6]));
            barEntries.add(new BarEntry(8, aArray[7]));
        }
        else
        if(best == "SuperValu")
        {
            barEntries.add(new BarEntry(1, sArray[0]));
            barEntries.add(new BarEntry(2, sArray[1]));
            barEntries.add(new BarEntry(3, sArray[2]));
            barEntries.add(new BarEntry(4, sArray[3]));
            barEntries.add(new BarEntry(5, sArray[4]));
            barEntries.add(new BarEntry(6, sArray[5]));
            barEntries.add(new BarEntry(7, sArray[6]));
            barEntries.add(new BarEntry(8, sArray[7]));
        }
        else
        if(best == "Tesco")
        {
            barEntries.add(new BarEntry(1, tArray[0]));
            barEntries.add(new BarEntry(2, tArray[1]));
            barEntries.add(new BarEntry(3, tArray[2]));
            barEntries.add(new BarEntry(4, tArray[3]));
            barEntries.add(new BarEntry(5, tArray[4]));
            barEntries.add(new BarEntry(6, tArray[5]));
            barEntries.add(new BarEntry(7, tArray[6]));
            barEntries.add(new BarEntry(8, tArray[7]));
        }
        else{
            barEntries.add(new BarEntry(1, lArray[0]));
            barEntries.add(new BarEntry(2, lArray[1]));
            barEntries.add(new BarEntry(3, lArray[2]));
            barEntries.add(new BarEntry(4, lArray[3]));
            barEntries.add(new BarEntry(5, lArray[4]));
            barEntries.add(new BarEntry(6, lArray[5]));
            barEntries.add(new BarEntry(7, lArray[6]));
            barEntries.add(new BarEntry(8, lArray[7]));
        }

        BarDataSet barDataSet = new BarDataSet(barEntries, best);
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        BarData data = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(data);
        barChart.setFitBars(true);
        int count = barEntries.size();
        barChart.getXAxis().setAxisMinimum(-data.getBarWidth() / 2);
        barChart.getXAxis().setAxisMaximum(count-data.getBarWidth() / 2);
        barDataSet.setValueTextSize(12);

        String[] labels = new String[] {"", "Fibre", "Carbs", "Calcium", "Vitamins", "Protein", "Saturates", "Sugars", "Salt"};
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new MyXAxisValueFormatter(labels));
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        xAxis.setGranularity(1);
        xAxis.setCenterAxisLabels(false);
        xAxis.setLabelCount(9);
        xAxis.setAxisMinimum(0.5f);
        xAxis.setAxisMaximum(9f);
    }


    public void createChart(String choice) {

        final String reference = choice;
        final ArrayList<BarEntry> barEntries = new ArrayList<>();
        //fibre, carbs, calcium, vitamins, protein saturates sugars salt calories price energy

        if(reference == "calories") {
            barEntries.add(new BarEntry(1, aArray[8]));
            barEntries.add(new BarEntry(2, sArray[8]));
            barEntries.add(new BarEntry(3, tArray[8]));
            barEntries.add(new BarEntry(4, lArray[8]));
        }
        else if(reference == "carbs") {
            barEntries.add(new BarEntry(1, aArray[1]));
            barEntries.add(new BarEntry(2, sArray[1]));
            barEntries.add(new BarEntry(3, tArray[1]));
            barEntries.add(new BarEntry(4, lArray[1]));
        }
        else{
            barEntries.add(new BarEntry(1, aArray[9]));
            barEntries.add(new BarEntry(2, sArray[9]));
            barEntries.add(new BarEntry(3, tArray[9]));
            barEntries.add(new BarEntry(4, lArray[9]));
        }

        BarDataSet barDataSet = new BarDataSet(barEntries, choice);
        barDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        BarData data = new BarData(barDataSet);
        barChart.setFitBars(true);
        barChart.setData(data);
        barDataSet.setValueTextSize(12);
        barChart.setFitBars(true);

        String[] names = new String[] {"", "Aldi", "SuperValu", "Tesco", "Lidl"};
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new MyXAxisValueFormatter(names));
        xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        xAxis.setGranularity(1);
        xAxis.setCenterAxisLabels(false);
        xAxis.setAxisMinimum(0.5f);
        xAxis.setAxisMaximum(4.5f);
    }

    public class MyXAxisValueFormatter implements IAxisValueFormatter {
        private String[] labels;

        public MyXAxisValueFormatter(String[] labels) {
            this.labels = labels;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            try {
                int index = (int) value;
                return labels[index];
            } catch (Exception e) {
                return "";
            }
        }
    }

}
