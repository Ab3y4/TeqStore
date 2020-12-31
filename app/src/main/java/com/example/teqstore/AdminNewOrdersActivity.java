package com.example.teqstore;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.teqstore.Model.AdminOrders;
import com.example.teqstore.Prevalent.Prevelant;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminNewOrdersActivity extends AppCompatActivity {


    private RecyclerView ordersList;
    private DatabaseReference ordersRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_new_orders);


        ordersRef = FirebaseDatabase.getInstance().getReference().child("Orders");

        ordersList = findViewById(R.id.ordersList);
        ordersList.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    protected void onStart()
    {
        super.onStart();

        FirebaseRecyclerOptions<AdminOrders> options =
                new FirebaseRecyclerOptions.Builder<AdminOrders>()
                .setQuery(ordersRef, AdminOrders.class)
                .build();

        FirebaseRecyclerAdapter<AdminOrders, AdminOrdersViewHolder> adapter =
                new FirebaseRecyclerAdapter<AdminOrders, AdminOrdersViewHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NonNull AdminOrdersViewHolder holder, final int position, final @NonNull AdminOrders model)
                    {
                        holder.userName.setText("Name: "+ model.getName());
                        holder.userPhoneNumber.setText("Phone Number: : "+ model.getPhone());
                        holder.userTotalPrice.setText("Total Amout: $"+ model.getTotalAmount());
                        holder.userDateTime.setText("Order Time: "+ model.getDate() + model.getTime());
                        holder.userShippingAddress.setText("Shipping Address: "+ model.getAddress() + "," + model.getCity());

                        holder.showOrdersBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v)
                            {
                                String uID = getRef(position).getKey();

                                Intent intent = new Intent(AdminNewOrdersActivity.this, AdminUserProductsActivity.class);
                                intent.putExtra("uid", uID);
                                startActivity(intent);
                            }
                        });

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view)
                            {
                                CharSequence options[] = new CharSequence[]
                                        {
                                               "Yes",
                                                "No"
                                        };
                                AlertDialog.Builder builder = new AlertDialog.Builder(AdminNewOrdersActivity.this);
                                builder.setTitle("Have you shipped this order?");

                                builder.setItems(options, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i)
                                    {
                                        if(i ==0)
                                        {
                                            String uID = getRef(position).getKey();

                                            RemoveOrder(uID);
                                            FirebaseDatabase.getInstance().getReference()
                                                    .child("Cart List")
                                                    .child("Admin View")
                                                    .child(Prevelant.currentOnlineUser.getUsername())
                                                    .removeValue()
                                            ;
                                        }
                                        else
                                        {

                                            finish();
                                        }
                                    }
                                });
                                builder.show();
                            }
                        });

                    }

                    @NonNull
                    @Override
                    public AdminOrdersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i)
                    {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.orders_layout, parent, false);
                        return new AdminOrdersViewHolder(view);
                    }
                };



        ordersList.setAdapter(adapter);
        adapter.startListening();
    }




    public static class AdminOrdersViewHolder extends RecyclerView.ViewHolder
    {
        public TextView userName, userPhoneNumber, userTotalPrice, userDateTime, userShippingAddress;
        public Button showOrdersBtn;


        public AdminOrdersViewHolder (View itemView)
        {
            super(itemView);

            userName = itemView.findViewById(R.id.orderUserName);
            userPhoneNumber = itemView.findViewById(R.id.orderPhoneNumber);
            userTotalPrice = itemView.findViewById(R.id.orderTotalPrice);
            userDateTime = itemView.findViewById(R.id.orderAddressCity);
            userShippingAddress = itemView.findViewById(R.id.orderDateTime);
            showOrdersBtn = itemView.findViewById(R.id.showAllProductsBtn);


        }
    }

    private void RemoveOrder(String uID)
    {
        ordersRef.child(uID).removeValue();
    }
}
