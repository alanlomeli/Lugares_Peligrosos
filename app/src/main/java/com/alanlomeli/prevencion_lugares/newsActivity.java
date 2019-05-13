package com.alanlomeli.prevencion_lugares;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.alanlomeli.prevencion_lugares.Adaptador.FeedAdapter;
import com.alanlomeli.prevencion_lugares.Common.HTTPDateHandler;
import com.alanlomeli.prevencion_lugares.Modelo.ObjetoRSS;
import com.google.gson.Gson;

public class newsActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    ObjetoRSS objetoRSS;

    private final String RSS_Link="https://www.informador.mx/rss/seguridad.xml";
    private final String RSS_to_Json_API = "https://api.rss2json.com/v1/api.json?rss_url=";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Noticias");
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView)findViewById(R.id.recylerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        cargarRSS();
    }

    private void cargarRSS() {
        AsyncTask<String,String,String> cargarRSSAsync = new AsyncTask<String, String, String>() {
            ProgressDialog mDialog = new ProgressDialog(newsActivity.this);

            @Override
            protected void onPreExecute() {
                mDialog.setMessage("Porfavor espera....");
                mDialog.show();
            }

            @Override
            protected String doInBackground(String... strings) {
                String resultado;
                HTTPDateHandler http = new HTTPDateHandler();
                resultado = http.GetHTTPData(strings[0]);
                return resultado;
            }

            @Override
            protected void onPostExecute(String s) {
                    mDialog.dismiss();
                    objetoRSS = new Gson().fromJson(s,ObjetoRSS.class);
                FeedAdapter adapter = new FeedAdapter(objetoRSS,getBaseContext());
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();

                    }
        };
        StringBuilder url_get_data = new StringBuilder(RSS_to_Json_API);
        url_get_data.append(RSS_Link);
        cargarRSSAsync.execute(url_get_data.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       // getMenuInflater().inflate(R.menu.);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() ==0)
            cargarRSS();
        return true;
    }
}
