package in.adityashukla.justshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private List<Product> data;


    public MyAdapter(Context context, List<Product> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false);
        }
        ImageView thumbnailImageView = convertView.findViewById(R.id.productThumbnail);
        TextView titleTextView = convertView.findViewById(R.id.productTitle);
        TextView descriptionView = convertView.findViewById(R.id.productDescription);
        TextView priceTextView = convertView.findViewById(R.id.productPrice);

        Product product = data.get(position);
        titleTextView.setText(product.getTitle());
        descriptionView.setText(product.getDescription());
        priceTextView.setText("MRP:"+ " " +String.valueOf(product.getPrice()));

        // Load the product thumbnail image using an image loading library like Glide or Picasso
        Glide.with(context)
                .load(product.getThumbnail())
                .into(thumbnailImageView);

        return convertView;
    }
}
