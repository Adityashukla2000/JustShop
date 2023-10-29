package in.adityashukla.justshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ItemShowActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView title,description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_show);

        imageView = findViewById(R.id.imgView);
        title = findViewById(R.id.title);
        description = findViewById(R.id.description);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            String img = bundle.getString("image");
            String hading = bundle.getString("title");
            String dec = bundle.getString("description");

            title.setText(hading.toString());
            description.setText(dec.toString());


            Glide.with(this)
                    .load(img)
                    .into(imageView);


        }


    }
}