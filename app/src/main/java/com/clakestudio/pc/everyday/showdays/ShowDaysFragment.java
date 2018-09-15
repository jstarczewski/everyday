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

import com.clakestudio.pc.everyday.R;
import com.clakestudio.pc.everyday.adddays.AddDayActivity;
import com.clakestudio.pc.everyday.countdown.CountdownActivity;
import com.clakestudio.pc.everyday.data.Day;
import com.clakestudio.pc.everyday.settings.SettingsActivity;


import java.util.ArrayList;
import java.util.List;

public class ShowDaysFragment extends Fragment implements ShowDaysContract.View, View.OnClickListener {


    private ShowDaysContract.Presenter daysPresenter;

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
            FloatingActionButton fab = getActivity().findViewById(R.id.fab);
            fab.setOnClickListener(this);
        }
        return v;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        ArrayList<Day> days = new ArrayList<>();

        DayItemListener dayItemListener = new DayItemListener() {
            @Override
            public void onDayClicked(Day day) {
                daysPresenter.editCurrentDay(day);
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
    public void showDays() {
        AsyncShowDays asyncShowDays = new AsyncShowDays(showDaysAdapter);
        asyncShowDays.execute(daysPresenter);
    }

    @Override
    public void showAddNewDay(int dayId) {
        Intent intent = new Intent(getContext(), CountdownActivity.class);
        intent.putExtra("dayId", dayId);
        startActivity(intent);
    }

    @Override
    public void showEditCurrentDay(int dayId, String title, String note) {
        Intent intent = new Intent(getContext(), AddDayActivity.class);
        intent.putExtra("dayId", dayId);
        intent.putExtra("title", title);
        intent.putExtra("note", note);
        startActivity(intent);
    }

    @Override
    public void showSettingsActivity() {
        startActivity(new Intent(getContext(), SettingsActivity.class));
        if (getActivity() != null)
            getActivity().finish();
    }

    @Override
    public void showDayAlreadyAddedToast() {

    }

    @Override
    public void onClick(View v) {
        if (showDaysAdapter.getDays().isEmpty())
            daysPresenter.addNewDay();
        else
            daysPresenter.checkIfDayAlreadyAdded((showDaysAdapter.getDays()).get(showDaysAdapter.getItemCount() - 1).getDate());
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
                    // click only on the last item
                    if (showDaysViewHolder.getAdapterPosition() == days.size() - 1)
                        dayItemListener.onDayClicked(days.get(showDaysViewHolder.getAdapterPosition()));
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

        void onDayClicked(Day day);

    }

}

/**
 * -> to another file to prevent memory leakse (by making it staic later)
 */

class AsyncShowDays extends AsyncTask<ShowDaysContract.Presenter, Void, List<Day>> {

    private ShowDaysFragment.ShowDaysAdapter showDaysAdapter;

    AsyncShowDays(ShowDaysFragment.ShowDaysAdapter showDaysAdapter) {
        this.showDaysAdapter = showDaysAdapter;
    }


    @Override
    protected List<Day> doInBackground(ShowDaysContract.Presenter... presenter) {
        return presenter[0].getDays();
    }

    @Override
    protected void onPostExecute(List<Day> days) {
        super.onPostExecute(days);
        showDaysAdapter.replaceData((ArrayList<Day>) days);
    }
}