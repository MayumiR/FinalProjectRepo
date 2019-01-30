package com.bit.sfa.DataControl;

import com.bit.sfa.Settings.TaskType;

import java.util.List;

/**
 * Created by Rashmi on 8/31/2018.
 */

public interface UploadTaskListener {
    void onTaskCompleted(TaskType taskType, List<String> list);
}
