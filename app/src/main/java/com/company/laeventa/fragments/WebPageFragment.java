package com.company.laeventa.fragments;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.company.laeventa.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class WebPageFragment extends Fragment {

    protected WebView webView;

    protected String url;

    public WebPageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_web_page, container, false);

        webView = view.findViewById(R.id.webview);

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        webView.loadUrl(url);
      //  webView.getSettings().setJavaScriptEnabled(true);

    }

    public void setUrl(String url) {
    this.url = url;
    }

}
