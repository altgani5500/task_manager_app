package com.example.ienovo.bnan;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.clans.fab.FloatingActionMenu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

//import static java.security.AccessController.getContext;

public class Choice_employ extends Activity {



    //a list to store all the products
    List<ProductMain> productList;
    FloatingActionMenu materialDesignFAM;
    SwipeRefreshLayout mSwipeRefreshLayout;
    //the recyclerview
    RecyclerView recyclerView;
    public static String passwordl0,massge_typel;
    public static int project_id2,job_id2;
    FloatingActionButton fab;
    ProgressBar Pbar;
    private Button etUserName;
    public  static JSONObject JSONestimate = new JSONObject();
    public  static JSONArray myarray = new JSONArray();
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.choice_employ);

        passwordl0 = getIntent().getStringExtra("password");
        massge_typel=getIntent().getStringExtra("massage_type");
        project_id2 = getIntent().getIntExtra("project_id",0);
        job_id2 = getIntent().getIntExtra("job_id",0);

       fab = (FloatingActionButton) findViewById(R.id.material_design_android_floating_action_menu);
        button = (Button) findViewById(R.id.button3);
        fab.setVisibility(View.GONE);
button.setVisibility(View.GONE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent alvvwefrerxx = new Intent(Choice_employ.this, SendMassge.class);

                int a=ProductsAdapter_employs.myarray2.length();

                if(a>0)
                {
                    alvvwefrerxx.putExtra("password",passwordl0);
                    alvvwefrerxx.putExtra("massage_type",massge_typel);
                    alvvwefrerxx.putExtra("project_id",project_id2);
                    alvvwefrerxx.putExtra("job_id",job_id2);
                    startActivity(alvvwefrerxx);
                    overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                }else if (a<=0){
                    Toast.makeText(Choice_employ.this, "يجب عليك تحديد شخص واحد على الاقل", Toast.LENGTH_LONG).show();
                }

            }
        });





        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_container2);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                loadProducts();

            }

        });

        recyclerView = (RecyclerView) findViewById(R.id.recylcerView22);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(Choice_employ.this));
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



        // getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        //    final String URL_PRODUCTS = "http://sultantec.com/files/api.php";
        /*
        * Creating a String Request
        * The request type is GET defined by first parameter
        * The URL is defined in the second parameter
        * Then we have a Response Listener and a Error Listener
        * In response listener we will get the JSON response as a String
        * */
        // recyclerView.setVisibility(View.GONE);
        Pbar.setVisibility(View.VISIBLE);
        etUserName.setVisibility(View.GONE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,"http://tagogx.000webhostapp.com/task_manager/Get_team.php?employ_pass="+passwordl0+"&project_id="+project_id2+"&p_job_id="+job_id2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        //  getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        //        Toast.makeText(mCtx, "Reponse : " + response, Toast.LENGTH_SHORT).show();
                        try {
//"https://sultantec.com/bisha/prudectall.php",
                            fab.setVisibility(View.VISIBLE);
                            button.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.VISIBLE);

                            // Pbar.setVisibility(View.VISIBLE);

                            mSwipeRefreshLayout.setRefreshing(false);
                            //converting the string to json array object

                            JSONObject jsonObject = new JSONObject(response);
                            //JSONArray array_1 = jsonObject.getJSONArray("locations");
                            JSONArray array_2 = jsonObject.getJSONArray("Get_Team");

                            //
                        //    JSONObject jo = array_2.getJSONObject(0);
                            //    Log.d("f",jo.toString());

                            //  Toast.makeText(mCtx, "Reponse : " + array_2, Toast.LENGTH_SHORT).show();

                            //  Toast.makeText(mCtx, "Reponse : " + array_2, Toast.LENGTH_SHORT).show();

                            //  JSONArray array = new JSONArray(response);

                            //  traversing through all the object
                            for (int i =array_2.length()-1 ; i>=0; i--) {


                               // Toast.makeText(Choice_employ.this, array_2.length(), Toast.LENGTH_SHORT).show();

                                String image1,image2,image3,image4,image5;

                                //getting product object from json array
                                JSONObject product = array_2.getJSONObject(i);

                                //adding the product to product list

                                productList.add(new ProductMain(
                                        product.getInt("employs_id"),
                                        product.getString("employs_name"),
                                        product.getString("employs_image"),
                                        product.getString("employs_email"),
                                        product.getString("employs_prunch"),
                                        product.getString("employs_stutus")

                                ));



                            }

                            //creating adapter object and setting it to recyclerview
                            ProductsAdapter_employs adapter = new ProductsAdapter_employs(Choice_employ.this, productList);


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

        //adding our stringrequest to queue
        RequestQueue requestQueue= Volley.newRequestQueue(Choice_employ.this);

        if (requestQueue ==null)
            requestQueue.add(stringRequest);

        else
        {
            requestQueue.cancelAll(Choice_employ.this);
            requestQueue.add(stringRequest);

        }}
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
    }
}
