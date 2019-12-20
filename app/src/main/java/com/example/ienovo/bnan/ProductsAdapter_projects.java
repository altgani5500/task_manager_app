package com.example.ienovo.bnan;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProductsAdapter_projects extends RecyclerView.Adapter<ProductsAdapter_projects.ProductViewHolder> {

public  static String text;
    public  static String date;
    public  static String adress;
    public  static String image1;
    int lastPosition=0;
    public  static  int project_id;
   String image11,image2,image3,image4,image5;
    int up=0;

    private static final int MY_SOCKET_TIMEOUT_MS = 400000;


String con;

    public  static String content;
    public  static int  id;

    private Context mCtx;
    private List<Element_project> productList;
   // Context mContxt;

    public ProductsAdapter_projects(Context mCtx, List<Element_project> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.product_list_projects, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, final int position) {
     final Element_project product = productList.get(position);
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
                .load(product.project_image())
                .into(holder.imageView);
        holder.textViewTitle.setText(product.project_name());
        holder.textViewShortDesc.setText(product.project_desc());



//        holder.textViewRating.setText(String.valueOf(product.getRating()));
//        holder.textViewPrice.setText(String.valueOf(product.getPrice()));



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





        holder.onclik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//text=product.getImage();
               // text=product.getImage().toString();

                project_id=product.project_id();
                Intent al = new Intent(mCtx, Choice_team.class);
                al.putExtra("password",Choice_Project.password);
                al.putExtra("massage_type",Choice_Project.massge_type);
               al.putExtra("project_id",project_id);

                mCtx.startActivity(al);

                ((Activity)mCtx).overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);



                Animation animation = AnimationUtils.loadAnimation(mCtx, R.anim.swing_up_left);
                ((ProductViewHolder) holder).onclik.startAnimation(animation);

              //  mCtx.overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);

              //overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);

     //  Toast.makeText(mCtx, "تمت الاضافة الي المفضلة" , Toast.LENGTH_LONG).show();

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
//            textViewRating = (TextView) itemView.findViewById(R.id.textView4);
//            textViewPrice = (TextView) itemView.findViewById(R.id.textView7);
            imageView = (CircleImageView) itemView.findViewById(R.id.user_profile_photo);


            onclik=(ConstraintLayout)itemView.findViewById(R.id.constr);
          //  fev = (ImageView) itemView.findViewById(R.id.button2);
        }




    }



}