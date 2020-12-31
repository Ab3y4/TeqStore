package com.example.teqstore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class AdminCategoryActivity extends AppCompatActivity {


    private ImageView monitor, desktop, motherboard, harddrive;
    private ImageView processor, ram, vga, headphones;
    private ImageView laptops, cameras, phones, smartwatch;

    private Button LogoutBtn, checkOrdersBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_category);



        LogoutBtn = (Button) findViewById(R.id.LogoutBtn);
        checkOrdersBtn = (Button) findViewById(R.id.checkOrdersBtn);

        LogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });

        checkOrdersBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, AdminNewOrdersActivity.class);
                startActivity(intent);
            }
        });


        monitor = (ImageView) findViewById(R.id.monitor);
        desktop = (ImageView) findViewById(R.id.desktop);
        motherboard = (ImageView) findViewById(R.id.motherboard);
        harddrive = (ImageView) findViewById(R.id.harddrive);

        processor = (ImageView) findViewById(R.id.processor);
        ram = (ImageView) findViewById(R.id.ram);
        vga = (ImageView) findViewById(R.id.vga);
        headphones = (ImageView) findViewById(R.id.headphones);

        laptops = (ImageView) findViewById(R.id.laptops);
        cameras = (ImageView) findViewById(R.id.cameras);
        phones = (ImageView) findViewById(R.id.phones);
        smartwatch = (ImageView) findViewById(R.id.smartwatch);


        monitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, Admin_Add_New_Product_Activity.class);
                intent.putExtra("category", "Monitor");
                startActivity(intent);
            }
        });

        desktop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, Admin_Add_New_Product_Activity.class);
                intent.putExtra("category", "Desktop");
                startActivity(intent);
            }
        });

        motherboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, Admin_Add_New_Product_Activity.class);
                intent.putExtra("category", "Motherboard");
                startActivity(intent);
            }
        });

        harddrive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, Admin_Add_New_Product_Activity.class);
                intent.putExtra("category", "Hard Drive");
                startActivity(intent);
            }
        });

        processor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, Admin_Add_New_Product_Activity.class);
                intent.putExtra("category", "Processor");
                startActivity(intent);
            }
        });

        ram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, Admin_Add_New_Product_Activity.class);
                intent.putExtra("category", "RAM");
                startActivity(intent);
            }
        });

        vga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, Admin_Add_New_Product_Activity.class);
                intent.putExtra("category", "VGA");
                startActivity(intent);
            }
        });

        headphones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, Admin_Add_New_Product_Activity.class);
                intent.putExtra("category", "Headphones");
                startActivity(intent);
            }
        });

        laptops.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, Admin_Add_New_Product_Activity.class);
                intent.putExtra("category", "Laptops");
                startActivity(intent);
            }
        });

        cameras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, Admin_Add_New_Product_Activity.class);
                intent.putExtra("category", "Cameras");
                startActivity(intent);
            }
        });

        phones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, Admin_Add_New_Product_Activity.class);
                intent.putExtra("category", "Mobile Phones");
                startActivity(intent);
            }
        });

        smartwatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(AdminCategoryActivity.this, Admin_Add_New_Product_Activity.class);
                intent.putExtra("category", "Smart Watches");
                startActivity(intent);
            }
        });
    }
}
