package com.clakestudio.pc.everyday.showdays;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.clakestudio.pc.everyday.R;
import com.clakestudio.pc.everyday.adddays.AddDayActivity;
import com.clakestudio.pc.everyday.countdown.CountdownActivity;
import com.clakestudio.pc.everyday.data.Day;
import com.clakestudio.pc.everyday.settings.SettingsActivity;


import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class ShowDaysFragment extends Fragment implements ShowDaysContract.View, View.OnClickListener {


    private ShowDaysContract.Presenter daysPresenter;
    private DayItemListener dayItemListener;
    private FloatingActionButton fab;
    private ShowDaysAdapter showDaysAdapter;

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
    }

    RecyclerView recyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_show_days, container, false);
        recyclerView = v.findViewById(R.id.recyclerView);
        if (getActivity() != null) {
            fab = getActivity().findViewById(R.id.fab);
            fab.setOnClickListener(this);
        }
        return v;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        ArrayList<Day> days = new ArrayList<>();

        dayItemListener = new DayItemListener() {
            @Override
            public void onDayClicked(Day day, int index, int size) {
            }
        };

        showDaysAdapter = new ShowDaysAdapter(days, dayItemListener);
        recyclerView.setAdapter(showDaysAdapter);

        daysPresenter.start();
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
    }

    @Override
    public void setPresenter(@NonNull ShowDaysContract.Presenter presenter) {
        daysPresenter = presenter;
    }

    @Override
    public void stop() {

        /**
         * Do not know whether this is needed
         * */

        if (fab != null)
            fab.setOnClickListener(null);
        dayItemListener = null;
    }

    /*
        @Override
        public void showDays() {
            AsyncShowDays asyncShowDays = new AsyncShowDays(this.showDaysAdapter);
            asyncShowDays.execute(daysPresenter);
        }
    */
    @Override
    public void showDays(List<Day> days) {
        showDaysAdapter.replaceData((ArrayList<Day>) days);
    }

    @Override
    public void showStartAddDayActivityToAddDay(int dayId) {
        Intent intent = new Intent(getContext(), CountdownActivity.class);
        intent.putExtra("dayId", dayId);
        startActivity(intent);
        if (getActivity()!=null)
            getActivity().finish();
    }

    @Override
    public void showStartAddDayActivityToEditDay(int dayId, String title, String note) {
        Intent intent = new Intent(getContext(), AddDayActivity.class);
        intent.putExtra("dayId", dayId);
        intent.putExtra("title", title);
        intent.putExtra("note", note);
        startActivity(intent);
        if (getActivity()!=null)
            getActivity().finish();
    }

    @Override
    public void showStartSettingsActivity() {
        startActivity(new Intent(getContext(), SettingsActivity.class));
        if (getActivity() != null)
            getActivity().finish();
    }

    @Override
    public void showDayAlreadyAddedToast() {
        Toast.makeText(getContext(), "Todays note already added", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        daysPresenter.addDay(showDaysAdapter.getDays().isEmpty(), (showDaysAdapter.getDays()).get(showDaysAdapter.getItemCount()).getDate());
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

    class ShowDaysAdapter extends RecyclerView.Adapter<ShowDaysAdapter.ShowDaysViewHolder> {

        private ArrayList<Day> days;
        private DayItemListener dayItemListener;

        class ShowDaysViewHolder extends RecyclerView.ViewHolder {

            private TextView tvTitle;
            private TextView tvNote;
            private CardView cvDay;
            private TextView tvDayInfo;


            ShowDaysViewHolder(View itemView) {
                super(itemView);

                tvTitle = itemView.findViewById(R.id.tvTitle);
                tvNote = itemView.findViewById(R.id.tvNote);
                tvDayInfo = itemView.findViewById(R.id.tvDayInfo);
                cvDay = itemView.findViewById(R.id.day);

            }
        }

        ShowDaysAdapter(ArrayList<Day> days, DayItemListener dayItemListener) {
            this.days = days;
            this.dayItemListener = dayItemListener;
        }


        ArrayList<Day> getDays() {
            return days;
        }

        void setDays(ArrayList<Day> days) {
            this.days = days;
        }

        void replaceData(ArrayList<Day> days) {
            setDays(days);
            notifyDataSetChanged();
        }


        @NonNull
        @Override
        public ShowDaysViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.day, parent, false);
            final ShowDaysViewHolder showDaysViewHolder = new ShowDaysViewHolder(view);

            showDaysViewHolder.cvDay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dayItemListener.onDayClicked(days.get(showDaysViewHolder.getAdapterPosition()), showDaysViewHolder.getAdapterPosition(), days.size() - 1);
                }
            });

            return showDaysViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ShowDaysViewHolder holder, int position) {
            holder.tvTitle.setText(days.get(position).getTitle());
            holder.tvNote.setText(days.get(position).getNote());
            String dayInfo = "Day " + days.get(position).getDayId() + " / " + days.get(position).getDate();
            holder.tvDayInfo.setText(dayInfo);
        }

        @Override
        public int getItemCount() {
            return days.size();
        }
    }

    public interface DayItemListener {

        void onDayClicked(Day day, int index, int size);

    }

}

