package com.braunster.chatsdk.Utils.sorter;

import com.braunster.chatsdk.dao.BMessage;
import com.braunster.chatsdk.dao.core.DaoCore;

import java.util.Comparator;

/**
 * Created by braunster on 18/06/14.
 */
public class MessageSorter implements Comparator<BMessage> {
    public static final int ORDER_TYPE_ASC = DaoCore.ORDER_ASC;
    public static final int ORDER_TYPE_DESC = DaoCore.ORDER_DESC;

    private int order = ORDER_TYPE_DESC;

    public MessageSorter(){}

    public MessageSorter(int order) {
        this.order = order;
    }

    @Override
    public int compare(BMessage x, BMessage y) {
        // TODO: Handle null x or y values
        if (order == ORDER_TYPE_ASC)
            return (x == null || y == null) ? -1 : x.getDate().compareTo(y.getDate());
        else return (x == null || y == null) ? 1 : y.getDate().compareTo(x.getDate());
    }
}