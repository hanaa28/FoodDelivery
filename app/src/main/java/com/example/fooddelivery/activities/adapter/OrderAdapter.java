package com.example.fooddelivery.activities.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddelivery.R;
import com.example.fooddelivery.activities.SharedPrefs;
import com.example.fooddelivery.activities.avtivities.MainresActivity;
import com.example.fooddelivery.activities.fragments.cardFragment;
import com.example.fooddelivery.activities.models.Food;
import com.example.fooddelivery.activities.models.FoodResponse;
import com.example.fooddelivery.databinding.FragmentCardBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderHolder> {

    private final int quantity = 1;
    private ArrayList<FoodResponse> orderArrayList;
    int price;
    private Context context;
    int pprice;
    int tsum = 0;
    // creating a constructor class.

    public OrderAdapter(ArrayList<FoodResponse> orderArrayList, Context context) {
        this.orderArrayList = orderArrayList;
        this.context = context;
    }



    @NonNull
    @Override
    public OrderHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View orderView = inflater.inflate(R.layout.order_item, parent, false);

        return new OrderHolder(orderView);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull OrderHolder holder, int position) {

        FoodResponse order=orderArrayList.get(position);
        holder.Ofoodname.setText(order.getData().get(0).getName());
        holder.Odescription.setText(order.getData().get(0).getDescription());
        System.out.println(order.getData().get(0).getPrice());
        System.out.println(order.getData().get(0).getQun());
        holder.Oprice.setText(String.valueOf(Integer.parseInt(order.getData().get(0).getPrice()) * order.getData().get(0).getQun()));

        System.out.println(order.getData().get(0).getPic());
        holder.Onum.setText(String.valueOf(order.getData().get(0).getQun()));

//        Picasso.get().load(order.getData().get(0).getPic()).into(holder.OImageFood, new Callback() {
//            @Override
//            public void onSuccess() {
//
//            }
//
//            @Override
//            public void onError(Exception e) {
//                Picasso.get().load(R.drawable.logo).into(holder.OImageFood);
//            }
//        });
        Picasso.get().load(R.drawable.logo).error(R.drawable.logo).into(holder.OImageFood);
        holder.Oplue.setOnClickListener((view) -> {
            int qun = Integer.parseInt(holder.Onum.getText().toString()) + 1;
            SharedPrefs sharedPrefs = new SharedPrefs(this.context);
            sharedPrefs.setOrder(String.valueOf(order.getData().get(0).getId()), 1);
            holder.Onum.setText(String.valueOf(qun));
            price=Integer.parseInt(order.getData().get(0).getPrice())*qun;
            setPrice(position,price);
            holder.Oprice.setText(String.valueOf(price));


        });
        holder.Ominus.setOnClickListener(View ->{
                        int qun = Integer.parseInt(holder.Onum.getText().toString());
            if (qun>1){
                qun -=1;
                SharedPrefs sharedPrefs = new SharedPrefs(this.context);
                sharedPrefs.setOrder(String.valueOf(order.getData().get(0).getId()), -1);
                holder.Onum.setText(String.valueOf(qun));
                price=Integer.parseInt(order.getData().get(0).getPrice())*qun;
                setPrice(position,price);
                System.out.println("POSTIOn"+position);
                holder.Oprice.setText(String.valueOf(price));

            }

        });

//       holder.totalprice.setText(String.valueOf(gettotal()));
//       setholder(Integer.parseInt(String.valueOf(holder.totalprice.getText())));
//
//
//


//       int count = getItemCount();
//
//        System.out.println("pppp"+pprice);
//        for (int i = 0; i < count; i++){
//
//            pprice += Integer.parseInt(orderArrayList.get(i).getData().get(0).getPrice())*Integer.parseInt(holder.Onum.getText().toString());
//
//
//            Log.d("total pay : ", String.valueOf(tsum));
//        }
//        System.out.println("ppppppppp"+pprice);
    }
    public void setPrice(int postiin,int price){

        this.price=price;
    }


    public int getPrice(){
        return price;
    }
    public int gettotal(){
        int count = getItemCount();
        int total=0;
        for (int i = 0; i < count; i++){

            total+= Integer.parseInt(orderArrayList.get(i).getData().get(0).getPrice())*orderArrayList.get(i).getData().get(0).getQun();


        }
        System.out.println("nnnossa"+ total);
        return total;
    }
    public void setholder(int p){
        pprice=p;

    }
    public int getholder(){
    return pprice;
    }
    public int getallpr(){
        int x=getholder();
        return x;
    }

    @Override
    public int getItemCount() {
        return orderArrayList.size();
    }
}
