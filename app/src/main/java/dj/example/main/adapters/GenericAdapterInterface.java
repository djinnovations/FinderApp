package dj.example.main.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

/**
 * Created by DJphy on 26-01-2017.
 */

public interface GenericAdapterInterface {
    void changeData(List dataList) throws Exception;
    int getRootLayout(int viewType);
    void setOnClickListener(RecyclerView.ViewHolder holder);
    RecyclerView.ViewHolder getViewHolder(View view, int viewType);

}
