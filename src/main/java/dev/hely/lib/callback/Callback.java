package dev.hely.lib.callback;

import java.io.Serializable;

/**
 * Created By LeandroSSJ
 * Created on 28/11/2021
 */

public interface Callback<T> extends Serializable {

    void callback(T t);

}
