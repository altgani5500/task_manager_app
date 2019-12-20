package com.example.ienovo.bnan;


import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import org.apache.commons.lang3.ArrayUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProductsAdapter_employs extends RecyclerView.Adapter<ProductsAdapter_employs.ProductViewHolder> {

public  static String text;
    public  static String date;
    public  static String adress;
    public  static String image1;
    int lastPosition=0;
   String image11,image2,image3,image4,image5;
    int up=0;
    String  employ_stutus="0";
    private static final int MY_SOCKET_TIMEOUT_MS = 400000;
    public static int[] employ_Array= new int[0];
    List<EstimateObject> employlist = new ArrayList<>();
   public static JSONArray myarray2 = new JSONArray();

    int Show=0;
String con;

    public  static String content;
    public  static int  id;

    private Context mCtx;
    private List<ProductMain> productList;

    //private ArrayList<ProductMain> myItems = new ArrayList<>();
   // Context mContxt;

    public ProductsAdapter_employs(Context mCtx, List<ProductMain> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }


    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.product_list_employes, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, final int position) {

     final ProductMain product = productList.get(position);
       // final Product product2 = productList.get(position);



        if (position > lastPosition) {                                           //anim_recycler_item_show
            Animation animation = AnimationUtils.loadAnimation(mCtx, R.anim.swing_up_left);

            ((ProductViewHolder) holder).onclik.startAnimation(animation);
            lastPosition = position;
        }
        lastPosition = -1;

       // String s= toString(position);

//        ((MyViewHolder) holder).layout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Do_this(items.o_id.trim());
//            }

        //loading the image
        Glide.with(mCtx)
                .load(product.employ_image())
                .into(holder.imageView);



        holder.textViewTitle.setText(product.employ_name());


      //  holder.textViewShortDesc.setText(product.employ_prunch());
        employ_stutus=product.employ_stutus();

        if (employ_stutus.equals("1")) {

            holder.textViewShortDesc.setText("متاح حاليا");

            holder.textViewShortDesc.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_fiber_manual_record_black_24dp, 0, 0, 0);

        } else if (employ_stutus.equals("2")){
            holder.textViewShortDesc.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_fiber_busy_record_black_24dp, 0, 0, 0);
            holder.textViewShortDesc.setText("مشغول حاليا");

        } else if (employ_stutus.equals("0")) {
            holder.textViewShortDesc.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_fiber_off_record_black_24dp, 0, 0, 0);
            holder.textViewShortDesc.setText("غير متاح");

        }


//        holder.textViewRating.setText(String.valueOf(product.getRating()));
//        holder.textViewPrice.setText(String.valueOf(product.getPrice()));
//        holder.fev.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ProductMain product = productList.get(position);
//                image1=product.getImage().toString();
//                text= holder.textViewShortDesc.getText().toString();
//                date= holder.textViewRating.getText().toString();
//                adress= holder.textViewTitle.getText().toString();
//                id=product.getId();
//                date= holder.textViewRating.getText().toString();
//
//                con=product.getShortdesc().toString();

//            }
//        });




// holder.onclik.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
////Log.isLoggable("postion",position);
//              //  Log.d("postion",position);
//  // Toast.makeText(mCtx, (, Toast.LENGTH_LONG).show();
//
//            }
//        });

        final ProductMain objIncome = productList.get(position);
       // holder.checkBox_employ.setChecked(objIncome.isSelected());


//        holder.checkBox_employ.setOnCheckedChangeListener(null);
//
//        holder.checkBox_employ.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                //set your object's last status
//              //  objIncome.setSelected(isChecked);
//                holder.checkBox_employ.setSelected(isChecked);
//            }
//        });




        holder.onclik.setOnClickListener(new  View.OnClickListener(){
            @Override
            public void onClick(View view) {
              // holder.checkBox_employ.setVisibility(View.VISIBLE);

                if(Show==1) {
                    String mtlob="";
                    Show=0;
                    holder.checkBox_employ.setVisibility(View.GONE);
                    //Toast.makeText(mCtx, Show, Toast.LENGTH_SHORT).show();
                    Log.e("jjjjjjjjjjjjjj",Show+"");
                   // holder.checkBox_employ.setChecked(false);

                    employ_Array =ArrayUtils.removeElement(employ_Array,product.employ_id());

                    // Toast.makeText(mCtx, product.getTitle() + "no", Toast.LENGTH_LONG).show();
                    for (int element:employ_Array ) {
                        //  System.out.println( element );

                      //  Log.d("arry",""+element);

                        // Log.d("arry",""+element);
                    }



//                    employlist.remove(new Integer(product.employ_id()));
//
//
//
//
                      // int b=employlist.;
//                        JSONObject jsonObj = null;
//                        try {
//                            jsonObj = new JSONObject(b);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                        try {
//                         mtlob = jsonObj.getString("employ_id");
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }


                 //  Log.d("lllllllooll : ", mtlob);


                        //employlist.remove(product.employ_id());



                   // employlist.remove(new Integer(product.employ_id()));








                    for (int i = 0; i < employlist.size(); i++) {

                        String j = employlist.get(i).id;


                        if (String.valueOf(product.employ_id()).equals(j)) {
                            //  Log.d("valueee : ", j);
                            employlist.remove(i);
                        }


                    }

//                    for (EstimateObject strArr : employlist) {
//                        // System.out.println(Arrays.toString(strArr));
//                        Log.d("valueee : ", String.valueOf(strArr));
//                    }

                  //  employlist.remove(product.employ_id());

                   // Toast.makeText(mCtx, ""+employlist, Toast.LENGTH_SHORT).show();
//                    Log.d("sizeeeee: ", employlist.size()+"");
//                    Log.d("JSONobject: ", JSONestimate.toString());
//                    Log.d("JSONArray : ", myarray.toString());
//


                    //  Toast.makeText(mCtx, productList.get(position) + "تمت الاضافة الي المفضلة", Toast.LENGTH_LONG).show();
                }else
                {

                    Show=1;
                    holder.checkBox_employ.setVisibility(View.VISIBLE);
                   // Toast.makeText(mCtx, Show, Toast.LENGTH_SHORT).show();

                    Log.e("jjjjjjjjjjjjjj",Show+"");
                   // holder.checkBox_employ.setChecked(true);

//                    int currentSize = employ_Array.length;
//                    int newSize = currentSize + 1;
//
//                    int[] tempArray = new int[ newSize ];
//                    for (int i=0; i < currentSize; i++)
//                    {
//                        tempArray[i] = employ_Array [i];
//                    }
//                    tempArray[newSize- 1] = product.employ_id();
//                    employ_Array = tempArray;
//                    for (int element:employ_Array ) {
//                        //  System.out.println( element );
//
//                       // Log.d("arry",""+element);
//                    }

                    String a=String.valueOf(product.employ_id());

                    employlist.add(new EstimateObject( a ));

//                    for (int i = 0; i < employlist.size(); i++) {
//
//                        try {
//                            JSONestimate.put("data:" + String.valueOf(i + 1), employlist.get(i).getJSONObject());
//                            myarray.put(employlist.get(i).getJSONObject());
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }

//                    Log.d("JSONobject: ", JSONestimate.toString());
//                    Log.d("JSONArray : ", myarray.toString());



                }






                JSONObject JSONestimate = new JSONObject();
                JSONArray myarray = new JSONArray();



                for (int i = 0; i < employlist.size(); i++) {


                    if (employlist.size() > 0) {
                        try {

                            JSONestimate.put("data:" + String.valueOf(i + 1), employlist.get(i).getJSONObject());
                            myarray.put(employlist.get(i).getJSONObject());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {

                        Log.d("JSONobject: ", "nuuuuuuuuul");

                    }
                }

                myarray2=myarray;
                Log.d("sizeeeee: ", employlist.size()+"");
               Log.d("JSONobject: ", JSONestimate.toString());
                Log.d("JSONArray : ", myarray2.toString());

            }


        });





//        holder.checkBox_employ.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//if(holder.checkBox_employ.isChecked()) {
//
//    int currentSize = employ_Array.length;
//    int newSize = currentSize + 1;
//
//    int[] tempArray = new int[ newSize ];
//    for (int i=0; i < currentSize; i++)
//    {
//        tempArray[i] = employ_Array [i];
//    }
//    tempArray[newSize- 1] = product.employ_id();
//    employ_Array = tempArray;
//    for (int element:employ_Array ) {
//      //  System.out.println( element );
//
//        Log.d("arry",""+element);
//    }
//
//
//  //  Toast.makeText(mCtx, productList.get(position) + "تمت الاضافة الي المفضلة", Toast.LENGTH_LONG).show();
//}else
//{
//
////Arrays.fill( employ_Array, null );
//
//        employ_Array =ArrayUtils.removeElement(employ_Array,product.employ_id());
//
//   // Toast.makeText(mCtx, product.getTitle() + "no", Toast.LENGTH_LONG).show();
//
//    for (int element:employ_Array ) {
//        //  System.out.println( element );
//
//        Log.d("arry",""+element);
//    }
//}
// }
//        });









//
//        holder.onclik.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
////text=product.getImage();
//               // text=product.getImage().toString();
//
//                image11=product.getImage1();
//                image2=product.getImage2();
//                image3=product.getImage3();
//                image4=product.getImage4();
//                image5=product.getImage5();
//
//                ProductMain product = productList.get(position);
//                image1=product.getImage().toString();
//                text= holder.textViewShortDesc.getText().toString();
//                date= holder.textViewRating.getText().toString();
//                adress= holder.textViewTitle.getText().toString();
//                  id=product.getId();
//                date= holder.textViewRating.getText().toString();
//
//                con=product.getShortdesc().toString();
//
//                Intent al = new Intent(mCtx, Show_adv.class);
//
//                al.putExtra("g1", text); al.putExtra("g2", image1); al.putExtra("g3", date); al.putExtra("g4", adress);
//
//                al.putExtra("g5", id);al.putExtra("g6", con);
//
//
//                al.putExtra("g7", image11);al.putExtra("g8", image2);
//                al.putExtra("g9", image3);al.putExtra("g10", image4);
//                al.putExtra("g11", image5);
//
//                mCtx.startActivity(al);
//
//
//
//              //overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
//
//     //  Toast.makeText(mCtx, "تمت الاضافة الي المفضلة" , Toast.LENGTH_LONG).show();
//
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView checkBox_employ;
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

checkBox_employ = (ImageView) itemView.findViewById(R.id.checkbox_employ);
         checkBox_employ.setVisibility(View.GONE);
            onclik=(ConstraintLayout)itemView.findViewById(R.id.constr);
          //  fev = (ImageView) itemView.findViewById(R.id.button2);
        }




    }

    public class EstimateObject {
        String id;
        int p;
        public EstimateObject(String id)
        {
            this.id = id;
           // this.name = name;
        //    this.p = position;
        }

        public JSONObject getJSONObject() {
            JSONObject obj = new JSONObject();
            try {
                obj.put("employ_id", id);
               // obj.put("Name", name);
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
            return obj;
        }


}

}