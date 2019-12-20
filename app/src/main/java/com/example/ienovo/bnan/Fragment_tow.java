
package com.example.ienovo.bnan;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Fragment_tow extends Fragment {

    private Context mCtx=getContext();

    //a list to store all the products
    List<ProductMain2> productList;

    SwipeRefreshLayout mSwipeRefreshLayout;
    //the recyclerview
    RecyclerView recyclerView;

    ProgressBar Pbar;
    private Button etUserName;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        View view =  inflater.inflate(R.layout.fragment_tow,
                container, false);

//        final Button button =
//                (Button) view.findViewById(R.id.erorrp);
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                loadProducts();
//            }
//        });


        mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipe_container);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                loadProductssw();

            }

        });
        //getting the recyclerview from xml
        recyclerView = (RecyclerView) view.findViewById(R.id.recylcerView2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setVisibility(View.GONE);




        Pbar = (ProgressBar)view.findViewById(R.id.progressBar1);
        Pbar.setVisibility(View.GONE);
        etUserName = (Button) view.findViewById(R.id.erorrp);
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



        return view;
    }

//    public void buttonClicked (View view) {
//
//    }


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

        StringRequest stringRequest = new StringRequest(Request.Method.POST,Home.url+"Getmssage_data.php?pass="+12345,
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
                            JSONArray array_2 = jsonObject.getJSONArray("Get_mssagedata");

                            //
                            JSONObject jo = array_2.getJSONObject(0);
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




                                productList.add(new ProductMain2(
                                        product.getInt("mssages_id"),

                                        product.getString("mssages_desc"),
                                        product.getString("mssages_date"),
                                        product.getString("mssages_time"),
                                        product.getString("mssages_type"),
                                        product.getString("mssages_done"),
                                        product.getString("employs_id"),
                                        product.getString("employs_name"),
                                        product.getString("employs_image"),
                                        product.getString("employs_email"),
                                        product.getString("employs_prunch"),
                                        product.getString("jobs_name"),
                                        product.getString("images_name")
                                ));



                            }

                            //creating adapter object and setting it to recyclerview
                            ProductsAdapter adapter = new ProductsAdapter(getContext(), productList);


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




                })

        {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();

                params.put("pass", Home.password1);

//                params.put("details", b);
                // params.put("domain", "http://itsalif.info");

                return params;
            }
        }



                ;

        //adding our stringrequest to queue
        RequestQueue requestQueue=Volley.newRequestQueue(getContext());

        if (requestQueue ==null)
            requestQueue.add(stringRequest);

        else
        {
            requestQueue.cancelAll(getContext());
            requestQueue.add(stringRequest);

    }}

    private void loadProductssw(){
    {
        productList.clear();



        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

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

        StringRequest stringRequest = new StringRequest(Request.Method.POST,Home.url+"Getmssage_data.php?pass="+123,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        //        Toast.makeText(mCtx, "Reponse : " + response, Toast.LENGTH_SHORT).show();
                        try {
//"https://sultantec.com/bisha/prudectall.php",
                            recyclerView.setVisibility(View.VISIBLE);

                            // Pbar.setVisibility(View.VISIBLE);

                            mSwipeRefreshLayout.setRefreshing(false);
                            //converting the string to json array object

                            JSONObject jsonObject = new JSONObject(response);
                            //JSONArray array_1 = jsonObject.getJSONArray("locations");
                            JSONArray array_2 = jsonObject.getJSONArray("Get_mssagedata");

                            //
                            JSONObject jo = array_2.getJSONObject(0);
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

                                productList.add(new ProductMain2(
                                        product.getInt("mssages_id"),

                                        product.getString("mssages_desc"),
                                        product.getString("mssages_date"),
                                        product.getString("mssages_time"),
                                        product.getString("mssages_type"),
                                        product.getString("mssages_done"),
                                        product.getString("employs_id"),
                                        product.getString("employs_name"),
                                        product.getString("employs_image"),
                                        product.getString("employs_email"),
                                        product.getString("employs_prunch"),
                                        product.getString("jobs_name"),
                                        product.getString("images_name")
                                ));



                            }

                            //creating adapter object and setting it to recyclerview
                            ProductsAdapter adapter = new ProductsAdapter(getContext(), productList);


                            recyclerView.setAdapter(adapter);


                            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

                            // recyclerView.setVisibility(View.VISIBLE);

                            Pbar.setVisibility(View.GONE);


                            //  etUserName.setVisibility(View.GONE);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            // Log.e("erroer res",e);
                            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                           // getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
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
                        getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    //    getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                        Pbar.setVisibility(View.GONE);
                        etUserName.setVisibility(View.VISIBLE);
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                })

        {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();

                params.put("pass", Home.password1);

//                params.put("details", b);
                // params.put("domain", "http://itsalif.info");

                return params;
            }
        };

        //adding our stringrequest to queue
        RequestQueue requestQueue=Volley.newRequestQueue(getContext());

        if (requestQueue ==null)
            requestQueue.add(stringRequest);

        else
        {
            requestQueue.cancelAll(getContext());
            requestQueue.add(stringRequest);

        }}
}
    @Override
    public void onDestroy() {
       if( mSwipeRefreshLayout.isRefreshing())
       {
           mSwipeRefreshLayout.setRefreshing(false);
           super.onDestroy();

       }else
       {

           super.onDestroy();

       }
    }
}
