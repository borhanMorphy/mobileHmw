package asus.studenttools;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by ASUS on 5.05.2019.
 */

public class AnnouncementsFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.announcements_fragment, container, false);
        LinearLayout myLayout = (LinearLayout) view.findViewById(R.id.announcementLayout);


        try {
            Document doc = Jsoup.connect("https://aybu.edu.tr/muhendislik/bilgisayar/").get();
            Elements items = doc.select("div.caContent > div.cncItem"); //skip the first table and get rows

            for (int i = 0; i < items.size(); i++) {
                TextView announcementTextView = new TextView(getActivity());
                announcementTextView.setLayoutParams(
                        new ViewGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT));
                announcementTextView.setText(
                        Html.fromHtml(
                                "announcement "+(i+1)+") "+
                                        "<a href=\""+items.get(i).select("a[href]").attr("abs:href")+"\">"
                                        +items.get(i).text()+"</a>"
                        )
                );
                announcementTextView.setMovementMethod(android.text.method.LinkMovementMethod.getInstance());

                myLayout.addView(announcementTextView);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return view;
    }
}
