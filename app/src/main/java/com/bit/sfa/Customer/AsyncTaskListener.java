package com.bit.sfa.Customer;

import com.bit.sfa.Settings.TaskType;

/**
 * Created by Sathiyaraja on 7/3/2018.
 */

public interface AsyncTaskListener {
    void onTaskCompleted(TaskType taskType);
}
