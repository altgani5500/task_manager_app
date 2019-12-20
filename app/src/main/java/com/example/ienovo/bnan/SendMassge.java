package com.example.ienovo.bnan;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class SendMassge extends Activity implements View.OnClickListener{
    private static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE =0 ;
    public  static String image_id="";
    private static final int PICK_FILE_REQUEST = 1;
    private static final String TAG = MainActivity.class.getSimpleName();
    private String selectedFilePath;
    private String SERVER_URL = "http://tagogx.000webhostapp.com/task_manager/Upload_mssage.php";
    ImageView ivAttachment;
    Button bUpload;
    TextView tvFileName;
    ProgressDialog dialog;
    EditText mssage_desc;
    public static String passwordl0,massge_typel;
    public static int project_id2,job_id2;
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private TextView textView;
    private ProgressDialog mProgressDialog;
    private static final int MY_SOCKET_TIMEOUT_MS = 400000;
    private Handler handler = new Handler();
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_massge);
        ivAttachment = (ImageView) findViewById(R.id.ivAttachment);
        bUpload = (Button) findViewById(R.id.button3);
        tvFileName = (TextView) findViewById(R.id.tv_file_name);
        ivAttachment.setOnClickListener(this);
        bUpload.setOnClickListener(this);
        mssage_desc=(EditText)findViewById(R.id.myEditText) ;


        par();

      this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        passwordl0 = getIntent().getStringExtra("password");
        massge_typel=getIntent().getStringExtra("massage_type");
        project_id2 = getIntent().getIntExtra("project_id",0);
        job_id2 = getIntent().getIntExtra("job_id",0);
//
//        Log.d("pass: ", passwordl0);
//        Log.d("massge_type: ", massge_typel);
//        Log.d("project_id2 : ", project_id2+"");
//        Log.d("job_id2 : ", job_id2+"");
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        textView = (TextView) findViewById(R.id.textView);

        progressBar.setVisibility(View.GONE);
        textView.setVisibility(View.GONE);




    }

    @Override
    public void onClick(View v) {
        if(v== ivAttachment){

            //on attachment icon click
            showFileChooser();
        }
        if(v== bUpload){



            String user_pass=mssage_desc.getText().toString();

            if (TextUtils.isEmpty(user_pass))
            {
                mssage_desc.setError("الحقل مطلوب");
                return;
            }

            //on upload button Click
            else {
         upload_data();
                //Toast.makeText(SendMassge.this,"Please choose a File First",Toast.LENGTH_SHORT).show();
            }

        }
    }
    private void showFileChooser() {
        //  Intent intent = new Intent();
        //sets the select file to all types of files
        // intent.setType("application/*");


        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        String [] mimeTypes = {"image/*", "application/*"};
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);

        startActivityForResult(Intent.createChooser(intent,"Choose File to Upload.."),PICK_FILE_REQUEST);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == PICK_FILE_REQUEST){
                if(data == null){
                    //no data present
                    return;
                }

                Uri selectedFileUri = data.getData();
                selectedFilePath = FilePath.getPath(this,selectedFileUri);
                Log.i(TAG,"Selected File Path:" + selectedFilePath);

                if(selectedFilePath != null && !selectedFilePath.equals("")){
                    tvFileName.setText("جاري تحميل الملف...");
                //    dialog = ProgressDialog.show(SendMassge.this,"","جاري ارسال المهمة...",true);

                   // android:src="@drawable/upoadf"
                    progressBar.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.VISIBLE);

                    new Thread(new Runnable() {
                        public void run() {
                            while (progressStatus < 100) {
                                progressStatus += 1;
                                // Update the progress bar and display the
                                //current value in the text view
                                handler.post(new Runnable() {
                                    public void run() {
                                        progressBar.setProgress(progressStatus);
                                        textView.setText(progressStatus+"/"+progressBar.getMax());
                                    }
                                });
                                try {
                                    // Sleep for 200 milliseconds.
                                    Thread.sleep(200);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }


                        }
                    }).start();






                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            //creating new thread to handle Http Operations
                            uploadFile(selectedFilePath);
                        }
                    }).start();


                }else{
                    Toast.makeText(this,"Cannot upload file to server",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    //android upload file to server
    public int uploadFile(final String selectedFilePath){

        int serverResponseCode = 0;

        HttpURLConnection connection;
        DataOutputStream dataOutputStream;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";


        int bytesRead,bytesAvailable,bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        File selectedFile = new File(selectedFilePath);


        String[] parts = selectedFilePath.split("/");
        final String fileName = parts[parts.length-1];

        if (!selectedFile.isFile()){
            dialog.dismiss();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tvFileName.setText("Source File Doesn't Exist: " + selectedFilePath);
                }
            });
            return 0;
        }else{
            try{
                FileInputStream fileInputStream = new FileInputStream(selectedFile);
                URL url = new URL(SERVER_URL);
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);//Allow Inputs
                connection.setDoOutput(true);//Allow Outputs
                connection.setUseCaches(false);//Don't use a cached Copy
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Connection", "Keep-Alive");
                connection.setRequestProperty("ENCTYPE", "multipart/form-data");
                connection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                connection.setRequestProperty("uploaded_file",selectedFilePath);

                //creating new dataoutputstream
                dataOutputStream = new DataOutputStream(connection.getOutputStream());

                //writing bytes to data outputstream
                dataOutputStream.writeBytes(twoHyphens + boundary + lineEnd);
                dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
                        + selectedFilePath + "\"" + lineEnd);

                dataOutputStream.writeBytes(lineEnd);

                //returns no. of bytes present in fileInputStream
                bytesAvailable = fileInputStream.available();
                //selecting the buffer size as minimum of available bytes or 1 MB
                bufferSize = Math.min(bytesAvailable,maxBufferSize);
                //setting the buffer as byte array of size of bufferSize
                buffer = new byte[bufferSize];

                //reads bytes from FileInputStream(from 0th index of buffer to buffersize)
                bytesRead = fileInputStream.read(buffer,0,bufferSize);

                //loop repeats till bytesRead = -1, i.e., no bytes are left to read
                while (bytesRead > 0){
                    //write the bytes read from inputstream
                    dataOutputStream.write(buffer,0,bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable,maxBufferSize);
                    bytesRead = fileInputStream.read(buffer,0,bufferSize);
                }

//                BufferedReader in = new BufferedReader(
//                        new InputStreamReader(
//                                connection.getInputStream()));
//
//
//                String decodedString;
//                while ((decodedString = in.readLine()) != null) {
//                    // System.out.println(decodedString);
//                    Log.e("ddddddddd",decodedString);
//                }


                dataOutputStream.writeBytes(lineEnd);
                dataOutputStream.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                serverResponseCode = connection.getResponseCode();
                String serverResponseMessage = connection.getResponseMessage();

                Log.e(TAG, "Server Response is: " + serverResponseMessage + ": " + serverResponseCode);

                //response code of 200 indicates the server status OK
                if(serverResponseCode == 200){


                    BufferedReader br=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuffer response=new StringBuffer();

                    String line;

                    //READ LINE BY LINE
                    while ((line=br.readLine()) != null)
                    {
                        response.append(line);
                    }
                    //RELEASE RES
                    br.close();
                    image_id=response.toString();
                    Log.e(TAG, "out fail put is ----------: "+response.toString());
                   // upload_data();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setVisibility(View.GONE);
                            textView.setVisibility(View.GONE);
                            tvFileName.setText("تم ارفاق الملف ");
                            ivAttachment.setImageResource(R.drawable.uploadf2);
                            progressStatus=0;


                            Toast.makeText(SendMassge.this, "تم تحميل الملف بنجاح", Toast.LENGTH_SHORT).show();
                          //  tvFileName.setText("File Upload completed.\n\n You can see the uploaded file here: \n\n" + "http://coderefer.com/extras/uploads/"+ fileName);
                        }
                    });
                }

                //closing the input and output streams
                fileInputStream.close();
                dataOutputStream.flush();
                dataOutputStream.close();



            } catch (FileNotFoundException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(SendMassge.this,"File Not Found",Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (MalformedURLException e) {
                e.printStackTrace();
                Toast.makeText(SendMassge.this, "URL error!", Toast.LENGTH_SHORT).show();

            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(SendMassge.this, "Cannot Read/Write File!", Toast.LENGTH_SHORT).show();
            }
           // dialog.dismiss();
            return serverResponseCode;
        }

    }

   public void upload_data(){





           //  Toast.makeText(mCtx, in, Toast.LENGTH_SHORT).show();

           mProgressDialog = ProgressDialog.show(SendMassge.this,
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

           String url =Home.url+"Upload_mssage_data.php";

           StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
               @Override
               public void onResponse(String s) {

//               Toast.makeText(SendMassge.this, " runing " + s, Toast.LENGTH_LONG).show();
//
//                Log.e("erooooooooooooor",s);

                   try {

                       JSONObject jsonObject = new JSONObject(s);
                       //JSONArray array_1 = jsonObject.getJSONArray("locations");


                       JSONArray array_2 = jsonObject.getJSONArray("status");

                       JSONObject product = array_2.getJSONObject(0);

                       String query_result = product.getString("success");


                       if (query_result.equals("1")) {


                           Toast.makeText(SendMassge.this, "تم " , Toast.LENGTH_LONG).show();
                           mssage_desc.setText(null);
                           mProgressDialog.dismiss();
                         //  dialog.dismiss();


                       } else if (!query_result.equals("1")) {
                           mProgressDialog.dismiss();
                           Toast.makeText(SendMassge.this, "لم يتم تعديل الحالة اعد المحاولة" , Toast.LENGTH_LONG).show();

                       }


                   } catch (JSONException e) {
                       //  rdr2.setVisibility(View.GONE);
                       mProgressDialog.dismiss();
                       Toast.makeText(SendMassge.this, "لم يتم تعديل الحالة اعد المحاولة"+e , Toast.LENGTH_LONG).show();
                       e.printStackTrace();

                   }


               }
           }, new Response.ErrorListener() {
               @Override
               public void onErrorResponse(VolleyError volleyError) {
                   mProgressDialog.dismiss();
                   Toast.makeText(SendMassge.this, "لم يتم تعديل الحالة تاكد من الانترنت" , Toast.LENGTH_LONG).show();
               }
           })      {
               @Override
               protected Map<String, String> getParams()
               {
                   Map<String, String>  params = new HashMap<String, String>();
//                   Log.d("pass: ", passwordl0);
//                   Log.d("massge_type: ", massge_typel);
//                   Log.d("project_id2 : ", project_id2+"");
//                   Log.d("job_id2 : ", job_id2+"");
//
                   String msseg_desc=mssage_desc.getText().toString();

                   params.put("pass", passwordl0);
                   params.put("massge_desc", msseg_desc);
                   params.put("massge_type", massge_typel);
                   params.put("image_id", image_id);
                   params.put("project_id", project_id2+"");
                   params.put("employs_rcivers", ProductsAdapter_employs.myarray2.toString());



//                params.put("details", b);
                   // params.put("domain", "http://itsalif.info");

                   return params;
               }
           };

           stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                   MY_SOCKET_TIMEOUT_MS,
                   DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                   DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

           Volley.newRequestQueue(SendMassge.this).add(stringRequest);


       }








    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
    }


    
    
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void par(){

        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (shouldShowRequestPermissionRationale(
                    Manifest.permission.READ_EXTERNAL_STORAGE)) {
                // Explain to the user why we need to read the contacts
            }

            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

            // MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE is an
            // app-defined int constant that should be quite unique
            return;
        }
    }

}
