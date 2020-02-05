package com.coressoft.breader.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.coressoft.breader.R;
import com.coressoft.breader.adapter.CatalogueAdapter;
import com.coressoft.breader.base.BaseFragment;
import com.coressoft.breader.bean.Verse;
import com.coressoft.breader.utils.PageFactory;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;


/**
 * Created by Administrator on 2016/8/31 0031.
 */
public class CatalogFragment extends BaseFragment {
    public static final String ARGUMENT = "argument";

    private PageFactory pageFactory;
    ArrayList<Verse> catalogueList = new ArrayList<>();

    @BindView(R.id.lv_catalogue)
    ListView lv_catalogue;

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected void initData(View view) {
        pageFactory = PageFactory.getInstance();
        catalogueList.addAll(pageFactory.getDirectoryList());
        CatalogueAdapter catalogueAdapter = new CatalogueAdapter(getActivity(), catalogueList);
        catalogueAdapter.setCharter(pageFactory.getCurrentCharter());
        lv_catalogue.setAdapter(catalogueAdapter);
        catalogueAdapter.notifyDataSetChanged();
    }

    @Override
    protected void initListener() {
        lv_catalogue.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                try {
                    pageFactory.openChapterContent(pageFactory.getBookNum(),position+1);
                    getActivity().finish();
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                pageFactory.changeChapter(catalogueList.get(position).getBookCatalogueStartPos());
//                getActivity().finish();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_catalog;
    }

    /**
     * 用于从Activity传递数据到Fragment
     * @param bookNum
     * @return
     */
    public static CatalogFragment newInstance(int bookNum)
    {
        Bundle bundle = new Bundle();
        bundle.putInt(ARGUMENT, bookNum);
        CatalogFragment catalogFragment = new CatalogFragment();
        catalogFragment.setArguments(bundle);
        return catalogFragment;
    }

}
