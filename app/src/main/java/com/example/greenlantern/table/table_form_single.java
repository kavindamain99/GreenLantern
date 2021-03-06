package com.example.greenlantern.table;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.greenlantern.MainActivity;
import com.example.greenlantern.MainAdapter;
import com.example.greenlantern.R;

public class table_form_single extends AppCompatActivity {

    //initialize variable
    DrawerLayout drawerLayout;
    ImageView btMenu;
    RecyclerView recyclerView;


    TextView tv_table_id;
    TextView tv_amount_value;
    TextView tv_unit_value;
    EditText et_total;
    EditText pt_for;
    EditText pt_phone;
    Button next;

    String forName;
    String tableId;
    TextView tableID;
    String userId;

    String errorMessage;
    ImageView table_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_form_single);

        //assign variable

        drawerLayout = findViewById(R.id.drawer_layout);
        btMenu = findViewById(R.id.bt_menu);
        recyclerView = findViewById(R.id.recycle_view);
        pt_for=findViewById(R.id.pt_for);
        pt_phone=findViewById(R.id.pt_Phone);
        next=findViewById(R.id.btn_next);
        tableID =findViewById(R.id.tv_table_id);
        table_img =findViewById(R.id.img_table);
        //set values
        forName = pt_for.getText().toString();


        //set layout manager

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MainAdapter(this,MainActivity.arrayList));

        btMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });


        //take a single table
        tv_table_id = findViewById(R.id.tv_table_id);
        tv_amount_value = findViewById(R.id.tv_amount_value);
        et_total = findViewById(R.id.et_total);

        //get data by intent
        Intent intent = getIntent();
        int table_id = intent.getIntExtra("table",0);
        tableId=intent.getStringExtra("tableId");
        userId=intent.getStringExtra("userId");

        if(table_id == R.id.tb001){
            tv_table_id.setText("TB001");
            tv_amount_value.setText("2");
            et_total.setText("Rs.2400.00");

        }
        else if(table_id==R.id.tb004){
            tv_table_id.setText("TB004");
            tv_amount_value.setText("2");
            et_total.setText("Rs.2400.00");
        }

        else if(table_id==R.id.tb003){
            tv_table_id.setText("TB003");
            tv_amount_value.setText("2");
            et_total.setText("Rs.2400.00");
        }
        else if(table_id==R.id.tb002){
            tv_table_id.setText("TB002");
            tv_amount_value.setText("2");
            et_total.setText("Rs.2400.00");
        }
        else if(table_id==R.id.tb005){
            tv_table_id.setText("TB005");
            tv_amount_value.setText("2");
            et_total.setText("Rs.2400.00");
        }
        else if(table_id==R.id.tb006){
            tv_table_id.setText("TB006");
            tv_amount_value.setText("4");
            et_total.setText("Rs.4800.00");
            table_img.setImageResource(R.drawable.dual);
        }
        else if(table_id==R.id.tb007){
            tv_table_id.setText("TB007");
            tv_amount_value.setText("4");
            et_total.setText("Rs.4800.00");
            table_img.setImageResource(R.drawable.dual);

        }
        else if(table_id==R.id.tb008){
            tv_table_id.setText("TB008");
            tv_amount_value.setText("4");
            et_total.setText("Rs.4800.00");
            table_img.setImageResource(R.drawable.dual);
        }

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //form validation
                if(TextUtils.isEmpty(pt_for.getText().toString())){
                    errorMessage = "For Can not be empty!";
                    showToast(errorMessage);

            }
            else if(TextUtils.isEmpty(pt_phone.getText().toString())){
                errorMessage = "Phone Number Can not be empty!";
                    showToast(errorMessage);
                }
                else if(pt_for.getText().toString().matches(".*\\d.*")){
                    errorMessage = "Name Can not be A number !";
                    showToast(errorMessage);

                }

                else if(pt_for.getText().toString().length()>20){
                    errorMessage = "Name Must be 1 to 20 Character !";
                    showToast(errorMessage);

                }
                else if(pt_phone.getText().toString().length()!=10){
                    errorMessage ="Phone Number Must Include 10 Numbers !";
                    showToast(errorMessage);

                }
            else{
                    nextPage(view);
                }
            }
        });

    }

    public void nextPage(View view){


            Intent intentnext = new Intent(this, table_form_2_single.class);
            intentnext.putExtra("table",view.getId());
            intentnext.putExtra("tableId",tableID.getText().toString());
            intentnext.putExtra("for",pt_for.getText().toString());
            intentnext.putExtra("phone",pt_phone.getText().toString());
            intentnext.putExtra("total",et_total.getText().toString());
            intentnext.putExtra("amount",tv_amount_value.getText().toString());
            intentnext.putExtra("userId",userId);
            startActivity(intentnext);


    }


    public void remove(View view){
        tv_unit_value = findViewById(R.id.tv_unit_price);
        et_total = findViewById(R.id.et_total);
        Intent intent = getIntent();
        int table_id = intent.getIntExtra("table",0);
        if(table_id==R.id.tb001||table_id==R.id.tb002||table_id==R.id.tb003||table_id==R.id.tb004||table_id==R.id.tb005) {
            tv_amount_value.setText("1");
            tv_unit_value.setText("Rs.1200x1");
            et_total.setText("Rs.1200.00");
        }
        else if(table_id==R.id.tb006||table_id==R.id.tb007||table_id==R.id.tb008){
            tv_amount_value.setText("3");
            tv_unit_value.setText("Rs.1200x3");
            et_total.setText("Rs.3600.00");
        }

    }

    public void add(View view){
        tv_unit_value = findViewById(R.id.tv_unit_price);
        et_total = findViewById(R.id.et_total);
        Intent intent = getIntent();
        int table_id = intent.getIntExtra("table",0);
        if(table_id==R.id.tb001||table_id==R.id.tb002||table_id==R.id.tb003||table_id==R.id.tb004||table_id==R.id.tb005) {
            tv_amount_value.setText("2");
            tv_unit_value.setText("Rs.1200x2");
            et_total.setText("Rs.2400.00");
        }
        else if(table_id==R.id.tb006||table_id==R.id.tb007||table_id==R.id.tb008){
            tv_amount_value.setText("4");
            tv_unit_value.setText("Rs.1200x4");
            et_total.setText("Rs.4800.00");
        }

    }

    public void back(View view){
        finish();
    }




    @Override
    protected void onPause(){
        super.onPause();

        //close drawer
        MainActivity.closeDrawer(drawerLayout);


    }

    //custom toast message

    public void showToast(String errorMessage) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.toast_message));


        TextView toastText = layout.findViewById(R.id.toast_text);
        ImageView toastImage = layout.findViewById(R.id.toast_image);

        toastText.setText(errorMessage);

        toastImage.setImageResource(R.drawable.emote);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG*471);


        toast.setView(layout);


        toast.show();
    }


}