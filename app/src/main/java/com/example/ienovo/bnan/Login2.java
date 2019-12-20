package com.example.ienovo.bnan;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login2 extends Activity {
    private ProgressDialog mProgressDialog;
    EditText editText1,editText2;
    String user_name,user_pass;
    private static final int MY_SOCKET_TIMEOUT_MS = 400000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login2);
        Button etUserName23 = (Button) findViewById(R.id.login);

        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        user_name=editText1.getText().toString();
        user_pass=editText2.getText().toString();
        etUserName23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vvv) {
                if (!TextUtils.isEmpty(user_name))
                {
                    editText1.setError("الحقل مطلوب");
                    return;
                }
                else if (!TextUtils.isEmpty(user_pass))
                {
                    editText2.setError("الحقل مطلوب");
                    return;
                }else {
                    Intent alvvwefrerxx = new Intent(Login2.this, Home.class);
                    alvvwefrerxx.putExtra("user_name","tagog");
                    alvvwefrerxx.putExtra("user_pass","6600");
                    startActivity(alvvwefrerxx);
                    overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
                }
            }
        });
    }







    public void  update_s(){


        //  Toast.makeText(mCtx, in, Toast.LENGTH_SHORT).show();

        mProgressDialog = ProgressDialog.show(Login2.this,
                "جاري ...","عملية التحقق من البيانات", false, false);

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
                        Toast.makeText(Login2.this, "تمت عملية تسجيل الدخول بنجاح" , Toast.LENGTH_LONG).show();
                        mProgressDialog.dismiss();


                    } else if (!query_result.equals("1")) {
                        mProgressDialog.dismiss();
                        Toast.makeText(Login2.this, "عفوا ,تاكد من صحة البيانات" , Toast.LENGTH_LONG).show();

                    }


                } catch (JSONException e) {
                    //  rdr2.setVisibility(View.GONE);
                    mProgressDialog.dismiss();
                    Toast.makeText(Login2.this, "هنالك خطا ما اعد المحاولة"+e , Toast.LENGTH_LONG).show();
                    e.printStackTrace();

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                mProgressDialog.dismiss();
                Toast.makeText(Login2.this, "لم يتم تعديل الحالة تاكد من الانترنت" , Toast.LENGTH_LONG).show();
            }
        })      {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();


                params.put("new_stutus", user_name);
                params.put("pass", user_pass);

//                params.put("details", b);
                // params.put("domain", "http://itsalif.info");

                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        Volley.newRequestQueue(Login2.this).add(stringRequest);


    }
}
