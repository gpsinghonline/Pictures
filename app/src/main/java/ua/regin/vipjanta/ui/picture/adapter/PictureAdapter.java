package ua.regin.vipjanta.ui.picture.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ua.regin.vipjanta.R;
import ua.regin.vipjanta.api.entity.Post;

public class PictureAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private final Context context;
    private List<Post> postList;
    private OnItemClickListener onItemClickListener;
    private final boolean withLogo;

    public PictureAdapter(Context context, boolean withLogo, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.withLogo = withLogo;
        this.onItemClickListener = onItemClickListener;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER && withLogo) {
            return new ViewHolderHeader(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_picture_header, parent, false));
        } else {
            return new ViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_picture, parent, false));

        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (viewHolder instanceof ViewHolder) {
            ViewHolder holder = (ViewHolder) viewHolder;
            Post post = postList.get(position);
            holder.title.setText(post.getTitle());
            Picasso.with(context).load(post.getImageUrl()).placeholder(R.drawable.spinner_animation).fit().centerCrop().into(holder.imageView);
            holder.setOnClickListener(v -> onItemClickListener.onItemClick(holder.imageView, post));
        }
    }

    @Override
    public int getItemCount() {
        int size = 0;
        if (postList != null) {
            size = postList.size();
        }
        return size;
    }

    @Override
    public long getItemId(int position) {
        return postList.get(position).getId();
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imageView;
        private TextView title;
        private View.OnClickListener onClickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            title = (TextView) itemView.findViewById(R.id.title);
            itemView.setOnClickListener(this);
        }

        public void setOnClickListener(View.OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
        }

        @Override
        public void onClick(View v) {
            onClickListener.onClick(v);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, Post post);
    }

    class ViewHolderHeader extends RecyclerView.ViewHolder {

        public ViewHolderHeader(View itemView) {
            super(itemView);
        }
    }
}