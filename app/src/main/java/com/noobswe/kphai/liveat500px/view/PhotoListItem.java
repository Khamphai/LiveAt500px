package com.noobswe.kphai.liveat500px.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.noobswe.kphai.liveat500px.R;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class PhotoListItem extends FrameLayout {

    TextView tvName;
    TextView tvDescription;
    ImageView ivImg;

    public PhotoListItem(Context context) {
        super(context);
        initInflate();
        initInstances();
    }

    public PhotoListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initInflate();
        initInstances();
        initWithAttrs(attrs);
    }

    public PhotoListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initInflate();
        initInstances();
        initWithAttrs(attrs);
    }

    private void initInflate() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.list_item_photo, this);
    }

    private void initInstances() {
        //findViewById here
        tvName = (TextView) findViewById(R.id.tvName);
        tvDescription = (TextView) findViewById(R.id.tvDescription);
        ivImg = (ImageView) findViewById(R.id.ivImg);

    }

    private void initWithAttrs(AttributeSet attrs) {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);  //width in px
        int height = width * 2 / 3;
        int newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(
                height,
                MeasureSpec.EXACTLY
        );
        //Child Views
        super.onMeasure(widthMeasureSpec, newHeightMeasureSpec);
        //Self
        setMeasuredDimension(width, height);
    }

    public void setNameText(String text) {
        tvName.setText(text);
    }

    public void setDescriptionText(String text) {
        tvDescription.setText(text);
    }

    //    .placeholder()
//    .error()
//    .transform()
    public void setImageUrl(String url) {
        Glide.with(getContext())
                .load(url)
                .placeholder(R.drawable.loading)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivImg);
    }
}
