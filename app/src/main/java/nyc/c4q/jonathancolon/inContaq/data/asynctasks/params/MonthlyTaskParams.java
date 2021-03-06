package nyc.c4q.jonathancolon.inContaq.data.asynctasks.params;

import java.util.ArrayList;
import java.util.TreeMap;

import nyc.c4q.jonathancolon.inContaq.sms.model.Sms;


public class MonthlyTaskParams {
    public final ArrayList<Sms> listSms;
    public final TreeMap<Integer, Integer> monthlyTexts;

    public MonthlyTaskParams(ArrayList<Sms> listSms, TreeMap<Integer, Integer> monthlyTexts) {
        this.listSms = listSms;
        this.monthlyTexts = monthlyTexts;
    }
}
