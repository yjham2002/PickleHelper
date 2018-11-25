package kr.co.picklecode.helper.helper;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kr.co.picklecode.helper.helper.models.Article;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    public static final int HEADER = 3, DEFAULT = 0;

    public Context mContext = null;
    public List<Article> mListData = new ArrayList<>();
    public int item_layout;
    public int addition = 0;

    public ArticleAdapter(Context mContext, int item_layout) {
        super();
        this.mContext = mContext;
        this.item_layout = item_layout;
    }

    public ArticleAdapter(Context mContext, int item_layout, int addition) {
        this.mContext = mContext;
        this.item_layout = item_layout;
        this.addition = addition;
    }

    @Override
    public int getItemViewType(int position){
        if(position == 0) return HEADER;
        else return DEFAULT;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = null;
        switch(viewType){
            default:
                v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_article, parent, false);
                break;
        }
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Article mData = mListData.get(position);
        switch (mData.getType()){
            default: {
//                holder._favicon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.div_01));
                break;
            }
        }
        final String title = mData.getTitle();
        final String date = mData.getRegDate();

        holder._subject.setText(title);
        holder._date.setText(date);

        holder.cardview.setOnClickListener(new CardView.OnClickListener() {
            @Override
            public void onClick(View v) {
//                final Article mData = mListData.get(position);
//                Intent i = new Intent(mContext, DetailActivity.class);
//                i.putExtra("URL", mData.Url);
//                i.putExtra("id", mData.id);
//                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView _subject;
        public TextView _date;
        public CardView cardview;

        public ViewHolder(View itemView) {
            super(itemView);
            _date = itemView.findViewById(R.id.rawdate);
            _subject = itemView.findViewById(R.id.subject);
            cardview = itemView.findViewById(R.id.cardview);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addItem(Article addInfo){
        mListData.add(addInfo);
    }

    public void dataChange(){
        this.notifyDataSetChanged();
    }

}