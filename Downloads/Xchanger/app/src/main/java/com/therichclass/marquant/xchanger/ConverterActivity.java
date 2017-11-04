package com.therichclass.marquant.xchanger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by marquant on 11/2/2017.
 */

public class ConverterActivity extends AppCompatActivity {
    //variables are declared
    String currency_name;
    String eth_rate;
    String btc_rate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convert);

        initViews();
        setRates();
        setFlags();
}
    private void initViews() {

        TextView btc = (TextView) findViewById(R.id.btc);
        TextView eth = (TextView) findViewById(R.id.eth);
        TextView btc_to = (TextView) findViewById(R.id.btcc);
        TextView eth_to = (TextView) findViewById(R.id.ethe);
    }
    private void setRates() {
        //get the extra that was passed along with the intent from the MainActivity
        Bundle extras = getIntent().getExtras();
        currency_name = extras.getString("currency_name");
        eth_rate = extras.getString("ETH_rate");
        btc_rate = extras.getString("BTC_rate");


        TextView my_currency = (TextView) findViewById(R.id.currency);
        my_currency.setText(currency_name);


        TextView btc_rates = (TextView) findViewById(R.id.btc_rates);
        btc_rates.setText(btc_rate);


        TextView eth_rates = (TextView) findViewById(R.id.eth_rates);
        eth_rates.setText(eth_rate);

    }
    private void setFlags() {
        ImageView national_flags = (ImageView) findViewById(R.id.flag);
        switch (currency_name) {
            default:
                national_flags.setImageResource(R.drawable.ic_ethereum);
                break;
            case "BTC":
                national_flags.setImageResource(R.drawable.ic_bitcoin);
                break;
            case "NGN":
                national_flags.setImageResource(R.drawable.ic_nigeria);
                break;
            case "USD":
                national_flags.setImageResource(R.drawable.ic_unitedstates);
                break;
            case "EUR":
                national_flags.setImageResource(R.drawable.ic_europe);
                break;
            case "AUD":
                national_flags.setImageResource(R.drawable.ic_australia);
                break;
            case "CAD":
                national_flags.setImageResource(R.drawable.ic_canada);
                break;
            case "GHS":
                national_flags.setImageResource(R.drawable.ic_ghana);
                break;
            case "HKD":
                national_flags.setImageResource(R.drawable.ic_hongkong);
                break;
            case "INR":
                national_flags.setImageResource(R.drawable.ic_india);
                break;
            case "JMD":
                national_flags.setImageResource(R.drawable.ic_jamaica);
                break;
            case "JPY":
                national_flags.setImageResource(R.drawable.ic_japan);
                break;
            case "KRW":
                national_flags.setImageResource(R.drawable.ic_southkorea);
                break;
            case "MXN":
                national_flags.setImageResource(R.drawable.ic_mexico);
                break;
            case "NAD":
                national_flags.setImageResource(R.drawable.ic_namibia);
                break;
            case "SGD":
                national_flags.setImageResource(R.drawable.ic_singapore);
                break;
            case "ZAR":
                national_flags.setImageResource(R.drawable.ic_southafrica);
                break;
            case "TWD":
                national_flags.setImageResource(R.drawable.ic_taiwan);
                break;
            case "GBP":
                national_flags.setImageResource(R.drawable.ic_england);
                break;
            case "LKR":
                national_flags.setImageResource(R.drawable.ic_srilanka);
                break;
            case "CHF":
                national_flags.setImageResource(R.drawable.ic_switzerland);
                break;
            case "CNY":
                national_flags.setImageResource(R.drawable.ic_china);
                break;
        }
}
    public void submit(View view) {

        EditText amount = (EditText) findViewById(R.id.amount);
        String my_amount = amount.getText().toString();

        TextView btc_amount = (TextView) findViewById(R.id.btcc_amount);
        TextView eth_amount = (TextView) findViewById(R.id.ethere_amount);

        btc_amount.setText(Double.toString(Double.parseDouble(btc_rate) *
                Double.parseDouble(my_amount)));
        eth_amount.setText(Double.toString(Double.parseDouble(eth_rate) *
                Double.parseDouble(my_amount)));

    }

}
