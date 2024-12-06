package com.example.wsw;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import androidx.fragment.app.Fragment;

public class SecondFragment extends Fragment {
    private TextView counterTextView;
    private int counter = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.second_fragment, container, false);
        counterTextView = view.findViewById(R.id.counterTextView);
        Button popupButton = view.findViewById(R.id.popupButton);
        popupButton.setOnClickListener(this::showPopupMenu);
        updateCounterDisplay();
        return view;
    }

    private void showPopupMenu(View v) {
        PopupMenu popup = new PopupMenu(requireContext(), v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.popup_menu, popup.getMenu());

        popup.setOnMenuItemClickListener(item -> {
            int id = item.getItemId();
            if (id == R.id.menu_increment) {
                counter++;
                updateCounterDisplay();
                return true;
            } else if (id == R.id.menu_decrement) {
                counter--;
                updateCounterDisplay();
                return true;
            } else if (id == R.id.menu_reset) {
                counter = 0;
                updateCounterDisplay();
                return true;
            }
            return false;
        }); popup.show();
    }

    @SuppressLint("SetTextI18n")
    private void updateCounterDisplay() {
        counterTextView.setText("Счетчик: " + counter);
    }
}