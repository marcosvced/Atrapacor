package devlrmve.atrapacor.com.atrapacor.Utils;

import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Color;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import devlrmve.atrapacor.com.atrapacor.R;


/**
 * Created by marcos_vicente on 13/01/16.
 */
public class RecordsAdapter extends
        RecyclerView.Adapter<RecordsAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView record_name_TextView;
        public TextView record_description_TextView;
        public TextView record_date_TextView;
        public ImageView imageView;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);
            itemView.setOnClickListener(this);
            record_name_TextView = (TextView) itemView.findViewById(R.id.record_name_item);
            record_description_TextView = (TextView) itemView.findViewById(R.id.record_description_item);
            imageView = (ImageView) itemView.findViewById(R.id.img_record);
            record_date_TextView = (TextView) itemView.findViewById(R.id.record_date_item);
        }

        @Override
        public void onClick(View v) {
            Log.i("Pulsar", String.valueOf(getPosition()));
        }
    }

    // Store a member variable for the contacts
    private List<RecordsName> mRecords;
    private List<Records> mRecord_user;
    private List<String> mTipe_View;
    private static FragmentManager mFragmentManager;

    // Pass in the contact array into the constructor
    public RecordsAdapter(List<RecordsName> records, List<String> tipe_view, List<Records> record_user, FragmentManager fragmentManager) {
        mRecords = records;
        mTipe_View = tipe_view;
        mRecord_user = record_user;
        mFragmentManager = fragmentManager;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public RecordsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View scoreView = inflater.inflate(R.layout.item_record, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(scoreView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(RecordsAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        RecordsName records = mRecords.get(position);

        // Set item views based on the data model
        TextView record_name_view = viewHolder.record_name_TextView;
        TextView record_description_view = viewHolder.record_description_TextView;
        ImageView imageView = viewHolder.imageView;
        TextView record_date_view = viewHolder.record_date_TextView;

        imageView.setColorFilter(Color.argb(170,70, 70, 70));
        record_date_view.setVisibility(View.GONE);

        if (mTipe_View.get(position).equalsIgnoreCase("TYPE_VIEW")) {
            imageView.setColorFilter(null);
            record_date_view.setVisibility(View.VISIBLE);
            record_date_view.setText(CurrentDate.dateFormatString(mRecord_user.get(position).getDate_Record()));
        }


        record_name_view.setText(records.getName_record());
        record_description_view.setText(records.getDescription());


    }

    // Return the total count of items
    @Override
    public int getItemCount() {
        return mRecords.size();
    }
}
