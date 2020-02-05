package com.coressoft.breader.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceScreen;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.coressoft.breader.R;
import com.coressoft.breader.lib.PreferenceFragment;
import com.coressoft.breader.lib.preference.CheckBoxPreference;
import com.coressoft.breader.lib.preference.Preference;
import com.coressoft.breader.mvp.SettingPresenter;
import com.coressoft.breader.mvp.impl.SettingView;
import com.coressoft.breader.ui.activity.SettingActivity;
import com.coressoft.breader.ui.activity.ThemeActivity;
import com.coressoft.breader.utils.DialogUtils;
import com.coressoft.breader.utils.PreferenceUtils;
import com.coressoft.breader.utils.XLog;

/**
 * Created by xingyw on 18-1-31.
 */

public class FragmentSettings extends PreferenceFragment implements SettingView {
    public static final String PREFERENCE_FILE_NAME = "note.settings";
    private CheckBoxPreference rightHandModePreference;
    private Preference feedbackPreference;
    private CheckBoxPreference cardLayoutPreference;

    private Preference everAccountPreference;


    SettingPresenter settingPresenter;
    private SettingActivity activity;
    public static FragmentSettings newInstance(){
        FragmentSettings fragment = new FragmentSettings();
        return fragment;
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (getActivity() != null && getActivity() instanceof SettingActivity){
            this.activity = (SettingActivity)getActivity();
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getActivity().setContentView(R.layout.fragment_setting);
        settingPresenter = new SettingPresenter(getActivity(), PreferenceUtils.getInstance(getActivity()),this);
        addPreferencesFromResource(R.xml.prefs);
        getPreferenceManager().setSharedPreferencesName(PREFERENCE_FILE_NAME);
        initializePresenter();
        settingPresenter.onCreate(savedInstanceState);
    }

    private void initializePresenter() {
        settingPresenter.attachView(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View.inflate(getActivity(),R.layout.fragment_setting, null);
        settingPresenter.onViewCreated(view);
    }


    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, android.preference.Preference preference) {
        settingPresenter.onPreferenceTreeClick(preference);
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }

    @Override
    public void onDestroy() {
//        settingPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void findPreference() {
        rightHandModePreference = (CheckBoxPreference)findPreference(getString(R.string.right_hand_mode_key));
        cardLayoutPreference = (CheckBoxPreference)findPreference(getString(R.string.card_note_item_layout_key));
        feedbackPreference = (Preference)findPreference(getString(R.string.advice_feedback_key));
        everAccountPreference = (Preference)findPreference(getString(R.string.ever_note_account_key));

    }

    @Override
    public void setRightHandModePreferenceChecked(boolean checked) {
        rightHandModePreference.setChecked(checked);
    }

    @Override
    public void setCardLayoutPreferenceChecked(boolean checked) {
        cardLayoutPreference.setChecked(checked);
    }

    @Override
    public void setFeedbackPreferenceSummary(CharSequence c) {
        feedbackPreference.setSummary(c);
    }

    @Override
    public void setFeedbackPreferenceClickListener(android.preference.Preference.OnPreferenceClickListener l) {
        feedbackPreference.setOnPreferenceClickListener(l);
    }

    @Override
    public void setEverNoteAccountPreferenceSummary(CharSequence c) {
        everAccountPreference.setSummary(c);
    }

    @Override
    public void setEverNoteAccountPreferenceTitle(CharSequence c) {
        everAccountPreference.setTitle(c);
    }

    @Override
    public void initPreferenceListView(View view) {
        ListView listView = (ListView)view.findViewById(android.R.id.list);
        listView.setHorizontalScrollBarEnabled(false);
        listView.setVerticalScrollBarEnabled(false);
        listView.setDivider(new ColorDrawable(getResources().getColor(R.color.grey)));
//        listView.setDividerHeight((int) getResources().getDimension(R.dimen.preference_divider_height));
        listView.setFooterDividersEnabled(false);
        listView.setHeaderDividersEnabled(false);
    }

    @Override
    public void showThemeChooseDialog(){
        XLog.i("点击了Theme");
        Intent intent = new Intent(getActivity(), ThemeActivity.class);
        this.startActivity(intent);
    }

    @Override
    public void showUnbindEverNoteDialog(){
        AlertDialog.Builder builder = DialogUtils.makeDialogBuilder(activity);
        builder.setTitle(R.string.has_unbind_ever_note_tip);
//        builder.setPositiveButton(R.string.sure, settingPresenter);
//        builder.setNegativeButton(R.string.cancel, settingPresenter);
        builder.show();
    }

    @Override
    public void showSnackbar(int message) {
        if (activity != null){}
//            SnackbarUtils.show(activity, message);
    }

    @Override
    public boolean isResume() {
        return isResumed();
    }

    @Override
    public void toast(int message) {
        if (activity == null)
            return;
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void reload() {
        if (activity != null){
//            activity.reload(false);
        }
    }
}
