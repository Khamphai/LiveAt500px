package com.noobswe.kphai.liveat500px.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.noobswe.kphai.liveat500px.R;
import com.noobswe.kphai.liveat500px.dao.PhotoItemDAO;


/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class PhotoSummaryFragment extends Fragment {

    PhotoItemDAO dao;

    private ImageView ivImg;
    private TextView tvName, tvDescription;

    public PhotoSummaryFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static PhotoSummaryFragment newInstance(PhotoItemDAO dao) {
        PhotoSummaryFragment fragment = new PhotoSummaryFragment();
        Bundle args = new Bundle();
        args.putParcelable("dao", dao);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);

        dao = getArguments().getParcelable("dao");

        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_photo_summary, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        ivImg = (ImageView) rootView.findViewById(R.id.ivImg);
        tvName = (TextView) rootView.findViewById(R.id.tvName);
        tvDescription = (TextView) rootView.findViewById(R.id.tvDescription);

        tvName.setText(dao.getCaption());
        tvDescription.setText(dao.getUserName() + "\n" + dao.getCamera());
        Glide.with(PhotoSummaryFragment.this)
                .load(dao.getImageUrl())
                .placeholder(R.drawable.loading)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivImg);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }

    /*
     * Restore Instance State Here
     */
    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance State here
    }

}
