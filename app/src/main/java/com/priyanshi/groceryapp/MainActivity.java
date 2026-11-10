package com.priyanshi.groceryapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView tvDate;
    private ListView listView;
    private Button btnSubmit;
    private List<GroceryItem> groceryList;
    private GroceryAdapter adapter;
    private String selectedDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvDate = findViewById(R.id.tvDate);
        listView = findViewById(R.id.listView);
        btnSubmit = findViewById(R.id.btnSubmit);

        // Set default date to today
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
        selectedDate = sdf.format(calendar.getTime());
        tvDate.setText(selectedDate);

        tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        // Initialize grocery items
        groceryList = new ArrayList<>();
        populateGroceryItems();

        adapter = new GroceryAdapter(this, groceryList);
        listView.setAdapter(adapter);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitOrder();
            }
        });
    }

    private void populateGroceryItems() {
        groceryList.add(new GroceryItem("Fresh Tomatoes", 40.0, R.drawable.tomato));
        groceryList.add(new GroceryItem("Farm Potatoes", 35.0, R.drawable.potato));
        groceryList.add(new GroceryItem("Red Onions", 45.0, R.drawable.onion));
        groceryList.add(new GroceryItem("Crisp Carrots", 50.0, R.drawable.carrot));
        groceryList.add(new GroceryItem("Green Capsicum", 60.0, R.drawable.capsicum));
    }

    private void showDatePicker() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                        Calendar selectedCal = Calendar.getInstance();
                        selectedCal.set(selectedYear, selectedMonth, selectedDay);
                        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault());
                        selectedDate = sdf.format(selectedCal.getTime());
                        tvDate.setText(selectedDate);
                    }
                },
                year, month, day
        );
        datePickerDialog.show();
    }

    private void submitOrder() {
        double grandTotal = 0.0;
        StringBuilder billSummary = new StringBuilder();
        int selectedItemCount = 0;

        for (GroceryItem item : groceryList) {
            if (item.getQuantity() > 0) {
                double itemTotal = item.getTotalItemPrice();
                grandTotal += itemTotal;
                selectedItemCount++;

                billSummary.append(String.format(Locale.getDefault(),
                        "• %s\n  Qty: %.2f kg  x  ₹%.2f/kg  =  ₹%.2f\n\n",
                        item.getName(), item.getQuantity(), item.getPricePerKg(), itemTotal));
            }
        }

        if (selectedItemCount == 0) {
            Toast.makeText(this, "Please enter quantity for at least one item!", Toast.LENGTH_SHORT).show();
            return;
        }

        billSummary.append("--------------------------------------------------\n");
        billSummary.append(String.format(Locale.getDefault(), "Grand Total: ₹ %.2f\n", grandTotal));
        billSummary.append("--------------------------------------------------");

        Intent intent = new Intent(MainActivity.this, SummaryActivity.class);
        intent.putExtra("ORDER_DATE", selectedDate);
        intent.putExtra("BILL_DETAILS", billSummary.toString());
        intent.putExtra("GRAND_TOTAL", grandTotal);
        startActivity(intent);
    }
}
