/*
 * Created by Itzik Braun on 12/3/2015.
 * Copyright (c) 2015 deluge. All rights reserved.
 *
 * Last Modification at: 3/12/15 4:27 PM
 */

package co.chatsdk.ui.helpers;

import android.text.Editable;
import android.text.TextWatcher;

import org.apache.commons.lang3.StringUtils;

import co.chatsdk.core.NM;

import co.chatsdk.core.dao.User;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by braunster on 04/11/14.
 *
 *
 * This class was made to make it easy to listen to text change then save them to the right user metadata.
 * Also this class update the user in the server and index the user details.
 *
 * Currently only for String meta data.
 */
public class SaveIndexDetailsTextWatcher implements TextWatcher {

    public static final long INDEX_DELAY_DEFAULT = 500;
    //private long indexDelay = INDEX_DELAY_DEFAULT;

    private String metaKey;

    /** Contain the string that was last typed.*/
    private Editable editable;

    public SaveIndexDetailsTextWatcher(String metaKey) {
        this.metaKey = metaKey;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        editable = s;

        if (StringUtils.isBlank(editable.toString()))
            return;

        User user = NM.currentUser();
        String metadata = user.metaStringForKey(metaKey);

        if (StringUtils.isNotBlank(metadata) && metadata.equals(editable.toString()))
            return;

        user.setMetaString(metaKey, editable.toString());

        NM.core().pushUser().observeOn(AndroidSchedulers.mainThread()).subscribe();

    }

}