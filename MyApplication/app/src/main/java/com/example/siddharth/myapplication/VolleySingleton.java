package com.example.siddharth.myapplication;
// REF :- https://gist.github.com/silmood/cc55f31c06da8a442771

import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import static com.android.volley.VolleyLog.TAG;

public class VolleySingleton {
    public static VolleySingleton objVolleySingletonInstance = null;
    Context objContext;
    private RequestQueue objRequestQueue;

    private VolleySingleton(Context context) {
        objContext = context;
        objRequestQueue = getRequestQueue();

    }

    public static synchronized VolleySingleton getInstance(Context context) {
        if (objVolleySingletonInstance == null) {
            objVolleySingletonInstance = new VolleySingleton(context);
        }
        return objVolleySingletonInstance;
    }

    public RequestQueue getRequestQueue() {
        if (objRequestQueue == null) {
            objRequestQueue = Volley.newRequestQueue(objContext.getApplicationContext());
        }
        return objRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request, String strTag) {
        request.setTag(TextUtils.isEmpty(strTag) ? TAG : strTag);
        getRequestQueue().add(request);
    }

    public <T> void addToRequestQueue(Request<T> request) {
        request.setTag(TAG);
        getRequestQueue().add(request);
    }

    public void cancelPendingRequests(Object tag) {
        if (objRequestQueue != null) {
            objRequestQueue.cancelAll(tag);
        }
    }
}
