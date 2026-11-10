package com.priyanshi.groceryapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SummaryActivity extends AppCompatActivity {
    private TextView tvOrderDate;
    private TextView tvBillDetails;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_summary);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.summaryMain), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvOrderDate = findViewById(R.id.tvOrderDate);
        tvBillDetails = findViewById(R.id.tvBillDetails);
        btnBack = findViewById(R.id.btnBack);

        String orderDate = getIntent().getStringExtra("ORDER_DATE");
        String billDetails = getIntent().getStringExtra("BILL_DETAILS");

        if (orderDate != null && !orderDate.isEmpty()) {
            tvOrderDate.setText(orderDate);
        }

        if (billDetails != null && !billDetails.isEmpty()) {
            tvBillDetails.setText(billDetails);
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
