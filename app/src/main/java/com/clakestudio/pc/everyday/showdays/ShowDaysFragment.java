package com.clakestudio.pc.everyday.showdays;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.clakestudio.pc.everyday.R;
import com.clakestudio.pc.everyday.data.Day;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShowDaysFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShowDaysFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShowDaysFragment extends Fragment implements ShowDaysContract.View {

    //

    private View noDaysView;

    private ShowDaysContract.Presenter daysPresenter;

    private ShowDaysAdapter showDaysAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ShowDaysFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ShowDaysFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShowDaysFragment newInstance() {
        return new ShowDaysFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_show_days, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);


        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        ArrayList<Day> days = new ArrayList<>();
        showDaysAdapter = new ShowDaysAdapter(days);
        recyclerView.setAdapter(showDaysAdapter);
        daysPresenter.addNewDay();
        daysPresenter.start();

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /*
        @Override
        public void onAttach(Context context) {
            super.onAttach(context);
            if (context instanceof OnFragmentInteractionListener) {
                mListener = (OnFragmentInteractionListener) context;
            } else {
                throw new RuntimeException(context.toString()
                        + " must implement OnFragmentInteractionListener");
            }
        }
    */

    /**
     * Methods from Contract interface
     */


    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void setPresenter(@NonNull ShowDaysContract.Presenter presenter) {
        daysPresenter = presenter;
    }


    @Override
    public void showDays(ArrayList<Day> days) {
        showDaysAdapter.replaceData(days);
    }

    @Override
    public void addNewDay() {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    class ShowDaysAdapter extends RecyclerView.Adapter<ShowDaysAdapter.ShowDaysViewHolder> {

        private ArrayList<Day> days;


        class ShowDaysViewHolder extends RecyclerView.ViewHolder {

            private TextView tvTitle;
            private TextView tvNote;


            ShowDaysViewHolder(View itemView) {
                super(itemView);

                tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
                tvNote = (TextView) itemView.findViewById(R.id.tvNote);

            }
        }

        public ShowDaysAdapter(ArrayList<Day> days) {
            this.days = days;
        }


        public ArrayList<Day> getDays() {
            return days;
        }

        public void setDays(ArrayList<Day> days) {
            this.days = days;
        }

        public void replaceData(ArrayList<Day> days) {
            setDays(days);
            notifyDataSetChanged();
        }


        @NonNull
        @Override
        public ShowDaysViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.day, parent, false);
            ShowDaysViewHolder showDaysViewHolder = new ShowDaysViewHolder(view);
            return showDaysViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ShowDaysViewHolder holder, int position) {
            holder.tvTitle.setText(days.get(position).getTitle());
            holder.tvNote.setText(days.get(position).getNote());
        }

        @Override
        public int getItemCount() {
            return days.size();
        }
    }
}

