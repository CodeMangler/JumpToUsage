package pal.plugins.intellij.jumptousage.wrappers;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;

public class DataHolder {
    public Project PROJECT;
    public Editor EDITOR;
    public Module MODULE;

    private DataHolder() {
    }

    private static DataHolder _instance = null;

    public static DataHolder getInstance() {
        return _instance = (_instance == null ? new DataHolder() : _instance);
    }
}
