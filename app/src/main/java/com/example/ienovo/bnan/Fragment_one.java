
package com.example.ienovo.bnan;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;



public class Fragment_one extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        View view =  inflater.inflate(R.layout.fragment_one,
                container, false);

//        final Button button =
//                (Button) view.findViewById(R.id.erorrp);
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                loadProducts();
//            }
//        });



        return view;
    }

//    public void buttonClicked (View view) {
//
//    }



}
