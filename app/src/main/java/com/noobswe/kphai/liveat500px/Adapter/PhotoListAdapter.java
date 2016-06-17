package com.noobswe.kphai.liveat500px.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;

import com.noobswe.kphai.liveat500px.R;
import com.noobswe.kphai.liveat500px.dao.PhotoItemCollectionDao;
import com.noobswe.kphai.liveat500px.dao.PhotoItemDAO;
import com.noobswe.kphai.liveat500px.datatype.MutableInteger;
import com.noobswe.kphai.liveat500px.manager.PhotoListManager;
import com.noobswe.kphai.liveat500px.view.PhotoListItem;

/**
 * Created by K'Phai on 3/4/2016.
 */
public class PhotoListAdapter extends BaseAdapter {

    PhotoItemCollectionDao dao;

    // int lastPosition = -1;
    MutableInteger lastPositionInteger;

    public PhotoListAdapter(MutableInteger lastPositionInteger) {
        this.lastPositionInteger = lastPositionInteger;
    }

    public void setDao(PhotoItemCollectionDao dao) {
        this.dao = dao;
    }

    @Override
    public int getCount() {
        if (dao == null)
            return 1;
        if (dao.getData() == null)
            return 1;
        return dao.getData().size() + 1;
    }

    @Override
    public Object getItem(int position) {
        return dao.getData().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return position == getCount() -1 ? 1 : 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (position == getCount() - 1) {
            //Progress Bar
            ProgressBar item;
            if (convertView != null) {
                item = (ProgressBar) convertView;
            } else {
                item = new ProgressBar(parent.getContext());
            }
            return item;
        }
        //return new PhotoListItem(parent.getContext());
        PhotoListItem item;
        if (convertView != null)
            item = (PhotoListItem) convertView;
        else
            item = new PhotoListItem(parent.getContext());

        PhotoItemDAO dao = (PhotoItemDAO) getItem(position);
        item.setNameText(dao.getCaption());
        item.setDescriptionText(dao.getUserName() + "\n" + dao.getCamera());
        item.setImageUrl(dao.getImageUrl());

        if (position > lastPositionInteger.getValue()) {
            Animation anim = AnimationUtils.loadAnimation(parent.getContext(),
                    R.anim.up_from_buttom);
            item.startAnimation(anim);
            lastPositionInteger.setValue(position);
        }
        return item;
    }

    public void increaseLastPosition(int amount) {
        lastPositionInteger.setValue(lastPositionInteger.getValue() + amount);
    }

} //End of Main Class
