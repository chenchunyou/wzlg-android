package com.minghao.wzlg.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 星期类
 */
public class Week {
    private int id; //第几周
    private List<KeTang> keTangs;//35个
    private List<String> datesOfWeek;

    public Week(int id){
        this.id = id;
        ArrayList<KeTang> keTangs = new ArrayList<KeTang>();
        for (int i = 0;i < 35;i++){
            keTangs.add(new KeTang(i));
        }
        this.setKeTangs(keTangs);

        String firstDateString = "2019-09-02";
        Date firstDate = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            firstDate = dateFormat.parse(firstDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long time = firstDate.getTime();

        SimpleDateFormat format = new SimpleDateFormat("MM-dd");

        List<String> datesOfWeek = new ArrayList<String >();

        datesOfWeek.add(format.format(new Date(time + 0 * 24 * 60 * 60 * 1000 + (7 * 24 * 60 * 60 * 1000) * (long)id)));
        datesOfWeek.add(format.format(new Date(time + 1 * 24 * 60 * 60 * 1000 + (7 * 24 * 60 * 60 * 1000) * (long)id)));
        datesOfWeek.add(format.format(new Date(time + 2 * 24 * 60 * 60 * 1000 + (7 * 24 * 60 * 60 * 1000) * (long)id)));
        datesOfWeek.add(format.format(new Date(time + 3 * 24 * 60 * 60 * 1000 + (7 * 24 * 60 * 60 * 1000) * (long)id)));
        datesOfWeek.add(format.format(new Date(time + 4 * 24 * 60 * 60 * 1000 + (7 * 24 * 60 * 60 * 1000) * (long)id)));
        datesOfWeek.add(format.format(new Date(time + 5 * 24 * 60 * 60 * 1000 + (7 * 24 * 60 * 60 * 1000) * (long)id)));
        datesOfWeek.add(format.format(new Date(time + 6 * 24 * 60 * 60 * 1000 + (7 * 24 * 60 * 60 * 1000) * (long)id)));

        this.setDatesOfWeek(datesOfWeek);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<KeTang> getKeTangs() {
        return keTangs;
    }

    public void setKeTangs(List<KeTang> keTangs) {
        this.keTangs = keTangs;
    }

    public List<String> getDatesOfWeek() {
        return datesOfWeek;
    }

    public void setDatesOfWeek(List<String> datesOfWeek) {
        this.datesOfWeek = datesOfWeek;
    }
}
