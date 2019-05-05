package asus.studenttools;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by ASUS on 5.05.2019.
 */

public class FoodFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.food_fragment, container, false);
        LinearLayout myLayout = (LinearLayout) view.findViewById(R.id.foodLayout);


        try {
            Document doc = Jsoup.connect("https://aybu.edu.tr/sks/").get();
            Elements rows = doc.select("table").get(1).select("tr"); //skip the first table and get rows

            for (int i = 1; i < rows.size(); i++) { // skip firs row because of image
                Element col = rows.get(i).selectFirst("td");
                TextView foodTextView = new TextView(getActivity());
                foodTextView.setLayoutParams(
                        new ViewGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT));
                if(i == 1)
                    foodTextView.setText("Tarih: "+col.text());
                else
                    foodTextView.setText((i-1)+": "+col.text());
                myLayout.addView(foodTextView);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return view;
    }
}
