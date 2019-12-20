package com.example.ienovo.bnan;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.constraint.ConstraintLayout;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

public class Show_Mssage extends Activity {

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
    Dialog myDialog;
ConstraintLayout constraintLayout;
    TextView button;
    TextView textView1,textView2,textView3,textView4,textView5,textView6;
ImageView imageView;
    FloatingActionMenu materialDesignFAM;
    FloatingActionButton floatingActionButton1, floatingActionButton2, floatingActionButton3;
    private static final int WRITE_REQUEST_CODE = 300;
    private static final String TAG = Show_Mssage.class.getSimpleName();
    private String url;
    Button btnShowProgress;
    // Progress Dialog
    private ProgressDialog pDialog;
    ImageView my_image;
    // Progress dialog type (0 - for Horizontal progress bar)
    public static final int progress_bar_type = 0;
    // File url to download
    private static String file_url = "https://api.androidhive.info/progressdialog/hive.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show__mssage);

        mssages_id=getIntent().getIntExtra("mssages_id",0);
        mssages_desc=getIntent().getStringExtra("mssages_desc");
        mssages_date=getIntent().getStringExtra("mssages_date");
        mssages_time=getIntent().getStringExtra("mssages_time");
        mssages_type=getIntent().getStringExtra("mssages_type");
        mssages_done=getIntent().getStringExtra("mssages_done");
        employs_id=getIntent().getStringExtra("employs_id");
        employs_name=getIntent().getStringExtra("employs_name");
        employs_image=getIntent().getStringExtra("employs_image");
        employs_email=getIntent().getStringExtra("employs_email");
        employs_prunch=getIntent().getStringExtra("employs_prunch");
        jobs_name=getIntent().getStringExtra("jobs_name");
        images_name=getIntent().getStringExtra("images_name");

        myDialog = new Dialog(this);
       constraintLayout = (ConstraintLayout) findViewById(R.id.constr2);
       button = ( TextView) findViewById(R.id.button17);

        imageView = (ImageView) findViewById(R.id.user_profile_photo);

        textView1 = (TextView) findViewById(R.id.textView2);  textView2 = (TextView) findViewById(R.id.textView3);
        textView3 = (TextView) findViewById(R.id.textView4);  textView4 = (TextView) findViewById(R.id.textView7);
        textView5 = (TextView) findViewById(R.id.textView9);  textView6 = (TextView) findViewById(R.id.myEditText);


        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);

//        floatingActionButton1 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item1);
//        floatingActionButton2 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item2);
        floatingActionButton3 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item3);
//        floatingActionButton3 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item3);

        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               ShowPopup();

            }
        });













        textView1.setText(employs_name);
        textView2.setText(jobs_name);

        Glide.with(Show_Mssage.this)
                .load(employs_image)
                .into(imageView);


        if(mssages_done.equals("0")){
            textView3.setText("لم يتم قبول المهمة");
            textView3.setTextColor(Color.parseColor("#FFC90404"));
        }else if(mssages_done.equals("1")){
            textView3.setText("تم قبول المهمة");
            textView3.setTextColor(Color.parseColor("#6ba100"));
        }
        textView4.setText(mssages_date);
        textView5.setText(mssages_time);
        textView6.setText(mssages_desc);


        if(images_name.equals("") || images_name.equals(null)){
      constraintLayout.setVisibility(View.GONE);
        }else {
            button.setVisibility(View.GONE);
        }

        TextView downloadButton = (TextView)findViewById(R.id.textView);
        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
file_url=images_name;

                new DownloadFileFromURL().execute(file_url);
                       // new DownloadFile().execute(url);


            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
    }



    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case progress_bar_type:
                pDialog = new ProgressDialog(this);
                pDialog.setMessage("Downloading file. Please wait...");
                pDialog.setIndeterminate(false);
                pDialog.setMax(100);
                pDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                pDialog.setCancelable(true);
                pDialog.show();
                return pDialog;
            default:
                return null;
        }
    }


    class DownloadFileFromURL extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread
         * Show Progress Bar Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showDialog(progress_bar_type);

        }

        /**
         * Downloading file in background thread
         * */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            try {
                URL url = new URL(f_url[0]);
                URLConnection conection = url.openConnection();
                conection.connect();
                // getting file length
                int lenghtOfFile = conection.getContentLength();

                // input stream to read file - with 8k buffer
                InputStream input = new BufferedInputStream(url.openStream(), 8192);

                // Output stream to write file
                OutputStream output = new FileOutputStream("/sdcard/"+file_url);

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress(""+(int)((total*100)/lenghtOfFile));

                    // writing data to file
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }

            return null;
        }

        /**
         * Updating progress bar
         * */
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            pDialog.setProgress(Integer.parseInt(progress[0]));
        }

        /**
         * After completing background task
         * Dismiss the progress dialog
         * **/
        @Override
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after the file was downloaded
            dismissDialog(progress_bar_type);

            Toast.makeText(Show_Mssage.this, "تم تحميل الملف بنجاح", Toast.LENGTH_LONG).show();
            // Displaying downloaded image into image view
            // Reading image path from sdcard
           // String imagePath = Environment.getExternalStorageDirectory().toString() + images_name;
            // setting downloaded into image view
          //  my_image.setImageDrawable(Drawable.createFromPath(imagePath));
        }

    }


    public void ShowPopup() {
        TextView txtclose;
        TextView name1;
     //   Button btnFollow;
        myDialog.setContentView(R.layout.chat);
        txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
        name1 =(TextView) myDialog.findViewById(R.id.name1);
     //   txtclose.setText("اغلاق");

        name1.setText(employs_name);
    //    btnFollow = (Button) myDialog.findViewById(R.id.btnfollow);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }


}
