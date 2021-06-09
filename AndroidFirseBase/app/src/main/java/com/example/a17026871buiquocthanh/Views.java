package com.example.a17026871buiquocthanh;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Views extends AppCompatActivity implements  ISenderUser{

    ArrayList<User> users;
    RecyclerView rcvUser;
    com.example.thandroid.UserAdapter userAdapter;
    TextView tvID,tvName,tvDiachi;
    EditText edtName,edtDiaChi;
    Button btnThem,btnSua,btnXoa,btnGetData;
    User userChon=null;
    String url = "https://60b09ee41f26610017ffeb60.mockapi.io/api/user/User";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_views);
        hideSoftKeyboard();
        init();
        GetArrayJson(url);

        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetArrayJson(url);
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostAPI(url);
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PutAPI(url);
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Delete(url);
            }
        });

    }

    private void GetArrayJson(String url){
            users = new ArrayList<>();
            edtName.setText("");
            edtDiaChi.setText("");
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject object = (JSONObject) response.get(i);
                        String id = object.getString("id").toString();
                        String name = object.getString("ten").toString();
                        String diaChi = object.getString("diaChi").toString();

                        //  User user=new User("1","name","diaChi");

                        User user = new User(id, name, diaChi);

                        users.add(user);
                        Log.i("String01", users.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Views.this, "Error by get Json Array!", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

        Log.i("String", users.toString());
        userAdapter=new com.example.thandroid.UserAdapter(this,users);
        rcvUser.setAdapter(userAdapter);
        rcvUser.setLayoutManager(new LinearLayoutManager(this));

    }

    private void PostAPI(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Views.this, "Successfully", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Views.this, "Error by Post data!", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> params = new HashMap<>();
                params.put("ten",edtName.getText().toString());
                params.put("diaChi",edtDiaChi.getText().toString());

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void PutAPI(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, url + "/" + userChon.getId(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Views.this, "Successfully", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Views.this, "Error by Put data!"+url + "/" + userChon.getId(), Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> params = new HashMap<>();
                params.put("ten",edtName.getText().toString());
                params.put("diaChi",edtDiaChi.getText().toString());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void Delete(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url +"/" + userChon.getId(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Views.this, "Successfully", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Views.this, "Error by Post data!"+ userChon.getId(), Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void hideSoftKeyboard() {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }

    }

    public void init(){
        rcvUser=findViewById(R.id.rcvUser);
        tvID=findViewById(R.id.tvID);
        tvName=findViewById(R.id.tvName);
        tvDiachi=findViewById(R.id.tvDiachi);
        btnGetData=findViewById(R.id.btnGetData);
        btnThem=findViewById(R.id.btnThem);
        btnSua=findViewById(R.id.btnSua);
        btnXoa=findViewById(R.id.btnXoa);
        edtName=findViewById(R.id.edtTen);
        edtDiaChi=findViewById(R.id.edtDiaChi);
    }

    @Override
    public void sendUser(User user) {
        userChon = user;
        edtName.setText(user.getName());
        edtDiaChi.setText(user.getDiaChi());
    }

}