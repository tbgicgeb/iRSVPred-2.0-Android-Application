package org.icgeb.irsvpred_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class RiceVarietyActivity extends AppCompatActivity {

    ViewPager viewPager;
    AdapterRice adapter;
    List<Model> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_rice_variety);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_rv);
        setSupportActionBar(toolbar);


        getSupportActionBar().setTitle("Rice Variety");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView img1 = (ImageView) findViewById(R.id.rv_image_1);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RiceVarietyActivity.this, FullScreenImageActivityRiceVariety1.class);
                startActivity(intent);
            }
        });


        ImageView img2 = (ImageView) findViewById(R.id.rv_image_2);
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RiceVarietyActivity.this, FullScreenImageActivityRiceVariety2.class);
                startActivity(intent);
            }
        });




        models = new ArrayList<>();
        models.add(new Model(R.drawable.rvs_1, "Pusa Basmati 1509", "Pusa Basmati 1509 was released in 2013 by CVRC for the Basmati growing areas Punjab, Haryana, Delhi, Western UP, Uttarakhand and Jammu & Kashmir. Previously it was known as PUSA Punjab Basmati 1509. Suitable for timely sown irrigated condition. Early maturing basmati rice variety with seed to seed maturity in 115-120 days, the shortest duration for any Basmati rice variety released so far with an average yield of 5 tons/ ha.", "", "SLIDE LEFT  "));
        models.add(new Model(R.drawable.rvs_2, "Basmati 370", "A tall photoperiod sensitive variety which is best suited for medium fertility soils. Its grains are long slender, highly aromatic and elongate almost double upon cooking. The cooked rice is non sticky and soft to eat. This variety attains a height of about 165cm and takes about 150 days to mature. It is susceptible to lodging therefore, limited use of nitrogenous fertilizers should be made. Average yield of this variety is 12.0 quintals of paddy per acre.", "SLIDE RIGHT", "SLIDE LEFT  "));
        models.add(new Model(R.drawable.rvs_3, "Pusa Basmati 1718", "PB 1718 possess long slender grains (8.1 mm) with very occasional grain chalkiness, very good kernel length after cooking (17.0 mm), intermediate amylose content (22.2 %) and strong aroma. This variety is resistant to bacterial leaf blight and is suitable for cultivation in Delhi, Punjab and Haryana. Approximate days of harvest-able maturity are 136-138days after sowing. 5 kg seed is sufficient for per acre of transplantation. Average yield is around 18-20 q/ac.\n" +
                "\n", "SLIDE LEFT", "SLIDE RIGHT  "));
        models.add(new Model(R.drawable.rvs_4, "Dehraduni Basmati", "Dehraduni Basmati rice is cultivated in a region that is flat, no higher than 700 meters altitude, located south of the Ganges River and north of the Yamuna River. All of the phases of cultivation are done by hand with traditional methods (from the transplanting of the seed to the harvest) and simple tools are used: sickle, plough and wooden harrow dragged by oxen. The rice is cultivated in rotation with peas, pulses, millets and wheat, or with mustard and wheat grain.", "SLIDE RIGHT", "SLIDE LEFT  "));
        models.add(new Model(R.drawable.rvs_5, "Pusa Basmati 1121 (2008)", "Pusa Basmati 1121 was released by the Delhi State as Pusa 1121 (Pusa Sugandh 4) in 2003 and subsequently notified as Pusa Basmati 1121 in the year 2008. It is about 120 cm tall. It possesses extra long slender grains with good cooking quality. It is photo period insensitive and it matures in 140-145 days. It yields on an average 16.0-18.0 quintals of paddy per acre. Suitable for timely sown irrigated condition. It requires low input and produces high yield with better quality characters.", "SLIDE RIGHT", "SLIDE LEFT  "));
        models.add(new Model(R.drawable.rvs_6, "Pusa Basmati 1637", "Pusa Basmati 1637 (IET 24570) is a MAS derived near isogenic line of Pusa Basmati 1 possessing Pi9 gene for blast resistance Released for commercial cultivation in the Basmati growing regions of the Western Uttar Pradesh, National Capital Region of Delhi, Uttarakhand, Haryana and Punjab. Pusa Basmati 1637 is an improved form of Pusa Basmati-1. Pusa Basmati-1 was more likely to cause blast disease, but in 1637 the disease would not come.", "SLIDE RIGHT", "SLIDE LEFT  "));
        models.add(new Model(R.drawable.rvs_7, "Basmati CSR 30", "CSR 30, a salt tolerant variety from Central Soil Salinity Research Institute (CSSRI), in moderately salt affected and reclaimed soils. CSR 30 mentioned that this variety has good potential to grow in saline and sodic environments, possesses good disease and insect pests' resistance, and has high market value and export demand. CSR 30 has compatibility with wider soil and climatic conditions. This is a cash variety and it has a greater market demand than others.", "SLIDE RIGHT", "SLIDE LEFT  "));
        models.add(new Model(R.drawable.rvs_7, "Basmati CSR 30", "CSR 30, a salt tolerant variety from Central Soil Salinity Research Institute (CSSRI), in moderately salt affected and reclaimed soils. CSR 30 mentioned that this variety has good potential to grow in saline and sodic environments, possesses good disease and insect pests' resistance, and has high market value and export demand. CSR 30 has compatibility with wider soil and climatic conditions. This is a cash variety and it has a greater market demand than others.", "SLIDE RIGHT", "SLIDE LEFT  "));
        models.add(new Model(R.drawable.rvs_8, "Pusa Basmati 6", "usa Basmati 6 (Pusa 1401) was relased in 2008 by CVRC for the Basmati growing areas Punjab, Haryana, Western UP and Uttarakhand. Pusa Basmati 6 (Pusa 1401) is an improvement over Pusa 1121 in its yielding ability, agronomy and cooking quality. It is characterized by semi-dwarf plant type, tolerant to lodging, chalky grains less than 4%, its grain on cooking has uniform shape and strong aroma. It matures in 140-145 days with an average yield of 5 tons/ ha.", "SLIDE RIGHT", "SLIDE LEFT  "));
        models.add(new Model(R.drawable.rvs_9, "Pusa basmati 1728", "It is suitable for cultivation in Punjab, Haryana, Delhi, Uttarakhand and Western UP. The crop is recommended for irrigated transplanted conditions and it needs water throughout the growing period. Approximate days of harvest-able maturity are 140 to 145 days after sowing. PB 1728 possesses extra-long slender grains with very occasional grain chalkiness, very good kernel length after cooking and very strong aroma. 5 kg seed is sufficient for per acre.", "SLIDE RIGHT", "SLIDE LEFT  "));
        models.add(new Model(R.drawable.rvs_10, "Pusa Basmati 1", "Pusa-1 basmati is a semi-dwarf plant which consists of almost all the features of traditional basmati including alkali content, grain elongation and rich fragrance. It is also known as ''todal'' because there are awns present in its flower. This is one of the most demanded rice species all over the world. Under the Seeds Act of 1966, Pusa-1 was declared as a variety of basmati. Maturity Time - Generally around 125 to 135 days. Locally Known As - Basmati. Average Yield- 45 quintal/hectare.", "SLIDE RIGHT", ""));

        adapter = new AdapterRice(models, this);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(0,0,0,0);






    }

}

