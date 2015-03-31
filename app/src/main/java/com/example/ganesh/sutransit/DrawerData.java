package com.example.ganesh.sutransit;

/**
 * Created by Ganesh on 3/16/2015.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DrawerData {

    List<Map<String,?>> drawerList;
    public static final int TYPE1 = 1;
    public static final int TYPE2 = 2;
    public static final int TYPE3 = 3;

    public List<Map<String, ?>> getDrawerList() {
        return drawerList;
    }

    public int getSize(){
        return drawerList.size();
    }

    public HashMap getItem(int i){
        return (HashMap) drawerList.get(i);
    }

    public DrawerData(){
        HashMap item;
        drawerList =new ArrayList<Map<String,?>>();

        item = new HashMap();
        item.put("type",TYPE1); item.put("icon", R.drawable.ic_home); item.put("title", "Home");
        drawerList.add(item);

        item = new HashMap();
        item.put("type",TYPE1); item.put("icon", R.drawable.ic_bus); item.put("title", "Search Bus");
        drawerList.add(item);

        item = new HashMap();
        item.put("type",TYPE1); item.put("icon", R.drawable.ic_pin); item.put("title", "Nearest Stop");
        drawerList.add(item);

        item = new HashMap();
        item.put("type",TYPE1); item.put("icon", R.drawable.ic_notify); item.put("title", "Notify Me");
        drawerList.add(item);

        item = new HashMap();
        item.put("type",TYPE1); item.put("icon", R.drawable.ic_docs); item.put("title", "Special Schedules");
        drawerList.add(item);

        item = new HashMap();
        item.put("type",TYPE1); item.put("icon", R.drawable.ic_gps); item.put("title", "Bus Tracker");
        drawerList.add(item);

        item = new HashMap();
        item.put("type",TYPE2); item.put("icon", R.drawable.ic_line);
        drawerList.add(item);

        item = new HashMap();
        item.put("type",TYPE1); item.put("icon", R.drawable.ic_gps); item.put("title", "About");
        drawerList.add(item);

        item = new HashMap();
        item.put("type",TYPE1); item.put("icon", R.drawable.ic_invitefriend); item.put("title", "Invite Friend");
        drawerList.add(item);

        item = new HashMap();
        item.put("type",TYPE1); item.put("icon", R.drawable.ic_rate); item.put("title", "Rate App");
        drawerList.add(item);

        item = new HashMap();
        item.put("type",TYPE1); item.put("icon", R.drawable.ic_logoff); item.put("title", "Log Out");
        drawerList.add(item);



       /* item = new HashMap();
        item.put("type",TYPE3); item.put("title", "About");
        drawerList.add(item);
        item = new HashMap();
        item.put("type",TYPE3);  item.put("title", "Invite Friend");
        drawerList.add(item);
        item = new HashMap();
        item.put("type",TYPE3);  item.put("title", "Rate this App");
        drawerList.add(item);
        item.put("type",TYPE3);  item.put("title", "Log Out");
        drawerList.add(item);*/
    }
}
