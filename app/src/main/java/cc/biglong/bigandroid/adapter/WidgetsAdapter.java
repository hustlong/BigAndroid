package cc.biglong.bigandroid.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cc.biglong.bigandroid.R;
import cc.biglong.bigandroid.entity.WidgetInfo;

/**
 * Created by biglong on 2017/3/29.
 */

public class WidgetsAdapter  extends RecyclerView.Adapter<WidgetsAdapter.ViewHolder>{
    private LayoutInflater mInflater;

    private List<WidgetInfo> categories;

    private OnItemClickListener onItemClickListener;

    public WidgetsAdapter(Context context, OnItemClickListener onItemClickListener) {
        mInflater = LayoutInflater.from(context);
        this.onItemClickListener = onItemClickListener;
    }

    public void setData(List<WidgetInfo> categories) {
        this.categories = categories;
        notifyDataSetChanged();
    }


    @Override
    public WidgetsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_category_layout,parent,false);
        return new ViewHolder(view,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(WidgetsAdapter.ViewHolder holder, int position) {
        holder.setData(categories.get(position));
    }

    @Override
    public int getItemCount() {
        return categories==null ? 0:categories.size();
    }

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /** 点击监听接口 */
    public interface OnItemClickListener {
        void onClick(int pos,WidgetInfo category);
    }

    /** 自定义ViewHolder */
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private OnItemClickListener onItemClickListener;
        private WidgetInfo category;

        ViewHolder(View itemView,OnItemClickListener onItemClickListener) {
            super(itemView);
            this.onItemClickListener = onItemClickListener;
            itemView.setOnClickListener(this);
            ButterKnife.bind(this,itemView);
        }

        void setData(WidgetInfo category) {
            this.category = category;
            name.setText(category.getWidgetName());
            resIv.setImageResource(category.getResId());
        }

        @BindView(R.id.category_name)
        TextView name;
        @BindView(R.id.resIv)
        ImageView resIv;

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null)
                onItemClickListener.onClick(getAdapterPosition(),category);
        }
    }

    /** 自定义间距 */
    public static class ItemDecoration extends RecyclerView.ItemDecoration {
        int space;
        int column;

        public ItemDecoration(int space,int column) {
            this.column = column!=0 ? column : 1;
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            //不是第一个的格子都设一个左边和底部的间距
            outRect.left = space;
            outRect.bottom = space;
            //由于每行都只有column个，所以第一个都是column的倍数，把左边距设为0
            if (parent.getChildLayoutPosition(view) %column==0) {
                outRect.left = 32;
            }
            if (parent.getChildLayoutPosition(view) < 4) {
                outRect.top = 32;
            }
        }
    }
}
