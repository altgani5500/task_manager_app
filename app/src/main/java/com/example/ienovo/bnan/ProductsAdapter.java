package com.example.ienovo.bnan;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {

public  static String text;
    public  static String date;
    public  static String adress;
    public  static String image1;
    int lastPosition=0;

    private int mssages_id;
    private String mssages_desc;
    private String mssages_date;
    private String mssages_time;
    private String mssages_type;
    private String mssages_done;

    private String employs_id;
    private String employs_name;
    private String employs_image;
    private String employs_email;
    private String employs_prunch;
    private String jobs_name;
    private String images_name;

   String image11,image2,image3,image4,image5;



    int up=0;

    private static final int MY_SOCKET_TIMEOUT_MS = 400000;


String con;

    public  static String content;
    public  static int  id;

    private Context mCtx;
    private List<ProductMain2> productList;
   // Context mContxt;

    public ProductsAdapter(Context mCtx, List<ProductMain2> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }


    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.product_list2, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, final int position) {
     final ProductMain2 product = productList.get(position);
       // final Product product2 = productList.get(position);



        if (position > lastPosition) {                                           //anim_recycler_item_show
            Animation animation = AnimationUtils.loadAnimation(mCtx, R.anim.swing_up_left);

            ((ProductViewHolder) holder).onclik.startAnimation(animation);
            lastPosition = position;
        }
        lastPosition = -1;

//        ((MyViewHolder) holder).layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Do_this(items.o_id.trim());
//            }






        //loading the image



        Glide.with(mCtx)
                .load(product.getImage3())
                .into(holder.imageView);

        holder.textViewTitle.setText(product.getImage2());

        holder.textViewShortDesc.setText(product.getImage6());

        holder.textViewRating.setText(String.valueOf(product.getRating()));


        holder.textViewPrice.setText(String.valueOf(product.getTitle() ));


//        holder.fev.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                ProductMain product = productList.get(position);
//                image1=product.getImage().toString();
//                text= holder.textViewShortDesc.getText().toString();
//                date= holder.textViewRating.getText().toString();
//                adress= holder.textViewTitle.getText().toString();
//                id=product.getId();
//                date= holder.textViewRating.getText().toString();
//
//                con=product.getShortdesc().toString();
//
//
//
//
//
//
//
//
//
//
//            }
//        });




//
        holder.onclik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//text=product.getImage();
               // text=product.getImage().toString();


                  mssages_id=product.getId();
                  mssages_desc=product.getTitle();
                  mssages_date=product.getShortdesc();
                  mssages_time=product.getRating();
                  mssages_type=product.getPrice();
                  mssages_done=product.getImage();
                  employs_id=product.getImage1();
                  employs_name=product.getImage2();
                  employs_image=product.getImage3();
                  employs_email=product.getImage4();
                  employs_prunch=product.getImage5();
                  jobs_name=product.getImage6();
                  images_name=product.getImage7();

//                image11=product.getImage1();
//                image2=product.getImage2();
//                image3=product.getImage3();
//                image4=product.getImage4();
//                image5=product.getImage5();
//                image1=product.getImage().toString();
//                text= holder.textViewShortDesc.getText().toString();
//                date= holder.textViewRating.getText().toString();
//                adress= holder.textViewTitle.getText().toString();
//                  id=product.getId();
//                date= holder.textViewRating.getText().toString();
//
//                con=product.getShortdesc().toString();
//
//
                Intent al = new Intent(mCtx, Show_Mssage.class);

                al.putExtra("mssages_id", mssages_id); al.putExtra("mssages_desc", mssages_desc); al.putExtra("mssages_date", mssages_date); al.putExtra("mssages_time", mssages_time);
                al.putExtra("mssages_type", mssages_type);al.putExtra("mssages_done", mssages_done);
                al.putExtra("employs_id", employs_id);al.putExtra("employs_name", employs_name);
                al.putExtra("employs_image", employs_image);al.putExtra("employs_email", employs_email);
                al.putExtra("employs_prunch", employs_prunch);al.putExtra("jobs_name", jobs_name);
                al.putExtra("images_name", images_name);

                mCtx.startActivity(al);
                ((Activity)mCtx).overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);

                Animation animation = AnimationUtils.loadAnimation(mCtx, R.anim.swing_up_left);
                ((ProductsAdapter.ProductViewHolder) holder).onclik.startAnimation(animation);

            }
        });
    }
    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc, textViewRating, textViewPrice;
       // ImageView imageView;
        CircleImageView imageView;
        ImageView fev;
ConstraintLayout onclik;



        public ProductViewHolder(View itemView) {
            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.textView2);
            textViewShortDesc = (TextView) itemView.findViewById(R.id.textView3);
            textViewRating = (TextView) itemView.findViewById(R.id.textView4);
            textViewPrice = (TextView) itemView.findViewById(R.id.textView7);
            imageView = (CircleImageView) itemView.findViewById(R.id.user_profile_photo);


            onclik=(ConstraintLayout)itemView.findViewById(R.id.constr);
          //  fev = (ImageView) itemView.findViewById(R.id.button2);
        }




    }



}