package ua.regin.pictures.ui.picture.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ua.regin.pictures.R;
import ua.regin.pictures.api.entity.Post;

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ViewHolder> {

    private final Context context;
    private List<Post> postList;
    private OnItemClickListener onItemClickListener;

    public PictureAdapter(Context context, List<Post> postList, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.postList = postList;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_picture, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Post post = postList.get(position);
        Picasso.with(context).load(post.getImageUrl()).fit().centerCrop().into(holder.imageView);
        holder.setOnClickListener(v -> onItemClickListener.onItemClick(holder.imageView, post));
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    @Override
    public long getItemId(int position) {
        return postList.get(position).getId();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imageView;
        private View.OnClickListener onClickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
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
}