package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

import com.b07.enums.ItemTypes;
import com.example.login.*;

public class ViewCurrentInventory extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_inventory);


        ArrayList<String> itemList = new ArrayList<>();
        itemList.add(ItemTypes.FISHING_ROD.name());
        itemList.add(ItemTypes.HOCKEY_STICK.name());
        itemList.add(ItemTypes.PROTEIN_BAR.name());
        itemList.add(ItemTypes.RUNNING_SHOES.name());
        itemList.add(ItemTypes.SKATES.name());

    }

}
