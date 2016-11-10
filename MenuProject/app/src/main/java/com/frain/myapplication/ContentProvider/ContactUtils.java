package com.frain.myapplication.ContentProvider;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

/**
 * Created by admin on 2016/11/10.
 */
public class ContactUtils {
    //      读取联系人
//     //分为以下步骤：
//     1、先读取contacts表，获取ContactsID；
//     2、再在raw_contacts表中根据ContactsID获取RawContactsID；
//     3、然后就可以在data表中根据RawContactsID获取该联系人的各数据了。
    public static void getContactsList(Context context) {
        // 获取用来操作数据的类的对象，对联系人的基本操作都是使用这个对象
        ContentResolver contentResolver = context.getContentResolver();//系统自动分配一个Resolver对象
        // 查询contacts表的所有记录
        Cursor cur = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null, null,
                null, null);
        // 如果记录不为空
        if (cur.getCount() > 0) {
            // 游标初始指向查询结果的第一条记录的上方，执行moveToNext函数会判断
            while (cur.moveToNext()) {// 下一条记录是否存在，如果存在，指向下一条记录。否则，返回false。
                String rawContactsId = "";
                int coulmnNumb = cur.getColumnIndex(ContactsContract.Contacts._ID);//获得id的列数
                String id = cur.getString(coulmnNumb);//根据列数取得对应列的值
                // 读取rawContactsId
                Cursor rawContactsIdCur = contentResolver.query(ContactsContract.RawContacts.CONTENT_URI,
                        null,
                        ContactsContract.RawContacts.CONTACT_ID + " = ?",
                        new String[]{id}, null);
                // 该查询结果一般只返回一条记录，所以我们直接让游标指向第一条记录
                if (rawContactsIdCur.moveToFirst()) {
                    // 读取第一条记录的RawContacts._ID列的值
                    rawContactsId = rawContactsIdCur.getString(rawContactsIdCur.getColumnIndex(
                            ContactsContract.RawContacts._ID));
                }
                rawContactsIdCur.close();//cursor使用完要关闭
                if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.
                        Contacts.HAS_PHONE_NUMBER))) > 0) {//HAS_PHONE_NUMBER是系统用来存放是否有号码的一个标示。如果大于0则有号码
                    // 根据查询RAW_CONTACT_ID查询该联系人的号码
                    Cursor PhoneCur = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.RAW_CONTACT_ID + "= ?",
                            new String[]{rawContactsId}, null);
                    // 上面的ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                    // 可以用下面的phoneUri代替
                    //  Uri phoneUri=Uri.parse("content://com.android.contacts/data/phones");
                    // 一个联系人可能有多个号码，需要遍历
                    while (PhoneCur.moveToNext()) {
                        // 号获取码
                        String number =
                                PhoneCur.getString(PhoneCur.getColumnIndex(
                                        ContactsContract.CommonDataKinds.Phone.NUMBER));
                        // 获取号码名字
                        String name =
                                PhoneCur.getString(PhoneCur.getColumnIndex(
                                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                        Log.i("number", number);
                        Log.i("name", name);
                    }
                    PhoneCur.close();
                }
            }
        }
    }

    //新建联系人时， 根据contacts、raw_ contacts两张表中ID的使用情况，自动生成ContactID和RawContactID。
    // Android源码新建重复姓名的联系人的ContactID是不重复的，所以会重复显示。
    public static long addContact(Context context, String name, String phoneNum) {
        ContentValues values = new ContentValues();//数据集合
        //先插入一条空数据到raw_contacts中，生成rawContactID
        Uri rawContactUri = context.getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, values);
        long rawContactId = ContentUris.parseId(rawContactUri);
        // 向data表插入数据
        if (name != "") {
            values.clear();
            values.put(ContactsContract.Contacts.Data.RAW_CONTACT_ID, rawContactId);//插入RawContactID到第三张数据表中
            values.put(ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);//联系人内容的类型
            values.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, name);//插入我们用户的名字
            context.getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);//向Data表中插入联系的人的数据
        }
        // 向data表插入电话号码
        if (phoneNum != "") {
            values.clear();
            values.put(ContactsContract.Contacts.Data.RAW_CONTACT_ID, rawContactId);//插入RawContactID到第三张数据表中
            values.put(ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);//联系人内容的类型
            values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, phoneNum);//联系人的手机号码
            values.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);//设置电话的类型为手机
            context.getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);
        }
        return rawContactId;
    }
    // 更新联系人
    // 联系人的所有信息都是保存在data表中，所以要更新联系人，我们只需要根据RawContactID和MIMETYPE修改data表中的内容。
    public static void updataCotact(Context context, long rawContactId) {
        ContentValues values = new ContentValues();//数据集合
        values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, "13813813800");//添加手机号为xxx
        values.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);//添加数据类型为手机
        String where = ContactsContract.Data.RAW_CONTACT_ID + "=? AND "
                + ContactsContract.Data.MIMETYPE + "=?";//where语句
        String[] selectionArgs = new String[]{String.valueOf(rawContactId),
                ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE};//条件的值
        context.getContentResolver().update(ContactsContract.Data.CONTENT_URI, values,
                where, selectionArgs);//更新指定条件的数据内容
    }
    //删除联系人
    //我们只需要将raw_contacts表中指定RawContactID的行删除，其他表中与之关联的数据都会自动删除。
    public static void deleteContact(Context context, long rawContactId) {
        context.getContentResolver().delete(
                ContentUris.withAppendedId(ContactsContract.RawContacts.CONTENT_URI,
                        rawContactId), null, null);//删除指定rawContactId的那条数据
    }
}
