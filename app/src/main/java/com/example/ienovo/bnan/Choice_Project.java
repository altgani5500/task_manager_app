package com.example.ienovo.bnan;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//import static java.security.AccessController.getContext;

public class Choice_Project extends Activity {

    //a list to store all the products
    List<Element_project> productList;

    SwipeRefreshLayout mSwipeRefreshLayout;
    //the recyclerview
    RecyclerView recyclerView;
public static String password,massge_type;
    ProgressBar Pbar;
    private Button etUserName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choice_project);

        password = getIntent().getStringExtra("password");
        massge_type=getIntent().getStringExtra("massage_type");




        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_container1);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                loadProducts();

            }


        });

        recyclerView = (RecyclerView) findViewById(R.id.recylcerView21);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(Choice_Project.this));
        recyclerView.setVisibility(View.GONE);




        Pbar = (ProgressBar)findViewById(R.id.progressBar1);
        Pbar.setVisibility(View.GONE);
        etUserName = (Button) findViewById(R.id.erorrp);
        etUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vvv) {
                loadProducts();

            }
        });

//        loadProducts();

        //initializing the productlist
        productList = new ArrayList<>();

        loadProducts();


    }



    private void loadProducts() {
        productList.clear();

        Pbar.setVisibility(View.VISIBLE);
        etUserName.setVisibility(View.GONE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,Home.url+"Get_Projects.php?pass="+password,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //  getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        //        Toast.makeText(mCtx, "Reponse : " + response, Toast.LENGTH_SHORT).show();
                        try {
//"https://sultantec.com/bisha/prudectall.php",
                            recyclerView.setVisibility(View.VISIBLE);

                            // Pbar.setVisibility(View.VISIBLE);

                            mSwipeRefreshLayout.setRefreshing(false);
                            //converting the string to json array object

                            JSONObject jsonObject = new JSONObject(response);

                            //JSONArray array_1 = jsonObject.getJSONArray("locations");
                            JSONArray array_2 = jsonObject.getJSONArray("Get_Projects");

                      // String query_result = jsonObject.getString("Get_Projects");


                            //  JSONObject jo = array_2.getJSONObject(0);
                            //    Log.d("f",jo.toString());

                            //  Toast.makeText(mCtx, "Reponse : " + array_2, Toast.LENGTH_SHORT).show();

                            //  Toast.makeText(mCtx, "Reponse : " + array_2, Toast.LENGTH_SHORT).show();

                            //  JSONArray array = new JSONArray(response);

                            //  traversing through all the object
                            for (int i =array_2.length()-1 ; i >=0; i--) {

                                String image1,image2,image3,image4,image5;

                                //getting product object from json array
                                JSONObject product = array_2.getJSONObject(i);

                                //adding the product to product list

                                productList.add(new Element_project(
                                        product.getInt("progect_id"),
                                        product.getString("project_name"),
                                        product.getString("project_desc"),
                                        product.getString("project_image"),
                                        product.getString("project_start"),
                                        product.getString("project_end")
                                ));



                            }

                            //creating adapter object and setting it to recyclerview
                            ProductsAdapter_projects adapter = new ProductsAdapter_projects(Choice_Project.this, productList);


                            recyclerView.setAdapter(adapter);




                            // recyclerView.setVisibility(View.VISIBLE);

                            Pbar.setVisibility(View.GONE);


                            //  etUserName.setVisibility(View.GONE);





                        } catch (JSONException e) {
                            e.printStackTrace();
                            // Log.e("erroer res",e);
                            //  getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                            mSwipeRefreshLayout.setRefreshing(false);

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
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
        Volley.newRequestQueue(Choice_Project.this).add(stringRequest);
     }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
    }
}
