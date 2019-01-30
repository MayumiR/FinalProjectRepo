package com.bit.sfa.DataControl;

import com.bit.sfa.Settings.TaskType;

/**
 * Created by Sathiyaraja on 6/19/2018.
 */

public interface DownloadTaskListener {
    void onTaskCompleted(TaskType taskType, String result);
}
