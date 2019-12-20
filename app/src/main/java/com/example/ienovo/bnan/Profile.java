package com.example.ienovo.bnan;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

import static java.security.AccessController.getContext;

public class Profile extends Activity {
    private static final int MY_SOCKET_TIMEOUT_MS = 400000;
    ProgressBar Pbar;
    private Button etUserName;
    RelativeLayout profile;
    TextView textView2, textView3, textView6, textView15, textView18, textView17, textView7, textView71, textView72;
    CircleImageView imageView;
    Dialog  dialog;
   // ProgressBar Pbar;
    private ProgressDialog mProgressDialog;
    String new_stuts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        Pbar = (ProgressBar) findViewById(R.id.progressBar1);
        profile = (RelativeLayout) findViewById(R.id.profile1);

        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView6 = (TextView) findViewById(R.id.textView6);


        textView15 = (TextView) findViewById(R.id.button15);
        textView18 = (TextView) findViewById(R.id.button18);
        textView17 = (TextView) findViewById(R.id.button17);
        textView7 = (TextView) findViewById(R.id.button7);
        textView71 = (TextView) findViewById(R.id.button71);
        textView72 = (TextView) findViewById(R.id.button72);

        imageView = (CircleImageView) findViewById(R.id.user_profile_photo);


        Pbar.setVisibility(View.VISIBLE);
        profile.setVisibility(View.GONE);

        etUserName = (Button) findViewById(R.id.erorrp);
        etUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vvv) {
                loadProducts();

            }
        });


        Button etUserName2 = (Button) findViewById(R.id.textView4);
        etUserName2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vvv) {
               update_stutus();

            }
        });



        loadProducts();
    }



    public void update_stutus() {

        dialog = new Dialog(Profile.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_edit);
        // dialog.setTitle("خروج من التطبيق");
        // set the custom dialog components - text, image and button
        //  ImageView image = (ImageView) dialog.findViewById(R.id.image);
        //  image.setImageResource(R.drawable.ic_launcher);



        Button dialogButtonc1 = (Button) dialog.findViewById(R.id.editText8);
        Button dialogButtonc2 = (Button) dialog.findViewById(R.id.editText12);
        Button dialogButtonc3 = (Button) dialog.findViewById(R.id.app);


        dialog.show();



        dialogButtonc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
new_stuts="1";
                update_s();
            }

        });


        dialogButtonc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new_stuts="2";
              update_s();
            }

        });



        dialogButtonc3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new_stuts="0";
              update_s();
            }

        });

    }


    public void  update_s(){


        //  Toast.makeText(mCtx, in, Toast.LENGTH_SHORT).show();

        mProgressDialog = ProgressDialog.show(Profile.this,
                "جاري ...","عملية التعديل", false, false);


//
//        try {
//
//
//            data ="&note=" + URLEncoder.encode(coment, "UTF-8");
//
//
////
//        } catch (Exception e) {
//            // return new String("Exception: " + e.getMessage());
//
//            Toast.makeText(mCtx, "theexption"+e, Toast.LENGTH_SHORT).show();
//        }

//        String a = adress.replaceAll(" ", "%20");
//        String b= con.replaceAll(" ", "%20");
//

        // Toast.makeText(mCtx, in+a+text+date+image1+b, Toast.LENGTH_SHORT).show();

//            final   String a = adrs.replaceAll(" ", "%20");
//            final   String b= cont.replaceAll(" ", "%20");


        //   String url = "https://sultantec.com/bisha/up_pro.php?a="+in+"&b="+a+"&c="+b;

        String url =Home.url+"Update_stutus.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {



                try {

                    JSONObject jsonObject = new JSONObject(s);
                    //JSONArray array_1 = jsonObject.getJSONArray("locations");


                    JSONArray array_2 = jsonObject.getJSONArray("status");

                        JSONObject product = array_2.getJSONObject(0);

                    String query_result = product.getString("success");
                    //Toast.makeText(Profile.this, " runing " + query_result, Toast.LENGTH_SHORT).show();

                    if (query_result.equals("1")) {
                        Toast.makeText(Profile.this, "تم تعديل الحالة" , Toast.LENGTH_LONG).show();
                        mProgressDialog.dismiss();
                        dialog.dismiss();
                        loadProducts();


//                        Intent al = new Intent(mCtx, My_adv.class);
//
//                        mCtx.startActivity(al);

//                        My_adv cls = new My_adv();
//                        cls.loadProducts();


                    } else if (!query_result.equals("1")) {
                        mProgressDialog.dismiss();
                        Toast.makeText(Profile.this, "لم يتم تعديل الحالة اعد المحاولة" , Toast.LENGTH_LONG).show();

                    }


                } catch (JSONException e) {
                    //  rdr2.setVisibility(View.GONE);
                    mProgressDialog.dismiss();
                    Toast.makeText(Profile.this, "لم يتم تعديل الحالة اعد المحاولة"+e , Toast.LENGTH_LONG).show();
                    e.printStackTrace();

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                mProgressDialog.dismiss();
                Toast.makeText(Profile.this, "لم يتم تعديل الحالة تاكد من الانترنت" , Toast.LENGTH_LONG).show();
            }
        })      {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("new_stutus", new_stuts);
                 params.put("pass", Home.password1);
//                params.put("details", b);
                // params.put("domain", "http://itsalif.info");

                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        Volley.newRequestQueue(Profile.this).add(stringRequest);


    }





    private void loadProducts() {

        //Pbar.setVisibility(View.VISIBLE);
        etUserName.setVisibility(View.GONE);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://tagogx.000webhostapp.com/task_manager/Get_Profile.php?pass=" + 6600,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //  getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        //        Toast.makeText(mCtx, "Reponse : " + response, Toast.LENGTH_SHORT).show();
                        try {
//"https://sultantec.com/bisha/prudectall.php",
                            // Toast.makeText(Profile.this, "Reponse : " + response, Toast.LENGTH_LONG).show();
                            profile.setVisibility(View.VISIBLE);
                            Pbar.setVisibility(View.GONE);
                            // Pbar.setVisibility(View.VISIBLE);
                            JSONObject jsonObject = new JSONObject(response);
                            //               String query_result = jsonObject.getString("Status");

                            JSONArray array_2 = jsonObject.getJSONArray("Get_profile");
                            // JSONObject product = array_2.getJSONObject(i);


                            for (int i = 0; i <= array_2.length(); i++) {

                                String image1, image2, image3, image4, image5;

                                //getting product object from json array
                                JSONObject product = array_2.getJSONObject(i);

                                //adding the product to product list

                                int employ_id = product.getInt("employ_id");
                                String employ_name = product.getString("employ_name");
                                String employ_phone = product.getString("employ_phone");
                                String employ_image = product.getString("employ_image");
                                String employ_email = product.getString("employ_email");
                                String employ_prunch = product.getString("employ_prunch");
                                String employ_user_name = product.getString("employ_user_name");
                                String employ_user_pass = product.getString("employs_user_pass");
                                String employ_jobs_name = product.getString("employ_jobs_name");
                                int employ_stutus = product.getInt("employ_stutus");


                                textView2.setText(employ_name);
                                textView3.setText(employ_jobs_name);
                                textView15.setText(employ_jobs_name);
                                textView18.setText(employ_prunch);
                                textView17.setText(employ_phone);
                                textView7.setText(employ_email);
                                textView71.setText(employ_user_name);
                                textView72.setText(employ_user_pass);
                                Glide.with(Profile.this)
                                        .load(employ_image)
                                        .into(imageView);


                                if (employ_stutus == 1) {

                                    textView6.setText("متاح حاليا");

                                    textView6.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_fiber_manual_record_black_24dp, 0, 0, 0);


                                } else if (employ_stutus == 2) {
                                    textView6.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_fiber_busy_record_black_24dp, 0, 0, 0);
                                    textView6.setText("مشغول حاليا");

                                } else if (employ_stutus == 0) {
                                    textView6.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_fiber_off_record_black_24dp, 0, 0, 0);
                                    textView6.setText("غير متاح");

                                }




                            }


                            //  etUserName.setVisibility(View.GONE);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            // Log.e("erroer res",e);
                            //  getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                            //   Toast.makeText(getContext(),""+e, Toast.LENGTH_LONG).show();

                            //etUserName.setVisibility(View.GONE);

                        }
                    }
                },


                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //      Log.e("erroer res",error);
                        //Toast.makeText(getContext(),""+error, Toast.LENGTH_LONG).show();
                        //   getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        Pbar.setVisibility(View.GONE);
                        etUserName.setVisibility(View.VISIBLE);

                    }
                });

        //adding our stringrequest to queue
        RequestQueue requestQueue = Volley.newRequestQueue(Profile.this);

        if (requestQueue == null)
            requestQueue.add(stringRequest);

        else {
            requestQueue.cancelAll(getContext());
            requestQueue.add(stringRequest);

        }


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
    }

}
