package com.noobswe.kphai.liveat500px.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.inthecheesefactory.thecheeselibrary.view.SlidingTabLayout;
import com.noobswe.kphai.liveat500px.R;
import com.noobswe.kphai.liveat500px.dao.PhotoItemDAO;

import java.util.zip.Inflater;


/**
 * Created by nuuneoi on 11/16/2014.
 */
@SuppressWarnings("unused")
public class MoreInfoFragment extends Fragment {

    ViewPager viewPager;
    private SlidingTabLayout slidingTabLayout;

    PhotoItemDAO dao;

    public MoreInfoFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static MoreInfoFragment newInstance(PhotoItemDAO dao) {
        MoreInfoFragment fragment = new MoreInfoFragment();
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
    }//end of OnCreate

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_more_info, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    } // end of OnCreateView

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
        setHasOptionsMenu(true);
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        viewPager = (ViewPager) rootView.findViewById(R.id.viewPager);
        viewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return PhotoSummaryFragment.newInstance(dao);
                    case 1:
                        return PhotoInfoFragment.newInstance(dao);
                    case 2:
                        return PhotoTagsFragment.newInstance(dao);
                    default:
                        return null;
                } // end of switch
            }

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "Summary";
                    case 1:
                        return "Info";
                    case 2:
                        return "Tags";
                    default:
                        return null;
                }

            }
        });
        slidingTabLayout = (SlidingTabLayout) rootView.findViewById(R.id.slidingTabLayout);
        slidingTabLayout.setViewPager(viewPager);
//            @Override
//            public int getCount() {
//                return 3;
//            }
//
//            @Override
//            public boolean isViewFromObject(View view, Object object) {
//                return view == object;
//            }
//
//            @Override
//            public Object instantiateItem(ViewGroup container, int position) {
//                Button btn = new Button(container.getContext());
//                btn.setText("Position! " + position);
//                container.addView(btn);
//                return btn;
//            }
//
//            @Override
//            public void destroyItem(ViewGroup container, int position, Object object) {
//                container.removeView((View) object);
//            }
//        }); // end of PagerAdapter
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_more_info, menu);

        MenuItem menuItem = (MenuItem) menu.findItem(R.id.action_share);
        ShareActionProvider shareActionProvider = (ShareActionProvider)
                MenuItemCompat.getActionProvider(menuItem);
        shareActionProvider.setShareIntent(getShareIntent());
    }

    private Intent getShareIntent() {
        Intent intent =new Intent(Intent.ACTION_SEND);
        intent.setType("image/jpeg");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
        intent.putExtra(intent.EXTRA_TEXT, "Extra Text");
        return intent;
    }

}//end of MainClass
