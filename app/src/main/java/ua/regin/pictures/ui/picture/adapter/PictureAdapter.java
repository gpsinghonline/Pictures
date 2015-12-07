package ua.regin.pictures.ui.picture.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.List;

import ua.regin.pictures.R;
import ua.regin.pictures.api.entity.Post;

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ViewHolder> {

    private final Context context;
    private List<Post> postList;

    public PictureAdapter(Context context, List<Post> postList) {
        this.context = context;
        this.postList = postList;
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
        Document document = Jsoup.parse(post.getContent());
        Element image = document.select("img").first();
        String url = image.absUrl("src");
        Picasso.with(context).load(url).fit().centerCrop().into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    @Override
    public long getItemId(int position) {
        return postList.get(position).getId();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}