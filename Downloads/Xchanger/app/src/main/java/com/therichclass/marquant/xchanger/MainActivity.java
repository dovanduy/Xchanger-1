package com.therichclass.marquant.xchanger;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.therichclass.marquant.xchanger.Adapter.RecyclerViewAdapter;
import com.therichclass.marquant.xchanger.Api.Client;
import com.therichclass.marquant.xchanger.Api.Service;
import com.therichclass.marquant.xchanger.Utils.Currency;
import com.therichclass.marquant.xchanger.Utils.CurrencyExchange;
import com.therichclass.marquant.xchanger.Utils.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.therichclass.marquant.xchanger.R.id.spinner;

public class MainActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener{

    //variables are declared and assigned
    private RecyclerView recyclerView;
    private RecyclerViewAdapter mAdapter;
    LinearLayoutManager mLayoutManager;
    private List<Currency> myCurrencies;
    private List<Currency> defaultList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myCurrencies = new ArrayList<>();
        defaultList = new ArrayList<>();

        initMyViews(); //setting and initializing respective views
    }
    private void initMySpinner() {

        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener to trace when an item is clicked on the spinner
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Select Currency");

        categories.add("ETH - Ethereum"); //adding currencies to the spinner
        categories.add("BTC - Bitcoin");
        categories.add("NGN - Nigerian Naira");
        categories.add("USD - United State Dollars");
        categories.add("EUR - Euro");

        categories.add("AUD - Australian Dollars");
        categories.add("CAD - Canadian Dollars");
        categories.add("GHS - Ghanaian Cedi");
        categories.add("HKD - Hong Kong Dollars");
        categories.add("INR - Indian Rupees");

        categories.add("JMD - Jamaican Dollars");
        categories.add("JPY - Japanese Yens");
        categories.add("KRW - South Korean Won");
        categories.add("MXN - Mexican Peso");
        categories.add("NAD - Namibian Dollar");

        categories.add("SGD - Singapore Dollar");
        categories.add("ZAR - South African Rand");
        categories.add("TWD - New Taiwan Dolla");
        categories.add("GBP - British Pound");
        categories.add("LKR - Sri Lankan Rupee");

        categories.add("CHF - Swiss Franc");


        // Creating adapter for spinner to hold values
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, categories);


        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
}


    //Method that initialize the recycler view, calls necessary methods and pays attention to scrolls
 
    private void initMyViews() {


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.smoothScrollToPosition(0);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(),
                recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Currency currency = defaultList.get(position);
                Intent intent = new Intent(MainActivity.this, ConverterActivity.class);
                intent.putExtra("currency_name", currency.getName());
                intent.putExtra("ETH_rate", Double.toString(currency.getEthrate()));
                intent.putExtra("BTC_rate", Double.toString(currency.getBtcrate()));
                startActivity(intent);

            }
            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        networkCall(); //perform network operations

    }

    private void networkCall() {

        try {
            Client client = new Client();
            Service service =
                    client.getClient().create(Service.class);
            Call<CurrencyExchange> call = service.loadCurrencyExchange(); //passing value
          
            call.enqueue(new Callback<CurrencyExchange>() {
                @Override
                public void onResponse(Call<CurrencyExchange> call, Response<CurrencyExchange>
                        response) {
                    if (response != null) {
                        CurrencyExchange currencyExchange = response.body();

                        List<Currency> items = currencyExchange.getCurrencyList(); //Save the
                        // response in an item list

                        myCurrencies.addAll(items);
                        initMySpinner(); //This method isets and intializes spinner elements
                        Toast.makeText(MainActivity.this, "Currencies updated.",
                                Toast.LENGTH_LONG).show();

                        mAdapter = new RecyclerViewAdapter(getApplicationContext(), defaultList);
                        recyclerView.setAdapter(mAdapter);
                    }
                }

                @Override
                public void onFailure(Call<CurrencyExchange> call, Throwable t) {
                    CoordinatorLayout layout = (CoordinatorLayout) findViewById(R.id.layout);
                    Snackbar snackbar = Snackbar
                            .make(layout, "Currency List update Failed. Check your network connection "
                                   , Snackbar.LENGTH_INDEFINITE)
                            .setAction("RETRY", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    networkCall();

                                }
                            });

                    snackbar.show();
                }
            });

        } catch (Exception e) {
            Log.d("Error", e.getMessage());
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item locate position and add to spinner
        String item = parent.getItemAtPosition(position).toString();
        switch (item) {
            case "ETH - Ethereum":
                defaultList.add(myCurrencies.get(0));
                mAdapter.notifyDataSetChanged();
                break;
            case "NGN - Nigerian Naira":
                defaultList.add(myCurrencies.get(1));
                mAdapter.notifyDataSetChanged();
                break;
            case "BTC - Bitcoin":
                defaultList.add(myCurrencies.get(2));
                mAdapter.notifyDataSetChanged();
                break;
            case "USD - United State Dollars":
                defaultList.add(myCurrencies.get(3));
                mAdapter.notifyDataSetChanged();
                break;
            case "EUR - Euro":
                defaultList.add(myCurrencies.get(4));
                mAdapter.notifyDataSetChanged();
                break;
            case "AUD - Australian Dollars":
                defaultList.add(myCurrencies.get(5));
                mAdapter.notifyDataSetChanged();
                break;
            case "CAD - Canadian Dollars":
                defaultList.add(myCurrencies.get(6));
                mAdapter.notifyDataSetChanged();
                break;
            case "GHS - Ghanaian Cedi":
                defaultList.add(myCurrencies.get(7));
                mAdapter.notifyDataSetChanged();
                break;
            case "HKD - Hong Kong Dollars":
                defaultList.add(myCurrencies.get(8));
                mAdapter.notifyDataSetChanged();
                break;
            case "INR - Indian Rupees":
                defaultList.add(myCurrencies.get(9));
                mAdapter.notifyDataSetChanged();
                break;
            case "JMD - Jamaican Dollars":
                defaultList.add(myCurrencies.get(10));
                mAdapter.notifyDataSetChanged();
                break;
            case "JPY - Japanese Yens":
                defaultList.add(myCurrencies.get(11));
                mAdapter.notifyDataSetChanged();
                break;
            case "KRW - South Korean Won":
                defaultList.add(myCurrencies.get(12));
                mAdapter.notifyDataSetChanged();
                break;
            case "MXN - Mexican Peso":
                defaultList.add(myCurrencies.get(13));
                mAdapter.notifyDataSetChanged();
                break;
            case "NAD - Namibian Dolla":
                defaultList.add(myCurrencies.get(14));
                mAdapter.notifyDataSetChanged();
                break;
            case "SGD - Singapore Dollar":
                defaultList.add(myCurrencies.get(15));
                mAdapter.notifyDataSetChanged();
                break;
            case "ZAR - South African Rand":
                defaultList.add(myCurrencies.get(16));
                mAdapter.notifyDataSetChanged();
                break;
            case "TWD - New Taiwan Dolla":
                defaultList.add(myCurrencies.get(17));
                mAdapter.notifyDataSetChanged();
                break;
            case "GBP - British Pound":
                defaultList.add(myCurrencies.get(18));
                mAdapter.notifyDataSetChanged();
                break;
            case "LKR - Sri Lankan Rupee":
                defaultList.add(myCurrencies.get(19));
                mAdapter.notifyDataSetChanged();
                break;
            case "CHF - Swiss Franc":
                defaultList.add(myCurrencies.get(20));
                mAdapter.notifyDataSetChanged();
                break;

        }
    }

    public void onNothingSelected(AdapterView<?> arg0) {
    }


}

