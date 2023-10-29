package in.adityashukla.justshop;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

 RequestQueue requestQueue;
    StringRequest stringRequest;

   private String Url = "https://dummyjson.com/products";
    Button button;
    TextView textView;
    List<Product>   list = new ArrayList();
    private MyAdapter adapter;

    ListView listView;

    SearchView searchView;
    private ImageView checkList,wishList;
    private List<Product> filteredProductList = new ArrayList();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);

        checkList = view.findViewById(R.id.checkList);
        wishList = view.findViewById(R.id.wishList);


        listView = view.findViewById(R.id.listView);
        adapter = new MyAdapter(getContext(),filteredProductList);

        listView.setAdapter(adapter);

        searchView = view.findViewById(R.id.searchView);



        requestQueue  = Volley.newRequestQueue(getContext());


        stringRequest = new StringRequest(Request.Method.GET, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {



                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("products");

//                    Log.d("data",jsonArray.toString());


                    for (int i =0;i<jsonArray.length();i++){

                        JSONObject productObject = jsonArray.getJSONObject(i);
                        int id = productObject.getInt("id");
                        String title = productObject.getString("title");
                        String description = productObject.getString("description");
                        double price = productObject.getDouble("price");
                        double discountPercentage = productObject.getDouble("discountPercentage");
                        double rating = productObject.getDouble("rating");
                        int stock = productObject.getInt("stock");
                        String brand = productObject.getString("brand");
                        String category = productObject.getString("category");
                        String thumbnail = productObject.getString("thumbnail");
                        JSONArray imagesArray = productObject.getJSONArray("images");

                        List<String> images = new ArrayList<>();

                        for (int j = 0; j < imagesArray.length(); j++) {
                            images.add(imagesArray.getString(j));
                        }
                        Product product = new Product(id, title, description, price, discountPercentage,
                                rating, stock, brand, category, thumbnail, images);



                        list.add(product);


                    }

                    filteredProductList.addAll(list);
                    // Notify the adapter that the data has changed
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Response :" + error.toString(), Toast.LENGTH_LONG).show();//display the response on screen
            }
        });


        requestQueue.add(stringRequest);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filteredProductList.clear();

                for (Product product : list){

                    if (product.getTitle().toLowerCase().contains(newText.toLowerCase())){
                        filteredProductList.add(product);
                    }

                    adapter.notifyDataSetChanged();

                }
                return true;
            }
        });

        checkList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),CheckListActivity.class);
                startActivity(intent);
            }
        });

        wishList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),WishListActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}