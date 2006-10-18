package pal.plugins.intellij.jumptousage.util;

import com.intellij.openapi.editor.ScrollType;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.TextEditor;
import pal.plugins.intellij.jumptousage.wrappers.DataHolder;
import pal.plugins.intellij.jumptousage.wrappers.Reference;

public class ReferenceNavigator {
    private Reference reference;

    public ReferenceNavigator(Reference reference) {
        this.reference = reference;
    }

    public void navigateToReference() {
        FileEditor[] fileEditors = FileEditorManager.getInstance(DataHolder.getInstance().PROJECT).openFile(reference.containingVirtualFile(), true);
        for (FileEditor fileEditor : fileEditors) {
            if (fileEditor instanceof TextEditor)
                ((TextEditor) fileEditor).navigateTo(reference.location());
            ((TextEditor) fileEditor).getEditor().getScrollingModel().scrollToCaret(ScrollType.MAKE_VISIBLE);
        }
    }
}
