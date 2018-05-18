package com.collegelasalle.felix.androidtest;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class TeamFragment extends ListFragment {

    String[] TEAMS = new String[] { "Brazil", "Germany", "Italy"};
    boolean isInfoActive;
    int currentIndex;

    public TeamFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, TEAMS));

        View infoFrame = getActivity().findViewById(R.id.info);
        isInfoActive = infoFrame != null && infoFrame.getVisibility() == View.VISIBLE;
        if (isInfoActive) {
            showInfo(currentIndex);
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        showInfo(position);
    }

    private void showInfo(int index) {
        currentIndex = index;
        if (isInfoActive) {
            InfoFragment infoFragment = (InfoFragment) getFragmentManager().findFragmentById(R.id.info);
            if (infoFragment == null || infoFragment.getIndex() != index) {
                infoFragment = InfoFragment.newInstance(index);
                getFragmentManager().beginTransaction().replace(R.id.info, infoFragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
            }
        } else {
            Intent intent = new Intent();
            intent.setClass(getActivity(), InfoActivity.class);
            intent.putExtra("index", index);
            startActivity(intent);
        }
    }
}
