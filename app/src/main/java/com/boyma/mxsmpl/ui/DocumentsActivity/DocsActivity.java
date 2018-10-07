package com.boyma.mxsmpl.ui.DocumentsActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.boyma.mxsmpl.R;
import com.boyma.mxsmpl.net.jsonplaceholder.allnews.models.ResponseJsonObj;
import com.boyma.mxsmpl.ui.PhotosActivity.PhotosActivity;
import com.boyma.mxsmpl.ui.adapters.RecAdapter;

import java.util.List;

public class DocsActivity extends MvpAppCompatActivity implements IDocsActivityView {

    @InjectPresenter
    DocsActivityPresenter mpresenter;
    private RecyclerView rec_view;
    private TextView logtv;
    private LinearLayoutManager mLayoutManager;
    private RecAdapter mAdapter;
    private ProgressDialog progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mpresenter.onCreate(getIntent().getIntExtra("idtype1",-1));

        initUI();

    }

    private void initUI() {
        rec_view = findViewById(R.id.rec_view);
        initRecView();
        logtv = findViewById(R.id.logtv);
    }

    private void initRecView() {
        rec_view.setHasFixedSize(false);
        mLayoutManager = new LinearLayoutManager(this);
        rec_view.setLayoutManager(mLayoutManager);
        mAdapter = new RecAdapter();
        rec_view.setAdapter(mAdapter);
        rec_view.addOnScrollListener(recyclerViewOnScrollListener);
        rec_view.addOnItemTouchListener(new RecyclerTouchListener(this, rec_view, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                mpresenter.onItemClick(position);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    @Override
    public void showToast(String s) {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

    private RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            int visibleItemCount = mLayoutManager.getChildCount();
            int totalItemCount = mLayoutManager.getItemCount();
            int firstVisibleItemPosition = mLayoutManager.findFirstVisibleItemPosition();
            //mpresenter.onScrolled(visibleItemCount,totalItemCount,firstVisibleItemPosition);
        }
    };

    @Override
    public void showLoadingDialog() {
        progress = new ProgressDialog(this);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setTitle("Загрузка");
        progress.setMessage("Подождите...");
        progress.setCancelable(false);
        progress.show();
    }


    @Override
    public void hideLogTextView() {
        logtv.setVisibility(View.GONE);
    }

    @Override
    public void hideLoadingView() {
        progress.dismiss();
    }

    @Override
    public void addToList(List<ResponseJsonObj> siteJsonObj) {
        mAdapter.addAll(siteJsonObj);
    }

    @Override
    public void startPhotosActivity(int idtype1, Integer idtype2) {

        Intent intent = new Intent(this,PhotosActivity.class);
        intent.putExtra("idtype1",idtype1);
        intent.putExtra("idtype2",idtype2);
        startActivity(intent);
    }


    //=================================
    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{

        private ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView, final ClickListener clicklistener){

            this.clicklistener=clicklistener;
            gestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child=recycleView.findChildViewUnder(e.getX(),e.getY());
                    if(child!=null && clicklistener!=null){
                        clicklistener.onLongClick(child,recycleView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child=rv.findChildViewUnder(e.getX(),e.getY());
            if(child!=null && clicklistener!=null && gestureDetector.onTouchEvent(e)){
                clicklistener.onClick(child,rv.getChildAdapterPosition(child));
            }

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    public interface ClickListener{
        void onClick(View view, int position);
        void onLongClick(View view, int position);
    }
    //=================================
}
