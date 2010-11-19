package pal.plugins.intellij.jumptousage.util;

import com.intellij.openapi.editor.ScrollType;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.TextEditor;
import com.intellij.openapi.fileEditor.NavigatableFileEditor;
import com.intellij.ide.structureView.StructureViewBuilder;
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
            if (fileEditor instanceof NavigatableFileEditor)
                ((NavigatableFileEditor) fileEditor).navigateTo(reference.location());
            scrollToCaret(fileEditor);
        }
    }

    private void scrollToCaret(FileEditor fileEditor) {
        if (fileEditor instanceof TextEditor)
            ((TextEditor) fileEditor).getEditor().getScrollingModel().scrollToCaret(ScrollType.MAKE_VISIBLE);
    }
}
